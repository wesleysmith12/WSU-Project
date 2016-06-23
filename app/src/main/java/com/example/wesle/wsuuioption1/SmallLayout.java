package com.example.wesle.wsuuioption1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.widget.Toast;

public class SmallLayout extends Activity {

    //boolean to determine if orientation is landscape or portrait
    /*View detailsFrame = findViewById(R.id.fragment_place);
    boolean mDualPane = detailsFrame != null && detailsFrame.getVisibility() == View.VISIBLE;*/

    int orientation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_small_layout);

        orientation = getResources().getConfiguration().orientation;
    }

    public void selectFrag(View view) {
        Fragment fr;

        if(orientation == 2) {
            Toast.makeText(SmallLayout.this, "landscape", Toast.LENGTH_SHORT).show();
            if (view == findViewById(R.id.buttonS1)) {
                fr = new RecipeFragment();
            } else if (view == findViewById(R.id.buttonS2)) {
                fr = new ChangeFragment();
            } else if (view == findViewById(R.id.buttonS3)) {
                fr = new Fragment1();
            } else if (view == findViewById(R.id.buttonS4)) {
                fr = new TeaFragment();
            } else if (view == findViewById(R.id.buttonS5)) {
                fr = new TravelBagFragment();
            } else if (view == findViewById(R.id.buttonS6)) {
                fr = new PhoneFragment();
            } else if (view == findViewById(R.id.buttonS7)) {
                fr = new SnackFragment();
            } else {
                fr = new ExitFragment();
            }

            FragmentManager fm = getFragmentManager();
            FragmentTransaction fragmentTransaction = fm.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_place, fr);
            fragmentTransaction.commit();
        }else if(orientation == 1){

                Intent intent = new Intent(getBaseContext(), PortraitActivity.class);
                intent.putExtra("BUTTON_ID", view.getId());
                startActivity(intent);



        }

    }
}
