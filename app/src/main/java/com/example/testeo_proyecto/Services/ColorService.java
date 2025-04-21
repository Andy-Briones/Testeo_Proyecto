package com.example.testeo_proyecto.Services;

import com.example.testeo_proyecto.entities.ColorResponse;
import com.example.testeo_proyecto.entities.Colores;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ColorService {
    //https://run.mocky.io/v3/fb6b3bb3-a9fe-4040-91c8-d5711b365c51
    //https://www.csscolorsapi.com/api/colors
    //https://67ff052558f18d7209efd0c8.mockapi.io/colores


    //https://67ff052558f18d7209efd0c8.mockapi.io/Colores?limit=10&page=2
    @GET("/Colores")
    Call<List<Colores>> getColors(@Query("limit") int limit, @Query("page") int page);

    //@GET("/Colores")
    //Call<List<Colores>> getColors();

    @POST("/Colores")
    Call<Colores>create(@Body Colores colores);

    //https://67ff052558f18d7209efd0c8.mockapi.io/Colores
    @PUT("/Colores/{id}")
    Call<Colores>update(@Body Colores colores, @Path("id") int id);

    @DELETE("Colores/{id}")
    Call<Colores>delete(@Path("id") int id);
}
