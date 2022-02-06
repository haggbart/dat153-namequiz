package com.haggbart.dat153.namequiz;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.haggbart.dat153.namequiz.database.People;
import com.haggbart.dat153.namequiz.person.PersonEntry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class QuizActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "QuizActivity";

    private final People database = People.getInstance();

    private final Random random = new Random();

    private final int ANSWERS_TOTAL = 3;

    private ImageView ivImage;
    private Button btnAnswer0;
    private Button btnAnswer1;
    private Button btnAnswer2;

    private PersonEntry currentPerson;
    private int nextCount;

    private List<PersonEntry> shuffledPeople;
    private List<String> options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        ivImage = findViewById(R.id.ivImage);
        btnAnswer0 = findViewById(R.id.btnAnswer0);
        btnAnswer1 = findViewById(R.id.btnAnswer1);
        btnAnswer2 = findViewById(R.id.btnAnswer2);



        shuffledPeople = new ArrayList<>(database.getPeople());
        Collections.shuffle(shuffledPeople);

        btnAnswer0.setOnClickListener(this);
        btnAnswer1.setOnClickListener(this);
        btnAnswer2.setOnClickListener(this);

        nextPerson();
    }

    private void nextPerson() {
        currentPerson = shuffledPeople.get(nextCount++);
        if (nextCount == shuffledPeople.size()) {
            Collections.shuffle(shuffledPeople);
            nextCount = 0;
        }

        int correctAnswer = random.nextInt(ANSWERS_TOTAL);

        options = new ArrayList<>(ANSWERS_TOTAL);
        for (int i = 0; i < ANSWERS_TOTAL; i++) {
            options.add(i == correctAnswer ? currentPerson.getFullName() : randomName());
        }

        ivImage.setImageURI(currentPerson.getImageUri());
        btnAnswer0.setText(options.get(0));
        btnAnswer1.setText(options.get(1));
        btnAnswer2.setText(options.get(2));
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
        nextPerson();
    }
}
