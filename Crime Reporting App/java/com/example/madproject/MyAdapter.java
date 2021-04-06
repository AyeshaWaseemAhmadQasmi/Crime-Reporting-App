package com.example.madproject;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MyAdapter<A> extends ArrayAdapter {
    public static final String KEY_CRIMENAME = "key-crimename";
    public static final String KEY_CRIMEDESC = "key-crimedesc";
    public static final String KEY_LOCLAT = "key-loclat";
    public static final String KEY_LOCLNG = "key-loclng";


    private ArrayList<Crimes> crimes;
    private Context mycontext;
    private int myresource;
    private MyAdapter<A> adapter;
    DatabaseHelper proj_db;

    public MyAdapter(@NonNull Context context, int resource, ArrayList<Crimes> crimesList) {
        super(context, resource, crimesList);
        crimes = crimesList;
        mycontext = context;
        myresource = resource;
        adapter = this;
        proj_db = new DatabaseHelper(getContext());
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(MainActivity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(myresource, null);

        TextView tv_crimename = view.findViewById(R.id.tv_crimename);
        ImageButton btn_delete = view.findViewById(R.id.btn_delete);
        ImageView iv_severity = view.findViewById(R.id.iv_severity);

        Crimes c = crimes.get(position);


        tv_crimename.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mycontext, MapsActivity.class);
                intent.putExtra(KEY_CRIMENAME, c.getCrimename());
                intent.putExtra(KEY_CRIMEDESC,c.getDesc());
                intent.putExtra(KEY_LOCLAT, c.getLatitude());
                intent.putExtra(KEY_LOCLNG,c.getLongitude());

                mycontext.startActivity(intent);
            }
        });

        tv_crimename.setText(c.getOffense_type());





        if (c.getSeverity_type().equals("Light")){
            iv_severity.setImageResource(R.drawable.light);
        }
        else if(c.getSeverity_type().equals("Moderate")){
            iv_severity.setImageResource(R.drawable.moderate);
        }
        else if(c.getSeverity_type().equals("Extreme")){
            iv_severity.setImageResource(R.drawable.extreme);
        }

        btn_delete.setTag(new Integer(position));
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = (Integer) v.getTag();
                Crimes c = crimes.get(position);
                adapter.remove(c);
                proj_db.deleteCrime(c.getPk_id());
            }
        });

        return view;
    }
}



