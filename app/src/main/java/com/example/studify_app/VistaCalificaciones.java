package com.example.studify_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class VistaCalificaciones extends AppCompatActivity {


    TableLayout tableCalificaciones;
    Button btnRMenuPrincipal7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.vista_calificaciones);

        mostrarCalificaciones();

        tableCalificaciones = findViewById(R.id.tableCalificaciones);



        btnRMenuPrincipal7 = findViewById(R.id.btnRMenuPrincipal4);

        btnRMenuPrincipal7.setOnClickListener(view -> {
            Intent intent = new Intent(VistaCalificaciones.this, MenuPrincipal.class);
            startActivity(intent);
            finish(); // opcional: para cerrar esta pantalla
        });

    }

    private void mostrarCalificaciones() {
        TableRow header = new TableRow(this);
        header.addView(crearCelda("Actividad"));
        header.addView(crearCelda("Nota"));
        header.addView(crearCelda("Ponderación"));
        tableCalificaciones.addView(header);

        for (Calificacion cal : ControlCalificaciones.listaCalificaciones) {
            TableRow fila = new TableRow(this);
            fila.addView(crearCelda(cal.getNombre()));
            fila.addView(crearCelda(String.valueOf(cal.getNota())));
            fila.addView(crearCelda(String.valueOf(cal.getPonderacion()) + "%"));
            tableCalificaciones.addView(fila);
        }
    }



    private View crearCelda(String texto) {

        TextView tv = new TextView(this);
        tv.setText(texto);
        tv.setPadding(8, 8, 8, 8);
        return tv;
    }
}


        
