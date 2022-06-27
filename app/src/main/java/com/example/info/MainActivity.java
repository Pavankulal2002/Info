package com.example.info;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.Button;

import java.time.Instant;

public class MainActivity extends AppCompatActivity {

    Button bt1,bt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt1=(Button) findViewById(R.id.b1);
        bt2=(Button) findViewById(R.id.b2);

        Intent inc=new Intent(MainActivity.this,register.class);
        Intent log=new Intent(MainActivity.this,login.class);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(inc);
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(log);
            }
        });
    }
}