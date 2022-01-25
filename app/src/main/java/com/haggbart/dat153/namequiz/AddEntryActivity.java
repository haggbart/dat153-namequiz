package com.haggbart.dat153.namequiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.haggbart.dat153.namequiz.database.People;
import com.haggbart.dat153.namequiz.person.PersonEntry;

public class AddEntryActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "AddEntryActivity";

    private static final People database = People.getInstance();

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

        PersonEntry person = new PersonEntry(forename.getText().toString(), surname.getText().toString());

        if (!database.add(person)) {
            Toast.makeText(this, "Please input all the required fields", Toast.LENGTH_SHORT).show();
            return;
        }

        database.isDirty = true;
        Toast.makeText(this, String.format("%s %s added to the database", person.getForename(), person.getSurname()), Toast.LENGTH_SHORT).show();
    }
}