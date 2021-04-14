package com.example.foodparcelsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageDisplayActivity extends AppCompatActivity {

    // UI variables
    ImageView food;
    TextView foodDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_display);
        initialiseUI(savedInstanceState);
    }

    // Load the UI
    private void initialiseUI(Bundle state)
    {
        // Set the UI variables
        food = findViewById(R.id.food);
        foodDesc = findViewById(R.id.foodDesc);

        // Check that we have a value for fruitId
        if(getIntent().hasExtra("fruitId"));
        {
            // Assign the imageview by using the fruitId
            food.setImageResource(getIntent().getIntExtra("fruitId", 0));
        }

        // Check that we have a value for fruitDesc
        if (getIntent().hasExtra("fruitDesc"));
        {
            // Assign the textview by using fruitDesc
            foodDesc.setText(getIntent().getStringExtra("fruitDesc"));
        }
    }
}
