package com.example.sebastian.scastro;

import android.content.Context;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sebastian.scastro.data.Atmosphere;
import com.example.sebastian.scastro.data.Channel;
import com.example.sebastian.scastro.data.Item;
import com.example.sebastian.scastro.databinding.Fragment1LayoutBinding;
import com.example.sebastian.scastro.service.CallbackWeatherService;
import com.example.sebastian.scastro.service.YahooWaetherSevice;

import java.util.ArrayList;
import java.util.List;

public class Fragment3 extends Fragment implements CallbackWeatherService {

    private ImageView imageViewStatus;
    private TextView textViewTemp3;
    private TextView textViewLocation;
    private TextView textViewDesc;
    private TextView textViewPreasure;
    private TextView textViewLat;
    private TextView textViewLong;
    private TextView textViewError;


    private YahooWaetherSevice yahooWaetherSevice;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = (View) inflater.inflate(R.layout.fragment3_layout,container,false);

        imageViewStatus = (ImageView)view.findViewById(R.id.imageViewStatus);
        textViewTemp3 = (TextView)view.findViewById(R.id.textViewTemp);
        textViewLocation = (TextView)view.findViewById(R.id.textViewLocation);
        textViewDesc = (TextView)view.findViewById(R.id.textViewDescription);
        textViewPreasure = (TextView)view.findViewById(R.id.textViewPreasuer);
        textViewLat = (TextView)view.findViewById(R.id.textViewGeoLocLat);
        textViewLong = (TextView)view.findViewById(R.id.textViewLocationLong);

        textViewError = (TextView)view.findViewById(R.id.textViewError);

        sharedPreferences = getActivity().getSharedPreferences("config.xml", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        String location = sharedPreferences.getString("selectedLocation", "lodz");
        String type = sharedPreferences.getString("typeU", "c");

        yahooWaetherSevice = new YahooWaetherSevice(this);
        yahooWaetherSevice.refreshWeather(location, type);

        return view;
    }

    @Override
    public void serviceSuccess(Channel channel) {
        //dialog.hide();

        Item item = channel.getItem();
        Atmosphere atmosphere = channel.getAtmosphere();


        editor.putInt("imageViewStatus30", getResources().getIdentifier("drawable/a" + item.getCondition().getCode(), null, getActivity().getPackageName()));
        editor.putString("textViewTemp30", item.getCondition().getTemp()+" \u00B0"+channel.getUnits().getTemperature());
        editor.putString("textViewLocation30", yahooWaetherSevice.getLocation());
        editor.putString("textViewDesc30", item.getCondition().getDescription());
        editor.putString("textViewPreasure30", atmosphere.getPressure().toString()+" \u33D4");
        editor.putString("textViewLat30", item.getLat().toString());
        editor.putString("textViewLong30", item.getLongi().toString());

        editor.commit();

        refreshWeather();

        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println(item.getCondition().getCode());
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");



    }

    @Override
    public void serviceFailure(Exception exception) {

        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println(exception);
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");

        refreshWeather();

    }

    @Override
    public void refreshWeather() {

        int resourceID =  sharedPreferences.getInt("imageViewStatus30", 0);
        Drawable weatherIconDrawable = getResources().getDrawable(resourceID);
        imageViewStatus.setImageDrawable(weatherIconDrawable);

        textViewTemp3.setText(sharedPreferences.getString("textViewTemp30", ""));
        textViewLocation.setText(sharedPreferences.getString("textViewLocation30", ""));
        textViewDesc.setText(sharedPreferences.getString("textViewDesc30", ""));
        textViewPreasure.setText(sharedPreferences.getString("textViewPreasure30", ""));
        textViewLat.setText(sharedPreferences.getString("textViewLat30", ""));
        textViewLong.setText(sharedPreferences.getString("textViewLong30", ""));
    }

}