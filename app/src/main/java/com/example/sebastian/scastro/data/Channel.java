package com.example.sebastian.scastro.data;

import org.json.JSONObject;

public class Channel implements JSONPopulator {
    private Units units;
    private Wind wind;
    private Atmosphere atmosphere;
    private Item item;
    private Location location;

    @Override
    public void populate(JSONObject jsonObject) {

        units = new Units();
        units.populate(jsonObject.optJSONObject("units"));

        wind = new Wind();
        wind.populate(jsonObject.optJSONObject("wind"));

        atmosphere = new Atmosphere();
        atmosphere.populate(jsonObject.optJSONObject("atmosphere"));

        item = new Item();
        item.populate(jsonObject.optJSONObject("item"));

        location = new Location();
        location.populate(jsonObject.optJSONObject("location"));
    }

    public Units getUnits() {
        return units;
    }

    public Wind getWind() {
        return wind;
    }

    public Atmosphere getAtmosphere() {
        return atmosphere;
    }

    public Item getItem() {
        return item;
    }

    public Location getLocation() {
        return location;
    }
}
