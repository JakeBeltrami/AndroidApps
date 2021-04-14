package au.edu.swin.sdmd.suncalculatorjava;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import au.edu.swin.sdmd.suncalculatorjava.calc.AstronomicalCalendar;
import au.edu.swin.sdmd.suncalculatorjava.calc.FileHelper;
import au.edu.swin.sdmd.suncalculatorjava.calc.GeoLocation;

public class MainActivity extends AppCompatActivity implements OnItemSelectedListener{

    // Storage Permissions
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    private Spinner spinner;
    private ArrayAdapter<String> dataAdapter;

    private String location = "Melbourne";
    private Double latitude = -37.50;
    private Double longtitude = 145.01;
    private TimeZone timezone = TimeZone.getTimeZone("GMT+11:00");

    private List<String> locations;
    private List<Double> latitudes;
    private List<Double> longtitudes;
    private List<String> timezones;

    private int year;
    private int monthOfYear;
    private int dayOfMonth;

    final static String TAG = "CatchM";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        verifyStoragePermissions(this);
        setupFile();
        //deleteFile();
        initializeUI();
        Log.d("test", "test");
    }

    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }

    private void setupFile()
    {
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/locationlist.txt");

        if (!file.exists())
        {
            String raw = "";
            String check = "";

            try {
                InputStream inputStream = getResources().openRawResource(R.raw.au_locations);
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));

                while ( (check = br.readLine()) != null )
                {
                    raw += check + "\n";
                }
                br.close();
                inputStream.close();
            }
            catch(FileNotFoundException ex) {
                Log.d(TAG, ex.getMessage());
            }
            catch(IOException ex) {
                Log.d(TAG, ex.getMessage());
            }

            try {
                if (!file.exists())
                {
                    file.createNewFile();
                }
                FileOutputStream fileOutputStream = new FileOutputStream(file,false);
                fileOutputStream.write(raw.getBytes());
                fileOutputStream.close();
            }
            catch(FileNotFoundException ex) {
                Log.d(TAG, ex.getMessage());
            }
            catch(IOException ex) {
                Log.d(TAG, ex.getMessage());
            }
        }
    }

    private void deleteFile()
    {
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/locationlist.txt");
        file.delete();
    }

    private void initializeUI() {
        DatePicker dp = findViewById(R.id.datePicker);
        Calendar cal = Calendar.getInstance();
        year = cal.get(Calendar.YEAR);
        monthOfYear = cal.get(Calendar.MONTH);
        dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
        dp.init(year,monthOfYear,dayOfMonth,dateChangeHandler); // setup initial values and reg. handler
        updateTime();

        FileHelper fileHelper = new FileHelper();
        locations = new ArrayList<String>();
        latitudes = new ArrayList<Double>();
        longtitudes = new ArrayList<Double>();
        timezones = new ArrayList<String>();

        locations = fileHelper.ReadString(MainActivity.this, 0);
        latitudes = fileHelper.ReadDouble(MainActivity.this, 1);
        longtitudes = fileHelper.ReadDouble(MainActivity.this, 2);
        timezones = fileHelper.ReadString(MainActivity.this, 4);

        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);

        // Creating adapter for spinner
        dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, locations);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

        Button addBut = findViewById(R.id.newBut);

        addBut.setOnClickListener(
            new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    Intent i = new Intent(getApplicationContext(), AddActivity.class);
                    startActivityForResult(i, 0);
                }
            }
        );
    }

    private void updateTime() {
        GeoLocation geolocation = new GeoLocation(location, latitude, longtitude, timezone);
        AstronomicalCalendar ac = new AstronomicalCalendar(geolocation);
        ac.getCalendar().set(year, monthOfYear, dayOfMonth);
        Date srise = ac.getSunrise();
        Date sset = ac.getSunset();

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

        TextView sunriseTV = findViewById(R.id.sunriseTimeTV);
        TextView sunsetTV = findViewById(R.id.sunsetTimeTV);
        Log.d("SUNRISE Unformatted", srise+"");

        sunriseTV.setText(sdf.format(srise));
        sunsetTV.setText(sdf.format(sset));
    }

    DatePicker.OnDateChangedListener dateChangeHandler = new DatePicker.OnDateChangedListener()
    {
        public void onDateChanged(DatePicker dp, int newYear, int newMonth, int newDay)
        {
            year = newYear;
            monthOfYear = newMonth;
            dayOfMonth = newDay;
            updateTime();
        }
    };

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        TextView locationTV = findViewById(R.id.locationTV);
        locationTV.setText(item);

        location = item;
        latitude = latitudes.get(locations.indexOf(item));
        longtitude = longtitudes.get(locations.indexOf(item));
        if (timezones.get(locations.indexOf(item)) != "")
        {
            Log.d("check", timezones.get(locations.indexOf(item)));
            timezone = TimeZone.getTimeZone("GMT" + timezones.get(locations.indexOf(item)));
        }
        else
        {
            timezone = TimeZone.getDefault();
        }
        updateTime();
    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        FileHelper fileHelper = new FileHelper();
        locations = fileHelper.ReadString(MainActivity.this, 0);
        latitudes = fileHelper.ReadDouble(MainActivity.this, 1);
        longtitudes = fileHelper.ReadDouble(MainActivity.this, 2);
        timezones = fileHelper.ReadString(MainActivity.this, 4);

        // Creating adapter for spinner
        dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, locations);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

        spinner.setSelection(spinner.getAdapter().getCount() - 1);
    }
}
