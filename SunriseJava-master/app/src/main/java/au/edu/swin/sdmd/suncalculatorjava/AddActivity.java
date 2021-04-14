package au.edu.swin.sdmd.suncalculatorjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import au.edu.swin.sdmd.suncalculatorjava.calc.FileHelper;

public class AddActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private String spinnerSelect;

    TextView location;
    TextView latitude;
    TextView longtitude;
    Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        InitiliaseUI();
    }

    private void InitiliaseUI()
    {
        location = findViewById(R.id.locationField);
        latitude = findViewById(R.id.latitudeField);
        longtitude = findViewById(R.id.longtitudeField);
        addButton = findViewById(R.id.addBut);

        List<String> times = new ArrayList<String>();
        times.add("-11:00");
        times.add("-10:00");
        times.add("-9:00");
        times.add("-8:00");
        times.add("-7:00");
        times.add("-6:00");
        times.add("-5:00");
        times.add("-4:00");
        times.add("-3:00");
        times.add("-2:00");
        times.add("-1:00");
        times.add("0:00");
        times.add("+1:00");
        times.add("+2:00");
        times.add("+3:00");
        times.add("+4:00");
        times.add("+5:00");
        times.add("+6:00");
        times.add("+7:00");
        times.add("+8:00");
        times.add("+9:00");
        times.add("+10:00");
        times.add("+11:00");
        times.add("+12:00");
        times.add("+13:00");
        times.add("+14:00");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, times);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner spinner = findViewById(R.id.timeSpin);
        spinner.setOnItemSelectedListener(this);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

        spinner.setSelection(11);

        addButton.setOnClickListener(
            new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    String userInput;
                    userInput = location.getText().toString() + ",";
                    userInput += latitude.getText().toString() + ",";
                    userInput += longtitude.getText().toString() + ",";
                    userInput += "Custom,";
                    userInput += spinnerSelect + "\n";

                    FileHelper fileHelper = new FileHelper();
                    fileHelper.saveToFile(userInput);
                    finish();
                }
            }
        );
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();
        spinnerSelect = item;
    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
}
