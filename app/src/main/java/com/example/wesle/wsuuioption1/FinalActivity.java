package com.example.wesle.wsuuioption1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FinalActivity extends AppCompatActivity implements View.OnClickListener{

    //FileOutputStream fos;
    //String FILENAME = "internalString";
    private TextView gathered, recorded, leavesHouse;
    private EditText changeRecorded, changeGathered, comments;
    private CheckBox recipeRead, costOfMovie, phoneCall;
    private TimePicker time;
    private int minutes;
    private int hours;
    private boolean alreadyCalculated = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        //SharedPreferences sharedPref = getSharedPreferences("FILENAME", Context.MODE_PRIVATE);
        time = (TimePicker) findViewById(R.id.timePicker);

        //this code makes so that the clock is pm by default
        if(time.getCurrentHour() < 12)
        {
            time.setCurrentHour(time.getCurrentHour()+12);
        }

        leavesHouse = (TextView) findViewById(R.id.leaveshouse);
        gathered = (TextView) findViewById(R.id.gathered);
        recorded = (TextView) findViewById(R.id.recorded);
        changeRecorded = (EditText) findViewById(R.id.editText4);
        changeGathered = (EditText) findViewById(R.id.editText5);
        comments = (EditText) findViewById(R.id.comments);
        /*if(sharedPref.getInt("movieScore", 1) == 4){
                time.setVisibility(View.GONE);
            leavesHouse.setVisibility(View.GONE);
        }
        if(sharedPref.getInt("moneyScore", 1) == 4){
            gathered.setVisibility(View.GONE);
            recorded.setVisibility(View.GONE);
            changeGathered.setVisibility(View.GONE);
            changeRecorded.setVisibility(View.GONE);
        }*/

        //leavesHouse = (EditText) findViewById(R.id.editText3);
        //leavesHouse.setInputType(InputType.TYPE_CLASS_DATETIME);
        //String leavesHouseTime = leavesHouse.getText().toString();
        ;

        recipeRead = (CheckBox) findViewById(R.id.checkBox2);
        costOfMovie = (CheckBox) findViewById(R.id.checkBox);
        phoneCall = (CheckBox) findViewById(R.id.checkBox3);

       /* if(leavesHouse.getT())*/
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.
                INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        return true;
    }

    //go to next activity
    public void nextActivity(View v){

        SharedPreferences sharedPref = getSharedPreferences("FILENAME", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("comments", comments.getText().toString());
        int sequencing = sharedPref.getInt("correctSequencing", 0);
        sequencing += calculateSequencing();
        editor.putInt("correctSequencing", sequencing);
        int moneyScore = sharedPref.getInt("moneyScore", 1);
        int movieScore = sharedPref.getInt("movieScore", 1);
        if(movieScore != 4){
            calculateTime(v);
        }

        editor.apply();

        if(moneyScore == 4 || compareChange()) {
            alreadyCalculated = true;

            Intent i = new Intent(this, SummaryActivity.class);
            //save sequencing with calculateSequencing()
            startActivity(i);
        }else{
            Toast.makeText(FinalActivity.this, "Enter change gathered, and recorded", Toast.LENGTH_SHORT).show();
        }
    }

    public int calculateSequencing(){
        if(alreadyCalculated){
            return 0;
        }
        int sequencing = 0;
        SharedPreferences sharedPref = getSharedPreferences("FILENAME", Context.MODE_PRIVATE);
        boolean phoneCallEnd = sharedPref.getBoolean("phoneCallEnd", false);

        if(recipeRead.isChecked()){
            sequencing++;
        }
        if(costOfMovie.isChecked()){
            sequencing++;
        }
        if(phoneCall.isChecked() && !phoneCallEnd)
        {
            sequencing++;
        }
        return sequencing;
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

    @Override
    public void onClick(View v) {

        Toast.makeText(FinalActivity.this, "onClickListener working", Toast.LENGTH_SHORT).show();

    }

    public boolean compareChange(){
        if(alreadyCalculated){
            return true;
        }

        if(changeRecorded.length() == 0 || changeGathered.length() == 0){
            return false;
        }
        Double changeRecordedText = Double.parseDouble(changeRecorded.getText().toString());
        Double changeGatheredText = Double.parseDouble(changeGathered.getText().toString());

        SharedPreferences sharedPref = getSharedPreferences("FILENAME", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        boolean moreMoney = sharedPref.getBoolean("moreMoney", false);
        boolean lessMoney = sharedPref.getBoolean("lessMoney", false);
        int moneyScore = sharedPref.getInt("moneyScore", 0);
        int moneyIneff = sharedPref.getInt("monineff", 0);
        int moneyInac = sharedPref.getInt("moninac", 0);
        int moneyIncom = sharedPref.getInt("monincom", 0);
        int errorTotals = sharedPref.getInt("errorTotals", 0);
        if(changeRecordedText > changeGatheredText){ //didn't get enough money: inaccurate and inefficient
            if(lessMoney){
                //do nothing
            }else if(moreMoney){
                if(moneyScore == 1 || moneyScore == 2){
                    moneyScore = 3;
                }
                moneyIneff--;
                errorTotals--;
                moneyIncom++;
                moneyInac++;
                errorTotals+=2;
                editor.putInt("moneyScore", moneyScore);
                editor.putInt("monineff", moneyIneff);
                editor.putInt("monincom", moneyIncom);
                editor.putInt("moninac", moneyInac);
                lessMoney = true;
            }else{
                //neither error button was pressed
                moneyIncom++;
                moneyInac++;
                errorTotals+=2;
                editor.putInt("moneyScore", moneyScore);
                editor.putInt("monineff", moneyIneff);
                editor.putInt("monincom", moneyIncom);
                editor.putInt("moninac", moneyInac);
                lessMoney = true;
            }
        }else if(changeRecordedText < changeGatheredText){ //grabbed too much money
            if(moreMoney){
                //do nothing
            }else if(lessMoney){
                moneyInac--;
                moneyIncom--;
                errorTotals-=2;
                if(moneyScore == 3 && moneyIncom == 0 && moneyInac == 0){
                    moneyScore = 2;
                }
                moneyIneff++;
                errorTotals++;
                editor.putInt("moneyScore", moneyScore);
                editor.putInt("monineff", moneyIneff);
                editor.putInt("monincom", moneyIncom);
                editor.putInt("moninac", moneyInac);
                moreMoney = true;
            }else{
                // neither button was pressed
                if(moneyScore == 1){
                    moneyScore = 2;
                }
                moneyIneff++;
                errorTotals++;
                editor.putInt("moneyScore", moneyScore);
                editor.putInt("monineff", moneyIneff);
                editor.putInt("monincom", moneyIncom);
                editor.putInt("moninac", moneyInac);
                moreMoney = true;

            }

        }else if(changeRecordedText == changeGatheredText){
            if(lessMoney){
                moneyInac--;
                moneyIncom--;
                errorTotals-=2;
                if(moneyScore == 3 && moneyIncom == 0){
                    moneyScore = 2;
                }

            }else if(moreMoney){
                moneyIneff--;
                errorTotals--;
                if(moneyScore == 2 && moneyIneff == 0){
                    moneyScore = 1;
                }
            }
            editor.putInt("moneyScore", moneyScore);
            editor.putInt("monineff", moneyIneff);
            editor.putInt("monincom", moneyIncom);
            editor.putInt("moninac", moneyInac);
        }
        editor.putInt("errorTotals", errorTotals);
        editor.putBoolean("moreMoney", moreMoney);
        editor.putBoolean("lessMoney", lessMoney);
        //Toast.makeText(FinalActivity.this, "Change Gathered: " + changeRecordedText, Toast.LENGTH_SHORT).show();
        //Toast.makeText(FinalActivity.this, "Change Recorded " + changeGatheredText, Toast.LENGTH_SHORT).show();
        editor.apply();
        return true;
    }

    public void calculateTime(View v){

        if(alreadyCalculated){
            return;
        }
        SharedPreferences sharedPref = getSharedPreferences("FILENAME", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        int movieScore = sharedPref.getInt("movieScore", 0);
        if(movieScore == 4){
            return;
        }
        int movieInef = sharedPref.getInt("movineff", 0);
        int movieIncom = sharedPref.getInt("movincom", 0);
        int movieInac = sharedPref.getInt("movinac", 0);
        boolean movieEarly = sharedPref.getBoolean("movieEarly", false);
        boolean movieLate = sharedPref.getBoolean("movieLate", false);
        int errorTotals = sharedPref.getInt("errorTotals", 0);

        minutes = time.getCurrentMinute();
        hours = time.getCurrentHour();
        if(minutes < 25 && (hours == 18 || hours == 17 || hours == 16 || hours == 15 || hours == 14 || hours == 13 || hours == 12 || hours == 11 ||
                hours == 10 || hours == 9 || hours == 8 || hours == 7 || hours == 6) && !movieEarly){
            if(movieScore == 1){
                movieScore = 2;
            }
            movieInef++;
            errorTotals++;
            //Toast.makeText(FinalActivity.this, "early", Toast.LENGTH_SHORT).show();
        }else if(minutes > 35 && (hours == 18 || hours == 19 || hours == 20 || hours == 21 || hours == 22 || hours == 23) && !movieLate){
            if(movieScore == 1 || movieScore == 2){
                movieScore = 3;
            }
            movieInac++;
            movieIncom++;
            errorTotals+=2;
            //Toast.makeText(FinalActivity.this, "late", Toast.LENGTH_SHORT).show();
        }
        editor.putInt("movieScore", movieScore);
        editor.putInt("movineff", movieInef);
        editor.putInt("movincom", movieIncom);
        editor.putInt("movinac", movieInac);
        editor.putBoolean("movieLate", movieLate);
        editor.putBoolean("movieEarly", movieEarly);
        editor.putInt("errorTotals", errorTotals);
        editor.apply();
    }
}
