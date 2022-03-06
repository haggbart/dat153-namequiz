package com.haggbart.dat153.namequiz.person;


import android.net.Uri;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity
public class PersonEntry {

    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "forname")
    private String forename;
    @ColumnInfo(name = "surname")
    private String surname;

    @ColumnInfo(name = "imageUri")
    private Uri imageUri;

    public PersonEntry(String forename, String surname, Uri imageUri) {
        this.forename = forename;
        this.surname = surname;
        this.imageUri = imageUri;
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

    public Uri getImageUri() {
        return imageUri;
    }

    public void setImageUri(Uri imageUri) {
        this.imageUri = imageUri;
    }

    public String getFullName() {
        return forename + " " + surname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PersonEntry that = (PersonEntry) o;

        return uid == that.uid;
    }

    @Override
    public int hashCode() {
        return uid;
    }

    @Override
    public String toString() {
        return "PersonEntry{" +
                "uid=" + uid +
                ", forename='" + forename + '\'' +
                ", surname='" + surname + '\'' +
                ", imageUri=" + imageUri +
                '}';
    }
}
