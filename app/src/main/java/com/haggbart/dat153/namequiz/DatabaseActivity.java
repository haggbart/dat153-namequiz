package com.haggbart.dat153.namequiz;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.haggbart.dat153.namequiz.data.AppDatabase;
import com.haggbart.dat153.namequiz.person.PersonAdapter;
import com.haggbart.dat153.namequiz.person.PersonDao;
import com.haggbart.dat153.namequiz.person.PersonTouchHelper;

import java.util.Comparator;
import java.util.Locale;

public class DatabaseActivity extends AppCompatActivity {

    private static final String TAG = "DatabaseActivity";

    private PersonAdapter personAdapter;
    private PersonDao dao;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        recyclerView = findViewById(R.id.listPeople);
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
            dao.getAllPeople().sort(Comparator.comparing(p -> p.getFullName().toLowerCase(Locale.ROOT)));
            personAdapter.notifyDataSetChanged();
            Toast.makeText(this, "Entries sorted", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
    
    @Override
    protected void onResume() {
        Log.d(TAG, "onResume: called");
        dao = AppDatabase.getINSTANCE(getApplicationContext()).personDao();
        personAdapter = new PersonAdapter(getApplicationContext(), dao.getAllPeople());

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(personAdapter);

        ItemTouchHelper.Callback callback = new PersonTouchHelper(personAdapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(recyclerView);
        super.onResume();
    }
}
