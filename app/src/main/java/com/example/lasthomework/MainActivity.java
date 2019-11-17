package com.example.lasthomework;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editTextBirdName, editTextZipcode, editTextPersonName;

    Button buttonSubmit, buttonSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        editTextBirdName = findViewById(R.id.editTextBirdName);
        editTextZipcode = findViewById(R.id.editTextZipcode);
        editTextPersonName = findViewById(R.id.editTextPersonName);

        buttonSubmit = findViewById(R.id.buttonSubmit);
        buttonSearch = findViewById(R.id.buttonSearch);

        buttonSubmit.setOnClickListener(this);
        buttonSearch.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Birds");

        if(buttonSubmit == view) {
            String birdName = editTextBirdName.getText().toString();
            int zipcode = Integer.parseInt(editTextZipcode.getText().toString());
            String personName = editTextPersonName.getText().toString();
            Bird myBird = new Bird(birdName, zipcode, personName);
            myRef.push().setValue(myBird);

        }


        if (buttonSearch == view) {
            Intent searchIntent = new Intent(this, SearchActivity.class);
            startActivity(searchIntent);
        }

    }
}

