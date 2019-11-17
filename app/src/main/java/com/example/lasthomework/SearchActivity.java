package com.example.lasthomework;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editTextSearchZipcode;
    Button buttonSearchBird, buttonReport;
    TextView textViewBirdName, textViewPersonName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        editTextSearchZipcode = findViewById(R.id.editTextSearchZipcode);
        buttonSearchBird = findViewById(R.id.buttonSearchBird);
        buttonReport = findViewById(R.id.buttonReport);
        textViewBirdName = findViewById(R.id.textViewBirdName);
        textViewPersonName = findViewById(R.id.textViewPersonName);

        buttonReport.setOnClickListener(this);
        buttonSearchBird.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Birds");

        if (view == buttonSearchBird) {
            int findZip = Integer.parseInt(editTextSearchZipcode.getText().toString());

            myRef.orderByChild("zipcode").equalTo(findZip).addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    String findKey = dataSnapshot.getKey();
                    Bird foundBird = dataSnapshot.getValue(Bird.class);
                    String findName = foundBird.birdname;
                    String findPerson = foundBird.personname;

                    textViewBirdName.setText(findName);
                    textViewPersonName.setText(findPerson);
                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

        if (view == buttonReport) {
            Intent reportIntent = new Intent(this, MainActivity.class);
            startActivity(reportIntent);

        }

    }
}

