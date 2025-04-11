package com.example.testeo_proyecto;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testeo_proyecto.Services.ColorService;
import com.example.testeo_proyecto.adapters.BasicAdapter;
import com.example.testeo_proyecto.entities.ColorResponse;
import com.example.testeo_proyecto.entities.Colores;
import com.example.testeo_proyecto.adapters.ColorAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ColorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_color);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        RecyclerView rvColoress = findViewById(R.id.rvColores);
        rvColoress.setLayoutManager(new LinearLayoutManager(this));


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.csscolorsapi.com").addConverterFactory(GsonConverterFactory.create())
                .build();

        //https://www.csscolorsapi.com/api/colors
        ColorService service = retrofit.create(ColorService.class);

        service.getColors().enqueue(new Callback<ColorResponse>() {
            @Override
            public void onResponse(Call<ColorResponse> call, Response<ColorResponse> response) {
                if (!response.isSuccessful()) return;
                List<Colores> data = response.body().Colors;
                ColorAdapter adapter = new ColorAdapter(data);
                rvColoress.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ColorResponse> call, Throwable throwable) {

            }
        });
    }
}