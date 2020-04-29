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

public class Register extends AppCompatActivity {

    private Button register;
    private EditText email,password,name,dob,confpassword;
    private FirebaseAuth mAuth;
    private ProgressBar pg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        name = findViewById(R.id.name);
        dob = findViewById(R.id.dob);
        confpassword = findViewById(R.id.confPassword);
        register = findViewById(R.id.register);
        mAuth = FirebaseAuth.getInstance();
        pg = findViewById(R.id.progressBar);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pg.setVisibility(View.VISIBLE);
                if(TextUtils.isEmpty(name.getText().toString())){
                    name.setError("Cannot be empty");
                }
                else if(TextUtils.isEmpty(email.getText().toString())){
                    email.setError("Cannot be empty");
                }
                else if(password.getText().toString().length()<=6){
                    name.setError("Must be greater than 6");
                }
                else if(!(confpassword.getText().toString()).equals(password.getText().toString())){
                    confpassword.setError("Must be same as password");
                }
                else if(TextUtils.isEmpty(dob.getText().toString())){
                    dob.setError("Cannot be empty");
                }else{
                    mAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        pg.setVisibility(View.INVISIBLE);
                                        Toast.makeText(Register.this, "User registered", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(), Login.class);
                                        startActivity(intent);
                                    }
                                    else{
                                        pg.setVisibility(View.INVISIBLE);
                                        Toast.makeText(Register.this, "Not registered. Try again!!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });
    }
}
