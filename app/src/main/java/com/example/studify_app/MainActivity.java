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

import org.json.*;

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

        ControladorVolley.init(getApplicationContext());


        edtxtNombre = findViewById(R.id.edtxtNombre);
        edtxtContrasena = findViewById(R.id.edtxtContrasena);
        btnlogin = findViewById(R.id.btnlogin);
        txtvSignin = findViewById(R.id.txtvSignin);

        txtvSignin.setOnClickListener(view ->{
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(intent);

        });

        btnlogin.setOnClickListener(view ->{
            String usuario = edtxtNombre.getText().toString().trim();
            String pass = edtxtContrasena.getText().toString().trim();

            if (!usuario.isEmpty()&&!pass.isEmpty()){
                Map<String, String> params = new HashMap<>();
                params.put("usuario", usuario);
                params.put("password", pass);

                ControladorVolley.postJSON("/login", params, new ControladorVolley.VolleyCallback() {
                    @Override
                    public void onSuccess(JSONObject response) {
                        // Maneja la respuesta exitosa
                        try {
                            String respuesta = response.get("EstadoComms").toString();
                            if(respuesta.equals("UsuarioExiste")){
                                Toast.makeText(MainActivity.this, String.valueOf("Ingresando"), Toast.LENGTH_LONG).show();
                                Log.d("RESPONSE", respuesta);
                                Intent intent = new Intent(MainActivity.this, MenuPrincipal.class);
                                intent.putExtra("usuario", usuario);
                                intent.putExtra("password", pass);
                                startActivity(intent);
                                finish(); // opcional: para cerrar esta pantalla
                            }else{
                                Toast.makeText(MainActivity.this, String.valueOf("Las credenciales ingresadas son incorrectas"), Toast.LENGTH_LONG).show();
                            }
                        }catch(Exception e){
                            Log.d("ERROR", e.toString());
                        }
                    }

                    @Override
                    public void onError(JSONObject error) {
                        // Maneja el error
                        Log.e("ERROR", error.toString());
                        if((error.toString()).equals("com.android.volley.TimeoutError")){
                            Toast.makeText(MainActivity.this, String.valueOf("El tiempo de espera para conectarse a la DB ha expirado"), Toast.LENGTH_LONG).show();
                        }else{
                            String errorDesc = "Error desconocido; ".concat(error.toString());
                            Toast.makeText(MainActivity.this, String.valueOf(errorDesc), Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }else {
                Toast.makeText(this, "Rellena todos los campos",Toast.LENGTH_SHORT).show();
            }
        });
    }
}