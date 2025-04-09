package com.example.testeo_proyecto;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.testeo_proyecto.adapters.ThirdActivity;

public class MainActivity extends AppCompatActivity {

    //int contador=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//define que layout se va a usar
        Intent intent = new Intent(this, ThirdActivity.class);
        startActivity(intent);
    }
}