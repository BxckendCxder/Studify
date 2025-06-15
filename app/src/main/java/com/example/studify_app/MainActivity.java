package com.example.studify_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

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
                Intent intent = new Intent(MainActivity.this, MenuPrincipal.class);
                startActivity(intent);
                finish(); // opcional: para cerrar esta pantalla
            }else {
                Toast.makeText(this, "Rellena todos los campos",Toast.LENGTH_SHORT).show();
            }

        });
    }
}