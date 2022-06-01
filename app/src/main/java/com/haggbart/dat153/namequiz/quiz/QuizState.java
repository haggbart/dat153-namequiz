package com.haggbart.dat153.namequiz.quiz;

import static com.haggbart.dat153.namequiz.quiz.QuizValues.ANSWERS_TOTAL;

import androidx.lifecycle.ViewModel;

import com.haggbart.dat153.namequiz.person.PersonEntry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class QuizState extends ViewModel {

    private final Random random = new Random();

    protected int nextCount;
    protected List<PersonEntry> shuffledPeople;
    protected List<String> options;
    protected PersonEntry currentPerson;
    protected int correctAnswer;

    // Stats
    protected int points;
    protected int attempts;

    public void answer(int guess) {
        if (correctAnswer == guess) {
            points++;
        }
        attempts++;
    }

    public void nextPerson() {
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
    }

    private String randomName() {
        String randomName;
        do {
            randomName = shuffledPeople.get(random.nextInt(shuffledPeople.size())).getFullName();
        } while (randomName.equals(currentPerson.getFullName()) || options.contains(randomName));
        return randomName;
    }
}
