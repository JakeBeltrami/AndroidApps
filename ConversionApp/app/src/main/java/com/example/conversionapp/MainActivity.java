package com.example.conversionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.CheckBox;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    EditText milesInput;
    EditText feetInput;
    EditText inchesInput;

    CheckBox meterCheck;

    Button convertBut;

    TextView cmOutput;
    TextView mOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialiseUI(savedInstanceState);
    }

    private void initialiseUI(Bundle state)
    {
        milesInput = findViewById(R.id.MilesInput);
        feetInput = findViewById(R.id.FeetInput);
        inchesInput = findViewById(R.id.InchesInput);

        meterCheck = findViewById(R.id.MeterCheck);

        convertBut = findViewById(R.id.ConvertBut);

        cmOutput = findViewById(R.id.CmOutput);
        mOutput = findViewById(R.id.MOutput);

        restoreState(state);

        convertBut.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                float miles = 0;
                if (!milesInput.getText().toString().equals(""))
                {
                    miles = Float.parseFloat(milesInput.getText().toString());
                }

                float feet = 0;
                if (!feetInput.getText().toString().equals(""))
                {
                    feet = Float.parseFloat(feetInput.getText().toString());
                }

                float inches = 0;
                if (!inchesInput.getText().toString().equals(""))
                {
                    inches = Float.parseFloat(inchesInput.getText().toString());
                }

                cmOutput.setText(Float.toString((miles * 5280f * 12f + feet * 12f + inches) * 2.54f) + "cm");
                if (meterCheck.isChecked())
                {
                    mOutput.setText(Float.toString((miles * 5280f * 12f + feet * 12f + inches) * 2.54f / 100) + "m");
                }
                else
                {
                    mOutput.setText("");
                }
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle state)
    {
        state.putString("Miles", milesInput.getText().toString());
        state.putString("Feet", feetInput.getText().toString());
        state.putString("Inches", inchesInput.getText().toString());

        state.putString("Cents", cmOutput.getText().toString());
        state.putString("Meters", mOutput.getText().toString());

        state.putBoolean("Check", meterCheck.isChecked());

        super.onSaveInstanceState(state);
    }

    private void restoreState(Bundle state)
    {
        if (state == null)
        {
            return;
        }

        milesInput.setText(state.getString("Miles"));
        feetInput.setText(state.getString("Feet"));
        inchesInput.setText(state.getString("Inches"));

        cmOutput.setText(state.getString("Cents"));
        mOutput.setText(state.getString("Meters"));

        meterCheck.setChecked(state.getBoolean("Check"));
    }
}
