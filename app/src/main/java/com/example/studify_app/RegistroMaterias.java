package com.example.studify_app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
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

import java.util.HashMap;
import java.util.Map;

public class RegistroMaterias extends AppCompatActivity {

    String usuario, pass, nombreMat, nombreProf, horario;
    EditText edtxtNombreMateria, edtxtNombreProfesor, edtxtHorario;
    Button btnRegistroMateria, btnMenuprincipal2;
    TextView txtvResultadoregm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.registro_materias);

        Intent intent1 = getIntent();
        usuario = intent1.getStringExtra("usuario");
        pass = intent1.getStringExtra("password");

        edtxtNombreMateria = findViewById(R.id.edtxtNombreMateria);
        edtxtNombreProfesor = findViewById(R.id.edtxtPonderacion);
        edtxtHorario = findViewById(R.id.edtxtHorario);

        btnRegistroMateria = findViewById(R.id.btnGuardarCalificacion);
        btnMenuprincipal2 =findViewById(R.id.btnRMenuPrincipal4);

        txtvResultadoregm = findViewById(R.id.txtvResultadoGuardarCalificacion);


        btnMenuprincipal2.setOnClickListener(view -> {
            Intent intent = new Intent(RegistroMaterias.this, MenuPrincipal.class);
            startActivity(intent);
            finish(); // opcional: para cerrar esta pantalla
        });

        btnRegistroMateria.setOnClickListener(view -> {
            nombreMat = edtxtNombreMateria.getText().toString().trim();
            nombreProf = edtxtNombreProfesor.getText().toString().trim();
            horario = edtxtHorario.getText().toString().trim();

            if(!nombreMat.isEmpty() && !nombreProf.isEmpty() && !horario.isEmpty()){
                //CAMPOS LLENOS
                JSONObject postData = new JSONObject();
                try {
                    postData.put("usuario", usuario);
                    postData.put("password", pass);
                    postData.put("NombreMat", nombreMat);
                    postData.put("NombreProf", nombreProf);
                    postData.put("Horario", horario);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                // URL del servidor
                String url = "http://192.168.0.12:5000/RegistrarMateria";

                // Crear la solicitud
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                        Request.Method.POST,
                        url,
                        postData,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {


                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // Manejar error
                                Log.e("ERROR", error.toString());
                                if((error.toString()).equals("com.android.volley.TimeoutError")){
                                    Toast.makeText(RegistroMaterias.this, String.valueOf("El tiempo de espera para conectarse a la DB ha expirado"), Toast.LENGTH_LONG).show();
                                }else{
                                    String errorDesc = "Error desconocido; ".concat(error.toString());
                                    Toast.makeText(RegistroMaterias.this, String.valueOf(errorDesc), Toast.LENGTH_LONG).show();
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
                RequestQueue queue = Volley.newRequestQueue(RegistroMaterias.this);
                queue.add(jsonObjectRequest);

            }else{
                //CAMPOS VACIOS
            }

        });



    }
}
