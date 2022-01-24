package com.haggbart.dat153.namequiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.haggbart.dat153.namequiz.database.Database;
import com.haggbart.dat153.namequiz.model.HvlPerson;

import java.util.ArrayList;

public class DatabaseActivity extends AppCompatActivity {

    private static final String TAG = "DatabaseActivity";

    private final Database database = Database.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        ListView listPeople = findViewById(R.id.listPeople);

        Log.d(TAG, "onCreate: data: " + database.getPeople());

        ArrayAdapter<HvlPerson> arrayAdapter = new ArrayAdapter<>(DatabaseActivity.this, R.layout.list_item, new ArrayList<>(database.getPeople()));
        listPeople.setAdapter(arrayAdapter);
    }
}
