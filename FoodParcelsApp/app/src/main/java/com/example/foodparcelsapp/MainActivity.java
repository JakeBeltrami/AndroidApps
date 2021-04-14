package com.example.foodparcelsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import android.view.View;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {

    // UI variables
    ImageView food1;
    ImageView food2;
    ImageView food3;
    ImageView food4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialiseUI(savedInstanceState);
    }

    // Load the UI
    private void initialiseUI(Bundle state) {
        // Connect the variables to the UI counterparts
        food1 = findViewById(R.id.food1);
        food2 = findViewById(R.id.food2);
        food3 = findViewById(R.id.food3);
        food4 = findViewById(R.id.food4);

        // Set a click listener for each imageview element
        food1.setOnClickListener(
                new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view) {
                        // Create a new intent from the current activity to imageDisplay
                        Intent i = new Intent(getApplicationContext(), ImageDisplayActivity.class);
                        // Get the image ID to display in the next activity
                        int fruitId = R.drawable.applesmall;
                        // Add the image ID and description to the intent
                        i.putExtra("fruitId", fruitId);
                        i.putExtra("fruitDesc", getString(R.string.appleDesc));
                        // Start the imageDisplay activity
                        startActivity(i);
                    }
                }
        );

        // Rinse repeat for the remaining images

        food2.setOnClickListener(
                new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(getApplicationContext(), ImageDisplayActivity.class);
                        int fruitId = R.drawable.bananasmall;
                        i.putExtra("fruitId", fruitId);
                        i.putExtra("fruitDesc", getString(R.string.bananaDesc));
                        startActivity(i);
                    }
                }
        );

        food3.setOnClickListener(
                new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(getApplicationContext(), ImageDisplayActivity.class);
                        int fruitId = R.drawable.orangesmall;
                        i.putExtra("fruitId", fruitId);
                        i.putExtra("fruitDesc", getString(R.string.orangeDesc));
                        startActivity(i);
                    }
                }
        );

        food4.setOnClickListener(
                new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(getApplicationContext(), ImageDisplayActivity.class);
                        int fruitId = R.drawable.pearsmall;
                        i.putExtra("fruitId", fruitId);
                        i.putExtra("fruitDesc", getString(R.string.pearDesc));
                        startActivity(i);
                    }
                }
        );
    }
}
