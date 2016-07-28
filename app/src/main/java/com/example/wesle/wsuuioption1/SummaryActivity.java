package com.example.wesle.wsuuioption1;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.jar.Attributes;
import java.util.prefs.Preferences;

public class SummaryActivity extends AppCompatActivity {

    private TextView movieScore, teaScore, snackScore, changeScore, phoneScore, recipeScore, travelScore, exitScore,
        movieTime, teaTime, snackTime, changeTime, phoneTime, recipeTime, travelTime, exitTime,
        movieMTime, teaMTime, snackMTime, changeMTime, phoneMTime, recipeMTime, travelMTime, exitMTime,
        movieSeq, teaSeq, snackSeq, changeSeq, phoneSeq, recipeSeq, travelSeq, exitSeq,
        movieSimu, teaSimu, snackSimu, changeSimu, phoneSimu, recipeSimu, travelSimu, exitSimu,
        movieIneff, teaIneff, snackIneff, changeIneff, phoneIneff, recipeIneff, travelIneff, exitIneff,
        movieIncom, teaIncom, snackIncom, changeIncom, phoneIncom, recipeIncom, travelIncom, exitIncom,
        movieInac, teaInac, snackInac, changeInac, phoneInac, recipeInac, travelInac, exitInac,
        taskPlanning, totalExecution, overallQuality, overallAccuracy, correctSequencing, errorTotals,
        inefficientTotal, incompleteTotal, inaccurateTotal;

    private Button export, restart;
    private EditText dataFileName;

    private int totalAccuracyScorex = 0;

    private String filename = "SampleFile.txt";
    private String filepath = "MyFileStorage";
    File myExternalFile;
    String myData = "";

    int defaultValue = 0;
    String defaultString = "not found";
    private PopupWindow myPopup;

    //public String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/prototype";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_summary);

        SharedPreferences sharedPref = getSharedPreferences("FILENAME", Context.MODE_PRIVATE);

        int totalIncomplete = sharedPref.getInt("totalIncomplete", 0);
        int totalInaccurate = sharedPref.getInt("totalInaccurate", 0);
        int totalInefficient = sharedPref.getInt("totalInefficient", 0);

        totalAccuracyScorex = sharedPref.getInt("movieScore", defaultValue) + sharedPref.getInt("teaScore", defaultValue) +
                sharedPref.getInt("snackScore", defaultValue) + sharedPref.getInt("moneyScore", defaultValue) +
                sharedPref.getInt("phoneScore", defaultValue) + sharedPref.getInt("recipeScore", defaultValue) +
                sharedPref.getInt("travelScore", defaultValue)+ sharedPref.getInt("exitScore", defaultValue);

        dataFileName = (EditText) findViewById(R.id.filename);
        export = (Button) findViewById(R.id.export);
        restart = (Button) findViewById(R.id.restart);

        taskPlanning = (TextView) findViewById(R.id.taskplanningtime);
        totalExecution = (TextView) findViewById(R.id.totaltxecutiontime);
        overallQuality = (TextView) findViewById(R.id.overalltaskquality);
        overallAccuracy = (TextView) findViewById(R.id.overaltotalaccuracy);
        correctSequencing = (TextView) findViewById(R.id.correctsequencingtotal);
        errorTotals = (TextView) findViewById(R.id.errortotals);
        inefficientTotal = (TextView) findViewById(R.id.inefficient);
        incompleteTotal = (TextView) findViewById(R.id.incomplete);
        inaccurateTotal = (TextView) findViewById(R.id.inaccurate);

        movieScore = (TextView) findViewById(R.id.moviecompletionscore);
        teaScore = (TextView) findViewById(R.id.teacompletionscore);
        snackScore = (TextView) findViewById(R.id.snackcompletionscore);
        changeScore = (TextView) findViewById(R.id.moneycompletionscore);
        phoneScore = (TextView) findViewById(R.id.phonecompletionscore);
        recipeScore = (TextView) findViewById(R.id.recipecompletionscore);
        travelScore = (TextView) findViewById(R.id.travelcompletionscore);
        exitScore = (TextView) findViewById(R.id.exitcompletionscore);

        movieTime = (TextView) findViewById(R.id.movieactivetime);
        teaTime = (TextView) findViewById(R.id.teaactivetime);
        snackTime = (TextView) findViewById(R.id.snackactivetime);
        changeTime = (TextView) findViewById(R.id.moneyactivetime);
        phoneTime = (TextView) findViewById(R.id.phoneactivetime);
        recipeTime = (TextView) findViewById(R.id.recipeactivetime);
        travelTime = (TextView) findViewById(R.id.travelactivetime);
        exitTime = (TextView) findViewById(R.id.exitactivetime);

        movieMTime = (TextView) findViewById(R.id.moviemultitasktime);
        teaMTime = (TextView) findViewById(R.id.teamultitasktime);
        snackMTime = (TextView) findViewById(R.id.snackmultitasktime);
        changeMTime = (TextView) findViewById(R.id.moneymultitasktime);
        phoneMTime = (TextView) findViewById(R.id.phonemultitasktime);
        recipeMTime = (TextView) findViewById(R.id.recipemultitasktime);
        travelMTime = (TextView) findViewById(R.id.travelmultitasktime);
        exitMTime = (TextView) findViewById(R.id.exitmultitasktime);

        movieSeq = (TextView) findViewById(R.id.moviesequencing);
        teaSeq = (TextView) findViewById(R.id.teasequencing);
        snackSeq = (TextView) findViewById(R.id.snacksequencing);
        changeSeq = (TextView) findViewById(R.id.moneysequencing);
        phoneSeq = (TextView) findViewById(R.id.phonesequencing);
        recipeSeq = (TextView) findViewById(R.id.recipesequencing);
        travelSeq = (TextView) findViewById(R.id.travelsequencing);
        exitSeq = (TextView) findViewById(R.id.exitsequencing);

        movieSimu = (TextView) findViewById(R.id.moviesimultaneous);
        teaSimu = (TextView) findViewById(R.id.teasimultaneous);
        snackSimu = (TextView) findViewById(R.id.snacksimultaneous);
        changeSimu = (TextView) findViewById(R.id.moneysimultaneous);
        phoneSimu = (TextView) findViewById(R.id.phonesimultaneous);
        recipeSimu = (TextView) findViewById(R.id.recipesimultaneous);
        travelSimu = (TextView) findViewById(R.id.travelsimultaneous);
        exitSimu = (TextView) findViewById(R.id.exitsimultaneous);

        movieIneff = (TextView) findViewById(R.id.movieinefficienterrors);
        teaIneff = (TextView) findViewById(R.id.teainefficienterrors);
        snackIneff = (TextView) findViewById(R.id.snackinefficienterrors);
        changeIneff = (TextView) findViewById(R.id.moneyinefficienterrors);
        phoneIneff = (TextView) findViewById(R.id.phoneinefficienterrors);
        recipeIneff = (TextView) findViewById(R.id.recipeinefficienterrors);
        travelIneff = (TextView) findViewById(R.id.travelinefficienterrors);
        exitIneff = (TextView) findViewById(R.id.exitinefficienterrors);

