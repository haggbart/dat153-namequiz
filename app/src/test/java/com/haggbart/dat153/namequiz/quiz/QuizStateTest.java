package com.haggbart.dat153.namequiz.quiz;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import androidx.collection.ArraySet;

import com.github.javafaker.Faker;
import com.haggbart.dat153.namequiz.person.PersonEntry;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Locale;

public class QuizStateTest {

    private final Faker faker = new Faker(new Locale("nb-NO"));

    private QuizState state;

    @Before
    public void setUp() throws Exception {
        state = new QuizState();
        state.shuffledPeople = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            state.shuffledPeople.add( new PersonEntry(faker.name().firstName(), faker.name().lastName(), null));
        }
    }

    @Test
    public void nextPersonPopulateOptions() {
        assertNull(state.options);
        state.nextPerson();
        assertNotNull(state.options);
    }

    @Test
    public void nextPersonPopulateCorrectSize() {
        state.nextPerson();
        assertEquals(QuizValues.ANSWERS_TOTAL, state.options.size());
    }

    @Test
    public void nextPersonPopulateUniqueOptions() {
        state.nextPerson();
        var set = new ArraySet<String>(QuizValues.ANSWERS_TOTAL);
        set.addAll(state.options);
        assertEquals(QuizValues.ANSWERS_TOTAL, set.size());
        assertEquals(QuizValues.ANSWERS_TOTAL, state.options.size());
    }

    @Test
    public void correctAnswerIncreasePointsAndAttempts() {
        state.correctAnswer = 2;
        assertEquals(0, state.points);
        assertEquals(0, state.attempts);
        state.answer(2);
        assertEquals(1, state.points);
        assertEquals(1, state.attempts);
    }

    @Test
    public void wrongAnswerAttempts() {
        state.correctAnswer = 2;
        assertEquals(0, state.points);
        assertEquals(0, state.attempts);
        state.answer(1);
        assertEquals(0, state.points);
        assertEquals(1, state.attempts);
    }
}
