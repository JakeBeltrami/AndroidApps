package com.example.lab2additionapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int num3 = 0;

                TextView result = findViewById(R.id.textView);
                EditText num1 = findViewById(R.id.editTextN1);
                EditText num2 = findViewById(R.id.editTextN2);

                if (isNumeric(num1.getText().toString()) && isNumeric(num2.getText().toString()))
                {
                    num3 = Integer.parseInt(num1.getText().toString()) + Integer.parseInt(num2.getText().toString());
                }

                result.setText(Integer.toString(num3));
            }
        });
    }

    public static boolean isNumeric(String strNum) {
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }
}
