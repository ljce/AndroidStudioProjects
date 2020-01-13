package com.grupp17.test;

import android.os.AsyncTask;

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

public class FetchData extends AsyncTask<String,Void,Void> {
    String forecast = "";
    String data = "";
    String weatherDescription;
    String temperature;
    String timeAndDateString;
    String timeString;
    URL url;


    @Override
    protected Void doInBackground(String... locationValue) {

        try {
            if (locationValue[0].equals("Malmö")){
                //Malmö
                url = new URL("https://opendata-download-metfcst.smhi.se/api/category/pmp3g/version/2/geotype/point/lon/13.00073/lat/55.60587/data.json");

            }

            else if(locationValue[0].equals("Stockholm")){
                //Stockholm
                url = new URL("https://opendata-download-metfcst.smhi.se/api/category/pmp3g/version/2/geotype/point/lon/18.0649/lat/59.33258/data.json");

            }

            else if (locationValue[0].equals("Gothenburg")){
                //Göteborg
                url = new URL("https://opendata-download-metfcst.smhi.se/api/category/pmp3g/version/2/geotype/point/lon/11.974560/lat/57.708870/data.json");

            }


            //URL url = new URL(" https://opendata-download-metfcst.smhi.se/api/category/pmp3g/version/2/geotype/point/lon/11/lat/57/data.json");
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
                i++;

            }
            while (correctDate.equals(timeAndDateString.substring(0,10)));

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

    }
}