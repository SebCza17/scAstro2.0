package com.example.sebastian.scastro;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sebastian.scastro.data.Atmosphere;
import com.example.sebastian.scastro.data.Channel;
import com.example.sebastian.scastro.data.Item;
import com.example.sebastian.scastro.data.Wind;
import com.example.sebastian.scastro.service.CallbackWeatherService;
import com.example.sebastian.scastro.service.YahooWaetherSevice;

public class Fragment4 extends Fragment implements CallbackWeatherService {

    private TextView textViewWind;
    private TextView textViewWindDir;
    private TextView textViewHumi;
    private TextView textViewVisibility;

    private YahooWaetherSevice yahooWaetherSevice;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment4_layout,container,false);


        textViewWind = (TextView)view.findViewById(R.id.textViewWind);
        textViewWindDir = (TextView)view.findViewById(R.id.textViewWindDir);
        textViewHumi = (TextView)view.findViewById(R.id.textViewHumidity);
        textViewVisibility = (TextView)view.findViewById(R.id.textViewVisibility);

        yahooWaetherSevice = new YahooWaetherSevice(this);
        yahooWaetherSevice.refreshWeather("Lodz, PL");


        return view;
    }

    @Override
    public void serviceSuccess(Channel channel) {

        Wind wind = channel.getWind();
        Atmosphere atmosphere = channel.getAtmosphere();


        textViewWind.setText(wind.getSpeed().toString());
        textViewWindDir.setText(wind.getDirection());
        textViewHumi.setText(atmosphere.getHumidity());
        textViewVisibility.setText(atmosphere.getVisibility());



    }

    @Override
    public void serviceFailure(Exception e) {

    }
}
