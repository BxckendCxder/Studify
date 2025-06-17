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

        Intent intent = getIntent();
        usuario = intent.getStringExtra("usuario");
        pass = intent.getStringExtra("password");

        edtxtNombreMateria = findViewById(R.id.edtxtNombreMateria);
        edtxtNombreProfesor = findViewById(R.id.edtxtPonderacion);
        edtxtHorario = findViewById(R.id.edtxtHorario);

        btnRegistroMateria = findViewById(R.id.btnGuardarCalificacion);
        btnMenuprincipal2 =findViewById(R.id.btnRMenuPrincipal4);

        txtvResultadoregm = findViewById(R.id.txtvResultadoGuardarCalificacion);


        btnMenuprincipal2.setOnClickListener(view -> {
            Intent intent1 = new Intent(RegistroMaterias.this, MenuPrincipal.class);
            intent1.putExtra("usuario", usuario);
            intent1.putExtra("password", pass);
            startActivity(intent1);
            finish(); // opcional: para cerrar esta pantalla
        });

        btnRegistroMateria.setOnClickListener(view -> {
            nombreMat = edtxtNombreMateria.getText().toString().trim();
            nombreProf = edtxtNombreProfesor.getText().toString().trim();
            horario = edtxtHorario.getText().toString().trim();

            if(!nombreMat.isEmpty() && !nombreProf.isEmpty() && !horario.isEmpty()){
                Map<String, String> params = new HashMap<>();
                params.put("usuario", usuario);
                params.put("password", pass);
                params.put("NombreMat", nombreMat);
                params.put("NombreProf", nombreProf);
                params.put("Horario", horario);

                ControladorVolley.postJSON("/RegistrarMateria", params, new ControladorVolley.VolleyCallback() {
                    @Override
                    public void onSuccess(JSONObject response) {
                        // Maneja la respuesta exitosa
                        try{
                            String respuesta = response.get("EstadoComms").toString();
                            if (respuesta.equals("Materia agregada correctamente")){
                                Toast.makeText(RegistroMaterias.this, String.valueOf("La materia fue agregada exitosamente"), Toast.LENGTH_LONG).show();
                                edtxtNombreMateria.setText("");
                                edtxtNombreProfesor.setText("");
                                edtxtHorario.setText("");
                            }else{
                                Toast.makeText(RegistroMaterias.this, String.valueOf("Ocurrió un error"), Toast.LENGTH_LONG).show();
                                edtxtNombreMateria.setText("");
                                edtxtNombreProfesor.setText("");
                                edtxtHorario.setText("");
                            }
                        }catch (Exception e){
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
                //CAMPOS VACIOS
                Toast.makeText(RegistroMaterias.this, String.valueOf("Los campos no pueden estar vacios"), Toast.LENGTH_LONG).show();
            }
        });
    }
}
