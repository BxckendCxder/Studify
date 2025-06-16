package com.example.studify_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class RegistroActividades extends AppCompatActivity {

    Spinner SpinCategoria,SpinMateria;
    EditText edtxtDescripcion,edtxtFechaEntrega;
    Button btnRegistroActividad, btnMenuPrincipal3;
    TextView txtvResultado;

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

        txtvResultado =findViewById(R.id.txtvResultadoGuardarCalificacion);


        String[] actividadesAcademicas = {"Tarea", "Laboratorio", "Parcial", "Proyecto","Exposición"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, actividadesAcademicas);
        SpinCategoria.setAdapter(adapter);




        btnRegistroActividad.setOnClickListener(view -> {
            String DescripcionString = edtxtDescripcion.getText().toString().trim();
            String FechaEntregaString = edtxtFechaEntrega.getText().toString().trim();
            String CategoriaString = SpinCategoria.getSelectedItem().toString().trim();
            String MateriaString = SpinMateria.getSelectedItem().toString().trim();


            // Validación
            if (!DescripcionString.isEmpty() && !FechaEntregaString.isEmpty() && !CategoriaString.isEmpty() && !MateriaString.isEmpty()) {
                Toast.makeText(this, "Actividad guardada correctamente.",Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(this, "Por favor, completa todos los campos.",Toast.LENGTH_SHORT).show();

            }
        });

        btnMenuPrincipal3.setOnClickListener(view ->{
            Intent intent = new Intent(RegistroActividades.this, MenuPrincipal.class);
            startActivity(intent);
            finish(); // opcional: para cerrar esta pantalla
        });
    }

}
