package com.example.studify_app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegistroActividades extends AppCompatActivity {

    Spinner SpinCategoria,SpinMateria;
    EditText edtxtDescripcion,edtxtFechaEntrega;
    Button btnRegistroActividad, btnMenuPrincipal3;
    TextView txtvResultado;

    String usuario, pass, listaMat;
;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.registro_actividades);

        Intent intent1 = getIntent();
        usuario = intent1.getStringExtra("usuario");
        pass = intent1.getStringExtra("password");
        listaMat = intent1.getStringExtra("lista");

        edtxtDescripcion = findViewById(R.id.edtxtPonderacion);
        edtxtFechaEntrega = findViewById(R.id.edtxtNota);


        btnRegistroActividad = findViewById(R.id.btnGuardarCalificacion);
        btnMenuPrincipal3 =findViewById(R.id.btnRMenuPrincipal4);

        SpinCategoria = findViewById(R.id.SpinCategoria);
        SpinMateria = findViewById(R.id.SpinMateria);

        txtvResultado =findViewById(R.id.txtvResultadoGuardarCalificacion);


        listaMat = listaMat.replaceAll("[\\[\\]']", "");

        // Dividir por comas y espacios
        String[] materias = listaMat.split(",\\s*");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, materias);
        SpinMateria.setAdapter(adapter);


        String[] actividadesAcademicas = {"Tarea", "Laboratorio", "Parcial", "Proyecto","Exposición"};
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, actividadesAcademicas);
        SpinCategoria.setAdapter(adapter);



        btnRegistroActividad.setOnClickListener(view -> {
            String DescripcionString = edtxtDescripcion.getText().toString().trim();
            String FechaEntregaString = edtxtFechaEntrega.getText().toString().trim();
            String CategoriaString = SpinCategoria.getSelectedItem().toString().trim();
            String MateriaString = SpinMateria.getSelectedItem().toString().trim();


            // Validación
            if (!DescripcionString.isEmpty() && !FechaEntregaString.isEmpty() && !CategoriaString.isEmpty() && !MateriaString.isEmpty()) {
                //CAMPOS COMPLETOS
                Toast.makeText(this, "Actividad guardada correctamente.",Toast.LENGTH_SHORT).show();



















            } else {
                //CAMPOS VACIOS
                Toast.makeText(this, "Por favor, completa todos los campos.",Toast.LENGTH_SHORT).show();

            }
        });

        btnMenuPrincipal3.setOnClickListener(view ->{
            Intent intent = new Intent(RegistroActividades.this, MenuPrincipal.class);
            intent.putExtra("usuario", usuario);
            intent.putExtra("password", pass);
            startActivity(intent);
            finish();
        });
    }

}