        movieIncom = (TextView) findViewById(R.id.movieincompleteerrors);
        teaIncom = (TextView) findViewById(R.id.teaincompleteerrors);
        snackIncom = (TextView) findViewById(R.id.snackincompleteerrors);
        changeIncom = (TextView) findViewById(R.id.moneyincompleteerrors);
        phoneIncom = (TextView) findViewById(R.id.phoneincompleteerrors);
        recipeIncom = (TextView) findViewById(R.id.recipeincompleteerrors);
        travelIncom = (TextView) findViewById(R.id.travelincompleteerrors);
        exitIncom = (TextView) findViewById(R.id.exitincompleteerrors);

        movieInac = (TextView) findViewById(R.id.movieinaccurateerrors);
        teaInac = (TextView) findViewById(R.id.teainaccurateerrors);
        snackInac = (TextView) findViewById(R.id.snackinaccurateerrors);
        changeInac = (TextView) findViewById(R.id.moneyinaccurateerrors);
        phoneInac = (TextView) findViewById(R.id.phoneinaccurateerrors);
        recipeInac = (TextView) findViewById(R.id.recipeinaccurateerrors);
        travelInac = (TextView) findViewById(R.id.travelinaccurateerrors);
        exitInac = (TextView) findViewById(R.id.exitinaccurateerrors);


        String planningTime = Long.toString(sharedPref.getLong("taskplanning", defaultValue));
        String totalExecutionx = Long.toString(sharedPref.getLong("totalexecution", defaultValue));
        String overallQualityx= sharedPref.getString("overallQuality", defaultString);
        String correctSequencingx = Integer.toString(sharedPref.getInt("correctSequencing", defaultValue));
        int errorTotalsx = sharedPref.getInt("errorTotals", defaultValue);
        taskPlanning.setText(planningTime);
        totalExecution.setText(totalExecutionx);
        overallQuality.setText(overallQualityx);
        overallAccuracy.setText(String.valueOf(totalAccuracyScorex));
        correctSequencing.setText(correctSequencingx);
        errorTotals.setText(String.valueOf(errorTotalsx));
        inaccurateTotal.setText(String.valueOf(totalInaccurate));
        incompleteTotal.setText(String.valueOf(totalIncomplete));
        inefficientTotal.setText(String.valueOf(totalInefficient));

        String movieScoreF = Integer.toString(sharedPref.getInt("movieScore", defaultValue));
        String teaScoreF = Integer.toString(sharedPref.getInt("teaScore", defaultValue));
        String snackScoreF = Integer.toString(sharedPref.getInt("snackScore", defaultValue));
        String changeScoreF = Integer.toString(sharedPref.getInt("moneyScore", defaultValue));
        String phoneScoreF = Integer.toString(sharedPref.getInt("phoneScore", defaultValue));
        String recipeScoreF = Integer.toString(sharedPref.getInt("recipeScore", defaultValue));
        String travelScoreF = Integer.toString(sharedPref.getInt("travelScore", defaultValue));
        String exitScoreF = Integer.toString(sharedPref.getInt("exitScore", defaultValue));
        movieScore.setText(movieScoreF);
        teaScore.setText(teaScoreF);
        snackScore.setText(snackScoreF);
        changeScore.setText(changeScoreF);
        phoneScore.setText(phoneScoreF);
        recipeScore.setText(recipeScoreF);
        travelScore.setText(travelScoreF);
        exitScore.setText(exitScoreF);

        String movieTimeF = Long.toString(sharedPref.getLong("movieActive", defaultValue));
        String teaTimeF = Long.toString(sharedPref.getLong("teaActive", defaultValue));
        String snackTimeF = Long.toString(sharedPref.getLong("snackActive", defaultValue));
        String moneyTimeF = Long.toString(sharedPref.getLong("moneyActive", defaultValue));
        String phoneTimeF = Long.toString(sharedPref.getLong("phoneActive", defaultValue));
        String recipeTimeF = Long.toString(sharedPref.getLong("recipeActive", defaultValue));
        String travelTimeF = Long.toString(sharedPref.getLong("travelActive", defaultValue));
        String exitTimeF = Long.toString(sharedPref.getLong("exitActive", defaultValue));
        movieTime.setText(movieTimeF);
        teaTime.setText(teaTimeF);
        snackTime.setText(snackTimeF);
        changeTime.setText(moneyTimeF);
        phoneTime.setText(phoneTimeF);
        recipeTime.setText(recipeTimeF);
        travelTime.setText(travelTimeF);
        exitTime.setText(exitTimeF);

        String movieMTimeF = Long.toString(sharedPref.getLong("multiMovie", defaultValue));
        String teaMTimeF = Long.toString(sharedPref.getLong("multiTea", defaultValue));
        String snackMTimeF = Long.toString(sharedPref.getLong("multiSnack", defaultValue));
        String changeMTimeF = Long.toString(sharedPref.getLong("multiMoney", defaultValue));
        String phoneMTimeF = Long.toString(sharedPref.getLong("multiPhone", defaultValue));
        String recipeMTimeF = Long.toString(sharedPref.getLong("multiRecipe", defaultValue));
        String travelMTimeF = Long.toString(sharedPref.getLong("multiTravel", defaultValue));
        String exitMTimeF = Long.toString(sharedPref.getLong("multiExit", defaultValue));
        movieMTime.setText(movieMTimeF);
        teaMTime.setText(teaMTimeF);
        snackMTime.setText(snackMTimeF);
        changeMTime.setText(changeMTimeF);
        phoneMTime.setText(phoneMTimeF);
        recipeMTime.setText(recipeMTimeF);
        travelMTime.setText(travelMTimeF);
        exitMTime.setText(exitMTimeF);

        String movieSeqF = sharedPref.getString("movieSeq", defaultString);
        String teaSeqF = sharedPref.getString("teaSeq", defaultString);
        String snackSeqF = sharedPref.getString("snackSeq", defaultString);
        String changeSeqF = sharedPref.getString("moneySeq", defaultString);
        String phoneSeqF = sharedPref.getString("phoneSeq", defaultString);
        String recipeSeqF = sharedPref.getString("recipeSeq", defaultString);
        String travelSeqF = sharedPref.getString("travelSeq", defaultString);
        String exitSeqF = sharedPref.getString("exitSeq", defaultString);
        movieSeq.setText(movieSeqF);
        teaSeq.setText(teaSeqF);
        snackSeq.setText(snackSeqF);
        changeSeq.setText(changeSeqF);
        phoneSeq.setText(phoneSeqF);
        recipeSeq.setText(recipeSeqF);
        travelSeq.setText(travelSeqF);
        exitSeq.setText(exitSeqF);

