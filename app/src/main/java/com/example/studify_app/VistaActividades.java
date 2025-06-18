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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VistaActividades extends AppCompatActivity {
    RecyclerView recyclerView;
    Button btnRMenuPrincipal6;
    Spinner SpinVistaMaterias;
    String usuario, pass, listaMat;
    AdaptadorActividades adaptador;
    List<Actividad> listaFiltrada = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.vista_actividades);

        ControladorVolley.init(getApplicationContext());

        Intent intent1 = getIntent();
        usuario = intent1.getStringExtra("usuario");
        pass = intent1.getStringExtra("password");
        listaMat = intent1.getStringExtra("lista");

        SpinVistaMaterias = findViewById(R.id.SpinnerMaterias);
        btnRMenuPrincipal6 = findViewById(R.id.btnRMenuPrincipal4);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Obtener lista de materias
        listaMat = intent1.getStringExtra("lista");
        listaMat = listaMat.replaceAll("[\\[\\]\"']", "");
        // Dividir por comas y espacios
        String[] materias = listaMat.split(",\\s*");
        //Configuramos adaptador para SPINNER MATERIAS
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, materias);
        SpinVistaMaterias.setAdapter(adapter);

        //PROCESO PARA RECYCLER
        adaptador = new AdaptadorActividades(this, listaFiltrada, new AdaptadorActividades.OnEliminarClickListener() {
            @Override
            public void onEliminarClick(Actividad actividad, int posicion) {
                // Aquí haces la petición Volley para eliminar la actividad de la DB
                Map<String, String> params = new HashMap<>();
                params.put("NombreMat", actividad.getNombre());
                params.put("Categoria", actividad.getCategoria());
                params.put("Descripcion", actividad.getDescripcion());
                Log.e("ERROR", params.toString());

                ControladorVolley.postJSON("/EliminarActividad", params, new ControladorVolley.VolleyCallback() {
                    @Override
                    public void onSuccess(JSONObject response) {
                        listaFiltrada.remove(posicion);
                        adaptador.notifyItemRemoved(posicion);
                    }

                    @Override
                    public void onError(JSONObject error) {
                        Toast.makeText(VistaActividades.this, "Error al eliminar", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        recyclerView.setAdapter(adaptador);

        SpinVistaMaterias.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String materiaSeleccionada = SpinVistaMaterias.getItemAtPosition(position).toString();

                //CODIGO DE VOLLEY SQL
                Map<String, String> params = new HashMap<>();
                params.put("Usuario", usuario);
                params.put("Nombre", pass);
                params.put("NombreMat", materiaSeleccionada);
                ControladorVolley.postJSON("/ListarActividades", params, new ControladorVolley.VolleyCallback() {
                    @Override
                    public void onSuccess(JSONObject response) {
                        try {
                            JSONArray actividadesArray = response.getJSONArray("actividades");
                            listaFiltrada.clear();
                            for (int i = 0; i < actividadesArray.length(); i++) {
                                JSONObject obj = actividadesArray.getJSONObject(i);
                                Actividad m = new Actividad(
                                        obj.getString("NombreMat"),
                                        obj.getString("Categoria"),
                                        obj.getString("FechaEntrega"),
                                        obj.getString("Descripcion")
                                );
                                listaFiltrada.add(m);
                            }
                            adaptador.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
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
                // No hacer nada
            }
        });







        btnRMenuPrincipal6.setOnClickListener(view -> {
            Intent intent = new Intent(VistaActividades.this, MenuPrincipal.class);
            intent.putExtra("usuario", usuario);
            intent.putExtra("password", pass);
            startActivity(intent);
            finish();
        });
    }
}
