package com.example.sebastian.scastro.service;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.AsyncTask;

import com.example.sebastian.scastro.data.Channel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class YahooWaetherSevice {
    private  CallbackWeatherService callbackWeatherService;
    private String location;
    private String typeU;
    private Exception exception;

    public YahooWaetherSevice(CallbackWeatherService callbackWeatherService) {
        this.callbackWeatherService = callbackWeatherService;
    }

    public void refreshWeather(final String loc, final String type){
        this.location = loc;
        this.typeU = type;
        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... strings) {

                String YQL = String.format("select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"%s\") and u =\"%s\"", strings[0], strings[1]);
                String endpoint = String.format("https://query.yahooapis.com/v1/public/yql?q=%s&format=json", Uri.encode(YQL));

                try {
                    URL url = new URL(endpoint);

                    URLConnection urlConnection = url.openConnection();

                    InputStream inputStream = urlConnection.getInputStream();

                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder stringBuilder = new StringBuilder();

                    String line;

                    while((line = bufferedReader.readLine()) != null){
                        stringBuilder.append(line);
                    }
                    return stringBuilder.toString();

                } catch (Exception e) {
                    exception = e;
                }



                return null;
            }

            @Override
            protected void onPostExecute(String s) {

                if (s == null && exception != null){
                    callbackWeatherService.serviceFailure(exception);
                    return;
                }

                try {
                    JSONObject jsonObject = new JSONObject(s);

                    JSONObject jsonObject1 = jsonObject.optJSONObject("query");
                    int count = jsonObject1.optInt("count");


                    if(count == 0){
                        callbackWeatherService.serviceFailure(new LocationWeatherException("Bledna Lokacja"));

                        return;
                    }

                    Channel channel = new Channel();
                    channel.populate(jsonObject1.optJSONObject("results").optJSONObject("channel"));

                    callbackWeatherService.serviceSuccess(channel);
                } catch (JSONException e) {
                    callbackWeatherService.serviceFailure(e);
                }
            }
        }.execute(location, typeU);
    }



    public String getLocation() {
        return location;
    }

    public class LocationWeatherException extends Exception{
        public  LocationWeatherException(String detailMessage) {
            super(detailMessage);
        }
    }
}
