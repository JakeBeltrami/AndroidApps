package com.example.foodparcelsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {

    // UI variables
    private ImageView food1;
    private ImageView food2;
    private ImageView food3;
    private ImageView food4;
    private TextView foodText1;
    private TextView foodText2;
    private TextView foodText3;
    private TextView foodText4;

    // Parcelables
    private FoodParcel apple;
    private FoodParcel banana;
    private FoodParcel orange;
    private FoodParcel pear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialiseParcelables();
        initialiseUI(savedInstanceState);
    }

    private void initialiseParcelables()
    {
        // Create parcelables
        apple = new FoodParcel(R.drawable.applesmall, "Apple", "https://www.amazon.com/produce-aisle-mburring-Honeycrisp-Medium/dp/B000RGZMTQ", "food, fruit, red", "20/06/2017", "someone@gmail.com", 2, false);
        banana = new FoodParcel(R.drawable.bananasmall, "Banana", "https://www.walmart.com/ip/Bananas-each/44390948", "food, fruit, yellow", "20/04/2015", "someone@gmail.com", 4, false);
        orange = new FoodParcel(R.drawable.orangesmall, "Orange", "https://www.instacart.com/harris-teeter/products/58635-navel-orange-each", "food, fruit, orange", "15/02/2012", "someone@gmail.com", 3, false);
        pear = new FoodParcel(R.drawable.pearsmall, "Pear", "https://www.indiamart.com/proddetail/pear-fruit-15198233588.html", "food, fruit, green", "27/11/2018", "someone@gmail.com", 4, false);
    }

    // Load the UI
    private void initialiseUI(Bundle state) {
        // Connect the variables to the UI counterparts
        food1 = findViewById(R.id.food1);
        food2 = findViewById(R.id.food2);
        food3 = findViewById(R.id.food3);
        food4 = findViewById(R.id.food4);
        foodText1 = findViewById(R.id.food1text);
        foodText2 = findViewById(R.id.food2text);
        foodText3 = findViewById(R.id.food3text);
        foodText4 = findViewById(R.id.food4text);

        foodText1.setText(apple.getName() + ": " + apple.getDateObtained());
        foodText2.setText(banana.getName() + ": " + banana.getDateObtained());
        foodText3.setText(orange.getName() + ": " + orange.getDateObtained());
        foodText4.setText(pear.getName() + ": " + pear.getDateObtained());

        // Set a click listener for each imageview element
        food1.setOnClickListener(
                new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view) {
                        LaunchImageDisplayActivity(apple, 1);
                    }
                }
        );

        // Rinse repeat for the remaining images

        food2.setOnClickListener(
                new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view) {
                        LaunchImageDisplayActivity(banana, 2);
                    }
                }
        );

        food3.setOnClickListener(
                new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view) {
                        LaunchImageDisplayActivity(orange, 3);
                    }
                }
        );

        food4.setOnClickListener(
                new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view) {
                        LaunchImageDisplayActivity(pear, 4);
                    }
                }
        );
    }

    private void LaunchImageDisplayActivity(FoodParcel food, int request)
    {
        Intent i = new Intent(getApplicationContext(), ImageDisplayActivity.class);
        i.putExtra("foodParcel", food);
        startActivityForResult(i, request);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data != null) {
            if (requestCode == 1) {
                apple = data.getParcelableExtra("returnParcel");
            } else if (requestCode == 2) {
                banana = data.getParcelableExtra("returnParcel");
            } else if (requestCode == 3) {
                orange = data.getParcelableExtra("returnParcel");
            } else if (requestCode == 4) {
                pear = data.getParcelableExtra("returnParcel");
            }

            foodText1.setText(apple.getName() + " " + apple.getDateObtained());
            foodText2.setText(banana.getName() + " " + banana.getDateObtained());
            foodText3.setText(orange.getName() + " " + orange.getDateObtained());
            foodText4.setText(pear.getName() + " " + pear.getDateObtained());
        }
    }
}
