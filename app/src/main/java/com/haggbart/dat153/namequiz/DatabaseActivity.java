package com.haggbart.dat153.namequiz;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.haggbart.dat153.namequiz.database.People;
import com.haggbart.dat153.namequiz.person.PersonAdapter;
import com.haggbart.dat153.namequiz.person.PersonEntry;
import com.haggbart.dat153.namequiz.person.PersonTouchHelper;

import java.util.Comparator;

public class DatabaseActivity extends AppCompatActivity {

    private static final String TAG = "DatabaseActivity";

    private final People database = People.getInstance();

    private PersonAdapter personAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        RecyclerView listPeople = findViewById(R.id.listPeople);

        personAdapter = new PersonAdapter();

        listPeople.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        listPeople.setItemAnimator(new DefaultItemAnimator());
        listPeople.setAdapter(personAdapter);

        ItemTouchHelper.Callback callback = new PersonTouchHelper(personAdapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(listPeople);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.database_menu, menu);
        return true;
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Log.d(TAG, "onOptionsItemSelected: menu item selected: " + item.getTitle());

        int id = item.getItemId();
        if (id == R.id.mnuAddEntry) {
            Intent intent = new Intent(this, AddEntryActivity.class);
            startActivity(intent);
        } else if (id == R.id.mnuSortEntries) {
            database.getPeople().sort(Comparator.comparing(PersonEntry::getFullName));
            personAdapter.notifyDataSetChanged();
        }
        return super.onOptionsItemSelected(item);
    }
    
    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onResume() {
        Log.d(TAG, "onResume: called");
        if (database.isDirty) personAdapter.notifyDataSetChanged();
        super.onResume();
    }
}
