package com.example.wesle.wsuuioption1;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FinalActivity extends AppCompatActivity {

    //FileOutputStream fos;
    //String FILENAME = "internalString";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);
    }

    //go to next activity
    public void nextActivity(View v){
        Intent i = new Intent(this, SummaryActivity.class);
        startActivity(i);
    }

    //public void uploadDataToFile() throws IOException{
        // Save data via file
        /*
        File f = new File(FILENAME);
        try {
            fos = new FileOutputStream(f);
            fos.close();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }*/

        //other file writing method
        /*
        try{
            fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
            fos.write(data.getBytes());
            fos.close();
        }catch (IOException e){
            e.printStackTrace();
        }

    }*/
}
