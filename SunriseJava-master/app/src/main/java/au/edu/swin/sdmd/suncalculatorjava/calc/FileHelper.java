package au.edu.swin.sdmd.suncalculatorjava.calc;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FileHelper {
    final static String fileName = "/locationlist.txt";
    final static String path = Environment.getExternalStorageDirectory().getAbsolutePath();
    final static String TAG = "Catch";

    public static List<String> ReadString(Context context, int i){
        List<String> values = new ArrayList<String>();
        String check = null;

        try {
            File file = new File (path + fileName);
            FileInputStream fileInputStream = new FileInputStream (file);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            while ( (check = bufferedReader.readLine()) != null )
            {
                String[] splitter = check.split(",");
                if (splitter.length > i)
                {
                    values.add(splitter[i]);
                }
                else
                {
                    values.add("");
                }
            }
            fileInputStream.close();

            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            Log.d(TAG, ex.getMessage());
        }
        catch(IOException ex) {
            Log.d(TAG, ex.getMessage());
        }
        return values;
    }

    public static List<Double> ReadDouble(Context context, int i){
        List<Double> values = new ArrayList<Double>();
        String check = null;

        try {
            File file = new File (path + fileName);
            FileInputStream fileInputStream = new FileInputStream (file);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            while ( (check = bufferedReader.readLine()) != null )
            {
                String[] splitter = check.split(",");
                if (splitter[i] != null)
                {
                    values.add(Double.parseDouble(splitter[i]));
                }
                else
                {
                    values.add(null);
                }
            }
            fileInputStream.close();

            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            Log.d(TAG, ex.getMessage());
        }
        catch(IOException ex) {
            Log.d(TAG, ex.getMessage());
        }
        return values;
    }

    public static boolean saveToFile( String data){
        try {
            new File(path  ).mkdir();
            File file = new File(path+ fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file,true);
            fileOutputStream.write(data.getBytes());
            fileOutputStream.close();

            return true;
        }  catch(FileNotFoundException ex) {
            Log.d(TAG, ex.getMessage());
        }  catch(IOException ex) {
            Log.d(TAG, ex.getMessage());
        }
        return  false;
    }
}
