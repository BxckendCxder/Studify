package com.example.studify_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MenuPrincipal extends AppCompatActivity {
    //MENU PRINCIPAL DE LA APP
    Button btnRegistroMaterias, btnRegistroActividades, btnControlCalificaciones,
            btnMaterias,btnActividades,btnCalificaciones,btnSalir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.menu_principal);

        btnRegistroMaterias = findViewById(R.id.btnRegistroMaterias);
        btnRegistroActividades = findViewById(R.id.btnRegistroActividades);
        btnControlCalificaciones = findViewById(R.id.btnControlCalificaciones);
        btnMaterias = findViewById(R.id.btnMaterias);
        btnActividades = findViewById(R.id.btnActividades);
        btnCalificaciones = findViewById(R.id.btnCalificaciones);
        btnSalir = findViewById(R.id.btnSalir);


        btnRegistroMaterias.setOnClickListener(view -> {
            startActivity(new Intent(this, RegistroMaterias.class));
        });

        btnRegistroActividades.setOnClickListener(view -> {
            startActivity(new Intent(this, RegistroActividades.class));
        });

        btnControlCalificaciones.setOnClickListener(view -> {
            startActivity(new Intent(this, ControlCalificaciones.class));
        });

        btnMaterias.setOnClickListener(view -> {
            startActivity(new Intent(this, VistaMaterias.class));
        });

        btnActividades.setOnClickListener(view -> {
            startActivity(new Intent(this, VistaActividades.class));
        });

        btnCalificaciones.setOnClickListener(view -> {
            startActivity(new Intent(this, VistaCalificaciones.class));
        });

        btnSalir.setOnClickListener(view -> {
            startActivity(new Intent(this, MainActivity.class));
        });





    }


}
