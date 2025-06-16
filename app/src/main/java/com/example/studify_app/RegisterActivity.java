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

        ControladorVolley.init(getApplicationContext());

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
                    Map<String, String> params = new HashMap<>();
                    params.put("Nombre", nombre);
                    params.put("FechaDNacimiento", fechaDNacimiento);
                    params.put("telefono", telefono);
                    params.put("DUI", dui);
                    params.put("Password", pass1);

                    ControladorVolley.postJSON("/login", params, new ControladorVolley.VolleyCallback() {
                        @Override
                        public void onSuccess(JSONObject response) {
                            // Maneja la respuesta exitosa
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
                        @Override
                        public void onError(JSONObject error) {
                            // Maneja el error
                            Log.e("ERROR", error.toString());
                        }
                    });
                }else{
                    //ERROR PASSWORDS DISTINT
                    Toast.makeText(RegisterActivity.this, String.valueOf("Las contraseñas no coinciden"), Toast.LENGTH_LONG).show();
                    edtxtContrasena2.setText("");
                    edtxtContrasenaConfirm.setText("");
                }
            }else{
                //ERROR CAMPOS VACIOS
                Toast.makeText(RegisterActivity.this, String.valueOf("Ningún campo debe estar vacio"), Toast.LENGTH_LONG).show();
                Log.d("ERROR", "CAMPOS VACIOS");
            }
        });
        btnRegSalir.setOnClickListener(view -> {
            startActivity(new Intent(this, MainActivity.class));
            finish(); // opcional: para cerrar esta pantalla
        });
    }
}
