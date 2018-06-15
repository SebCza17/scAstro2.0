package com.example.sebastian.scastro;

import android.content.Context;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sebastian.scastro.data.Atmosphere;
import com.example.sebastian.scastro.data.Channel;
import com.example.sebastian.scastro.data.Wind;
import com.example.sebastian.scastro.databinding.Fragment4LayoutBinding;
import com.example.sebastian.scastro.service.CallbackWeatherService;
import com.example.sebastian.scastro.service.YahooWaetherSevice;


public class Fragment4 extends Fragment implements CallbackWeatherService {

    Fragment4LayoutBinding fragment4LayoutBinding;

    private YahooWaetherSevice yahooWaetherSevice;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        fragment4LayoutBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment2_layout, container, false);

        yahooWaetherSevice = new YahooWaetherSevice(this);
        yahooWaetherSevice.refreshWeather("Lodz, PL");

        sharedPreferences = getActivity().getSharedPreferences("com.example.sebastian.scastro", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();


        return view;
    }

    @Override
    public void serviceSuccess(Channel channel) {

        Wind wind = channel.getWind();
        Atmosphere atmosphere = channel.getAtmosphere();

        editor.putString("textViewWind40", wind.getSpeed().toString());
        editor.putString("textViewWindDir40", wind.getDirection());
        editor.putString("textViewHumi40", atmosphere.getHumidity());
        editor.putString("textViewVisibility40", atmosphere.getVisibility());

        editor.commit();


        refreshWeather();

    }

    @Override
    public void serviceFailure(Exception e) {

        refreshWeather();
    }

    @Override
    public void refreshWeather() {
    }
}
