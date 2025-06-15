package com.example.studify_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class RegistroActividades extends AppCompatActivity {

    Spinner SpinCategoria,SpinMateria;
    EditText edtxtDescripcion,edtxtFechaEntrega;
    Button btnRegistroActividad, btnMenuPrincipal3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.registro_actividades);

        edtxtDescripcion = findViewById(R.id.edtxtPonderacion);
        edtxtFechaEntrega = findViewById(R.id.edtxtNota);


        btnRegistroActividad = findViewById(R.id.btnGuardarCalificacion);
        btnMenuPrincipal3 =findViewById(R.id.btnRMenuPrincipal4);

        SpinCategoria = findViewById(R.id.SpinCategoria);
        SpinMateria = findViewById(R.id.SpinMateria);

        btnMenuPrincipal3.setOnClickListener(view -> {
            Intent intent = new Intent(RegistroActividades.this, MenuPrincipal.class);
            startActivity(intent);
            finish(); // opcional: para cerrar esta pantalla
        });



    }

}
