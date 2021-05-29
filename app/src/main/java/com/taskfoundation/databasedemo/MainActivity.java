package com.taskfoundation.databasedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import java.security.spec.ECField;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            SQLiteDatabase database = this.openOrCreateDatabase("Events", MODE_PRIVATE, null);

            database.execSQL("CREATE TABLE IF NOT EXISTS events (name VARCHAR, year INT(4))");

            database.execSQL("INSERT INTO users(name,year) VALUES('Ramadan',2021)");
            database.execSQL("INSERT INTO users(name,year) VALUES('Eid AL-Fetr',2021)");

            Cursor cursor = database.rawQuery("SELECT * FROM events", null);

            int nameIndex = cursor.getColumnIndex("name");
            int yearIndex = cursor.getColumnIndex("year");

            cursor.moveToFirst();

            while (!cursor.isAfterLast()) {
                Log.i("event name:", cursor.getString(nameIndex));
                Log.i("event year:", cursor.getString(yearIndex));

                cursor.moveToNext();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}