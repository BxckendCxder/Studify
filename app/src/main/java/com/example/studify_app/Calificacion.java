package com.example.studify_app;

import androidx.appcompat.app.AppCompatActivity;

public class Calificacion  {
    private String nombreEvaluacion;
    private double nota;
    private double ponderacion;

    public Calificacion(String nombreEvaluacion, double nota, double ponderacion) {
        this.nombreEvaluacion = nombreEvaluacion;
        this.nota = nota;
        this.ponderacion = ponderacion;
    }

    public String getNombre() {
        return nombreEvaluacion;
    }

    public double getNota() {
        return nota;
    }

    public double getPonderacion() {
        return ponderacion;
    }
}
