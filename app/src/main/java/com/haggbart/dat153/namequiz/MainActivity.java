package com.haggbart.dat153.namequiz;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.haggbart.dat153.namequiz.data.AppDatabase;
import com.haggbart.dat153.namequiz.data.Bootstrap;
import com.haggbart.dat153.namequiz.person.PersonDao;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    private PersonDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        dao = AppDatabase.getINSTANCE(getApplicationContext()).personDao();
        new Bootstrap().initialize(dao);
        super.onCreate(savedInstanceState);

        var people = dao.getAllPeople();
        Log.d(TAG, "onCreate: people: " + people.size());
        people.forEach(p -> Log.d(TAG, "onCreate: person: " + p));

        setContentView(R.layout.activity_main);

        TextView tvQuiz = findViewById(R.id.tvQuiz);
        TextView tvDatabase = findViewById(R.id.tvDatabase);
        TextView tvAddEntry = findViewById(R.id.tvAddEntry);

        tvQuiz.setOnClickListener(this);
        tvDatabase.setOnClickListener(this);
        tvAddEntry.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        Log.d(TAG, "onClick: Button clicked: " + view.getResources().getResourceEntryName(view.getId()));
        Intent intent = new Intent(this, DatabaseActivity.class);

        int id = view.getId();
        if (id == R.id.tvQuiz) {
            intent = new Intent(this, QuizActivity.class);
        } else if (id == R.id.tvDatabase) {
            intent = new Intent(this, DatabaseActivity.class);
        } else if (id == R.id.tvAddEntry) {
            intent = new Intent(this, AddEntryActivity.class);
        }
        startActivity(intent);
    }
}
