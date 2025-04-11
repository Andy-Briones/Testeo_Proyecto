package com.example.testeo_proyecto.Services;

import com.example.testeo_proyecto.entities.ColorResponse;
import com.example.testeo_proyecto.entities.Colores;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ColorService {
    //https://run.mocky.io/v3/fb6b3bb3-a9fe-4040-91c8-d5711b365c51
    //https://www.csscolorsapi.com/api/colors
    @GET("/api/colors")
    Call<ColorResponse> getColors();
}
