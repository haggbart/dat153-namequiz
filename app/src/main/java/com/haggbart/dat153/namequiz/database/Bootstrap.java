package com.haggbart.dat153.namequiz.database;

import static com.haggbart.dat153.namequiz.helper.ImageHelper.getUri;

import android.util.Log;

import com.haggbart.dat153.namequiz.R;
import com.haggbart.dat153.namequiz.person.PersonEntry;

public class Bootstrap {


    private static final String TAG = "Bootstrap";
    private final People database = People.getInstance();

    public void initialize() {
        
        database.add(
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
                new PersonEntry("Volker", "Stolz", getUri(R.drawable.volker_stolz))
        );
        Log.d(TAG, "initialize: successful");
    }
}
