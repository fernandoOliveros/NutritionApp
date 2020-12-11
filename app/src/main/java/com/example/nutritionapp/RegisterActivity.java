package com.example.nutritionapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {
    EditText nameText, emailText, psswdText;
    Button btnReg;

    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Crear Cuenta");
        //boton regresar(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        nameText = findViewById(R.id.textName);
        emailText = findViewById(R.id.textEmail);
        psswdText = findViewById(R.id.textPsswd);
        btnReg = findViewById(R.id.btn_reg);
        progressDialog = new ProgressDialog(this);

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });






    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();  //ir a actividad previa
        return super.onSupportNavigateUp();
    }
}