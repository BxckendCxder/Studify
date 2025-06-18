package com.example.studify_app;

public class Actividad {
    private String nombre;
    private String categoria;
    private String fechaEntrega;
    private String descripcion;

    public Actividad(String nombre, String categoria, String fechaEntrega, String descripcion) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.fechaEntrega = fechaEntrega;
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getFechaEntrega() {
        return fechaEntrega;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
