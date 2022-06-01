package com.haggbart.dat153.namequiz;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withSubstring;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.haggbart.dat153.namequiz.quiz.QuizActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class QuizActivityTest {

    @Rule
    public ActivityScenarioRule<QuizActivity> activityRule = new ActivityScenarioRule<>(QuizActivity.class);

    @Test
    public void imageIsDisplayed() {
        onView(withId(R.id.ivImage)).check(matches(isDisplayed()));
    }

    @Test
    public void scoreIsDisplayedWithOneAttemptAfterGuess() {
        onView(withId(R.id.btnAnswer0)).perform(click());
        onView(withId(R.id.tvStats)).check(matches(isDisplayed()));
        onView(withId(R.id.tvStats)).check(matches(withSubstring("1")));
    }
}
