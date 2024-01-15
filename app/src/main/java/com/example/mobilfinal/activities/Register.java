package com.example.mobilfinal.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobilfinal.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {
    FirebaseAuth auth;
    EditText etFName, etLName, etEmail, etPassword;
    Button btnSignUp;
    TextView tvLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etFName = findViewById(R.id.et_firstname);
        etLName = findViewById(R.id.et_lastname);
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        btnSignUp = findViewById(R.id.btn_signup);
        tvLogin = findViewById(R.id.tv_tologin);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etFName.getText().toString() + " " + etLName.getText().toString();
                String email = etEmail.getText().toString().toLowerCase();
                String passwd = etPassword.getText().toString();

                if (name.isEmpty() || email.isEmpty() || passwd.isEmpty())  {
                    Toast.makeText(getApplicationContext(), "Lütfen herhangi bir alanı boş bırakmayınız.", Toast.LENGTH_SHORT).show();
                }

                auth = FirebaseAuth.getInstance();
                auth.createUserWithEmailAndPassword(email, passwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                         // String uid = task.getResult().getUser().getUid().toString();
                            Toast.makeText(getApplicationContext(), "Başarıyla kayıt oldunuz.", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), Login.class));

                            FirebaseFirestore db = FirebaseFirestore.getInstance();
                            CollectionReference ref = db.collection("UserModel");

                            Map<String, Object> user = new HashMap<>();
                            user.put("email", email);
                            user.put("name", name);

                            ref.add(user);
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Kayıt başarısız.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Login.class));
            }
        });
    }
}