        String movieSimF = Integer.toString(sharedPref.getInt("movieSim", 10));
        String teaSimF = Integer.toString(sharedPref.getInt("teaSim", 10));
        String snackSimF = Integer.toString(sharedPref.getInt("snackSim", 10));
        String changeSimF = Integer.toString(sharedPref.getInt("moneySim", 10));
        String phoneSimF = Integer.toString(sharedPref.getInt("phoneSim", 10));
        String recipeSimF = Integer.toString(sharedPref.getInt("recipeSim", 10));
        String travelSimF = Integer.toString(sharedPref.getInt("travelSim", 10));
        String exitSimF = Integer.toString(sharedPref.getInt("exitSim", 10));
        movieSimu.setText(movieSimF);
        teaSimu.setText(teaSimF);
        snackSimu.setText(snackSimF);
        changeSimu.setText(changeSimF);
        phoneSimu.setText(phoneSimF);
        recipeSimu.setText(recipeSimF);
        travelSimu.setText(travelSimF);
        exitSimu.setText(exitSimF);

        String movieIneffF = Integer.toString(sharedPref.getInt("movineff", defaultValue));
        String teaIneffF = Integer.toString(sharedPref.getInt("teaineff", defaultValue));
        String snackIneffF = Integer.toString(sharedPref.getInt("snaineff", defaultValue));
        String changeIneffF = Integer.toString(sharedPref.getInt("monineff", defaultValue));
        String phoneIneffF = Integer.toString(sharedPref.getInt("phoineff", defaultValue));
        String recipeIneffF = Integer.toString(sharedPref.getInt("recineff", defaultValue));
        String travelIneffF = Integer.toString(sharedPref.getInt("traineff", defaultValue));
        String exitIneffF = Integer.toString(sharedPref.getInt("exiineff", defaultValue));
        movieIneff.setText(movieIneffF);
        teaIneff.setText(teaIneffF);
        snackIneff.setText(snackIneffF);
        changeIneff.setText(changeIneffF);
        phoneIneff.setText(phoneIneffF);
        recipeIneff.setText(recipeIneffF);
        travelIneff.setText(travelIneffF);
        exitIneff.setText(exitIneffF);

        String movieIncomF = Integer.toString(sharedPref.getInt("movincom", defaultValue));
        String teaIncomF = Integer.toString(sharedPref.getInt("teaincom", defaultValue));
        String snackIncomF = Integer.toString(sharedPref.getInt("snaincom", defaultValue));
        String changeIncomF = Integer.toString(sharedPref.getInt("monincom", defaultValue));
        String phoneIncomF = Integer.toString(sharedPref.getInt("phoincom", defaultValue));
        String recipeIncomF = Integer.toString(sharedPref.getInt("recincom", defaultValue));
        String travelIncomF = Integer.toString(sharedPref.getInt("traincom", defaultValue));
        String exitIncomF = Integer.toString(sharedPref.getInt("exiincom", defaultValue));
        movieIncom.setText(movieIncomF);
        teaIncom.setText(teaIncomF);
        snackIncom.setText(snackIncomF);
        changeIncom.setText(changeIncomF);
        phoneIncom.setText(phoneIncomF);
        recipeIncom.setText(recipeIncomF);
        travelIncom.setText(travelIncomF);
        exitIncom.setText(exitIncomF);

        String movieInacF = Integer.toString(sharedPref.getInt("movinac", defaultValue));
        String teaInacF = Integer.toString(sharedPref.getInt("teainac", defaultValue));
        String snackInacF = Integer.toString(sharedPref.getInt("snainac", defaultValue));
        String changeInacF = Integer.toString(sharedPref.getInt("moninac", defaultValue));
        String phoneInacF = Integer.toString(sharedPref.getInt("phoinac", defaultValue));
        String recipeInacF = Integer.toString(sharedPref.getInt("recinac", defaultValue));
        String travelInacF = Integer.toString(sharedPref.getInt("trainac", defaultValue));
        String exitInacF = Integer.toString(sharedPref.getInt("exiinac", defaultValue));
        movieInac.setText(movieInacF);
        teaInac.setText(teaInacF);
        snackInac.setText(snackInacF);
        changeInac.setText(changeInacF);
        phoneInac.setText(phoneInacF);
        recipeInac.setText(recipeInacF);
        travelInac.setText(travelInacF);
        exitInac.setText(exitInacF);

        /*String movieScoreF = Integer.toString(sharedPref.getInt("movieScore", defaultValue));
        String movieActiveF = Long.toString(sharedPref.getLong("movieActive", defaultValue));
        String movieMultiF = Long.toString(sharedPref.getLong("multiMovie", defaultValue));
        String movieSeqF = sharedPref.getString("movieSeq", defaultString);
        String movieSimF = Integer.toString(sharedPref.getInt("movieSim", defaultValue));
        String movieIneffF = Integer.toString(sharedPref.getInt("movineff", defaultValue));
        String movieIncomF = Integer.toString(sharedPref.getInt("movincom", defaultValue));*/


        String string = "Hello world!";
        /*File file = new File(path + "/savedFile.txt");


        FileOutputStream outputStream;

        FileOutputStream fos = null;
        try{
            fos = new FileOutputStream(file);
        }catch(FileNotFoundExceptione e){e.printStackTrace();}*/


