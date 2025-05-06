package com.example.testeo_proyecto.entities;

import com.google.gson.annotations.SerializedName;

public class Colores
{
    public String id;
    @SerializedName("name_color")
    public String name;
    @SerializedName("color_hex")
    public String num;
    public Colores()
    {

    }

    public Colores(String nombre, String num)
    {
        this.name=nombre;
        this.num = num;
    }
}
