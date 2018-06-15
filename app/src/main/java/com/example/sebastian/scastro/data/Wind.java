package com.example.sebastian.scastro.data;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.example.sebastian.scastro.BR;

import org.json.JSONObject;

public class Wind extends BaseObservable implements JSONPopulator {
    private Double speed;
    private String direction;

    @Bindable
    public Double getSpeed() {
        return speed;
    }

    @Bindable
    public String getDirection() {
        return direction;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
        notifyPropertyChanged(BR.speed);
    }

    public void setDirection(String direction) {
        this.direction = direction;
        notifyPropertyChanged(BR.direction);
    }

    @Override
    public void populate(JSONObject jsonObject) {

        speed = jsonObject.optDouble("speed");
        direction = jsonObject.optString("direction");
    }
}
