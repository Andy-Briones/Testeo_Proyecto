package com.example.testeo_proyecto;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testeo_proyecto.adapters.BasicAdapter;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {

//    String N;
//    Integer Num;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

//        Contacto contact = new Contacto(N, Num);
//        Contacto contact1 = new Contacto(N, Num);
//        Contacto contact2 = new Contacto(N, Num);
//        Contacto contact3 = new Contacto(N, Num);
//        contact.nombre = "Jose Luis";
//        contact.num = 123456789;
//        contact1.nombre = "Jose Manuel";
//        contact1.num = 789456123;
//        contact2.nombre = "Jose Reyes";
//        contact2.num = 987123456;
//        contact3.nombre = "Jose Bazan";
//        contact3.num = 321456987;

        List<String> data = new ArrayList<>();
//        data.add(contact.nombre + "\\\n"+contact.num);
//        data.add(contact1.nombre + "\\\n"+contact1.num);
//        data.add(contact2.nombre + "\\\n"+contact2.num);
//        data.add(contact3.nombre + "\\\n"+contact3.num);

        BasicAdapter basicAdapter = new BasicAdapter(data);
        RecyclerView rvBasic = findViewById(R.id.rvBasic);
        rvBasic.setLayoutManager(new LinearLayoutManager(this));
        rvBasic.setAdapter(basicAdapter);
    }
//    public class Contacto
//    {
//        String nombre;
//        Integer num;
//
//        public Contacto(String nombre, Integer num)
//        {
//            this.nombre=nombre;
//            this.num =num;
//        }
//    }

}