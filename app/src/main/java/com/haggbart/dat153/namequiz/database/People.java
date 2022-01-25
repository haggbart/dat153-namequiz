package com.haggbart.dat153.namequiz.database;


import android.util.Log;

import com.github.javafaker.Faker;
import com.haggbart.dat153.namequiz.person.PersonEntry;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class People {

    private static final String TAG = "Database";

    private static People instance = null;

    private final Faker faker = new Faker(new Locale("nb-NO"));
    private final List<PersonEntry> people = new ArrayList<>();

    private People() {
        for (int i = 0; i < 4; i++) {
            people.add(new PersonEntry(faker.name().firstName(), faker.name().lastName()));
        }
        Log.d(TAG, "Database: data: " + people);
    }

    public static People getInstance() {
        if (instance == null) {
            synchronized (People.class) {
                instance = new People();
            }
        }
        return instance;
    }

    public List<PersonEntry> getPeople() {
        return people;
    }

    public boolean add(PersonEntry person) {
        if (person.getForename().isEmpty() || person.getSurname().isEmpty()) return false;
        return people.add(person);
    }

    public PersonEntry remove(int position) {
        return people.remove(position);
    }

    public PersonEntry get(int position) {
        return people.get(position);
    }

    public boolean isDirty;
}
