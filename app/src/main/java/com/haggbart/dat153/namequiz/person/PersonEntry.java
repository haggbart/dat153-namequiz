package com.haggbart.dat153.namequiz.person;


import androidx.annotation.NonNull;

import java.util.Objects;

public class PersonEntry {

    private Long id;
    private String forename;
    private String surname;

    public PersonEntry(String forename, String surname) {
        this.forename = forename;
        this.surname = surname;
    }

    public PersonEntry(Long id, String forename, String surname) {
        this.id = id;
        this.forename = forename;
        this.surname = surname;
    }

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonEntry personEntry = (PersonEntry) o;
        return Objects.equals(id, personEntry.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @NonNull
    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", forename='" + forename + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
