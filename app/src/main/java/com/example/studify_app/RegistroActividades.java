package com.example.studify_app;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class RegistroActividades extends AppCompatActivity {
    private DatePickerDialog datePickerDialog;
    private Button dateButton;
    private int selectedYear;
    private int selectedMonth;
    private int selectedDay;

    Spinner SpinCategoria,SpinMateria;
    EditText edtxtDescripcion;
    Button btnRegistroActividad, btnMenuPrincipal3;

    String usuario, pass, listaMat;
;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.registro_actividades);

        Intent intent1 = getIntent();
        usuario = intent1.getStringExtra("usuario");
        pass = intent1.getStringExtra("password");
        listaMat = intent1.getStringExtra("lista");

        edtxtDescripcion = findViewById(R.id.edtxtPonderacion);


        btnRegistroActividad = findViewById(R.id.btnGuardarCalificacion);
        btnMenuPrincipal3 =findViewById(R.id.btnRMenuPrincipal4);

        SpinCategoria = findViewById(R.id.SpinCategoria);
        SpinMateria = findViewById(R.id.SpinMateria);


        listaMat = listaMat.replaceAll("[\\[\\]\"']", "");

        // Dividir por comas y espacios
        String[] materias = listaMat.split(",\\s*");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, materias);
        SpinMateria.setAdapter(adapter);


        String[] actividadesAcademicas = {"Tarea", "Laboratorio", "Parcial", "Proyecto","Exposición"};
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, actividadesAcademicas);
        SpinCategoria.setAdapter(adapter);

        initDatePicker();
        dateButton = findViewById(R.id.btnFechaEntrega);
        dateButton.setText(getDefaultDate());

        btnRegistroActividad.setOnClickListener(view -> {
            String DescripcionString = edtxtDescripcion.getText().toString().trim();
            String CategoriaString = SpinCategoria.getSelectedItem().toString().trim();
            String MateriaString = SpinMateria.getSelectedItem().toString().trim();


            // Validación
            if (!DescripcionString.isEmpty() && !CategoriaString.isEmpty() && !MateriaString.isEmpty()) {
                //CAMPOS COMPLETOS
                //Toast.makeText(this, "Actividad guardada correctamente.",Toast.LENGTH_SHORT).show();
                Toast.makeText(this, "Fecha seleccionada: " + getSelectedDate(), Toast.LENGTH_SHORT).show();
                // AGREGAR A LA CONDICIONAL LA FECHA VALIDA



















            } else {
                //CAMPOS VACIOS
                Toast.makeText(this, "Por favor, completa todos los campos.",Toast.LENGTH_SHORT).show();

            }
        });

        btnMenuPrincipal3.setOnClickListener(view ->{
            Intent intent = new Intent(RegistroActividades.this, MenuPrincipal.class);
            intent.putExtra("usuario", usuario);
            intent.putExtra("password", pass);
            startActivity(intent);
            finish();
        });
    }


    private String getDefaultDate()
    {
        return makeDateString(1, 1, 2000);
    }

    private void initDatePicker()
    {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                month = month + 1;

                selectedYear = year;
                selectedMonth = month;
                selectedDay = day;

                Toast.makeText(RegistroActividades.this, "Fecha seleccionada: " + day + "/" + month + "/" + year, Toast.LENGTH_SHORT).show();

                String date = makeDateString(day, month, year);
                dateButton.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        //datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

    }

    private String makeDateString(int day, int month, int year)
    {
        return getMonthFormat(month) + " " + day + " " + year;
    }

    private String getMonthFormat(int month)
    {
        if(month == 1)
            return "ENE";
        if(month == 2)
            return "FEB";
        if(month == 3)
            return "MAR";
        if(month == 4)
            return "ABR";
        if(month == 5)
            return "MAY";
        if(month == 6)
            return "JUN";
        if(month == 7)
            return "JUL";
        if(month == 8)
            return "AGO";
        if(month == 9)
            return "SEP";
        if(month == 10)
            return "OCT";
        if(month == 11)
            return "NOV";
        if(month == 12)
            return "DIC";
        return "ENE";
    }

    public String getSelectedDate() {
        return makeDateString(selectedDay, selectedMonth, selectedYear);
    }

    public void openDatePicker(View view)
    {
        datePickerDialog.show();
    }
}
