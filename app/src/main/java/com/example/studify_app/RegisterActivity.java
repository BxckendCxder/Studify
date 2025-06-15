package com.example.studify_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

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

        btnRegSalir.setOnClickListener(view -> {
            startActivity(new Intent(this, MainActivity.class));
        });



    }



}
