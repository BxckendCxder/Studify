package com.example.studify_app;

import androidx.appcompat.app.AppCompatActivity;

public class Calificacion  {
    private String nombre;
    private double nota;
    private double ponderacion;

    public Calificacion(String nombre, double nota, double ponderacion) {
        this.nombre = nombre;
        this.nota = nota;
        this.ponderacion = ponderacion;
    }

    public String getNombre() {
        return nombre;
    }

    public double getNota() {
        return nota;
    }

    public double getPonderacion() {
        return ponderacion;
    }
}
