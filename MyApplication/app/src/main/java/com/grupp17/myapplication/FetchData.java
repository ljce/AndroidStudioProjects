package com.grupp17.myapplication;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.grupp17.myapplication.Weather.getWeatherDesc;

public class FetchData extends AsyncTask<Void, Void, Void> {
    String data = "";
    String timeAndDateString;
    String timeString;
    String tempString;
    String weatherDescription;
    String forecast = "";
    String stad = "g";

    URL url;
    // SÄTT IN TYP OM MAN KLICKAT PÅ EN VISS KNAPP !!
    @Override
    protected Void doInBackground(Void... voids) {
        try {
            if (stad == "g"){
                url = new URL("https://opendata-download-metfcst.smhi.se/api/category/pmp3g/version/2/geotype/point/lon/11.974560/lat/57.708870/data.json");
            }

            else if(stad == "s"){
                url = new URL("https://opendata-download-metfcst.smhi.se/api/category/pmp3g/version/2/geotype/point/lon/18.0649/lat/59.33258/data.json");
                }

            else{
                url = new URL("https://opendata-download-metfcst.smhi.se/api/category/pmp3g/version/2/geotype/point/lon/19.82292/lat/66.60696/data.json");
            }
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while (line != null) {
                line = bufferedReader.readLine();
                data = data + line;
            }
            JSONObject jsonObject = new JSONObject(data);
            String weatherNumber;


            String correctDate = jsonObject.getJSONArray("timeSeries").getJSONObject(0).get("validTime").toString();
            correctDate = correctDate.substring(0, 10);
            System.out.println(correctDate);


            String[] weatherDesc = getWeatherDesc();
            //int varv = 0;
            int i = 0;
            do {
                weatherNumber = jsonObject.getJSONArray("timeSeries").getJSONObject(i).getJSONArray("parameters").getJSONObject(18).getJSONArray("values").getString(0);
                weatherDescription = weatherDesc[Integer.parseInt(weatherNumber)];
                //System.out.println(jsonObject.getJSONArray("timeSeries").getJSONObject(0).getJSONArray("parameters").getJSONObject(18).getJSONArray("values").getString(0));

                Weather weather;
                tempString = jsonObject.getJSONArray("timeSeries").getJSONObject(i).getJSONArray("parameters").getJSONObject(11).getJSONArray("values").getString(0);

                timeAndDateString = jsonObject.getJSONArray("timeSeries").getJSONObject(i).get("validTime").toString();

                timeString = timeAndDateString.substring(11,16);

                if (correctDate.equals(timeAndDateString.substring(0,10))) {
                    forecast = forecast + " " + weatherDescription + " " + tempString + " " + timeString + "\n";

                }
                //System.out.println(correctDate.equals(timeString.substring(0,10)));
                i++;
                //varv++;
            }
            while (correctDate.equals(timeAndDateString.substring(0,10)));
            //System.out.println(correctDate);
            System.out.println(forecast);

            //System.out.println(correctDate.equals(timeString.substring(0,10)));
            // System.out.println(timeString.substring(0,10));
            //System.out.println(varv);
            //System.out.println(jsonObject.getJSONArray("timeSeries").getJSONObject(0).get("validTime"));


        } catch (IOException | JSONException | ArrayIndexOutOfBoundsException | NullPointerException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        MainActivity.arrayView.setText(this.forecast);
        MainActivity.data.setText(this.timeString);

    }
}
