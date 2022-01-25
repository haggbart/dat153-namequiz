package com.haggbart.dat153.namequiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.haggbart.dat153.namequiz.database.Database;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnQuiz = findViewById(R.id.btnQuiz);
        Button btnDatabase = findViewById(R.id.btnDatabase);

        btnQuiz.setOnClickListener(this);
        btnDatabase.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        Log.d(TAG, "onClick: Button clicked: " + view.getResources().getResourceEntryName(view.getId()));
        Intent intent = new Intent(this, DatabaseActivity.class);

        startActivity(intent);
    }
}
