package com.example.asynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private Button timer;
    private Button aSyncTimer;
    private TextView timerText;
    private TextView aSyncText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialiseUI();
    }

    private void initialiseUI()
    {
        timer = findViewById(R.id.timerBut);
        aSyncTimer = findViewById(R.id.AsyncBut);
        timerText = findViewById(R.id.timerText);
        aSyncText = findViewById(R.id.AsyncText);

        timer.setOnClickListener(
            new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    try
                    {
                        for (int i = 3; i >= 0; i--)
                        {
                            Thread.sleep(1000);
                            timerText.setText(Integer.toString(i));
                        }
                    } catch (InterruptedException ie)
                    {
                        ie.printStackTrace();
                    }
                }
            }
        );

        aSyncTimer.setOnClickListener(
                new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        ASyncTimer aSync = new ASyncTimer();
                        aSync.execute(3);
                    }
                }
        );
    }

    private class ASyncTimer extends AsyncTask<Integer, String, Void> {

        @Override
        protected Void doInBackground(Integer... ints)
        {
            try
            {
                for (int i = ints[0]; i >= 0; i--)
                {
                    Thread.sleep(1000);
                    publishProgress(Integer.toString(i));
                }
            } catch (InterruptedException ie)
            {
                ie.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(String... update) {
            aSyncText.setText(update[0]);
        }
    }
}
