package com.haggbart.dat153.namequiz.database;


import com.haggbart.dat153.namequiz.person.PersonEntry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Threadsafe singleton class, the "database"
 */
public class People {

    private static final String TAG = "Database";

    private static People instance = null;

    private final List<PersonEntry> people = new ArrayList<>();

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

    public void add(PersonEntry... people) {
        Arrays.stream(people).forEach(this::add);
    }

    public PersonEntry remove(int position) {
        return people.remove(position);
    }

    public PersonEntry get(int position) {
        return people.get(position);
    }

    public boolean isDirty;
}
