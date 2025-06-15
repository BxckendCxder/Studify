package com.example.studify_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class ControlCalificaciones extends AppCompatActivity {

    Spinner spinMateria;
    EditText edtxtPonderacion, edtxtNota;
    Button btnGuardarCalificacion, btnRMenuPrincipal4;
    TextView txtvGuardarCalificacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.control_calificaciones);

        btnRMenuPrincipal4 = findViewById(R.id.btnRMenuPrincipal4);
        spinMateria = findViewById(R.id.SpinVistaCategorias);
        edtxtPonderacion = findViewById(R.id.edtxtPonderacion);
        edtxtNota = findViewById(R.id.edtxtNota);

        btnGuardarCalificacion = findViewById(R.id.btnGuardarCalificacion);
        txtvGuardarCalificacion = findViewById(R.id.txtvResultadoGuardarCalificacion);


        btnRMenuPrincipal4.setOnClickListener(view -> {
            Intent intent = new Intent(ControlCalificaciones.this, MenuPrincipal.class);
            startActivity(intent);
            finish();
        });


    }

}
