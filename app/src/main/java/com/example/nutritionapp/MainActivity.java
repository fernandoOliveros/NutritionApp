package com.example.nutritionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;



public class MainActivity extends AppCompatActivity {


    Button btnRegister = (Button) findViewById(R.id.register_btn);
    Button btnLogin = (Button) findViewById(R.id.login_btn);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //iniciar RegisterActivity
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
            }
        });

    }



}