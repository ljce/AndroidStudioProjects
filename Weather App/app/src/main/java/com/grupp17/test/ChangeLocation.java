package com.grupp17.test;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ChangeLocation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_location);

        final Spinner spinner = findViewById(R.id.spinner);
        final Button setLocationButton = findViewById(R.id.setLocationButton);

        List<String> categories = new ArrayList<>();
        categories.add("Gothenburg");
        categories.add("Stockholm");
        categories.add("Malmö");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
        spinner.setAdapter(dataAdapter);

        setLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent myIntent = new Intent(ChangeLocation.this, MainActivity.class);
                myIntent.putExtra("locationValue",String.valueOf(spinner.getSelectedItem()));
                myIntent.setAction(".MainActivity");
                startActivity(myIntent);
            }
        });

    }

    @Override
    protected void onPause(){
        super.onPause();

    }

}


