package com.example.wesle.wsuuioption1;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class PortraitActivity extends AppCompatActivity {

    int orientation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portrait);

        Fragment fr;

        int id = getIntent().getIntExtra("BUTTON_ID", R.id.buttonS1);

        if (id == R.id.buttonS1) {
            fr = new RecipeFragment();
        } else if (id == R.id.buttonS2) {
            fr = new ChangeFragment();
        } else if (id == R.id.buttonS3) {
            fr = new Fragment1();
        } else if (id == R.id.buttonS4) {
            fr = new TeaFragment();
        } else if (id == R.id.buttonS5) {
            fr = new TravelBagFragment();
        } else if (id == R.id.buttonS6) {
            fr = new PhoneFragment();
        } else if (id == R.id.buttonS7) {
            fr = new SnackFragment();
        } else {
            fr = new ExitFragment();
        }

        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.portrait_frame, fr);
        fragmentTransaction.commit();
        //Toast.makeText(PortraitActivity.this, id, Toast.LENGTH_SHORT).show();

        orientation = getResources().getConfiguration().orientation;

        if(orientation == 2){
            finish();
            return;
        }

    }
}
