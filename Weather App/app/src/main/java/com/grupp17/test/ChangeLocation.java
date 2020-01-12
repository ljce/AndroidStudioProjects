package com.grupp17.test;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChangeLocation extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_location);

            //Intent intent = getIntent();
            //String value = intent.getStringExtra(); //if it's a string you stored.


        final Spinner spinner = findViewById(R.id.spinner);
        final Button setLocationButton = findViewById(R.id.setLocationButton);

        spinner.setOnItemSelectedListener(this);

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

/*String a =spinner.getSelectedItem().toString();
                System.out.println("bajs");
                System.out.println(a);
                myIntent.putExtra("locationValue", a);*/

        /*// Initializing a String Array
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
        });*/


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        //Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();

    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }



    @Override
    protected void onPause(){
        super.onPause();

    }

}


