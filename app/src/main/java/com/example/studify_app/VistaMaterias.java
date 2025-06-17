package com.example.studify_app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;

public class VistaMaterias extends AppCompatActivity {
    Spinner SpinVistaMaterias;
    TextView txtvProfesor,txtvHorario;
    Button btnRMenuPrincipal5;

    String listaMat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.vista_materias);

        Intent intent1 = getIntent();
        listaMat = intent1.getStringExtra("lista");

        listaMat = listaMat.replaceAll("[\\[\\]\"']", "");

        // Dividir por comas y espacios
        String[] materias = listaMat.split(",\\s*"); //LISTA DE MATERIAS, AGREGA EL ADAPTADOR


       // Log.e("ERROR", Arrays.toString(materias)); //QUITAR

        SpinVistaMaterias = findViewById(R.id.SpinnerMaterias);
        txtvProfesor = findViewById(R.id.txtvTitutoCalificaciones);
        txtvHorario = findViewById(R.id.txtvFechaEntrega);



        btnRMenuPrincipal5 = findViewById(R.id.btnRMenuPrincipal4);

        btnRMenuPrincipal5.setOnClickListener(view -> {
            Intent intent = new Intent(VistaMaterias.this, MenuPrincipal.class);
            startActivity(intent);
            finish(); // opcional: para cerrar esta pantalla
        });


    }
}
