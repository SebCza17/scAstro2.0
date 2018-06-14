package com.example.sebastian.scastro;

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

import com.example.sebastian.scastro.data.Channel;
import com.example.sebastian.scastro.data.Item;
import com.example.sebastian.scastro.databinding.Fragment1LayoutBinding;
import com.example.sebastian.scastro.service.CallbackWeatherService;
import com.example.sebastian.scastro.service.YahooWaetherSevice;

public class Fragment3 extends Fragment implements CallbackWeatherService {

    private ImageView imageViewStatus;
    private TextView textViewLocation;
    private TextView textViewTemp;
    private TextView textViewWind;


    private YahooWaetherSevice yahooWaetherSevice;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = (View) inflater.inflate(R.layout.fragment3_layout,container,false);

        imageViewStatus = (ImageView)view.findViewById(R.id.imageViewStatus);
        textViewLocation = (TextView)view.findViewById(R.id.textViewLocation);
        textViewTemp = (TextView)view.findViewById(R.id.textViewTemp);
        textViewWind = (TextView)view.findViewById(R.id.textViewWind);


        yahooWaetherSevice = new YahooWaetherSevice(this);
        yahooWaetherSevice.refreshWeather("Lodz, PL");

        return view;
    }

    @Override
    public void serviceSuccess(Channel channel) {
        //dialog.hide();

        Item item = channel.getItem();
        int resourceID = getResources().getIdentifier("drawable/a" + item.getCondition().getCode(), null, getActivity().getPackageName());

        //@SuppressWarnings("deprecation")
        Drawable weatherIconDrawable = getResources().getDrawable(resourceID);

        imageViewStatus.setImageDrawable(weatherIconDrawable);


        textViewTemp.setText(item.getCondition().getTemp()+"\u00B0"+channel.getUnits().getTemperature());
    }

    @Override
    public void serviceFailure(Exception exception) {
        //dialog.hide();
        //Toast.makeText(this, exception.getMessage(), Toast.LENGTH_LONG).show();
    }
}
