package com.example.foodparcelsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.util.regex.Pattern;

public class ImageDisplayActivity extends AppCompatActivity {

    // UI variables
    ImageView foodImage;
    TextView imageName;
    TextView imageURL;
    TextView imageKeywords;
    TextView imageDate;
    TextView imageOwner;
    TextView imageRating;
    CheckBox shareCheck;
    Button saveButton;

    // Parcel
    private FoodParcel food;

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
        foodImage = findViewById(R.id.food);
        imageName = findViewById(R.id.imageName);
        imageURL = findViewById(R.id.imageURL);
        imageKeywords = findViewById(R.id.imageKeywords);
        imageDate = findViewById(R.id.imageDate);
        imageOwner = findViewById(R.id.imageOwner);
        imageRating = findViewById(R.id.imageRating);
        shareCheck = findViewById(R.id.shareCheck);
        saveButton = findViewById(R.id.saveButton);

        // Check that we have a value for fruitId
        if(getIntent().hasExtra("foodParcel"));
        {
            food = getIntent().getParcelableExtra("foodParcel");
            foodImage.setImageResource(food.getImageID());
            imageName.setText(food.getName());
            imageURL.setText(food.getUrl());
            imageKeywords.setText(food.getKeywords());
            imageDate.setText(food.getDateObtained());
            imageOwner.setText(food.getOwner());
            imageRating.setText(Integer.toString(food.getRating()));
            shareCheck.setChecked(food.isShare());
        }

        saveButton.setOnClickListener(
                new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view) {
                        if (patternCheck()) {
                            if (getIntent().hasExtra("foodParcel")) ;
                            {
                                food.setName(imageName.getText().toString());
                                food.setUrl(imageURL.getText().toString());
                                food.setKeywords(imageKeywords.getText().toString());
                                food.setDateObtained(imageDate.getText().toString());
                                food.setOwner(imageOwner.getText().toString());
                                food.setRating(Integer.parseInt(imageRating.getText().toString()));
                                food.setShare(shareCheck.isChecked());
                            }

                            Intent iResult = new Intent();
                            iResult.putExtra("returnParcel", food);
                            setResult(1, iResult);
                            finish();
                        }
                        else
                        {
                            Snackbar mySnackbar = Snackbar.make(view, "Please enter fields correctly.", 2);
                        }
                    }
                }
        );
    }

    private boolean patternCheck()
    {
        if (TextUtils.isEmpty(imageName.getText().toString()))
        {
            return false;
        }

        if (TextUtils.isEmpty(imageOwner.getText().toString()))
        {
            return false;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(imageOwner.getText().toString()).matches())
        {
            return false;
        }

        if (!Pattern.matches("[0-5]{1}", imageRating.getText().toString()))
        {
            return false;
        }

        return true;
    }
}
