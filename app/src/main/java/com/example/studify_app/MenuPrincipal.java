package com.example.studify_app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
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

import java.util.HashMap;
import java.util.Map;

public class MenuPrincipal extends AppCompatActivity {
    //MENU PRINCIPAL DE LA APP
    //PRUEBA 1
    Button btnRegistroMaterias, btnRegistroActividades, btnControlCalificaciones,
            btnMaterias,btnActividades,btnCalificaciones,btnSalir;

    String usuario, pass;

    String lista = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.menu_principal);

        Intent intent1 = getIntent();
        usuario = intent1.getStringExtra("usuario");
        pass = intent1.getStringExtra("password");

        btnRegistroMaterias = findViewById(R.id.btnRegistroMaterias);
        btnRegistroActividades = findViewById(R.id.btnRegistroActividades);
        btnControlCalificaciones = findViewById(R.id.btnControlCalificaciones);
        btnMaterias = findViewById(R.id.btnMaterias);
        btnActividades = findViewById(R.id.btnActividades);
        btnCalificaciones = findViewById(R.id.btnCalificaciones);
        btnSalir = findViewById(R.id.btnSalir);


        btnRegistroMaterias.setOnClickListener(view -> {
            Intent intent = new Intent(MenuPrincipal.this, RegistroMaterias.class);
            intent.putExtra("usuario", usuario);
            intent.putExtra("password", pass);
            startActivity(intent);
        }); //ACCION CUANDO USUARIO CLIQUEE EN BTN REGISTRO MATERIAS

        btnRegistroActividades.setOnClickListener(view -> {
            Intent intent = new Intent(MenuPrincipal.this, RegistroActividades.class);
            intent.putExtra("usuario", usuario);
            intent.putExtra("password", pass);

            JSONObject postData = new JSONObject();
            try {
                postData.put("usuario", usuario);
                postData.put("password", pass);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            // URL del servidor
            String url = "http://192.168.0.12:5000/listarMaterias";

            // Crear la solicitud
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.POST,
                    url,
                    postData,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                lista = response.getString("Lista").toString();
                            }catch(Exception e){
                                Log.d("ERROR", e.toString());
                            }
                            intent.putExtra("lista", lista);
                            startActivity(intent);
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // Manejar error
                            Log.e("ERROR", error.toString());
                            if((error.toString()).equals("com.android.volley.TimeoutError")){
                                Toast.makeText(MenuPrincipal.this, String.valueOf("El tiempo de espera para conectarse a la DB ha expirado"), Toast.LENGTH_LONG).show();
                            }else{
                                String errorDesc = "Error desconocido; ".concat(error.toString());
                                Toast.makeText(MenuPrincipal.this, String.valueOf(errorDesc), Toast.LENGTH_LONG).show();
                            }
                        }
                    }
            ) {
                @Override
                public Map<String, String> getHeaders() {
                    Map<String, String> headers = new HashMap<>();
                    headers.put("Content-Type", "application/json");
                    return headers;
                }
            };
            RequestQueue queue = Volley.newRequestQueue(MenuPrincipal.this);
            queue.add(jsonObjectRequest);
        });//ACCION CUANDO USUARIO CLIQUEE BTN REGISTRO ACTIVIDADES

        btnControlCalificaciones.setOnClickListener(view -> {
            Intent intent = new Intent(this, ControlCalificaciones.class);
            intent.putExtra("usuario", usuario);
            intent.putExtra("password", pass);
            startActivity(intent);
        });

        btnMaterias.setOnClickListener(view -> {
            Intent intent = new Intent(this, VistaMaterias.class);
            intent.putExtra("usuario", usuario);
            intent.putExtra("password", pass);
            startActivity(intent);
        });

        btnActividades.setOnClickListener(view -> {
            Intent intent = new Intent(this, VistaActividades.class);
            intent.putExtra("usuario", usuario);
            intent.putExtra("password", pass);
            startActivity(intent);

        });

        btnCalificaciones.setOnClickListener(view -> {
            Intent intent = new Intent(this, VistaCalificaciones.class);
            intent.putExtra("usuario", usuario);
            intent.putExtra("password", pass);
            startActivity(intent);
        });

        btnSalir.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("usuario", usuario);
            intent.putExtra("password", pass);
            startActivity(intent);
        });





    }


}
