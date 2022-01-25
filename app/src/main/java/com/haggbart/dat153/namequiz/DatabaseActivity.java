package com.haggbart.dat153.namequiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.haggbart.dat153.namequiz.database.People;
import com.haggbart.dat153.namequiz.person.PersonAdapter;
import com.haggbart.dat153.namequiz.person.PersonEntry;

public class DatabaseActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private static final String TAG = "DatabaseActivity";

    private final People database = People.getInstance();

    private PersonAdapter<PersonEntry> personAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        ListView listPeople = findViewById(R.id.listPeople);
        personAdapter = new PersonAdapter<>(DatabaseActivity.this, R.layout.list_record, database.getPeople());
        listPeople.setAdapter(personAdapter);
        listPeople.setOnItemClickListener(this);
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
        if (database.isDirty) personAdapter.notifyDataSetChanged();
        super.onResume();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        PersonEntry person = database.remove(position);
        Log.d(TAG, "onItemClick: clicked item " + person);
        personAdapter.notifyDataSetChanged();
        Toast.makeText(this, String.format("Removed %s %s", person.getForename(), person.getSurname()), Toast.LENGTH_SHORT).show();
    }
}
