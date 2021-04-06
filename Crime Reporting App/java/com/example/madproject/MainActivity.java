package com.example.madproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.net.Inet4Address;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper proj_db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        proj_db = new DatabaseHelper(this);

        Button btn_signup = findViewById(R.id.btn_new_user);
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent s2 = new Intent(getApplicationContext(), MainActivity2.class);
                startActivity(s2);
            }
        });

        Button btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent s3 = new Intent(getApplicationContext(), MainActivity3.class);
                startActivity(s3);
            }
        });
    }
}