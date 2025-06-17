package com.example.studify_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ControlCalificaciones extends AppCompatActivity {

    public static List<Calificacion> listaCalificaciones = new ArrayList<>();


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

            String nombre = SpinnerMaterias.getSelectedItem().toString();
            double nota = Double.parseDouble(edtxtNota.getText().toString());
            double ponderacion = Double.parseDouble(edtxtPonderacion.getText().toString());

            listaCalificaciones.add(new Calificacion(nombre, nota, ponderacion));
            Toast.makeText(this, "Calificación guardada", Toast.LENGTH_SHORT).show();

            String Materia = SpinnerMaterias.getSelectedItem().toString().trim();
            String NombreActividad = edtxtNombreActividad.getText().toString().trim();
            String Nota = edtxtNota.getText().toString().trim();
            String Ponderacion = edtxtPonderacion.getText().toString().trim();

            /*
            if (!Materia.isEmpty() && !NombreActividad.isEmpty() && !Nota.isEmpty() && !Ponderacion.isEmpty()) {
                Toast.makeText(this, "Actividad guardada correctamente.",Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Por favor, completa todos los campos.",Toast.LENGTH_SHORT).show();

                // Aquí podrías crear el objeto Calificacion
                double nota = Double.parseDouble(Nota);
                double ponderacion = Double.parseDouble(Ponderacion);
                Calificacion nueva = new Calificacion(NombreActividad, nota, ponderacion);

                // Aquí podrías guardar en una lista o enviar a otra actividad
            }*/
        });



                btnRMenuPrincipal4.setOnClickListener(view -> {
            Intent intent = new Intent(ControlCalificaciones.this, MenuPrincipal.class);
            startActivity(intent);
            finish();
        });


    }

}
