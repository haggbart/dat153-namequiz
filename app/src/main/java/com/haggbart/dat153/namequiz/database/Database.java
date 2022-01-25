package com.haggbart.dat153.namequiz.database;


import android.util.Log;

import com.github.javafaker.Faker;
import com.haggbart.dat153.namequiz.person.PersonEntry;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Database {

    private static final String TAG = "Database";

    private static Database instance = null;

    private static long idCounter;

    private final Faker faker = new Faker(new Locale("nb-NO"));
    private final Map<Long, PersonEntry> people = new HashMap<>();

    private Database() {
        for (int i = 0; i < 4; i++) {
            addPerson(new PersonEntry(faker.name().firstName(), faker.name().lastName()));
        }
        Log.d(TAG, "Database: data" + people);
    }

    public static Database getInstance() {
        if (instance == null) {
            synchronized (Database.class) {
                instance = new Database();
            }
        }
        return instance;
    }

    public synchronized PersonEntry addPerson(PersonEntry person) {
        if (person.getForename().isEmpty() || person.getSurname().isEmpty() ) return null;
        PersonEntry newPerson = new PersonEntry(++idCounter, person.getForename(), person.getSurname());
        people.put(newPerson.getId(), newPerson);
        Log.d(TAG, "addPerson: added " + newPerson);
        return newPerson;
    }

    public List<PersonEntry> getPeople() {
        return new ArrayList<>(people.values());
    }
}
