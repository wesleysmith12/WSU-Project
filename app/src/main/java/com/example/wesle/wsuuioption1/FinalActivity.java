package com.example.wesle.wsuuioption1;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FinalActivity extends AppCompatActivity {

    FileOutputStream fos;
    String FILENAME = "internalString";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);
    }

    public void uploadDataToFile() throws IOException{
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
        */
    }
}
