package com.example.studify_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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


        btnMenuprincipal2.setOnClickListener(view -> {
            Intent intent = new Intent(RegistroMaterias.this, MenuPrincipal.class);
            startActivity(intent);
            finish(); // opcional: para cerrar esta pantalla
        });



    }
}
