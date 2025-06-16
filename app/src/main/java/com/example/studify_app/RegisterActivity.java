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

public class RegisterActivity extends AppCompatActivity {

    EditText edtxtNombreUsuario, edtxtFechaNacimiento, edtxtTelefono, edtxtDUI,edtxtContrasena2, edtxtContrasenaConfirm;
    TextView txtvEstado;
    Button btnRegistrar,btnRegSalir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

      edtxtNombreUsuario = findViewById(R.id.edtxtNombreUsuario);
      edtxtFechaNacimiento = findViewById(R.id.edtxtFechanacimiento);
      edtxtTelefono =findViewById(R.id.edtxtTelefono);
      edtxtDUI = findViewById(R.id.edtxtDUI);
      edtxtContrasena2 = findViewById(R.id.edtxContrasena2);
      edtxtContrasenaConfirm = findViewById(R.id.edtxtcontrasenaConfirm);

        txtvEstado = findViewById(R.id.txtvEstado);

      btnRegistrar = findViewById(R.id.btnRegistrar);
      btnRegSalir =findViewById(R.id.btnRegSalir);


        btnRegistrar.setOnClickListener(view -> {
            String nombre, fechaDNacimiento, telefono, dui, pass1, pass2;

            txtvEstado.setText("");

            nombre = edtxtNombreUsuario.getText().toString().trim();
            fechaDNacimiento = edtxtFechaNacimiento.getText().toString().trim();
            telefono = edtxtTelefono.getText().toString().trim();
            dui = edtxtDUI.getText().toString().trim();
            pass1 = edtxtContrasena2.getText().toString().trim();
            pass2 = edtxtContrasenaConfirm.getText().toString().trim();

            if(!nombre.isEmpty() && !fechaDNacimiento.isEmpty() && !telefono.isEmpty() &&
                    !dui.isEmpty() && !pass1.isEmpty() && !pass2.isEmpty()){
                //CAMPOS NO VACIOS
                if(pass1.equals(pass2)) {
                    //SI LAS PASS COINCIDEN
                    JSONObject postData = new JSONObject();
                    try {
                        postData.put("Nombre", nombre);
                        postData.put("FechaDNacimiento", fechaDNacimiento);
                        postData.put("telefono", telefono);
                        postData.put("DUI", dui);
                        postData.put("Password", pass1);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    // URL del servidor
                    String url = "http://192.168.0.12:5000/buscarUserPorDUI";

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
                                        if (respuesta.equals("UsuarioExiste")) {
                                            txtvEstado.setText("Este usuario ya existe, por favor contacte al administrador");
                                        } else {
                                            String usuarioGenerado = response.get("NombreUsuario").toString();
                                            txtvEstado.setText("Registro Exitoso. \nSu usuario es: " + usuarioGenerado + "\nLa contraseña es la que usted ingresó");
                                            edtxtNombreUsuario.setText("");
                                            edtxtFechaNacimiento.setText("");
                                            edtxtTelefono.setText("");
                                            edtxtDUI.setText("");
                                            edtxtContrasena2.setText("");
                                            edtxtContrasenaConfirm.setText("");
                                        }
                                    } catch (Exception e) {
                                        Log.d("ERROR", e.toString());
                                    }
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    // Manejar error
                                    Log.e("ERROR", error.toString());
                                    if ((error.toString()).equals("com.android.volley.TimeoutError")) {
                                        Toast.makeText(RegisterActivity.this, String.valueOf("El tiempo de espera para conectarse a la DB ha expirado"), Toast.LENGTH_LONG).show();
                                    } else {
                                        String errorDesc = "Error desconocido; ".concat(error.toString());
                                        Toast.makeText(RegisterActivity.this, String.valueOf(errorDesc), Toast.LENGTH_LONG).show();
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
                    RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                    queue.add(jsonObjectRequest);
                }else{
                    //ERROR PASSWORDS DISTINTAS
                }
            }else{
                //ERROR CAMPOS VACIOS
                Log.d("ERROR", "CAMPOS VACIOS");
            }

        });
        btnRegSalir.setOnClickListener(view -> {
            startActivity(new Intent(this, MainActivity.class));
            finish(); // opcional: para cerrar esta pantalla
        });



    }



}
