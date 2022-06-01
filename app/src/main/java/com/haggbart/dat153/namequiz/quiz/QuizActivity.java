package com.haggbart.dat153.namequiz.quiz;

import static com.haggbart.dat153.namequiz.quiz.QuizValues.ANSWERS_TOTAL;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import com.haggbart.dat153.namequiz.R;
import com.haggbart.dat153.namequiz.data.AppDatabase;
import com.haggbart.dat153.namequiz.person.PersonDao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class QuizActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "QuizActivity";

    private ImageView ivImage;
    private final Button[] btnAnswers = new Button[ANSWERS_TOTAL];
    private TextView tvStatsPercent;
    private TextView tvStats;

    private QuizState state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        state = new ViewModelProvider(this).get(QuizState.class);

        setContentView(R.layout.activity_quiz);

        ivImage = findViewById(R.id.ivImage);
        btnAnswers[0] = findViewById(R.id.btnAnswer0);
        btnAnswers[1] = findViewById(R.id.btnAnswer1);
        btnAnswers[2] = findViewById(R.id.btnAnswer2);
        tvStats = findViewById(R.id.tvStats);
        tvStatsPercent = findViewById(R.id.tvStatsPercent);


        for (var btn : btnAnswers) {
            btn.setOnClickListener(this);
        }

        Log.d(TAG, "onCreate: " + state.options);

        if (state.currentPerson == null) {
            PersonDao dao = AppDatabase.getINSTANCE(getApplicationContext()).personDao();
            state.shuffledPeople = new ArrayList<>(dao.getAllPeople());
            Collections.shuffle(state.shuffledPeople);
            state.nextPerson();
        } else {
            updateStats();
        }
        setPerson();
    }

    private void setPerson() {
        ivImage.setImageURI(state.currentPerson.getImageUri());

        for (int i = 0; i < ANSWERS_TOTAL; i++) {
            btnAnswers[i].setText(state.options.get(i));
        }
    }

    private void updateStats() {
        tvStats.setText(String.format(Locale.ROOT, "Score: %d/%d", state.points, state.attempts));
        tvStatsPercent.setText(String.format(Locale.ROOT, "%.0f%%", (state.points / (float)state.attempts * 100)));
    }

    @Override
    public void onClick(View view) {
        state.answer(Integer.parseInt((String) view.getTag()));
        updateStats();

        view.setBackgroundColor(ContextCompat.getColor(this, R.color.red));
        btnAnswers[state.correctAnswer].setBackgroundColor(ContextCompat.getColor(this, R.color.green));
        new CountDownTimer(1000, 1000) {

            public void onTick(long millisUntilFinished) { }

            public void onFinish() {
                for (var btn : btnAnswers) {
                    btn.setBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.design_default_color_primary));
                }

                state.nextPerson();
                setPerson();
            }
        }.start();
    }
}
