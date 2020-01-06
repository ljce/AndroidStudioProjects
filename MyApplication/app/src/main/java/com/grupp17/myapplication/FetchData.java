package com.grupp17.myapplication;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.grupp17.myapplication.WeatherDesc.getWeatherDesc;


public class FetchData extends AsyncTask<Void, Void, Void> {
    String data = "";
    String timeString;
    String weatherNumber;
    String weatherDescription;

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL url = new URL("https://opendata-download-metfcst.smhi.se/api/category/pmp3g/version/2/geotype/point/lon/11.974560/lat/57.708870/data.json");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while (line != null) {
                line = bufferedReader.readLine();
                data = data + line;
            }
            JSONObject jsonObject = new JSONObject(data);

            weatherNumber = jsonObject.getJSONArray("timeSeries").getJSONObject(0).getJSONArray("parameters").getJSONObject(18).getJSONArray("values").getString(0);

            //System.out.println(jsonObject.getJSONArray("timeSeries").getJSONObject(0).getJSONArray("parameters").getJSONObject(18).getJSONArray("values").getString(0));

            timeString = jsonObject.getJSONArray("timeSeries").getJSONObject(0).get("validTime").toString();

            //System.out.println(jsonObject.getJSONArray("timeSeries").getJSONObject(0).get("validTime"));

            String[] weatherDesc = getWeatherDesc();
            weatherDescription = weatherDesc[Integer.parseInt(weatherNumber)];


        } catch (IOException | JSONException | ArrayIndexOutOfBoundsException | NullPointerException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        MainActivity.arrayView.setText(this.weatherDescription);
        MainActivity.data.setText(this.timeString);

    }
}


//JSONArray jsonArray = jsonObject.getJSONArray("timeSeries");

//System.out.println(jsonArray.length());

            /*for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject explrObjectJSON = jsonArray.getJSONObject(i);
                //explrObjectString = explrObjectJSON.toString();
            }*/


    /* JSONObject json = new JSONObject(line);
            JSONObject jsonResponse = json.getJSONObject("response");
            dataString = jsonResponse.getString("Wsymb2");*/

           /* JSONArray JA = new JSONArray(data);
            for(int i =0 ;i < JA.length(); i++){
                JSONObject JO = (JSONObject) JA.get(i);
                singleParsed =  "Name:" + JO.get("name") + "\n"+
                        "Password:" + JO.get("password") + "\n"+
                        "Contact:" + JO.get("contact") + "\n"+
                        "Country:" + JO.get("country") + "\n";

                dataParsed = dataParsed + singleParsed +"\n" ;


            }*/


             /*Object find(JSONbject jObj, String k) throws JSONException {
        Iterator<?> keys = jObj.keys();

        while (keys.hasNext()) {
            String key = (String) keys.next();
            if (key.equals(k)) {
                return jObj.get(key);
            }

            if (jObj.get(key) instanceof JSONObject) {
                return find((JSONObject) jObj.get(key), k);
            }

            if (jObj.get(key) instanceof JSONArray) {
                JSONArray jar = (JSONArray) jObj.get(key);
                for (int i = 0; i < jar.length(); i++) {
                    JSONObject j = jar.getJSONObject(i);
                    find(j, k);
                }
            }
        }
        return null;
    }*/