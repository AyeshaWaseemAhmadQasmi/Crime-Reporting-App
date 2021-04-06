package com.example.madproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import static com.example.madproject.MainActivity3.KEY_USERNAME;

public class MainActivity5 extends AppCompatActivity {

//    public static final String KEY_LAT = "key-lat";
//    public static final String KEY_LNG = "key-lng";

    EditText et_crimename, et_desc;
    RadioGroup offense_type, severity_type;
    RadioButton selectedOffense, selectedSeverity;
    public static Double lat,lng;
    Button btn_submit;
    private int TAG_CODE_PERMISSION_LOCATION = 0x342 ;
    DatabaseHelper proj_db;

    LocationManager locationManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);


        proj_db = new DatabaseHelper(this);

        Intent data = getIntent();
        if (data == null) {
            finish();
        }
        String username = data.getStringExtra(MainActivity3.KEY_USERNAME);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        LocationListener locationListener = new MyLocationListener(this);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            ActivityCompat.requestPermissions(this,
                    new String[]{
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION
                    },
                    TAG_CODE_PERMISSION_LOCATION);
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 0, locationListener);


        et_crimename = (EditText) findViewById(R.id.et_crime_name);
        et_desc = (EditText) findViewById(R.id.et_description);
        offense_type = (RadioGroup) findViewById(R.id.offense_type);
        severity_type = (RadioGroup) findViewById(R.id.severity_type);

        btn_submit = (Button) findViewById(R.id.btn_submit);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedOffense  = (RadioButton)findViewById(offense_type.getCheckedRadioButtonId());
                selectedSeverity  = (RadioButton)findViewById(severity_type.getCheckedRadioButtonId());
                String lat = getLat().toString();
                String lng = getLng().toString();

                boolean isCrime = proj_db.insertCrimeData(et_crimename.getText().toString(), et_desc.getText().toString(), selectedOffense.getText().toString(), selectedSeverity.getText().toString(), lat, lng, username);
                if (isCrime == true){
                    Intent intent = new Intent(getApplicationContext(), MainActivity4.class);
                    startActivity(intent);
                }

            }
        });
    }
    public void setLatitude(Double latitiude) {

        lat = latitiude;
    }

    public void setLongitude(Double longitude) {

        lng = longitude;
    }

    public static Double getLat() {
        return lat;
    }

    public static Double getLng() {
        return lng;
    }
}