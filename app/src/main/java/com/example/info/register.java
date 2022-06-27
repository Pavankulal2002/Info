package com.example.info;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class register extends AppCompatActivity {

    private FirebaseAuth mAuth;   // Initialize Firebase Auth

    EditText name1,email1,password1,phoneno;
    Button register;
    TextView progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance(); //firebase
        DatabaseReference reference=FirebaseDatabase.getInstance().getReference("users");

        name1=(EditText) findViewById(R.id.e1);
        email1=(EditText) findViewById(R.id.e2);
        password1=(EditText) findViewById(R.id.e3);
        register=(Button) findViewById(R.id.b3);
        phoneno=(EditText) findViewById(R.id.e9);



        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = email1.getText().toString().trim();
                String name = name1.getText().toString().trim();
                String password = password1.getText().toString().trim();
                String phone = phoneno.getText().toString().trim();


                if(name.isEmpty()){
                    name1.setError("Enter correct name");
                    name1.requestFocus();
                    return;
                }
                if(email.isEmpty()){
                    email1.setError("Enter the email");
                    email1.requestFocus();
                    return;
                }
                if(password.isEmpty()){
                    password1.setError("Password is Required");
                    password1.requestFocus();
                    return;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    email1.setError("Enter the valid email");
                    email1.requestFocus();
                    return;
                }
                if(phone.isEmpty()){
                    phoneno.setError("Phone number is Required");
                    phoneno.requestFocus();
                    return;
                }



                mAuth.createUserWithEmailAndPassword(email,password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    User user=new User(name,email,password,phone);

                                    FirebaseDatabase.getInstance().getReference("Users")
                                           .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                          //  .child(phone)
                                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()){
                                                Toast.makeText(register.this, "Registration Successfull", Toast.LENGTH_SHORT).show();
                                                Intent login=new Intent(register.this,login.class);
                                                startActivity(login);
                                            }
                                            else{
                                                Toast.makeText(register.this, "Registration failed", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                                }else{
                                    Toast.makeText(register.this, "Registration failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });

    }
}