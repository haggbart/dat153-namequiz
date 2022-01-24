package com.haggbart.dat153.namequiz.database;


import android.util.Log;

import com.github.javafaker.Faker;
import com.haggbart.dat153.namequiz.model.HvlPerson;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;

public class Database {

    private static final String TAG = "Database";

    private static Database instance = null;

    private static long idCounter;

    private final Faker faker = new Faker(new Locale("nb-NO"));
    private final HashMap<Long, HvlPerson> people = new HashMap<>();

    private Database() {
        for (int i = 0; i < 40; i++) {
            addPerson(new HvlPerson(faker.name().firstName(), faker.name().lastName()));
        }

        Log.d(TAG, "Database: data" + people.values());
    }

    public static Database getInstance() {
        if (instance == null) {
            synchronized (Database.class) {
                instance = new Database();
            }
        }
        return instance;
    }

    public synchronized HvlPerson addPerson(HvlPerson person) {
        person.setId(++idCounter);
        people.put(person.getId(), person);
        Log.d(TAG, "addPerson: added " + person);
        return person;
    }

    public Collection<HvlPerson> getPeople() {
        return people.values();
    }
}
