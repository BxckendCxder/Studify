package com.example.studify_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class VistaCalificaciones extends AppCompatActivity {

    Button btnRMenuPrincipal7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.vista_calificaciones);





        btnRMenuPrincipal7 = findViewById(R.id.btnRMenuPrincipal4);

        btnRMenuPrincipal7.setOnClickListener(view -> {
            Intent intent = new Intent(VistaCalificaciones.this, MenuPrincipal.class);
            startActivity(intent);
            finish(); // opcional: para cerrar esta pantalla
        });


    }
}
