package com.example.testeo_proyecto;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

public class FormColorActivity extends AppCompatActivity {

    ColorService service;
    Button btnSave;
    Button btnDelete;
    EditText edtNombreColor;
    EditText edtHexColor;
    int colorId=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_form_color);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://67ff052558f18d7209efd0c8.mockapi.io").addConverterFactory(GsonConverterFactory.create())
                .build();
        //https://67ff052558f18d7209efd0c8.mockapi.io/Colores

        service = retrofit.create(ColorService.class);

        setUpViews();
        setUpButtonSave();
        setUpButtonDelete();

        //los getIntent() permiten obtener la informacion que se envio desde otra actividad anterior
        Intent intent = getIntent();
        colorId = intent.getIntExtra("colorId",0);
        String  colorName = intent.getStringExtra("colorName");
        String  colorHex = intent.getStringExtra("colorHex");

        if (colorName != null && colorHex != null)
        {
            edtNombreColor.setText(colorName);
            edtHexColor.setText(colorHex);
        }
        if (colorId == 0)
        {
            btnDelete.setVisibility(View.GONE);
        }
    }
    private void setUpViews()
    {
        btnSave = findViewById(R.id.btnsave);
        btnDelete = findViewById(R.id.btndelete);
        edtNombreColor=findViewById(R.id.etColorName);
        edtHexColor=findViewById(R.id.etColorHex);
    }
    private void setUpButtonSave()
    {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //finish(); //termina la actividad actual
                if (colorId == 0)
                {
                    save();
                }
                else
                {
                    update();
                }
            }
        });
    }

    private void setUpButtonDelete()
    {
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (colorId != 0)
                {
                    delete();
                }
            }
        });
    }
    private void save()
    {
        Colores color = new Colores();
        color.name = edtNombreColor.getText().toString();
        color.num = edtHexColor.getText().toString();

        service.create(color).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<Colores> call, Response<Colores> response) {
                if (response.isSuccessful()) {
                    finish(); //termina la actividad actual (hacer atras en la pantalla)
                    Toast.makeText(getApplicationContext(), "Color creado", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Error al crear color", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Colores> call, Throwable throwable) {
                Toast.makeText(getApplicationContext(), "Error de red", Toast.LENGTH_LONG).show();
            }
        });
    }
    private void update()
    {
        Colores color = new Colores();
        color.name = edtNombreColor.getText().toString();
        color.num = edtHexColor.getText().toString();

        service.update(color,colorId).enqueue(new Callback<Colores>() {
            @Override
            public void onResponse(Call<Colores> call, Response<Colores> response) {
                if (response.isSuccessful()) {
                    finish();
                    Toast.makeText(getApplicationContext(), "Color actualizado", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Error al actualizar color", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Colores> call, Throwable throwable) {

            }
        });
    }
    private void delete()
    {
        service.delete(colorId).enqueue(new Callback<Colores>() {
            @Override
            public void onResponse(Call<Colores> call, Response<Colores> response) {
                if (response.isSuccessful()) {
                    finish();
                    Toast.makeText(getApplicationContext(), "Color eliminado", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Error al eliminar color", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Colores> call, Throwable throwable) {

            }
        });
    }
}