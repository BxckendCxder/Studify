package com.example.studify_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class ControlCalificaciones extends AppCompatActivity {

    Spinner SpinnerMaterias;
    EditText edtxtPonderacion, edtxtNota,edtxtNombreActividad;
    Button btnGuardarCalificacion, btnRMenuPrincipal4;
    TextView txtvGuardarCalificacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.control_calificaciones);

        btnRMenuPrincipal4 = findViewById(R.id.btnRMenuPrincipal4);
        SpinnerMaterias = findViewById(R.id.SpinnerMaterias);

        edtxtPonderacion = findViewById(R.id.edtxtPonderacion);
        edtxtNombreActividad =findViewById(R.id.edtxtNombreActividad);
        edtxtNota = findViewById(R.id.edtxtNota);

        btnGuardarCalificacion = findViewById(R.id.btnGuardarCalificacion);
        txtvGuardarCalificacion = findViewById(R.id.txtvResultadoGuardarCalificacion);



        btnGuardarCalificacion.setOnClickListener(view ->{
            String Materia = SpinnerMaterias.getSelectedItem().toString().trim();
            String NombreActividad = edtxtNombreActividad.getText().toString().trim();
            String Nota = edtxtNota.getText().toString().trim();
            String Ponderacion = edtxtPonderacion.getText().toString().trim();

            // Validación
            if (Materia.isEmpty() || NombreActividad.isEmpty() || Nota.isEmpty() || Ponderacion.isEmpty()) {
                txtvGuardarCalificacion.setText("Por favor, completa todos los campos.");
            } else {
                txtvGuardarCalificacion.setText("Calificación guardada correctamente.");

                // Aquí podrías crear el objeto Calificacion
                double nota = Double.parseDouble(Nota);
                double ponderacion = Double.parseDouble(Ponderacion);
                Calificacion nueva = new Calificacion(NombreActividad, nota, ponderacion);

                // Aquí podrías guardar en una lista o enviar a otra actividad
            }
        });



                btnRMenuPrincipal4.setOnClickListener(view -> {
            Intent intent = new Intent(ControlCalificaciones.this, MenuPrincipal.class);
            startActivity(intent);
            finish();
        });


    }

}
