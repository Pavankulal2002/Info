package com.example.info;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class home extends AppCompatActivity {
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    private FirebaseAuth mAuth;

    EditText userid,email;
    TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        userid =(EditText) findViewById(R.id.uid);
        email=(EditText) findViewById(R.id.emailadd);
        name=(TextView) findViewById(R.id.name);


        //Getting values from login page

        Intent intent=getIntent();
        String id= intent.getStringExtra("uid");

        //retrieving data from database using userId

        rootNode =FirebaseDatabase.getInstance();
        rootNode.getReference().child("Users").child(id)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        String nameDB=snapshot.child("name").getValue(String.class);


                        Intent intent=getIntent();
                        String UID= intent.getStringExtra("uid");
                        String Mail= intent.getStringExtra("email");

                        userid.setText(UID);
                        email.setText(Mail);
                        name.setText(nameDB);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });





    }


}