package com.example.lab2lightbulb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private boolean ON = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageView lightBulb = (ImageView) findViewById(R.id.lightBulb);

        lightBulb.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                if (ON)
                {
                    lightBulb.setImageDrawable(getResources().getDrawable(R.drawable.light_bulb_off));
                    ON = false;
                }
                else
                {
                    lightBulb.setImageDrawable(getResources().getDrawable(R.drawable.light_bulb_on));
                    ON = true;
                }
            }
        });
    }
}
