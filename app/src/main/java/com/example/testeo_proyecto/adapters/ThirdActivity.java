package com.example.testeo_proyecto.adapters;


import android.content.Intent;
import android.os.Bundle;
import android.provider.Telephony;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testeo_proyecto.R;

import java.util.ArrayList;
import java.util.List;

public class ThirdActivity extends AppCompatActivity {

    String C, Co;
    Button n;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_third);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });

        //Button btn1 = new Button(R.id.btnColor);
        // (R.id.btnColor));

        Colores col = new Colores("Blue", "3300CC");


        List<String> data2 = new ArrayList<>();
        data2.add(col.nombreColor +col.Codigo);

        BasicAdapter basicAdapter = new BasicAdapter(data2);
        RecyclerView rvBasic2 = findViewById(R.id.rvBasic2);
        rvBasic2.setLayoutManager(new LinearLayoutManager(this));
        rvBasic2.setAdapter(basicAdapter);

    }

    public class Colores
    {
        String nombreColor;
        String Codigo;
        public Colores (String nombreColor, String codigo)
        {
            this.nombreColor=nombreColor;
            this.Codigo=codigo;
        }
    }
    public Colores[] colors = new Colores[]{
            new Colores("Rojo", "#FF0000"),
            new Colores("Verde", "#00FF00"),
            new Colores("Azul", "#0000FF"),
            new Colores("Amarillo", "#FFFF00"),
            new Colores("Naranja", "#FFA500"),
            new Colores("Morado", "#800080")
    };
}