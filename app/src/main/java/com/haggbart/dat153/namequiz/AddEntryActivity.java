package com.haggbart.dat153.namequiz;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Person;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.haggbart.dat153.namequiz.database.Database;
import com.haggbart.dat153.namequiz.person.PersonAdapter;
import com.haggbart.dat153.namequiz.person.PersonEntry;

import java.util.List;

public class AddEntryActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "AddEntryActivity";

    private static final Database database = Database.getInstance();

    private EditText forename;
    private EditText surname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_entry);

        forename = findViewById(R.id.etForename);
        surname = findViewById(R.id.etSurname);

        Button btnAddEntry = findViewById(R.id.btnAddEntry);
        btnAddEntry.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Log.d(TAG, "onClick: button clicked: " + view.getResources().getResourceEntryName(view.getId()));
        if (view.getId() != R.id.btnAddEntry) return;

        Log.d(TAG, "onClick: passed check");
        var person = new PersonEntry(forename.getText().toString(), surname.getText().toString());
        person = database.addPerson(person);

        if (person == null) {
            Toast.makeText(this, "Please input all the required fields", Toast.LENGTH_SHORT).show();
            return;
        }

        Toast.makeText(this, String.format("%s %s added to the database", person.getForename(), person.getSurname()), Toast.LENGTH_SHORT).show();
    }
}