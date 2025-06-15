package com.example.studify_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class VistaActividades extends AppCompatActivity {

    Spinner SpinVistaCategorias;
    TextView txtvNombreActividad,txtvFechaEntrega,txtvMateria;
    Button btnRMenuPrincipal6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.vista_actividades);

        SpinVistaCategorias = findViewById(R.id.SpinnerMaterias);
        txtvNombreActividad = findViewById(R.id.txtvTitutoCalificaciones);
        txtvFechaEntrega = findViewById(R.id.txtvFechaEntrega);
        txtvMateria = findViewById(R.id.txtvMateria);



        btnRMenuPrincipal6 = findViewById(R.id.btnRMenuPrincipal4);

        btnRMenuPrincipal6.setOnClickListener(view -> {
            Intent intent = new Intent(VistaActividades.this, MenuPrincipal.class);
            startActivity(intent);
            finish(); // opcional: para cerrar esta pantalla
        });


    }
}
