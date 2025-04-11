package com.example.testeo_proyecto;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    //int contador=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//define que layout se va a usar
        Intent intent = new Intent(this, ColorActivity.class);
        startActivity(intent);
    }
}