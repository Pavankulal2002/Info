package com.example.info;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class menu extends AppCompatActivity {
    TextView name;
    Button profile,education,personal;

    FirebaseDatabase rootNode;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        name=(TextView)  findViewById(R.id.menu_user);
        profile=(Button) findViewById(R.id.btprofile);
        education=(Button) findViewById(R.id.bteducational);
        personal=(Button) findViewById(R.id.btpersonal);

        Intent intent=getIntent();
        String id= intent.getStringExtra("uid");

        rootNode =FirebaseDatabase.getInstance();
        rootNode.getReference().child("Users").child(id)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        String nameDB=snapshot.child("name").getValue(String.class);



                        name.setText(nameDB);




                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent prof=new Intent(getApplicationContext(),profile.class);
                prof.putExtra("uid",id);

                startActivity(prof);

            }
        });



    }
}