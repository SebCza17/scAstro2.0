package com.example.sebastian.scastro.data;

import org.json.JSONObject;

public class Item implements JSONPopulator {
    private Condition condition;
    private Double lat;
    private Double longi;

    public Condition getCondition() {
        return condition;
    }

    public Double getLat() {
        return lat;
    }

    public Double getLongi() {
        return longi;
    }

    @Override
    public void populate(JSONObject jsonObject) {
        condition = new Condition();
        condition.populate(jsonObject.optJSONObject("condition"));

        lat = jsonObject.optDouble("lat");
        longi = jsonObject.optDouble("long");
    }
}
