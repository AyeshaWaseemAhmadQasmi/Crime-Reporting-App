package com.example.madproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String dbname = "proj.db";
    public static final String user_table = "user_info";
    public static final String username_col = "username";
    public static final String password_col = "password";
    public static final String email_col = "email";
    public static final String uni_id_col = "uni_id";

    public static final String crimes_table = "crime_info";
    public static final String id_col = "id";
    public static final String crimename_col = "crimename";
    public static final String description_col = "description";
    public static final String offense_col = "offense";
    public static final String severity_col = "severity";
    public static final String lat_col = "latitude";
    public static final String long_col = "longitude";
    public static final String username_foreign_col = "username_foreign";


    public DatabaseHelper(Context context) {
        super(context, dbname, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + user_table + "(username text primary key, password varchar(225) not null, email varchar(225) not null unique, uni_id varchar(20) not null unique)");
        db.execSQL("create table " + crimes_table + "(id integer primary key AUTOINCREMENT, crimename text not null, description text not null, offense varchar(50) not null, severity varchar(50) not null, latitude varchar(225) not null, longitude varchar(225) not null, username_foreign text, FOREIGN KEY(username_foreign) REFERENCES user_info (username))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + user_table);
        db.execSQL("DROP TABLE IF EXISTS " + crimes_table);
        onCreate(db);
    }

    public boolean insertUserData(String username, String password, String email, String uni_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(username_col, username);
        contentValues.put(password_col, password);
        contentValues.put(email_col, email);
        contentValues.put(uni_id_col, uni_id);
        long result = db.insert(user_table, null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean checkLogin(String username, String password) {
        String[] columns = {username_col};
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = username_col + " = ?" + " AND " + password_col + " = ?";
        String[] selectionArgs = {username, password};
        Cursor cursor = db.query(user_table, columns, selection, selectionArgs, null, null, null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean insertCrimeData(String crimename, String desc, String offense, String severity, String latitude, String longitude, String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(crimename_col, crimename);
        contentValues.put(description_col, desc);
        contentValues.put(offense_col, offense);
        contentValues.put(severity_col, severity);
        contentValues.put(lat_col, latitude);
        contentValues.put(long_col, longitude);
        contentValues.put(username_foreign_col, username);
        long result = db.insert(crimes_table, null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor displayCrime(String username) {
        String[] columns = {id_col,crimename_col, description_col, offense_col, severity_col, lat_col, long_col};
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = username_foreign_col + " = ?";
        String[] selectionArgs = {username};
        Cursor cursor = db.query(crimes_table, columns, null, null, null, null, null);
        return cursor;
    }

    public boolean deleteCrime(Integer id) {
        String[] columns = {id_col, crimename_col, description_col, offense_col, severity_col, lat_col, long_col, username_foreign_col};
        SQLiteDatabase db = this.getReadableDatabase();
        long result = db.delete(crimes_table, id_col + " = ?", new String[]{id.toString()});
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
}







