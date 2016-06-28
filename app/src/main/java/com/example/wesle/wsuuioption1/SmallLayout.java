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
        Fragment fr = new RecipeFragment();

        if(orientation == 2) {
            Toast.makeText(SmallLayout.this, "you are in the small layout in the landscape orientation", Toast.LENGTH_SHORT).show();

            switch(view.getId()){
            case R.id.button1:
                fr = new RecipeFragment();
                break;
            case R.id.buttonS2:
                fr = new ChangeFragment();
                break;
            case R.id.buttonS3:
                fr = new Fragment1();
                break;
            case R.id.buttonS4:
                fr = new TeaFragment();
                break;
            case R.id.buttonS5:
                fr = new TravelBagFragment();
                break;
            case R.id.buttonS6:
                fr = new PhoneFragment();
                break;
            case R.id.buttonS7:
                fr = new SnackFragment();
                break;
            case R.id.buttonS8:
                fr = new ExitFragment();
                break;
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
