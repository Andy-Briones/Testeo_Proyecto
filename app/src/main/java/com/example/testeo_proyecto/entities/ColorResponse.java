package com.example.testeo_proyecto.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ColorResponse {
    //https://www.csscolorsapi.com/api/colors
    public int status;
    public String message;

    @SerializedName("colors")
    public List<Colores>Colors;

}
