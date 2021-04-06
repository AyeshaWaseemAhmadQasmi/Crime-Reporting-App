package com.example.madproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Struct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

import static com.example.madproject.DatabaseHelper.crimename_col;
import static com.example.madproject.DatabaseHelper.description_col;
import static com.example.madproject.DatabaseHelper.id_col;
import static com.example.madproject.DatabaseHelper.lat_col;
import static com.example.madproject.DatabaseHelper.long_col;
import static com.example.madproject.DatabaseHelper.offense_col;
import static com.example.madproject.DatabaseHelper.severity_col;

public class MainActivity4 extends AppCompatActivity {
    private ArrayList<Crimes> crimesList;
    DatabaseHelper proj_db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        proj_db = new DatabaseHelper(this);

        Intent data = getIntent();
        if (data == null) {
            finish();
        }
        String username = data.getStringExtra(MainActivity3.KEY_USERNAME);

        crimesList = new ArrayList<Crimes>();

        Cursor cursor = proj_db.displayCrime(username);
        String crime_name, crime_desc, offense_tag, severity_tag, latitude, longitude;
        Integer crime_id;

        while(cursor.moveToNext()) {
            crime_id = cursor.getInt(cursor.getColumnIndex(id_col));
            crime_name = cursor.getString(cursor.getColumnIndex(crimename_col));
            crime_desc = cursor.getString(cursor.getColumnIndex(description_col));
            offense_tag = cursor.getString(cursor.getColumnIndex(offense_col));
            severity_tag  = cursor.getString(cursor.getColumnIndex(severity_col));
            latitude  = cursor.getString(cursor.getColumnIndex(lat_col));
            longitude = cursor.getString(cursor.getColumnIndex(long_col));
            Crimes crime = new Crimes(crime_id, crime_name,crime_desc,offense_tag,severity_tag,latitude,longitude);
            crimesList.add(crime);
        }
        ListView lv = findViewById(R.id.lv_crimes);

        final MyAdapter adapter = new MyAdapter(this, R.layout.crime_row,crimesList);

        lv.setAdapter(adapter);

//        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                if (position == 0) {
//                    Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
//                    startActivity(intent);
//
//                }
//            }
//        });

        Button new_crime = findViewById(R.id.btn_addcrime);
        new_crime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent s5 = new Intent(getApplicationContext(), MainActivity5.class);
                startActivity(s5);
            }
        });
    }
}