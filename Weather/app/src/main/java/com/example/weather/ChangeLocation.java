package com.example.weather;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChangeLocation extends AppCompatActivity {

    String locationValue;

    //nånting som har att göra med array-listorna
    ListView listView;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_location);

        Intent intent = getIntent();
        //String value = intent.getStringExtra(); //if it's a string you stored.

        //hämta min plats med geolocation

        // välj från en rullista

        final Button setLocationButton = findViewById(R.id.setLocationButton);
        setLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //välj stad som default
                Intent myIntent = new Intent(ChangeLocation.this, MainActivity.class);
                //myIntent.putExtra("key", null); //Optional parameters
                myIntent.setAction(".MainActivity");
                startActivity(myIntent);
            }
        });

        // Initializing a String Array
        String[] locations = findViewById(R.array.locations_array);

        final Spinner spinner = findViewById(R.id.spinner);

        final List<String> locationsList = new ArrayList<>(Arrays.asList(locations));

        // Initializing an ArrayAdapter
        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                this,android.R.layout.simple_spinner_item,locationsList);

        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(spinnerArrayAdapter);

        setLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Select the Spinner element at index position 2
                // It will select third element/item from Spinner
                spinner.setSelection(2);
            }
        });


    }




    @Override
    protected void onPause(){
        super.onPause();

    }

}
