package com.example.madproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity3 extends AppCompatActivity {
    public static final String KEY_USERNAME = "key-username";
    EditText et_username, et_password;
    Button btn_login;
    DatabaseHelper proj_db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        proj_db = new DatabaseHelper(this);

        et_username = (EditText) findViewById(R.id.et_login_username);
        et_password = (EditText) findViewById(R.id.et_login_password);
        btn_login = (Button) findViewById(R.id.btn_login2);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isUser = proj_db.checkLogin(et_username.getText().toString(), et_password.getText().toString());
                if (isUser == true){
                    Intent intent = new Intent(getApplicationContext(), MainActivity4.class);
                    intent.putExtra(KEY_USERNAME, et_username.getText().toString());
                    startActivity(intent);
                }
            }
        });
    }
}