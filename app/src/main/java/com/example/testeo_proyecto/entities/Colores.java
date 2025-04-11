package com.example.testeo_proyecto.entities;

import com.google.gson.annotations.SerializedName;

public class Colores
{
    @SerializedName("name")
    public String nombre;
    @SerializedName("hex")
    public String num;

    public Colores(String nombre, String num)
    {
        this.nombre=nombre;
        this.num =num;
    }
}
