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
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText edtxtNombre,edtxtContrasena;
    Button btnlogin;
    TextView txtvSignin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        edtxtNombre = findViewById(R.id.edtxtNombre);
        edtxtContrasena = findViewById(R.id.edtxtContrasena);
        btnlogin = findViewById(R.id.btnlogin);
        txtvSignin = findViewById(R.id.txtvSignin);

        txtvSignin.setOnClickListener(view ->{ Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(intent);

        });

        btnlogin.setOnClickListener(view ->{
            String usuario = edtxtNombre.getText().toString().trim();
            String pass = edtxtContrasena.getText().toString().trim();

            if (!usuario.isEmpty()&&!pass.isEmpty()){
                JSONObject postData = new JSONObject();
                try {
                    postData.put("usuario", usuario);
                    postData.put("password", pass);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                // URL del servidor
                String url = "http://192.168.0.12:5000/buscarUsuario";
                /*
                // Crear la solicitud
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                        Request.Method.POST,
                        url,
                        postData,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    String respuesta = response.get("EstadoComms").toString();
                                    txtSalida.setText(respuesta);
                                    Log.d("RESPONSE", respuesta);
                                }catch(Exception e){
                                    Log.d("ERROR", e.toString());
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // Manejar error
                                Log.e("ERROR", error.toString());
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
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                queue.add(jsonObjectRequest);
                */


                Intent intent = new Intent(MainActivity.this, MenuPrincipal.class);
                startActivity(intent);
                finish(); // opcional: para cerrar esta pantalla
            }else {
                Toast.makeText(this, "Rellena todos los campos",Toast.LENGTH_SHORT).show();
            }

        });
    }
}