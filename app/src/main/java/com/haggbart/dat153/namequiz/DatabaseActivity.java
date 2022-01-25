package com.haggbart.dat153.namequiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.haggbart.dat153.namequiz.database.Database;
import com.haggbart.dat153.namequiz.person.PersonAdapter;
import com.haggbart.dat153.namequiz.person.PersonEntry;

import java.util.ArrayList;
import java.util.List;

public class DatabaseActivity extends AppCompatActivity {

    private static final String TAG = "DatabaseActivity";

    private final Database database = Database.getInstance();
    private List<PersonEntry> people;

    private PersonAdapter<PersonEntry> personAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        people = database.getPeople();

        ListView listPeople = findViewById(R.id.listPeople);
        personAdapter = new PersonAdapter<>(DatabaseActivity.this, R.layout.list_record, people);
        listPeople.setAdapter(personAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.database_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Log.d(TAG, "onOptionsItemSelected: menu item selected: " + item.getTitle());

        int id = item.getItemId();
        if (id == R.id.mnuAddEntry) {
            Intent intent = new Intent(this, AddEntryActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
    
    @Override
    protected void onResume() {
        Log.d(TAG, "onResume: called");
        people.clear();
        people.addAll(database.getPeople());
        personAdapter.notifyDataSetChanged();
        super.onResume();
    }
}
