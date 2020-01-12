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


        //om trycker på knappen så startar den Change Location activity
        final Button ChangeLocationButton = findViewById(R.id.changeLocationButton);
        ChangeLocationButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //add your code here..
                Intent myIntent = new Intent(MainActivity.this, ChangeLocation.class);
                //myIntent.putExtra("key", null); //Optional parameters
                myIntent.setAction("com.test.app.ChangeLocation");
                startActivity(myIntent);


                WindowManager.LayoutParams params = getWindow().getAttributes();
                getWindow().setAttributes(params);

            }
        });



        //ImageSetter imageset = new ImageSetter();
        Intent myIntent = getIntent();
        String locationValue = myIntent.getStringExtra("locationValue");

        System.out.println(locationValue);
        if (locationValue != null) {
            new FetchData().execute(locationValue);
        } else {
            new FetchData().execute("");
        }
        //imageset.execute();



    }
}


// https://api.myjson.com/bins/ktxhk   FETCH MEEEE