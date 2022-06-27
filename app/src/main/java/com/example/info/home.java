package com.example.info;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

public class home extends AppCompatActivity {

    EditText userid,email,name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        userid =(EditText) findViewById(R.id.uid);
        email=(EditText) findViewById(R.id.emailadd);

        showuserdetails();


    }

    private void showuserdetails() {

        Intent intent=getIntent();
        String UID= intent.getStringExtra("uid");
        String Mail= intent.getStringExtra("email");

        userid.setText(UID);
        email.setText(Mail);

    }
}