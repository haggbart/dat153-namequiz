package com.haggbart.dat153.namequiz.person;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.haggbart.dat153.namequiz.data.AppDatabase;
import com.haggbart.dat153.namequiz.data.Bootstrap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class PersonDaoTest {

    private PersonDao dao;

    @Before
    public void setUp() throws Exception {
        Context context = ApplicationProvider.getApplicationContext();
        AppDatabase database = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        dao = database.personDao();
        new Bootstrap().initialize(dao);
    }

    @Test
    public void boostrapInitialized() {
        assertTrue(dao.getAllPeople().size() > 0);
    }

    @Test
    public void insertPerson() {
        int before = dao.getAllPeople().size();
        dao.insertPerson(new PersonEntry("Test", "Testsson", dao.getAllPeople().get(0).getImageUri()));
        assertEquals(dao.getAllPeople().size(), before + 1);
    }

    @Test
    public void insertMultiplePeople() {
        int before = dao.getAllPeople().size();
        dao.insertPerson(
                new PersonEntry("Test", "Testsson", dao.getAllPeople().get(0).getImageUri()),
                new PersonEntry("Test2", "Testsson2", dao.getAllPeople().get(1).getImageUri()));
        assertEquals(dao.getAllPeople().size(), before + 2);
    }

    @Test
    public void delete() {
        int before = dao.getAllPeople().size();
        dao.delete(dao.getAllPeople().get(0));
        assertEquals(dao.getAllPeople().size(), before - 1);
    }
}
