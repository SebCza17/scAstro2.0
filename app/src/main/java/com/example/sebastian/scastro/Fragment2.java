package com.example.sebastian.scastro;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Sebastian on 16.05.2018.
 */

public class Fragment2 extends Fragment {

    private TextView textViewMoonRise;
    private TextView textViewMoonSet;
    private TextView textViewMoonNewFull;
    private TextView textViewMoonPhase;
    private TextView textViewMoonAstroDay;

    private Button button3;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment2_layout,container,false);

        textViewMoonRise = (TextView)view.findViewById(R.id.textViewMoonRise);
        textViewMoonSet = (TextView)view.findViewById(R.id.textViewMoonSet);
        textViewMoonNewFull = (TextView)view.findViewById(R.id.textViewNewFullMoon);
        textViewMoonPhase = (TextView)view.findViewById(R.id.textViewPhaseMoon);
        textViewMoonAstroDay = (TextView)view.findViewById(R.id.textViewAstroDay);

        button3 = (Button) view.findViewById(R.id.button3);


        return view;
    }
}
