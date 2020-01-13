package com.grupp17.test;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    public static TextView data;

    private TextView dateTimeDisplay;
    private TextView locationDisplay;
    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private String date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        data = findViewById(R.id.Fetchdata);
        data.setMovementMethod(new ScrollingMovementMethod());
        //changeLocationButton = (Button) findViewById(R.id.changeLocationButton);

        ImageView image = new ImageView(this);
        // image = findViewById(int);


        dateTimeDisplay = findViewById(R.id.text_date_display);

        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("EEE, MMM d, yyyy");
        date = dateFormat.format(calendar.getTime());
        dateTimeDisplay.setText(date);


        //om man trycker på knappen så startar den ChangeLocation
        final Button ChangeLocationButton = findViewById(R.id.changeLocationButton);
        ChangeLocationButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this, ChangeLocationActivity.class);
                myIntent.setAction("com.test.app.ChangeLocation");
                startActivity(myIntent);


                WindowManager.LayoutParams params = getWindow().getAttributes();
                getWindow().setAttributes(params);

            }
        });



        Intent myIntent = getIntent();
        String locationValue = myIntent.getStringExtra("locationValue");
        locationDisplay = findViewById(R.id.locationDisplay);
        locationDisplay.setText(locationValue);

        if (locationValue != null) {
            new FetchData().execute(locationValue);
            locationDisplay.setText(locationValue);
        } else {
            new FetchData().execute("Gothenburg");
            locationDisplay.setText("Gothenburg");
        }




    }
}
