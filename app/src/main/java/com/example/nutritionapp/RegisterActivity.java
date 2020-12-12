package com.example.nutritionapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {
    EditText nameText, emailText, psswdText;
    Button btnReg;
    private FirebaseAuth mAuth;

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
        btnReg = (Button)findViewById(R.id.btn_reg);

        mAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Registrando...");

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = nameText.getText().toString().trim();
                String email = emailText.getText().toString().trim();
                String psswd = psswdText.getText().toString().trim();

                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    emailText.setError("Email Inválido");
                    emailText.setFocusable(true);

                }
                else if(psswd.length() < 6){
                    psswdText.setError("Contrasen debe ser almenos 6 caracteres");
                    psswdText   .setFocusable(true);

                }else{
                    registerUser(nombre, email, psswd);
                }

            }
        });






    }

    private void registerUser(String nombre, String email, String psswd) {

        progressDialog.show();
        mAuth.createUserWithEmailAndPassword(email, psswd)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            FirebaseUser user = mAuth.getCurrentUser();
                            progressDialog.dismiss();
                            Toast.makeText(RegisterActivity.this, "Registrado...\n" + user.getEmail(),Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(RegisterActivity.this,ProfileActivity.class));
                                    finish();

                        } else {
                            // If sign in fails, display a message to the user.
                            progressDialog.dismiss();
                            Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
                Toast.makeText(RegisterActivity.this, "" +e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();  //ir a actividad previa
        return super.onSupportNavigateUp();
    }
}