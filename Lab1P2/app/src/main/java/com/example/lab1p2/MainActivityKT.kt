package com.example.lab1p2

import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.util.Log

class MainActivityKT : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("Tag", MainActivity().addNumbers(927.5329, 342.175819).toString());
    }

    private fun addNumbers(n1: Double, n2: Double): Double
    {
        val result: Double = n1 + n2;
        return result;
    }
}