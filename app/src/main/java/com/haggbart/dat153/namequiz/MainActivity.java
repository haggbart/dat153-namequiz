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
import com.haggbart.dat153.namequiz.quiz.QuizActivity;


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

        // Avoid switch case statements:
        // Resource IDs will be non-final by default in Android Gradle Plugin version 8.0
        //
        // In this case, no performance is lost or gained by using switch case statements
        //
        // The type of performance that a "switch" statement may give,
        // has to be taken into consideration for more specific edge cases
        // which may require high efficiency, such as render loops, or algorithms
        // with efficiency in focus.
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
