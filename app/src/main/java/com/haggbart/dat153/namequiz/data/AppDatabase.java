package com.haggbart.dat153.namequiz.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.haggbart.dat153.namequiz.helper.Converters;
import com.haggbart.dat153.namequiz.person.PersonDao;
import com.haggbart.dat153.namequiz.person.PersonEntry;

@Database(entities = {PersonEntry.class}, version = 1, exportSchema = false)

@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract PersonDao personDao();

    private static AppDatabase INSTANCE;

    // TODO disallow main thread queries
    // Double-checked locking for performance
    public static AppDatabase getINSTANCE(Context applicationContext) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(applicationContext,
                            AppDatabase.class, "database").allowMainThreadQueries().build();
                }
            }
        }
        return INSTANCE;
    }
}
