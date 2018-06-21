package com.example.sebastian.scastro;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.example.sebastian.scastro.data.Channel;
import com.example.sebastian.scastro.service.CallbackWeatherService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sebastian on 16.05.2018.
 */

public class SecondActivity extends AppCompatActivity {

    private EditText editTextLa;
    private EditText editTextLo;
    private EditText editTextRe;
    private EditText editTextAdd;
    private Spinner spinner;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private List<String> spinnerList = new ArrayList<>();
    private int howMuch = 0;
    private Switch aSwitch;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity_layout);

        sharedPreferences = getSharedPreferences("config.xml", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        editTextLa = (EditText) findViewById(R.id.editTextLatitude);
        editTextLo = (EditText) findViewById(R.id.editTextLongitude);
        editTextRe = (EditText) findViewById(R.id.editTextRefreshTime);
        editTextAdd = (EditText) findViewById(R.id.editTextAdd);

        editTextLa.setText("" + String.valueOf(MainActivity.latitude));
        editTextLo.setText("" + String.valueOf(MainActivity.longitude));
        editTextRe.setText("" + MainActivity.refreshTime/1000);

        spinner = (Spinner) findViewById(R.id.spinner);
        aSwitch = (Switch) findViewById(R.id.switch1);

        spinnerList.add("Lodz");
        spinnerList.add("Warszawa");


        int prefsInt = sharedPreferences.getInt("howMuch5", 0);
        if(prefsInt != 0){
            howMuch = prefsInt;
            for(int i = 0; i < howMuch; i++){
                spinnerList.add(sharedPreferences.getString("wordPlace" + (i+1), ""));
            }
        }

        int prefsInt2 = sharedPreferences.getInt("selectedLocationID", 0);


        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item, spinnerList);
        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                String selected = parent.getItemAtPosition(position).toString();
                editor.putString("selectedLocation", selected);
                editor.putInt("selectedLocationID", position);
                editor.commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        spinner.setSelection(prefsInt2);

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if(b){
                    editor.putString("typeU", "c");

                }else{
                    editor.putString("typeU", "f");
                }
                editor.commit();
            }
        });

        String type = sharedPreferences.getString("typeU", "c");
        if(type.equals("c"))
            aSwitch.setChecked(true);
        else
            aSwitch.setChecked(false);

    }



    public void onClickSave(View view) {
        if(!editTextAdd.getText().toString().equals("")) {
            howMuch++;
            editor.putInt("howMuch5", howMuch);
            editor.putString("wordPlace" + howMuch, editTextAdd.getText().toString());
            editor.commit();
        }


        MainActivity.latitude = Double.parseDouble(editTextLa.getText().toString());
        MainActivity.longitude = Double.parseDouble(editTextLo.getText().toString());
        if(Integer.valueOf(editTextRe.getText().toString()) > 0) {
            MainActivity.refreshTime = Integer.parseInt(editTextRe.getText().toString()) * 1000;
        }
        else
            MainActivity.refreshTime = 1000;

        MainActivity.dateTime.refreshAllTime();
        MainActivity.sun.refresh();
        MainActivity.moon.refresh();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }



}
