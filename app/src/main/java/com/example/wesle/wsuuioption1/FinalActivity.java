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
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FinalActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView gathered, recorded, leavesHouse, orText;
    private EditText changeRecorded, changeGathered, comments;
    private TimePicker time;
    private RadioGroup radioGroup1, radioGroup2, radioGroup3;
    private int minutes;
    private int hours;
    private boolean alreadyCalculated = false;
    private boolean checkBoxesChecked = false;
    private RadioButton radioY, radioY2, radioY3;
    private Button naButton;
    private boolean timeNotApplicable = false;

    TextView errorsHeader;
    TextView error1;
    EditText errorDescription1;
    TextView error2;
    EditText errorDescription2;
    TextView error3;
    EditText errorDescription3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        time = (TimePicker) findViewById(R.id.timePicker);

        orText = (TextView) findViewById(R.id.or);
        naButton = (Button) findViewById(R.id.timena);

        //this code makes so that the clock is pm by default
        try{
            if(time.getCurrentHour() < 12)
            {
                time.setCurrentHour(time.getCurrentHour()+12);
            }
        }catch(NullPointerException e) {

        }

        SharedPreferences sharedPref = getSharedPreferences("FILENAME", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        leavesHouse = (TextView) findViewById(R.id.leaveshouse);
        gathered = (TextView) findViewById(R.id.gathered);
        recorded = (TextView) findViewById(R.id.recorded);
        changeRecorded = (EditText) findViewById(R.id.editText4);
        changeGathered = (EditText) findViewById(R.id.editText5);
        comments = (EditText) findViewById(R.id.comments);
        radioGroup1 = (RadioGroup) findViewById(R.id.radiogroup1);
        radioGroup2 = (RadioGroup) findViewById(R.id.radiogroup2);
        radioGroup3 = (RadioGroup) findViewById(R.id.radiogroup3);
        radioY = (RadioButton) findViewById(R.id.radioButton);
        radioY2 = (RadioButton) findViewById(R.id.radioButton3);
        radioY3 = (RadioButton) findViewById(R.id.radioButton5);

        if(sharedPref.getInt("moneyScore", 1) == 4){
            gathered.setVisibility(View.GONE);
            recorded.setVisibility(View.GONE);
            changeGathered.setVisibility(View.GONE);
            changeRecorded.setVisibility(View.GONE);
        }


        errorsHeader = (TextView) findViewById(R.id.othererrorsheader);
        error1 = (TextView) findViewById(R.id.error1catagory);
        errorDescription1 = (EditText) findViewById(R.id.error1description);
        error2 = (TextView) findViewById(R.id.error2catagory);
        errorDescription2 = (EditText) findViewById(R.id.error2description);
        error3 = (TextView) findViewById(R.id.error3catagory);
        errorDescription3 = (EditText) findViewById(R.id.error3description);

        int errors = sharedPref.getInt("othererrortotal", 0);

        if(errors > 0){
            for(int i = 0; i<errors; i++){
                switch(i){
                    case 0:
                        error1.setText(sharedPref.getString("errorone", "ERROR CATEGORY NOT FOUND"));
                        error1.setVisibility(View.VISIBLE);
                        errorsHeader.setVisibility(View.VISIBLE);
                        errorDescription1.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        error2.setText(sharedPref.getString("errortwo", "ERROR CATEGORY NOT FOUND"));
                        error2.setVisibility(View.VISIBLE);
                        errorDescription2.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        error3.setText(sharedPref.getString("errorthree", "ERROR CATEGORY NOT FOUND"));
                        error3.setVisibility(View.VISIBLE);
                        errorDescription3.setVisibility(View.VISIBLE);
                        break;
                }
            }
        }

        editor.apply();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.
                INPUT_METHOD_SERVICE);
        try{
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }catch(NullPointerException e){

        }

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

        boolean errorsComplete = validateErrors();

        if(errorsComplete && checkBoxesChecked && (moneyScore == 4 || compareChange())) {
            alreadyCalculated = true;

            Intent i = new Intent(this, SummaryActivity.class);
            startActivity(i);
        }else{
                Toast.makeText(FinalActivity.this, "Please answer every question", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean validateErrors(){
        if(alreadyCalculated){
            return true;
        }
        boolean a = true;
        SharedPreferences sharedPref = getSharedPreferences("FILENAME", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        int errors = sharedPref.getInt("othererrortotal", 0);

        if(errors == 0){
            return true;
        }
        if(errors >= 1){
            if(errorDescription1.getText().length() == 0) {
                editor.putString("error1", errorDescription1.getText().toString());
                a = false;
            }
        }
        if(errors >= 2){
            if(errorDescription2.getText().length() == 0) {
                editor.putString("error2", errorDescription2.getText().toString());
                a = false;
            }
        }
        if(errors == 3){
            if(errorDescription3.getText().length() == 0) {
                editor.putString("error3", errorDescription3.getText().toString());
                a = false;
            }
        }
        editor.apply();
        return a;
    }

    public int calculateSequencing(){
        if(radioGroup1.getCheckedRadioButtonId() == -1 || radioGroup1.getCheckedRadioButtonId() == -1 ||
                radioGroup1.getCheckedRadioButtonId() == -1){
            return 0;
        }else{
            checkBoxesChecked = true;
        }
        if(alreadyCalculated){
            return 0;
        }
        int sequencing = 0;

        SharedPreferences sharedPref = getSharedPreferences("FILENAME", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        if(radioGroup1.getCheckedRadioButtonId() == radioY.getId()){
            sequencing++;
            editor.putString("costOfMovie", "1");
        }else{
            editor.putString("costOfMovie", "2");
        }
        if(radioGroup2.getCheckedRadioButtonId() == radioY2.getId()){
            sequencing++;
            editor.putString("recipeRetrieve", "1");
        }else{
            editor.putString("recipeRetrieve", "2");
        }
        if(radioGroup3.getCheckedRadioButtonId() ==  radioY3.getId()/* && !phoneCallEnd*/) {
            sequencing++;
            editor.putString("phoneNearEnd", "1");
        }else{
            editor.putString("phoneNearEnd", "2");
        }

        editor.apply();

        return sequencing;
    }

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
        if(changeRecordedText > changeGatheredText){

            if(moreMoney){
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
            /*if(moreMoney){
                //do nothing
            }else */if(lessMoney){
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
        editor.putString("changeRecorded", changeRecordedText.toString());
        editor.putString("changeGathered", changeGatheredText.toString());
        editor.apply();
        return true;
    }


    public void timeNA(View v){
        if(naButton.getText().equals("N/A")){
            orText.setText("Time is not applicable");
            naButton.setText("UNDO");
            time.setVisibility(View.GONE);
            timeNotApplicable = true;
        }else{
            orText.setText("OR");
            naButton.setText("N/A");
            timeNotApplicable = false;
            time.setVisibility(View.VISIBLE);
        }

        //orText.setVisibility(View.GONE);

    }



    public void calculateTime(View v){

        if(alreadyCalculated || timeNotApplicable){
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

        }else if(minutes > 35 && (hours == 18 || hours == 19 || hours == 20 || hours == 21 || hours == 22 || hours == 23) && !movieLate){
            if(movieScore == 1 || movieScore == 2){
                movieScore = 3;
            }
            movieInac++;
            errorTotals++;

        }
        editor.putInt("movieScore", movieScore);
        editor.putInt("movineff", movieInef);
        editor.putInt("movincom", movieIncom);
        editor.putInt("movinac", movieInac);
        editor.putBoolean("movieLate", movieLate);
        editor.putBoolean("movieEarly", movieEarly);
        editor.putInt("errorTotals", errorTotals);

        //save time
        editor.putInt("movieHours", hours);
        editor.putInt("movieMinutes", minutes);

        editor.apply();
    }
}
