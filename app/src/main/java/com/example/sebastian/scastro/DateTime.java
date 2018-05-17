package com.example.sebastian.scastro;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Sebastian on 17.05.2018.
 */

public class DateTime extends BaseObservable {

    private String dateTime;

    private final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private Calendar calendar;
    private String dateTest;
    private String[] dateTestTab;

    public DateTime() {
        calendar = Calendar.getInstance();
        dateTest = df.format(calendar.getTime());
        dateTestTab = dateTest.split("[-: ]");

        dateTime = dateTest;
    }

    @Bindable
    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
        notifyPropertyChanged(BR.dateTime);
    }

    public SimpleDateFormat getDf() {
        return df;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public String getDateTest() {
        return dateTest;
    }


    public String[] getDateTestTab() {
        return dateTestTab;
    }

    public void refreshTime(){
        calendar = Calendar.getInstance();
        dateTest = df.format(calendar.getTime());

        setDateTime(dateTest);
    }

}
