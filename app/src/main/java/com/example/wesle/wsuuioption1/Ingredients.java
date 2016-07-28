package com.example.wesle.wsuuioption1;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;


public class Ingredients extends Fragment {

    private CheckBox cereal;
    private CheckBox chocolateChips;
    private CheckBox vanilla;
    private CheckBox crunchyPB;
    private CheckBox espresso;
    private CheckBox salt;
    private CheckBox sugar;
    private CheckBox marsh;

    boolean[] ingredientChecked = {false, false, false, false, false, false, false, false};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_ingredients, container, false);

        // define buttons
        cereal = (CheckBox) v.findViewById(R.id.cereal);
        chocolateChips = (CheckBox) v.findViewById(R.id.chocolate);
        vanilla = (CheckBox) v.findViewById(R.id.vanilla);
        crunchyPB = (CheckBox) v.findViewById(R.id.peanut);
        espresso = (CheckBox) v.findViewById(R.id.espresso);
        salt = (CheckBox) v.findViewById(R.id.kosher);
        sugar = (CheckBox) v.findViewById(R.id.sugar);
        marsh = (CheckBox) v.findViewById(R.id.marsh);

        cereal.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

               @Override
               public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){
                        ingredientChecked[0] = true;
                    }else{
                        ingredientChecked[0] = false;
                    }
               }
           }
        );

        cereal.setChecked(true);

        Toast.makeText(getActivity(),"Text!",Toast.LENGTH_SHORT).show();
        return v;
    }

    {
    }

    /*@Override
    public void onStart() {
        super.onStart();
        cereal.setChecked(true);

    }*/
}
