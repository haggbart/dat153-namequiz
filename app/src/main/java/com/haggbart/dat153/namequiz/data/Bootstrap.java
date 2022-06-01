package com.haggbart.dat153.namequiz.data;

import static com.haggbart.dat153.namequiz.helper.ImageHelper.getUri;

import android.util.Log;

import com.github.javafaker.Faker;
import com.haggbart.dat153.namequiz.R;
import com.haggbart.dat153.namequiz.person.PersonDao;
import com.haggbart.dat153.namequiz.person.PersonEntry;

import java.util.Locale;

/**
 * Initialize example data for the database
 */
public class Bootstrap {

    private static final String TAG = "Bootstrap";

    public static final PersonEntry[] DEFAULT_PEOPLE = new PersonEntry[]{
            new PersonEntry("Eli", "Nummedal", getUri(R.drawable.eli_nummedal)),
            new PersonEntry("Finn", "Arne", getUri(R.drawable.finn_arne)),
            new PersonEntry("Per", "Otto", getUri(R.drawable.per_otto)),
            new PersonEntry("Sven-Olai", "Høyland", getUri(R.drawable.sven_olai)),
            new PersonEntry("Tosin", "Oyetoyan", getUri(R.drawable.tosin)),
            new PersonEntry("Lars-Petter", "Helland", getUri(R.drawable.lars_petter)),
            new PersonEntry("Lars Michael", "Kristensen", getUri(R.drawable.lars_michael)),
            new PersonEntry("Atle", "Geitung", getUri(R.drawable.atle_geitung)),
            new PersonEntry("Harald", "Solheim", getUri(R.drawable.harald_solheim)),
            new PersonEntry("Bjarte", "Kileng", getUri(R.drawable.bjarte_kileng)),
            new PersonEntry("Roger", "Karlsen", getUri(R.drawable.roger_karlsen)),
            new PersonEntry("Volker", "Stolz", getUri(R.drawable.volker_stolz)),
            new PersonEntry("Mads Henrik", "Sørbø", getUri(R.drawable.mads_henrik))
    };

    private final Faker faker = new Faker(new Locale("nb-NO"));

    public void initialize(PersonDao dao) {

        if (dao.getAllPeople().size() > 0) return;

        dao.insertPerson(DEFAULT_PEOPLE);

//        for (int i = 0; i < 100_000; i++) {
//            database.add(new PersonEntry(faker.name().firstName(), faker.name().lastName()));
//        }
        Log.d(TAG, "Bootstrap initialized");
    }
}
