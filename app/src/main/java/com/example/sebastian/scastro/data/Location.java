package com.example.sebastian.scastro.data;

import org.json.JSONObject;

public class Location implements JSONPopulator {
    private String city;
    private String country;
    private String region;

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getRegion() {
        return region;
    }

    @Override
    public void populate(JSONObject jsonObject) {

        city = jsonObject.optString("city");
        country = jsonObject.optString("country");
        region = jsonObject.optString("region");
    }
}
