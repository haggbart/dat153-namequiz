package com.haggbart.dat153.namequiz.person;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PersonDao {

    @Query("SELECT * FROM PersonEntry")
    List<PersonEntry> getAllPeople();

    @Insert
    void insertPerson(PersonEntry... people);

    @Delete
    void delete(PersonEntry person);
}
