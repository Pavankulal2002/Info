package com.example.info;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class profile extends AppCompatActivity {

    TextView uname1,uemail1,uphone1,uname3;
    FirebaseDatabase rootNode;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        uname1 = findViewById(R.id.uname);
        uname3 = findViewById(R.id.uname5);
        uemail1 = findViewById(R.id.uemail);
        uphone1 = findViewById(R.id.uphone);

        Intent intent=getIntent();
        String id= intent.getStringExtra("uid");

        rootNode =FirebaseDatabase.getInstance();
        rootNode.getReference().child("Users").child(id)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        String nameDB=snapshot.child("name").getValue(String.class);
                        String emailDB=snapshot.child("email").getValue(String.class);
                        String phoneDB=snapshot.child("phone").getValue(String.class);




                        uname1.setText(nameDB);
                        uname3.setText(nameDB);
                        uemail1.setText(emailDB);
                        uphone1.setText(phoneDB);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });



    }
}