package com.example.sebastian.scastro;


import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.astrocalculator.AstroCalculator;
import com.astrocalculator.AstroDateTime;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Sebastian on 17.05.2018.
 */

public class Sun extends BaseObservable {

    private String sunRise;
    private String sunRiseAzimuth;
    private String sunSet;
    private String sunSetAzimuth;
    private String sunTwilightEvening;
    private String sunTwilightMorning;


    private AstroCalculator astroCalculator;


    public Sun() {

        AstroCalculator.Location location = new AstroCalculator.Location( 52, 21);
        AstroDateTime astroDateTime = new AstroDateTime(
                Integer.parseInt(MainActivity.dateTime.getDateTestTab()[0]), Integer.parseInt(MainActivity.dateTime.getDateTestTab()[1]),Integer.parseInt(MainActivity.dateTime.getDateTestTab()[2]),
                Integer.parseInt(MainActivity.dateTime.getDateTestTab()[3]),Integer.parseInt(MainActivity.dateTime.getDateTestTab()[4]),Integer.parseInt(MainActivity.dateTime.getDateTestTab()[5]),
                2, false);


        astroCalculator = new AstroCalculator(astroDateTime, location);

        this.sunRise = astroCalculator.getSunInfo().getSunrise().toString();
        this.sunRiseAzimuth = sunRiseAzimuth;
        this.sunSet = sunSet;
        this.sunSetAzimuth = sunSetAzimuth;
        this.sunTwilightEvening = sunTwilightEvening;
        this.sunTwilightMorning = sunTwilightMorning;


    }

    @Bindable
    public String getSunRise() {
        return sunRise;
    }

    public void setSunRise(String sunRise) {
        this.sunRise = sunRise;
        notifyPropertyChanged(BR.sunRise);
    }

    public String getSunRiseAzimuth() {
        return sunRiseAzimuth;
    }


    public String getSunSet() {
        return sunSet;
    }


    public String getSunSetAzimuth() {
        return sunSetAzimuth;
    }


    public String getSunTwilightEvening() {
        return sunTwilightEvening;
    }


    public String getSunTwilightMorning() {
        return sunTwilightMorning;
    }

}
