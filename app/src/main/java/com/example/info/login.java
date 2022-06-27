package com.example.info;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login extends AppCompatActivity {

    private FirebaseAuth mAuth;   // Initialize Firebase Auth

    EditText email,password;
    Button login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance(); //firebase

        email=(EditText) findViewById(R.id.e4);
        password=(EditText) findViewById(R.id.e5);
        login=(Button) findViewById(R.id.b4);



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email1 = email.getText().toString().trim();
                String password1 = password.getText().toString().trim();

                if (email1.isEmpty()) {
                    email.setError("Enter the email");
                    email.requestFocus();
                    return;
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(email1).matches()) {
                    email.setError("Enter the valid email");
                    email.requestFocus();
                    return;
                }
                if (password1.isEmpty()) {
                    password.setError("Password is Required");
                    password.requestFocus();
                    return;
                }

                mAuth.signInWithEmailAndPassword(email1, password1)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Toast.makeText(login.this, "login successful", Toast.LENGTH_SHORT).show();

                                    FirebaseUser user = mAuth.getCurrentUser();

                                    String UID=user.getUid();
                                    String mail=user.getEmail();


                                    Intent intent =new Intent(getApplicationContext(),home.class);

                                    intent.putExtra("uid",UID);
                                    intent.putExtra("email",mail);

                                    startActivity(intent);



                                } else {
                                    // If sign in fails, display a message to the user.

                                    Toast.makeText(login.this, "Wrong username or password.", Toast.LENGTH_LONG).show();

                                }


                            }
                        });
            }
        });
    }
}