package com.example.studify_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class RegistroMaterias extends AppCompatActivity {
    EditText edtxtNombreMateria, edtxtNombreProfesor, edtxtHorario;
    Button btnRegistroMateria, btnMenuprincipal2;
    TextView txtvResultadoregm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.registro_materias);

        edtxtNombreMateria = findViewById(R.id.edtxtNombreMateria);
        edtxtNombreProfesor = findViewById(R.id.edtxtPonderacion);
        edtxtHorario = findViewById(R.id.edtxtHorario);

        btnRegistroMateria = findViewById(R.id.btnGuardarCalificacion);
        btnMenuprincipal2 =findViewById(R.id.btnRMenuPrincipal4);

        txtvResultadoregm = findViewById(R.id.txtvResultadoGuardarCalificacion);

        btnRegistroMateria.setOnClickListener(view ->{
            String NombreMateria = edtxtNombreMateria.getText().toString().trim();
            String NombreProfesor = edtxtNombreProfesor.getText().toString().trim();
            String Horario = edtxtHorario.getText().toString().trim();

            // Validación
            if (!NombreMateria.isEmpty() && !NombreProfesor.isEmpty() && !Horario.isEmpty()) {
                Toast.makeText(this, "Actividad guardada correctamente.",Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(this, "Por favor, completa todos los campos.",Toast.LENGTH_SHORT).show();

            }


        });


        btnMenuprincipal2.setOnClickListener(view -> {
            Intent intent = new Intent(RegistroMaterias.this, MenuPrincipal.class);
            startActivity(intent);
            finish(); // opcional: para cerrar esta pantalla
        });



    }
}
