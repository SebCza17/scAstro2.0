package com.example.sebastian.scastro.data;

import org.json.JSONObject;

public class Condition implements JSONPopulator {
    private int code;
    private int temp;

    public int getCode() {
        return code;
    }

    public int getTemp() {
        return temp;
    }

    @Override
    public void populate(JSONObject jsonObject) {

        code = jsonObject.optInt("code");
        temp = jsonObject.optInt("temp");
    }
}
