package com.haggbart.dat153.namequiz.model;


import androidx.annotation.NonNull;

public class HvlPerson {

    private static long idCounter;

    private Long id;
    private String forename;
    private String surname;

    public HvlPerson(String forename, String surname) {
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
