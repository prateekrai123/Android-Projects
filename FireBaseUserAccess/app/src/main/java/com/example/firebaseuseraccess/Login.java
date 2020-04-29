package com.example.firebaseuseraccess;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Button login;
    private EditText email,password;
    private ProgressBar pg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login = findViewById(R.id.login);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        mAuth = FirebaseAuth.getInstance();
        pg = findViewById(R.id.progressBar);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pg.setVisibility(View.VISIBLE);
                if(TextUtils.isEmpty(email.getText().toString())){
                    email.setError("Cannot be empty");
                }
                else if(TextUtils.isEmpty(password.getText().toString())){
                    password.setError("Cannot be empty");
                }
                else if(email.getText().toString().length()<6){
                    password.setError("Must be greater than 6");
                }
                else
                    mAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                pg.setVisibility(View.INVISIBLE);
                                Intent intent = new Intent(getApplicationContext(), User.class);
                                startActivity(intent);
                            }
                            else{
                                pg.setVisibility(View.INVISIBLE);
                                Toast.makeText(Login.this, "Wrong Credentials! Try again", Toast.LENGTH_SHORT).show();
                            }
                        }
                });
            }
        });
    }
}
