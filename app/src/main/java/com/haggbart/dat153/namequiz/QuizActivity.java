package com.haggbart.dat153.namequiz;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.haggbart.dat153.namequiz.data.AppDatabase;
import com.haggbart.dat153.namequiz.person.PersonDao;
import com.haggbart.dat153.namequiz.person.PersonEntry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class QuizActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "QuizActivity";

    private PersonDao dao;

    private final Random random = new Random();

    private final int ANSWERS_TOTAL = 3;

    private int nextCount;
    private List<PersonEntry> shuffledPeople;
    private List<String> options;
    private PersonEntry currentPerson;
    private int correctAnswer;


    // Views
    private ImageView ivImage;
    private final Button[] btnAnswers = new Button[ANSWERS_TOTAL];
    private TextView tvStatsPercent;
    private TextView tvStats;

    // Stats
    private int points;
    private int attempts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        ivImage = findViewById(R.id.ivImage);
        btnAnswers[0] = findViewById(R.id.btnAnswer0);
        btnAnswers[1] = findViewById(R.id.btnAnswer1);
        btnAnswers[2] = findViewById(R.id.btnAnswer2);
        tvStats = findViewById(R.id.tvStats);
        tvStatsPercent = findViewById(R.id.tvStatsPercent);

        dao = AppDatabase.getINSTANCE(getApplicationContext()).personDao();
        shuffledPeople = new ArrayList<>(dao.getAllPeople());
        Collections.shuffle(shuffledPeople);

        for (var btn : btnAnswers) {
            btn.setOnClickListener(this);
        }

        nextPerson();
    }

    private void nextPerson() {
        currentPerson = shuffledPeople.get(nextCount++);
        if (nextCount == shuffledPeople.size()) {
            Collections.shuffle(shuffledPeople);
            nextCount = 0;
        }

        correctAnswer = random.nextInt(ANSWERS_TOTAL);

        options = new ArrayList<>(ANSWERS_TOTAL);
        for (int i = 0; i < ANSWERS_TOTAL; i++) {
            options.add(i == correctAnswer ? currentPerson.getFullName() : randomName());
        }

        ivImage.setImageURI(currentPerson.getImageUri());

        for (int i = 0; i < ANSWERS_TOTAL; i++) {
            btnAnswers[i].setText(options.get(i));
        }
    }

    private String randomName() {
        String randomName;
        do {
            randomName = shuffledPeople.get(random.nextInt(shuffledPeople.size())).getFullName();
        } while (randomName.equals(currentPerson.getFullName()) || options.contains(randomName));
        return randomName;
    }

    @Override
    public void onClick(View view) {
        if (btnAnswers[correctAnswer].equals(view)) {
            points++;
        }
        attempts++;
        
        tvStats.setText(String.format(Locale.ROOT, "Score: %d/%d", points, attempts));
        tvStatsPercent.setText(String.format(Locale.ROOT, "%.0f%%", (points / (float)attempts * 100)));

        view.setBackgroundColor(ContextCompat.getColor(this, R.color.red));
        btnAnswers[correctAnswer].setBackgroundColor(ContextCompat.getColor(this, R.color.green));
        new CountDownTimer(1000, 1000) {

            public void onTick(long millisUntilFinished) { }

            public void onFinish() {
                for (var btn : btnAnswers) {
                    btn.setBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.design_default_color_primary));
                }
                nextPerson();
            }
        }.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: called");
    }
}
