package com.example.wesle.wsuuioption1;

import android.os.Bundle;
import android.view.View;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

public class SmallLayout extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_small_layout);
    }

    public void selectFrag(View view) {
        Fragment fr;

        if(view == findViewById(R.id.buttonS1)) {
            fr = new RecipeFragment();
        }else if(view == findViewById(R.id.buttonS2)){
            fr = new ChangeFragment();
        }else if(view == findViewById(R.id.buttonS3)){
            fr = new Fragment1();
        }else if(view == findViewById(R.id.buttonS4)){
            fr = new TeaFragment();
        }else if(view == findViewById(R.id.buttonS5)){
            fr = new TravelBagFragment();
        }else if(view == findViewById(R.id.buttonS6)){
            fr = new PhoneFragment();
        }else if(view == findViewById(R.id.buttonS7)){
            fr = new SnackFragment();
        }else {
            fr = new ExitFragment();
        }

        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_place, fr);
        fragmentTransaction.commit();

    }
}
