package com.example.sebastian.scastro.data;

import org.json.JSONObject;

public class Units implements JSONPopulator {
    private String temperature;
    private String speed;
    private String pressure;
    private String distance;

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    @Override
    public void populate(JSONObject jsonObject) {

        temperature = jsonObject.optString("temperature");
        speed = jsonObject.optString("speed");
        pressure = jsonObject.optString("pressure");
        distance = jsonObject.optString("distance");
    }
}
