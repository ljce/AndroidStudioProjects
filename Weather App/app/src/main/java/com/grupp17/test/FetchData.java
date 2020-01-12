package com.grupp17.test;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static com.grupp17.test.WeatherDesc.getWeatherDesc;
import static java.lang.String.format;

public class FetchData extends AsyncTask<Void,Void,Void> {
    String data ="";
    String dataParsed = "";
    String output = "";
    String weatherValue;
    String weatherDescription;
    String temperature;
    String timeAndDateString;
    String timeString;

    String forecast = "";

    String locationValue;


    @Override
    protected Void doInBackground(Void... voids) {

        try {

           /* if (text.equals("Göteborg"){
                //Göteborg
                url = new URL("https://opendata-download-metfcst.smhi.se/api/category/pmp3g/version/2/geotype/point/lon/11.974560/lat/57.708870/data.json");
            }

            else if(locationValue == "Stockholm"){
                //Stockholm
                url = new URL("https://opendata-download-metfcst.smhi.se/api/category/pmp3g/version/2/geotype/point/lon/18.0649/lat/59.33258/data.json");
            }

            else{
                //Jokkmokk
                url = new URL("https://opendata-download-metfcst.smhi.se/api/category/pmp3g/version/2/geotype/point/lon/19.82292/lat/66.60696/data.json");
            }*/
            //en if-sats som sätter olika värden till url.
            //if ( locationValue.isSetTo("Göteborg") {
              //  url = new URL("https://opendata-download-metfcst.smhi.se/api/category/pmp3g/version/2/geotype/point/lon/11/lat/57/data.json");
            //}

            //hämtar URL från SMHI med koodinater satta till Göteborg.

            URL url = new URL(" https://opendata-download-metfcst.smhi.se/api/category/pmp3g/version/2/geotype/point/lon/11/lat/57/data.json");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while (line != null) {
                line = bufferedReader.readLine();
                data = data + line;
            }


            JSONObject jsonData = new JSONObject(data);
            String weatherValue;

            String[] weatherDesc = getWeatherDesc();

            //forecast
            String correctDate = jsonData.getJSONArray("timeSeries").getJSONObject(0).get("validTime").toString();
            correctDate = correctDate.substring(0, 10);
            System.out.println(correctDate);

            int i = 0;
            do {
                weatherValue = jsonData.getJSONArray("timeSeries").getJSONObject(i).getJSONArray("parameters").getJSONObject(18).getJSONArray("values").getString(0);
                weatherDescription = weatherDesc[Integer.parseInt(weatherValue)];

                temperature = jsonData.getJSONArray("timeSeries").getJSONObject(i).getJSONArray("parameters").getJSONObject(11).getJSONArray("values").getString(0);

                timeAndDateString = jsonData.getJSONArray("timeSeries").getJSONObject(i).get("validTime").toString();

                timeString = timeAndDateString.substring(11,16);

                if (correctDate.equals(timeAndDateString.substring(0,10))) {
                    forecast = forecast +" At " + timeString + " :\n " + weatherDescription + " and " + temperature + "°C " + "\n\n";

                }
                //System.out.println(correctDate.equals(timeString.substring(0,10)));
                i++;
                //varv++;
            }
            while (correctDate.equals(timeAndDateString.substring(0,10)));
            //System.out.println(correctDate);
            System.out.println(forecast);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }






    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        MainActivity.data.setText(forecast);



        }}