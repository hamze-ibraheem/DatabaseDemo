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
            SQLiteDatabase database = this.openOrCreateDatabase("Users", MODE_PRIVATE, null);

            database.execSQL("CREATE TABLE IF NOT EXISTS users (name VARCHAR, age INT(3), id INTEGER PRIMARY KEY)");

            database.execSQL("INSERT INTO users(name,age) VALUES('Hamza',36)");
            database.execSQL("INSERT INTO users(name,age) VALUES('Ahmed',37)");
            database.execSQL("DELETE FROM users WHERE name='Ahmed'");

            Cursor cursor = database.rawQuery("SELECT * FROM users WHERE id = 2 AND age > 18 AND name LIKE '%a%' LIMIT 1", null);

            int nameIndex = cursor.getColumnIndex("name");
            int ageIndex = cursor.getColumnIndex("age");
            int idIndex = cursor.getColumnIndex("id");

            cursor.moveToFirst();

            while (!cursor.isAfterLast()) {
                Log.i("name:", cursor.getString(nameIndex));
                Log.i("age:", Integer.toString(cursor.getInt(ageIndex)));
                Log.i("id:", Integer.toString(cursor.getInt(idIndex)));

                cursor.moveToNext();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}