        restart = (Button)findViewById(R.id.restart);
        restart.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder a_builder = new AlertDialog.Builder(SummaryActivity.this);
                        a_builder.setMessage("Do you want restart app? All data will be lost")
                                .setCancelable(false)
                                .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        restart();
                                    }
                                })
                                .setNegativeButton("No",new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                }) ;
                        AlertDialog alert = a_builder.create();
                        alert.setTitle("Restart");
                        alert.show();
                    }
                }
        );

    }

    public void restart(){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.
                INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        return true;
    }


    /*public void restart(View v) {
        restart = (Button)findViewById(R.id.restart);
        restart.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder a_builder = new AlertDialog.Builder(SummaryActivity.this);
                        a_builder.setMessage("Do you want to Close this App !!!")
                                .setCancelable(false)
                                .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        finish();
                                    }
                                })
                                .setNegativeButton("No",new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                }) ;
                        AlertDialog alert = a_builder.create();
                        alert.setTitle("Alert !!!");
                        alert.show();
                    }
                }
        );
    }*/

    public void exportData(View v){
        String enteredName = dataFileName.getText().toString();

        SharedPreferences sharedPref = getSharedPreferences("FILENAME", Context.MODE_PRIVATE);

        String planningTime = Long.toString(sharedPref.getLong("taskplanning", defaultValue));
        Long totalExecutionx = sharedPref.getLong("totalexecution", defaultValue);
        String overallQualityx= sharedPref.getString("overallQuality", defaultString);
        int correctSequencingx = sharedPref.getInt("correctSequencing", defaultValue);
        String errorTotalsx = Integer.toString(sharedPref.getInt("errortotals", defaultValue));

        int totalIncomplete = sharedPref.getInt("totalIncomplete", 0);
        int totalInaccurate = sharedPref.getInt("totalInaccurate", 0);
        int totalInefficient = sharedPref.getInt("totalInefficient", 0);

        String movieScoreF = Integer.toString(sharedPref.getInt("movieScore", defaultValue));
        String teaScoreF = Integer.toString(sharedPref.getInt("teaScore", defaultValue));
        String snackScoreF = Integer.toString(sharedPref.getInt("snackScore", defaultValue));
        String changeScoreF = Integer.toString(sharedPref.getInt("moneyScore", defaultValue));
        String phoneScoreF = Integer.toString(sharedPref.getInt("phoneScore", defaultValue));
        String recipeScoreF = Integer.toString(sharedPref.getInt("recipeScore", defaultValue));
        String travelScoreF = Integer.toString(sharedPref.getInt("travelScore", defaultValue));
        String exitScoreF = Integer.toString(sharedPref.getInt("exitScore", defaultValue));

        String movieTimeF = Long.toString(sharedPref.getLong("movieActive", defaultValue));
        String teaTimeF = Long.toString(sharedPref.getLong("teaActive", defaultValue));
        String snackTimeF = Long.toString(sharedPref.getLong("snackActive", defaultValue));
        String moneyTimeF = Long.toString(sharedPref.getLong("moneyActive", defaultValue));
        String phoneTimeF = Long.toString(sharedPref.getLong("phoneActive", defaultValue));
        String recipeTimeF = Long.toString(sharedPref.getLong("recipeActive", defaultValue));
        String travelTimeF = Long.toString(sharedPref.getLong("travelActive", defaultValue));
        String exitTimeF = Long.toString(sharedPref.getLong("exitActive", defaultValue));

        String movieMTimeF = Long.toString(sharedPref.getLong("multiMovie", defaultValue));
        String teaMTimeF = Long.toString(sharedPref.getLong("multiTea", defaultValue));
        String snackMTimeF = Long.toString(sharedPref.getLong("multiSnack", defaultValue));
        String changeMTimeF = Long.toString(sharedPref.getLong("multiMoney", defaultValue));
        String phoneMTimeF = Long.toString(sharedPref.getLong("multiPhone", defaultValue));
        String recipeMTimeF = Long.toString(sharedPref.getLong("multiRecipe", defaultValue));
        String travelMTimeF = Long.toString(sharedPref.getLong("multiTravel", defaultValue));
        String exitMTimeF = Long.toString(sharedPref.getLong("multiExit", defaultValue));

        String movieSeqF = sharedPref.getString("movieSeq", defaultString);
        String teaSeqF = sharedPref.getString("teaSeq", defaultString);
        String snackSeqF = sharedPref.getString("snackSeq", defaultString);
        String changeSeqF = sharedPref.getString("moneySeq", defaultString);
        String phoneSeqF = sharedPref.getString("phoneSeq", defaultString);
        String recipeSeqF = sharedPref.getString("recipeSeq", defaultString);
        String travelSeqF = sharedPref.getString("travelSeq", defaultString);
        String exitSeqF = sharedPref.getString("exitSeq", defaultString);

        String movieSimF = Integer.toString(sharedPref.getInt("movieSim", defaultValue));
        String teaSimF = Integer.toString(sharedPref.getInt("teaSim", defaultValue));
        String snackSimF = Integer.toString(sharedPref.getInt("snackSim", defaultValue));
        String changeSimF = Integer.toString(sharedPref.getInt("moneySim", defaultValue));
        String phoneSimF = Integer.toString(sharedPref.getInt("phoneSim", defaultValue));
        String recipeSimF = Integer.toString(sharedPref.getInt("recipeSim", defaultValue));
        String travelSimF = Integer.toString(sharedPref.getInt("travelSim", defaultValue));
        String exitSimF = Integer.toString(sharedPref.getInt("exitSim", defaultValue));

        String movieIneffF = Integer.toString(sharedPref.getInt("movineff", defaultValue));
        String teaIneffF = Integer.toString(sharedPref.getInt("teaineff", defaultValue));
        String snackIneffF = Integer.toString(sharedPref.getInt("snaineff", defaultValue));
        String changeIneffF = Integer.toString(sharedPref.getInt("monineff", defaultValue));
        String phoneIneffF = Integer.toString(sharedPref.getInt("phoineff", defaultValue));
        String recipeIneffF = Integer.toString(sharedPref.getInt("recineff", defaultValue));
        String travelIneffF = Integer.toString(sharedPref.getInt("traineff", defaultValue));
        String exitIneffF = Integer.toString(sharedPref.getInt("exiineff", defaultValue));

        String movieIncomF = Integer.toString(sharedPref.getInt("movincom", defaultValue));
        String teaIncomF = Integer.toString(sharedPref.getInt("teaincom", defaultValue));
        String snackIncomF = Integer.toString(sharedPref.getInt("snaincom", defaultValue));
        String changeIncomF = Integer.toString(sharedPref.getInt("monincom", defaultValue));
        String phoneIncomF = Integer.toString(sharedPref.getInt("phoincom", defaultValue));
        String recipeIncomF = Integer.toString(sharedPref.getInt("recincom", defaultValue));
        String travelIncomF = Integer.toString(sharedPref.getInt("traincom", defaultValue));
        String exitIncomF = Integer.toString(sharedPref.getInt("exiincom", defaultValue));

        String movieInacF = Integer.toString(sharedPref.getInt("movinac", defaultValue));
        String teaInacF = Integer.toString(sharedPref.getInt("teainac", defaultValue));
        String snackInacF = Integer.toString(sharedPref.getInt("snainac", defaultValue));
        String changeInacF = Integer.toString(sharedPref.getInt("moninac", defaultValue));
        String phoneInacF = Integer.toString(sharedPref.getInt("phoinac", defaultValue));
        String recipeInacF = Integer.toString(sharedPref.getInt("recinac", defaultValue));
        String travelInacF = Integer.toString(sharedPref.getInt("trainac", defaultValue));
        String exitInacF = Integer.toString(sharedPref.getInt("exiinac", defaultValue));

        String comments = sharedPref.getString("comments", "NO COMMENTS WRITTEN");
        boolean misc1 = sharedPref.getBoolean("additionalNTRA", false);
        boolean misc2 = sharedPref.getBoolean("perseveration", false);
        boolean misc3 = sharedPref.getBoolean("wandering", false);
        boolean misc4 = sharedPref.getBoolean("requests help", false);
        String  miscButtons = "";
        if(misc1){
            miscButtons += "additionalNTRA, ";
        }
        if(misc2){
            miscButtons += "perseveration, ";
        }
        if(misc3){
            miscButtons += "wandering, ";
        }
        if(misc4){
            miscButtons += "requests help, ";
        }

        //int length = enteredName.length();
        if(validateFileName(enteredName)){
            if (!isExternalStorageAvailable()) {
                Toast.makeText(SummaryActivity.this, "Storage space unavailable", Toast.LENGTH_SHORT).show();
            }
            else {
                myExternalFile = new File(getExternalFilesDir(filepath), enteredName);
            }

            if(isExternalStorageAvailable()){
                try {
                    FileOutputStream fos = new FileOutputStream(myExternalFile + ".txt");
                    //fos.write((" , , Movie, Tea, Snack, Change, Phone, Recipe, Travel, Exit,").getBytes());
                    fos.write((" , , Score, Time, Multitask Time, Sequence, Simultaneous, Inefficient, Incomplete, Inaccurate,").getBytes());
                    fos.write("\n".getBytes());
                    //fos.write(("Score, " + movieScoreF + "," + teaScoreF + "," + snackScoreF + "," + changeScoreF + "," + phoneScoreF + "," + recipeScoreF + "," + travelScoreF + "," + exitScoreF + ",").getBytes());
                    fos.write(("Movie, " + movieScoreF + "," + movieTimeF + "," + movieMTimeF + "," + movieSeqF + "," + movieSimF + "," + movieIneffF + "," + movieIncomF + "," + movieInacF + ",").getBytes());
                    fos.write("\n".getBytes());
                    fos.write(("Tea, " + teaScoreF + "," + teaTimeF + "," + teaMTimeF + "," + teaSeqF + "," + teaSimF + "," + teaIneffF + "," + teaIncomF + "," + teaInacF + ",").getBytes());
                    //fos.write(("Time, " + movieTimeF + "," + teaTimeF + "," + snackTimeF + "," + moneyTimeF + "," + phoneTimeF + "," + recipeTimeF + "," + travelTimeF + "," + exitTimeF + ",").getBytes());
                    fos.write("\n".getBytes());
                    //fos.write(("Multitask Time, " + movieMTimeF + "," + teaMTimeF + "," + snackMTimeF + "," + changeMTimeF + "," + phoneMTimeF + "," + recipeMTimeF + "," + travelMTimeF + "," + exitMTimeF + ",").getBytes());
                    fos.write(("Snack, " + snackScoreF + "," + snackTimeF + "," + snackMTimeF + "," + snackSeqF + "," + snackSimF + "," + snackIneffF + "," + snackIncomF + "," + snackInacF + ",").getBytes());
                    fos.write("\n".getBytes());
                    //fos.write(("Sequence, " + movieSeqF + "," + teaSeqF + "," + snackSeqF + "," + changeSeqF + "," + phoneSeqF + "," + recipeSeqF + "," + travelSeqF + "," + exitSeqF + ",").getBytes());
                    fos.write(("Change, " + changeScoreF + "," + moneyTimeF + "," + changeMTimeF + "," + changeSeqF + "," + changeSimF + "," + changeIneffF + "," + changeIncomF + "," + changeInacF + ",").getBytes());
                    fos.write("\n".getBytes());
                    fos.write(("Phone, " + phoneScoreF + "," + phoneTimeF + "," + phoneMTimeF + "," + phoneSeqF + "," + phoneSimF + "," + phoneIneffF + "," + phoneIncomF + "," + phoneInacF + ",").getBytes());
                    //fos.write(("Simultaneous, " + movieSimF + "," + teaSimF + "," + snackSimF + "," + changeSimF + "," + phoneSimF + "," + recipeSimF + "," + travelSimF + "," + exitSimF + ",").getBytes());
                    fos.write("\n".getBytes());
                    //fos.write(("Inefficient, " + movieIneffF + "," + teaIneffF + "," + snackIneffF + "," + changeIneffF + "," + phoneIneffF + "," + recipeIneffF + "," + travelIneffF + "," + exitIneffF + ",").getBytes());
                    fos.write(("Recipe, " + recipeScoreF + "," + recipeTimeF + "," + recipeMTimeF + "," + recipeSeqF + "," + recipeSimF + "," + recipeIneffF + "," + recipeIncomF + "," + recipeInacF + ",").getBytes());
                    fos.write("\n".getBytes());
                    //fos.write(("Score, " + movieIncomF + "," + teaIncomF + "," + snackIncomF + "," + changeIncomF + "," + phoneIncomF + "," + recipeIncomF + "," + travelIncomF + "," + exitIncomF + ",").getBytes());
                    fos.write(("Travel, " + travelScoreF + "," + travelTimeF + "," + travelMTimeF + "," + travelSeqF + "," + travelSimF + "," + travelIneffF + "," + travelIncomF + "," + travelInacF + ",").getBytes());
                    fos.write("\n".getBytes());
                    //fos.write(("Inaccurate, " + movieInacF + "," + teaInacF + "," + snackInacF + "," + changeInacF + "," + phoneInacF + "," + recipeInacF + "," + travelInacF + "," + exitInacF + ",").getBytes());
                    fos.write(("Exit, " + exitScoreF + "," + exitTimeF + "," + exitMTimeF + "," + exitSeqF + "," + exitSimF + "," + exitIneffF + "," + exitIncomF + "," + exitInacF + ",").getBytes());
                    fos.write("\n".getBytes());
                    fos.write(("Summary:, Planning, Total Time, Overall Quality, Overall Accuracy, Sequencing Total, Error Total, Inefficient Total, IncompleteTotal, Inaccurate Total").getBytes());
                    fos.write("\n".getBytes());
                    fos.write(("," + planningTime + "," + totalExecutionx + "," + overallQualityx + "," + totalAccuracyScorex + "," + correctSequencingx + "," + errorTotalsx + "," + totalInefficient + "," + totalIncomplete + "," + totalInaccurate).getBytes());
                    fos.write("\n".getBytes());
                    fos.write(("COMMENTS: " + "," + comments).getBytes());
                    fos.write("\n".getBytes());
                    fos.write(("misc buttons" + "," + miscButtons).getBytes());
                    fos.close();

                    Toast.makeText(SummaryActivity.this, "File is written and saved to external storage", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(SummaryActivity.this, "File did not write", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(SummaryActivity.this, "External storage unavailable", Toast.LENGTH_LONG).show();
            }
            ////// save errors file
            if (!isExternalStorageAvailable()) {
                Toast.makeText(SummaryActivity.this, "Storage space unavailable", Toast.LENGTH_SHORT).show();
            }
            else {
                myExternalFile = new File(getExternalFilesDir(filepath), enteredName + "errors");
            }

            if(isExternalStorageAvailable()){
                try {
                    FileOutputStream fos = new FileOutputStream(myExternalFile + ".txt");
                    //fos.write((" , , Movie, Tea, Snack, Change, Phone, Recipe, Travel, Exit,").getBytes());
                    /*fos.write((" , , Score, Time, Multitask Time, Sequence, Simultaneous, Inefficient, Incomplete, Inaccurate,").getBytes());
                    fos.write("\n".getBytes());
                    //fos.write(("Score, " + movieScoreF + "," + teaScoreF + "," + snackScoreF + "," + changeScoreF + "," + phoneScoreF + "," + recipeScoreF + "," + travelScoreF + "," + exitScoreF + ",").getBytes());
                    fos.write(("Movie, " + movieScoreF + "," + movieTimeF + "," + movieMTimeF + "," + movieSeqF + "," + movieSimF + "," + movieIneffF + "," + movieIncomF + "," + movieInacF + ",").getBytes());
                    fos.write("\n".getBytes());
                    fos.write(("Tea, " + teaScoreF + "," + teaTimeF + "," + teaMTimeF + "," + teaSeqF + "," + teaSimF + "," + teaIneffF + "," + teaIncomF + "," + teaInacF + ",").getBytes());
                    //fos.write(("Time, " + movieTimeF + "," + teaTimeF + "," + snackTimeF + "," + moneyTimeF + "," + phoneTimeF + "," + recipeTimeF + "," + travelTimeF + "," + exitTimeF + ",").getBytes());
                    fos.write("\n".getBytes());
                    //fos.write(("Multitask Time, " + movieMTimeF + "," + teaMTimeF + "," + snackMTimeF + "," + changeMTimeF + "," + phoneMTimeF + "," + recipeMTimeF + "," + travelMTimeF + "," + exitMTimeF + ",").getBytes());
                    fos.write(("Snack, " + snackScoreF + "," + snackTimeF + "," + snackMTimeF + "," + snackSeqF + "," + snackSimF + "," + snackIneffF + "," + snackIncomF + "," + snackInacF + ",").getBytes());
                    fos.write("\n".getBytes());
                    //fos.write(("Sequence, " + movieSeqF + "," + teaSeqF + "," + snackSeqF + "," + changeSeqF + "," + phoneSeqF + "," + recipeSeqF + "," + travelSeqF + "," + exitSeqF + ",").getBytes());
                    fos.write(("Change, " + changeScoreF + "," + moneyTimeF + "," + changeMTimeF + "," + changeSeqF + "," + changeSimF + "," + changeIneffF + "," + changeIncomF + "," + changeInacF + ",").getBytes());
                    fos.write("\n".getBytes());
                    fos.write(("Phone, " + phoneScoreF + "," + phoneTimeF + "," + phoneMTimeF + "," + phoneSeqF + "," + phoneSimF + "," + phoneIneffF + "," + phoneIncomF + "," + phoneInacF + ",").getBytes());
                    //fos.write(("Simultaneous, " + movieSimF + "," + teaSimF + "," + snackSimF + "," + changeSimF + "," + phoneSimF + "," + recipeSimF + "," + travelSimF + "," + exitSimF + ",").getBytes());
                    fos.write("\n".getBytes());*/

                    fos.write(("Errors:").getBytes());
                    fos.write("\n".getBytes());
                    if(!sharedPref.getString("movie1b", " ").equals(" ")){
                        fos.write((sharedPref.getString("movie1b", " ")).getBytes());
                        fos.write("\n".getBytes());
                    }
                    if(!sharedPref.getString("movie9b", " ").equals(" ")){
                        fos.write((sharedPref.getString("movie9b", " ")).getBytes());
                        fos.write("\n".getBytes());
                    }
                    if(!sharedPref.getString("movie2b", " ").equals(" ")){
                        fos.write((sharedPref.getString("movie2b", " ")).getBytes());
                        fos.write("\n".getBytes());
                    }
                    if(!sharedPref.getString("movie3b", " ").equals(" ")){
                        fos.write((sharedPref.getString("movie3b", " ")).getBytes());
                        fos.write("\n".getBytes());
                    }
                    if(!sharedPref.getString("movie4b", " ").equals(" ")){
                        fos.write((sharedPref.getString("movie4b", " ")).getBytes());
                        fos.write("\n".getBytes());
                    }
                    if(!sharedPref.getString("movie5b", " ").equals(" ")){
                        fos.write((sharedPref.getString("movie5b", " ")).getBytes());
                        fos.write("\n".getBytes());
                    }
                    if(!sharedPref.getString("movie6b", " ").equals(" ")){
                        fos.write((sharedPref.getString("movie6b", " ")).getBytes());
                        fos.write("\n".getBytes());
                    }
                    if(!sharedPref.getString("movie7b", " ").equals(" ")){
                        fos.write((sharedPref.getString("movie7b", " ")).getBytes());
                        fos.write("\n".getBytes());
                    }
                    if(!sharedPref.getString("movie8b", " ").equals(" ")){
                        fos.write((sharedPref.getString("movie8b", " ")).getBytes());
                        fos.write("\n".getBytes());
                    }
                    if(!sharedPref.getString("money1b", " ").equals(" ")){
                        fos.write((sharedPref.getString("money1b", " ")).getBytes());
                        fos.write("\n".getBytes());
                    }
                    if(!sharedPref.getString("money2b", " ").equals(" ")){
                        fos.write((sharedPref.getString("money2b", " ")).getBytes());
                        fos.write("\n".getBytes());
                    }
                    if(!sharedPref.getString("money3b", " ").equals(" ")){
                        fos.write((sharedPref.getString("money3b", " ")).getBytes());
                        fos.write("\n".getBytes());
                    }
                    if(!sharedPref.getString("money4b", " ").equals(" ")){
                        fos.write((sharedPref.getString("money4b", " ")).getBytes());
                        fos.write("\n".getBytes());
                    }
                    if(!sharedPref.getString("money5b", " ").equals(" ")){
                        fos.write((sharedPref.getString("money5b", " ")).getBytes());
                        fos.write("\n".getBytes());
                    }
                    if(!sharedPref.getString("money6b", " ").equals(" ")){
                        fos.write((sharedPref.getString("money6b", " ")).getBytes());
                        fos.write("\n".getBytes());
                    }
                    if(!sharedPref.getString("money7b", " ").equals(" ")){
                        fos.write((sharedPref.getString("money7b", " ")).getBytes());
                        fos.write("\n".getBytes());
                    }
                    if(!sharedPref.getString("money8b", " ").equals(" ")){
                        fos.write((sharedPref.getString("money8b", " ")).getBytes());
                        fos.write("\n".getBytes());
                    }
                    if(!sharedPref.getString("exit1b", " ").equals(" ")){
                        fos.write((sharedPref.getString("exit1b", " ")).getBytes());
                        fos.write("\n".getBytes());
                    }
                    if(!sharedPref.getString("exit2b", " ").equals(" ")){
                        fos.write((sharedPref.getString("exit2b", " ")).getBytes());
                        fos.write("\n".getBytes());
                    }
                    if(!sharedPref.getString("exit3b", " ").equals(" ")){
                        fos.write((sharedPref.getString("exit3b", " ")).getBytes());
                        fos.write("\n".getBytes());
                    }
                    if(!sharedPref.getString("exit4b", " ").equals(" ")){
                        fos.write((sharedPref.getString("exit4b", " ")).getBytes());
                        fos.write("\n".getBytes());
                    }
                    if(!sharedPref.getString("phone1b", " ").equals(" ")){
                        fos.write((sharedPref.getString("phone1b", " ")).getBytes());
                        fos.write("\n".getBytes());
                    }
                    if(!sharedPref.getString("phone2b", " ").equals(" ")){
                        fos.write((sharedPref.getString("phone2b", " ")).getBytes());
                        fos.write("\n".getBytes());
                    }
                    if(!sharedPref.getString("phone3b", " ").equals(" ")){
                        fos.write((sharedPref.getString("phone3b", " ")).getBytes());
                        fos.write("\n".getBytes());
                    }
                    if(!sharedPref.getString("phone4b", " ").equals(" ")){
                        fos.write((sharedPref.getString("phone4b", " ")).getBytes());
                        fos.write("\n".getBytes());
                    }
                    if(!sharedPref.getString("phone5b", " ").equals(" ")){
                        fos.write((sharedPref.getString("phone5b", " ")).getBytes());
                        fos.write("\n".getBytes());
                    }
                    if(!sharedPref.getString("phone6b", " ").equals(" ")){
                        fos.write((sharedPref.getString("phone6b", " ")).getBytes());
                        fos.write("\n".getBytes());
                    }
                    if(!sharedPref.getString("phone7b", " ").equals(" ")){
                        fos.write((sharedPref.getString("phone7b", " ")).getBytes());
                        fos.write("\n".getBytes());
                    }
                    if(!sharedPref.getString("recipe1b", " ").equals(" ")){
                        fos.write((sharedPref.getString("recipe1b", " ")).getBytes());
                        fos.write("\n".getBytes());
                    }
                    if(!sharedPref.getString("recipe2b", " ").equals(" ")){
                        fos.write((sharedPref.getString("recipe2b", " ")).getBytes());
                        fos.write("\n".getBytes());
                    }
                    if(!sharedPref.getString("recipe3b", " ").equals(" ")){
                        fos.write((sharedPref.getString("recipe3b", " ")).getBytes());
                        fos.write("\n".getBytes());
                    }
                    if(!sharedPref.getString("recipe4b", " ").equals(" ")){
                        fos.write((sharedPref.getString("recipe4b", " ")).getBytes());
                        fos.write("\n".getBytes());
                    }
                    if(!sharedPref.getString("recipe5b", " ").equals(" ")){
                        fos.write((sharedPref.getString("recipe5b", " ")).getBytes());
                        fos.write("\n".getBytes());
                    }
                    if(!sharedPref.getString("recipe6b", " ").equals(" ")){
                        fos.write((sharedPref.getString("recipe6b", " ")).getBytes());
                        fos.write("\n".getBytes());
                    }
                    if(!sharedPref.getString("recipe7b", " ").equals(" ")){
                        fos.write((sharedPref.getString("recipe7b", " ")).getBytes());
                        fos.write("\n".getBytes());
                    }
                    if(!sharedPref.getString("recipe8b", " ").equals(" ")){
                        fos.write((sharedPref.getString("recipe8b", " ")).getBytes());
                        fos.write("\n".getBytes());
                    }
                    if(!sharedPref.getString("recipe9b", " ").equals(" ")){
                        fos.write((sharedPref.getString("recipe9b", " ")).getBytes());
                        fos.write("\n".getBytes());
                    }
                    if(!sharedPref.getString("recipe10b", " ").equals(" ")){
                        fos.write((sharedPref.getString("recipe10b", " ")).getBytes());
                        fos.write("\n".getBytes());
                    }
                    if(!sharedPref.getString("recipe11b", " ").equals(" ")){
                        fos.write((sharedPref.getString("recipe11b", " ")).getBytes());
                        fos.write("\n".getBytes());
                    }
                    if(!sharedPref.getString("recipe12b", " ").equals(" ")){
                        fos.write((sharedPref.getString("recipe12b", " ")).getBytes());
                        fos.write("\n".getBytes());
                    }
                    if(!sharedPref.getString("recipe13b", " ").equals(" ")){
                        fos.write((sharedPref.getString("recipe13b", " ")).getBytes());
                        fos.write("\n".getBytes());
                    }
                    if(!sharedPref.getString("recipe14b", " ").equals(" ")){
                        fos.write((sharedPref.getString("recipe14b", " ")).getBytes());
                        fos.write("\n".getBytes());
                    }
                    if(!sharedPref.getString("recipe15b", " ").equals(" ")){
                        fos.write((sharedPref.getString("recipe15b", " ")).getBytes());
                        fos.write("\n".getBytes());
                    }
                    if(!sharedPref.getString("snack1b", " ").equals(" ")){
                        fos.write((sharedPref.getString("snack1b", " ")).getBytes());
                        fos.write("\n".getBytes());
                    }
                    if(!sharedPref.getString("snack2b", " ").equals(" ")){
                        fos.write((sharedPref.getString("snack2b", " ")).getBytes());
                        fos.write("\n".getBytes());
                    }
                    if(!sharedPref.getString("snack3b", " ").equals(" ")){
                        fos.write((sharedPref.getString("snack3b", " ")).getBytes());
                        fos.write("\n".getBytes());
                    }
                    if(!sharedPref.getString("snack4b", " ").equals(" ")){
                        fos.write((sharedPref.getString("snack4b", " ")).getBytes());
                        fos.write("\n".getBytes());
                    }
                    if(!sharedPref.getString("snack5b", " ").equals(" ")){
                        fos.write((sharedPref.getString("snack5b", " ")).getBytes());
                        fos.write("\n".getBytes());
                    }
                    if(!sharedPref.getString("snack6b", " ").equals(" ")){
                        fos.write((sharedPref.getString("snack6b", " ")).getBytes());
                        fos.write("\n".getBytes());
                    }
                    if(!sharedPref.getString("snack7b", " ").equals(" ")){
                        fos.write((sharedPref.getString("snack7b", " ")).getBytes());
                        fos.write("\n".getBytes());
                    }
                    if(!sharedPref.getString("snack8b", " ").equals(" ")){
                        fos.write((sharedPref.getString("snack8b", " ")).getBytes());
                        fos.write("\n".getBytes());
                    }
                    if(!sharedPref.getString("tea1b", " ").equals(" ")){
                        fos.write((sharedPref.getString("tea1b", " ")).getBytes());
                        fos.write("\n".getBytes());
                    }
                    if(!sharedPref.getString("tea2b", " ").equals(" ")){
                        fos.write((sharedPref.getString("tea2b", " ")).getBytes());
                        fos.write("\n".getBytes());
                    }
                    if(!sharedPref.getString("tea3b", " ").equals(" ")){
                        fos.write((sharedPref.getString("tea3b", " ")).getBytes());
                        fos.write("\n".getBytes());
                    }
                    if(!sharedPref.getString("tea4b", " ").equals(" ")){
                        fos.write((sharedPref.getString("tea4b", " ")).getBytes());
                        fos.write("\n".getBytes());
                    }
                    if(!sharedPref.getString("tea5b", " ").equals(" ")){
                        fos.write((sharedPref.getString("tea5b", " ")).getBytes());
                        fos.write("\n".getBytes());
                    }
                    if(!sharedPref.getString("tea6b", " ").equals(" ")){
                        fos.write((sharedPref.getString("tea6b", " ")).getBytes());
                        fos.write("\n".getBytes());
                    }
                    if(!sharedPref.getString("tea7b", " ").equals(" ")){
                        fos.write((sharedPref.getString("tea7b", " ")).getBytes());
                        fos.write("\n".getBytes());
                    }
                    if(!sharedPref.getString("tea8b", " ").equals(" ")){
                        fos.write((sharedPref.getString("tea8b", " ")).getBytes());
                        fos.write("\n".getBytes());
                    }
                    if(!sharedPref.getString("tea9b", " ").equals(" ")){
                        fos.write((sharedPref.getString("tea9b", " ")).getBytes());
                        fos.write("\n".getBytes());
                    }
                    if(!sharedPref.getString("tea10b", " ").equals(" ")){
                        fos.write((sharedPref.getString("tea10b", " ")).getBytes());
                        fos.write("\n".getBytes());
                    }
                    if(!sharedPref.getString("tea11b", " ").equals(" ")){
                        fos.write((sharedPref.getString("tea11b", " ")).getBytes());
                        fos.write("\n".getBytes());
                    }
                    if(!sharedPref.getString("travel1b", " ").equals(" ")){
                        fos.write((sharedPref.getString("travel1b", " ")).getBytes());
                        fos.write("\n".getBytes());
                    }
                    if(!sharedPref.getString("travel2b", " ").equals(" ")){
                        fos.write((sharedPref.getString("travel2b", " ")).getBytes());
                        fos.write("\n".getBytes());
                    }
                    if(!sharedPref.getString("travel3b", " ").equals(" ")){
                        fos.write((sharedPref.getString("travel3b", " ")).getBytes());
                        fos.write("\n".getBytes());
                    }
                    if(!sharedPref.getString("travel4b", " ").equals(" ")){
                        fos.write((sharedPref.getString("travel4b", " ")).getBytes());
                        fos.write("\n".getBytes());
                    }
                    if(!sharedPref.getString("travel5b", " ").equals(" ")){
                        fos.write((sharedPref.getString("travel5b", " ")).getBytes());
                        fos.write("\n".getBytes());
                    }
                    if(!sharedPref.getString("misc1b", " ").equals(" ")){
                        fos.write((sharedPref.getString("misc1b", " ")).getBytes());
                        fos.write("\n".getBytes());
                    }
                    if(!sharedPref.getString("misc2b", " ").equals(" ")){
                        fos.write((sharedPref.getString("misc2b", " ")).getBytes());
                        fos.write("\n".getBytes());
                    }
                    if(!sharedPref.getString("misc3b", " ").equals(" ")){
                        fos.write((sharedPref.getString("misc3b", " ")).getBytes());
                        fos.write("\n".getBytes());
                    }
                    if(!sharedPref.getString("misc4b", " ").equals(" ")){
                        fos.write((sharedPref.getString("misc4b", " ")).getBytes());
                        fos.write("\n".getBytes());
                    }

                    fos.close();
                    //Toast.makeText(SummaryActivity.this, "File is written and saved to external storage", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(SummaryActivity.this, "File did not write", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(SummaryActivity.this, "External storage unavailable", Toast.LENGTH_LONG).show();
            }
        }

            //Toast.makeText(SummaryActivity.this, "You need a filename with only number and letters", Toast.LENGTH_LONG).show();
    }

    public boolean validateFileName(String name){
        if(name == null || name.length() == 0){
            Toast.makeText(SummaryActivity.this, "You need a filename", Toast.LENGTH_LONG).show();
            return false;
        }
        for(int i = 0; i < name.length(); i++){
            if(!Character.isLetter(name.charAt(i)) && !Character.isDigit(name.charAt(i))){
                Toast.makeText(SummaryActivity.this, "Only use numbers and letters in your filename", Toast.LENGTH_LONG).show();
                return false;
            }
        }
        return true;
    }

    public boolean isExternalStorageAvailable() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(extStorageState)) {
            //Toast.makeText(SummaryActivity.this, "Storage space available", Toast.LENGTH_SHORT).show();
            return true;
        }
        //Toast.makeText(SummaryActivity.this, "Storage space unavailable", Toast.LENGTH_SHORT).show();
        return false;
    }

}


/*
    FileOutputStream fos = new FileOutputStream(myExternalFile + ".txt");
fos.write((" , , Movie, Tea, Snack, Change, Phone, Recipe, Travel, Exit,").getBytes());
        fos.write("\n".getBytes());
        fos.write(("Score, " + movieScoreF + "," + teaScoreF + "," + snackScoreF + "," + changeScoreF + "," + phoneScoreF + "," + recipeScoreF + "," + travelScoreF + "," + exitScoreF + ",").getBytes());
        fos.write("\n".getBytes());
        fos.write(("Time, " + movieTimeF + "," + teaTimeF + "," + snackTimeF + "," + moneyTimeF + "," + phoneTimeF + "," + recipeTimeF + "," + travelTimeF + "," + exitTimeF + ",").getBytes());
        fos.write("\n".getBytes());
        fos.write(("Multitask Time, " + movieMTimeF + "," + teaMTimeF + "," + snackMTimeF + "," + changeMTimeF + "," + phoneMTimeF + "," + recipeMTimeF + "," + travelMTimeF + "," + exitMTimeF + ",").getBytes());
        fos.write("\n".getBytes());
        fos.write(("Sequence, " + movieSeqF + "," + teaSeqF + "," + snackSeqF + "," + changeSeqF + "," + phoneSeqF + "," + recipeSeqF + "," + travelSeqF + "," + exitSeqF + ",").getBytes());
        fos.write("\n".getBytes());
        fos.write(("Simultaneous, " + movieSimF + "," + teaSimF + "," + snackSimF + "," + changeSimF + "," + phoneSimF + "," + recipeSimF + "," + travelSimF + "," + exitSimF + ",").getBytes());
        fos.write("\n".getBytes());
        fos.write(("Inefficient, " + movieIneffF + "," + teaIneffF + "," + snackIneffF + "," + changeIneffF + "," + phoneIneffF + "," + recipeIneffF + "," + travelIneffF + "," + exitIneffF + ",").getBytes());
        fos.write("\n".getBytes());
        fos.write(("Score, " + movieIncomF + "," + teaIncomF + "," + snackIncomF + "," + changeIncomF + "," + phoneIncomF + "," + recipeIncomF + "," + travelIncomF + "," + exitIncomF + ",").getBytes());
        fos.write("\n".getBytes());
        fos.write(("Inaccurate, " + movieInacF + "," + teaInacF + "," + snackInacF + "," + changeInacF + "," + phoneInacF + "," + recipeInacF + "," + travelInacF + "," + exitInacF + ",").getBytes());
        fos.write("\n".getBytes());
        fos.write(("Summary:, Planning, Total Time, Overall Quality, Overall Accuracy, Sequencing Total, Error Total").getBytes());
        fos.write("\n".getBytes());
        fos.write(("," + planningTime + "," + totalExecutionx + "," + overallQualityx + "," + totalAccuracyScorex + "," + correctSequencingx + "," + errorTotalsx + ",").getBytes());
        fos.write("\n".getBytes());
        fos.write(("COMMENTS: " + "," + comments).getBytes());
        fos.write("\n".getBytes());
        fos.write(("misc buttons" + "," + miscButtons).getBytes());
        fos.close();*/
