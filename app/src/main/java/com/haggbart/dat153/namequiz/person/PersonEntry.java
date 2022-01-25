package com.haggbart.dat153.namequiz.person;


import java.util.Objects;

public class PersonEntry {

    private String forename;
    private String surname;

    public PersonEntry(String forename, String surname) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonEntry that = (PersonEntry) o;
        return forename.equals(that.forename) && surname.equals(that.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(forename, surname);
    }
}
