package com.haggbart.dat153.namequiz;

import static com.haggbart.dat153.namequiz.helper.ImageHelper.getUri;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.haggbart.dat153.namequiz.database.People;
import com.haggbart.dat153.namequiz.person.PersonEntry;

public class AddEntryActivity extends AppCompatActivity {

    private static final String TAG = "AddEntryActivity";

    private static final People database = People.getInstance();

    private EditText forename;
    private EditText surname;
    private Uri imageUri;

    private ImageView ivImage;
    private ActivityResultLauncher<Intent> chooseImageResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_entry);

        forename = findViewById(R.id.etForename);
        surname = findViewById(R.id.etSurname);

        ivImage = findViewById(R.id.ivImage);


        Button btnChoose = findViewById(R.id.btnChoose);
        Button btnAddEntry = findViewById(R.id.btnAddEntry);
        btnChoose.setOnClickListener(view -> chooseImage());
        btnAddEntry.setOnClickListener(view -> addEntry());


        /*
         * Creates an ActivityResultLauncher<Intent> that set URI for selected image on the imageView.
         * This is the "new" way of doing since onActivityResult was depricated.
         *
         * Also flags the view with URI permission so it can be opened later.
         */
        chooseImageResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                    if (result.getResultCode() != Activity.RESULT_OK || result.getData() == null) return;
                    imageUri = result.getData().getData();
                    getContentResolver().takePersistableUriPermission(imageUri, Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    Log.d(TAG, "onActivityResult: uri: " + imageUri);
                    Log.d(TAG, "onActivityResult: result: " + result);
                    ivImage.setImageURI(imageUri);
                }
        );
    }

    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        chooseImageResult.launch(intent);
    }


    private void addEntry() {
        PersonEntry person = new PersonEntry(forename.getText().toString(), surname.getText().toString(), imageUri);

        Log.d(TAG, "onClick: imageUri: " + imageUri);

        if (imageUri == null || !database.add(person)) {
            Toast.makeText(this, "Please input all the required fields", Toast.LENGTH_SHORT).show();
            return;
        }

        database.isDirty = true;
        ivImage.setImageURI(getUri(R.drawable.insert_photo));
        forename.setText("");
        surname.setText("");
        Toast.makeText(this, String.format("%s %s added to the database", person.getForename(), person.getSurname()), Toast.LENGTH_SHORT).show();
    }
}
