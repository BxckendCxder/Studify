package com.example.studify_app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class VistaMaterias extends AppCompatActivity {
    Spinner SpinVistaMaterias;
    TextView txtvProfesor,txtvHorario;
    Button btnRMenuPrincipal5, btnEliminarMat;

    String listaMat, usuario, pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.vista_materias);

        ControladorVolley.init(getApplicationContext());

        Intent intent1 = getIntent();
        usuario = intent1.getStringExtra("usuario");
        pass = intent1.getStringExtra("password");
        listaMat = intent1.getStringExtra("lista");

        SpinVistaMaterias = findViewById(R.id.SpinnerMaterias);
        txtvProfesor = findViewById(R.id.txtvTitutoCalificaciones);
        txtvHorario = findViewById(R.id.txtvFechaEntrega);
        btnRMenuPrincipal5 = findViewById(R.id.btnRMenuPrincipal4);
        btnEliminarMat = findViewById(R.id.btnEliminarMat);

        listaMat = intent1.getStringExtra("lista");
        listaMat = listaMat.replaceAll("[\\[\\]\"']", "");

        // Dividir por comas y espacios
        String[] materias = listaMat.split(",\\s*");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, materias);
        SpinVistaMaterias.setAdapter(adapter);

        SpinVistaMaterias.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                String matSel = SpinVistaMaterias.getSelectedItem().toString();
                //CODIGO DE VOLLEY SQL
                Map<String, String> params = new HashMap<>();
                params.put("Nombre", matSel);
                ControladorVolley.postJSON("/InfoMateria", params, new ControladorVolley.VolleyCallback() {
                    @Override
                    public void onSuccess(JSONObject response) {
                        // Maneja la respuesta exitosa
                        String respuesta;
                        try{
                            respuesta = response.getString("EstadoComms");
                            if (respuesta.equals("OK")){
                                txtvProfesor.setText("Docente: " + response.getString("Profesor"));
                                txtvHorario.setText("Horario: " + response.getString("Horario"));
                            }else{
                                Log.d("ERROR", response.toString());
                            }
                        }catch (Exception e){
                            Log.d("ERROR", response.toString());
                        }
                    }

                    @Override
                    public void onError(JSONObject error) {
                        // Maneja el error
                        Log.e("ERROR", error.toString());
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        btnRMenuPrincipal5.setOnClickListener(view -> {
            Intent intent = new Intent(VistaMaterias.this, MenuPrincipal.class);
            intent.putExtra("usuario", usuario);
            intent.putExtra("password", pass);
            startActivity(intent);
            finish();
        });

        btnEliminarMat.setOnClickListener(view ->{

            String matSel = SpinVistaMaterias.getSelectedItem().toString();
            if(!matSel.isEmpty()) {
                //CODIGO DE VOLLEY SQL
                Map<String, String> params = new HashMap<>();
                params.put("Nombre", matSel);
                ControladorVolley.postJSON("/EliminarMateria", params, new ControladorVolley.VolleyCallback() {
                    @Override
                    public void onSuccess(JSONObject response) {
                        // Maneja la respuesta exitosa
                        String respuesta;
                        try {
                            respuesta = response.getString("EstadoComms");
                            if (respuesta.equals("OK")) {
                                Toast.makeText(VistaMaterias.this, String.valueOf("Materia y actividades relacionadas fueron eliminadas"), Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(VistaMaterias.this, VistaMaterias.class);
                                intent.putExtra("usuario", usuario);
                                intent.putExtra("password", pass);
                                intent.putExtra("lista", response.getString("lista"));
                                startActivity(intent);
                                finish();
                            } else {
                                Log.d("ERROR", response.toString());
                            }
                        } catch (Exception e) {
                            Log.d("ERROR", response.toString());
                        }
                    }

                    @Override
                    public void onError(JSONObject error) {
                        // Maneja el error
                        Log.e("ERROR", error.toString());
                    }
                });
            }else{
                Toast.makeText(VistaMaterias.this, String.valueOf("No existe ninguna materia, por favor cree una"), Toast.LENGTH_LONG).show();
            }
        });

    }
}
