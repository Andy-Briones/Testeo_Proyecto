package com.example.testeo_proyecto;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.testeo_proyecto.Services.ColorService;
import com.example.testeo_proyecto.entities.Colores;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CreateColorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create_color);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        String nombre = "blue";
        String colorHex = "#000000";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://67ff052558f18d7209efd0c8.mockapi.io")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //https://67ff052558f18d7209efd0c8.mockapi.io/Colores
        ColorService service = retrofit.create(ColorService.class);

        Colores color = new Colores();
        color.name = nombre;
        color.num = colorHex;

        service.create(color).enqueue(new Callback<Colores>() {
            @Override
            public void onResponse(Call<Colores> call, Response<Colores> response) {

            }

            @Override
            public void onFailure(Call<Colores> call, Throwable throwable) {

            }
        });
    }
}