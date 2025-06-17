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


        btnRegistroMaterias.setOnClickListener(view -> { //ACCION CUANDO USUARIO CLIQUEE EN BTN REGISTRO MATERIAS
            Intent intent = new Intent(MenuPrincipal.this, RegistroMaterias.class);
            intent.putExtra("usuario", usuario);
            intent.putExtra("password", pass);
            startActivity(intent);
        });


        btnRegistroActividades.setOnClickListener(view -> { //ACCION CUANDO USUARIO CLIQUEE BTN REGISTRO ACTIVIDADES
            Intent intent = new Intent(MenuPrincipal.this, RegistroActividades.class);
            intent.putExtra("usuario", usuario);
            intent.putExtra("password", pass);

            Map<String, String> params = new HashMap<>();
            params.put("usuario", usuario);
            params.put("password", pass);

            ControladorVolley.postJSON("/listarMaterias", params, new ControladorVolley.VolleyCallback() {
                @Override
                public void onSuccess(JSONObject response) {
                    try {
                        lista = response.getString("Lista").toString();
                    }catch(Exception e){
                        Log.d("ERROR", e.toString());
                    }
                    intent.putExtra("lista", lista);
                    startActivity(intent);
                }
                @Override
                public void onError(JSONObject error) {
                    // Maneja el error
                    Log.e("ERROR", error.toString());
                }
            });
        });

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

            Map<String, String> params = new HashMap<>();
            params.put("usuario", usuario);
            params.put("password", pass);

            ControladorVolley.postJSON("/listarMaterias", params, new ControladorVolley.VolleyCallback() {
                @Override
                public void onSuccess(JSONObject response) {
                    try {
                        lista = response.getString("Lista").toString();
                    }catch(Exception e){
                        Log.d("ERROR", e.toString());
                    }
                    intent.putExtra("lista", lista);
                    startActivity(intent);
                }
                @Override
                public void onError(JSONObject error) {
                    // Maneja el error
                    Log.e("ERROR", error.toString());
                }
            });
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
