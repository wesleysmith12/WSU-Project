package com.example.wesle.wsuuioption1;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class SummaryActivity extends AppCompatActivity {

    private TextView movieScore, teaScore, snackScore, changeScore, phoneScore, recipeScore, travelScore, exitScore,
            movieTime, teaTime, snackTime, changeTime, phoneTime, recipeTime, travelTime, exitTime,
            movieMTime, teaMTime, snackMTime, changeMTime, phoneMTime, recipeMTime, travelMTime, exitMTime,
            movieSeq, teaSeq, snackSeq, changeSeq, phoneSeq, recipeSeq, travelSeq, exitSeq,
            movieSimu, teaSimu, snackSimu, changeSimu, phoneSimu, recipeSimu, travelSimu, exitSimu,
            movieIneff, teaIneff, snackIneff, changeIneff, phoneIneff, recipeIneff, travelIneff, exitIneff,
            movieIncom, teaIncom, snackIncom, changeIncom, phoneIncom, recipeIncom, travelIncom, exitIncom,
            movieInac, teaInac, snackInac, changeInac, phoneInac, recipeInac, travelInac, exitInac,
            taskPlanning, totalExecution, overallQuality, overallAccuracy, correctSequencing, detailInput;

    int misc1Count;
    int misc2Count;
    int misc3Count;
    int misc4Count;

    private Button export, restart;
    private EditText dataFileName;

    private int totalAccuracyScorex = 0;
    private int totalIncomplete;
    private int totalInaccurate;
    private int totalInefficient;

    private int totalEfficient;
    private int totalNotAttempted;

    private String filepath = "MyFileStorage";
    private int errorTotalsx;
    File myExternalFile;

    int defaultValue = -1;
    String defaultString = "not found";

    long midTaskPlanningTotalTime;
    int totalInterruptions;

    String teaSeqF;

    //public String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/prototype";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try{
            getSupportActionBar().hide();
        }catch(NullPointerException e){

        }

        setContentView(R.layout.activity_summary);

        SharedPreferences sharedPref = getSharedPreferences("FILENAME", Context.MODE_PRIVATE);

        totalIncomplete = sharedPref.getInt("totalIncomplete", 0);
        totalInaccurate = sharedPref.getInt("totalInaccurate", 0);
        totalInefficient = sharedPref.getInt("totalInefficient", 0);

        midTaskPlanningTotalTime = sharedPref.getLong("timeMidTaskPlanning", -1);
        totalInterruptions = sharedPref.getInt("totalInterruptions", -1);

        totalEfficient = 0;
        totalNotAttempted = 0;

        totalAccuracyScorex = sharedPref.getInt("movieScore", defaultValue) + sharedPref.getInt("teaScore", defaultValue) +
                sharedPref.getInt("snackScore", defaultValue) + sharedPref.getInt("moneyScore", defaultValue) +
                sharedPref.getInt("phoneScore", defaultValue) + sharedPref.getInt("recipeScore", defaultValue) +
                sharedPref.getInt("travelScore", defaultValue)+ sharedPref.getInt("exitScore", defaultValue);

        dataFileName = (EditText) findViewById(R.id.filename);
        export = (Button) findViewById(R.id.export);
        restart = (Button) findViewById(R.id.restart);

        taskPlanning = (TextView) findViewById(R.id.taskplanningtime);
        totalExecution = (TextView) findViewById(R.id.totaltxecutiontime);
        //overallQuality = (TextView) findViewById(R.id.overalltaskquality);
        overallAccuracy = (TextView) findViewById(R.id.overaltotalaccuracy);
        correctSequencing = (TextView) findViewById(R.id.correctsequencingtotal);

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
        //String overallQualityx= sharedPref.getString("overallQuality", defaultString);
        String correctSequencingx = Integer.toString(sharedPref.getInt("correctSequencing", defaultValue));
        errorTotalsx = sharedPref.getInt("errorTotals", defaultValue);

        taskPlanning.setText(planningTime);
        totalExecution.setText(totalExecutionx);
        //overallQuality.setText(overallQualityx);
        overallAccuracy.setText(String.valueOf(totalAccuracyScorex));
        correctSequencing.setText(correctSequencingx);

        misc1Count = sharedPref.getInt("misc1Count", defaultValue);
        misc2Count = sharedPref.getInt("misc2Count", defaultValue);
        misc3Count = sharedPref.getInt("misc3Count", defaultValue);
        misc4Count = sharedPref.getInt("misc4Count", defaultValue);

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

        String[] scores = {movieScoreF, teaScoreF, snackScoreF, changeScoreF, phoneScoreF, recipeScoreF, travelScoreF, exitScoreF};

        for(int i = 0; i < 8; i++){
            if(scores[i].equals("4")){
                totalNotAttempted++;
            }else if(scores[i].equals("1")){
                totalEfficient++;
            }
        }

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
        teaSeqF = sharedPref.getString("teaSeq", defaultString);
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
                                        clearComments();
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

    public void details(View v){

        /***********************************************/

 /*       String filename="whereami.csv";
        File filelocation = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), filename);
        Uri path = Uri.fromFile(filelocation);
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
// set the type to 'email'
        emailIntent .setType("vnd.android.cursor.dir/email");
        String to[] = {"wesultimatef@gmail.com"};
        emailIntent .putExtra(Intent.EXTRA_EMAIL, to);
// the attachment
        emailIntent .putExtra(Intent.EXTRA_STREAM, path);
// the mail subject
        emailIntent .putExtra(Intent.EXTRA_SUBJECT, "Subject");
        startActivity(Intent.createChooser(emailIntent , "Send email..."));*/

        /***********************************************/

        AlertDialog.Builder builder2;

        builder2 = new AlertDialog.Builder(this);
        // Get the layout inflater
        LayoutInflater inflater = getLayoutInflater();

        builder2.setTitle("Error and Task Details");
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        View view = inflater.inflate(R.layout.fragment_details, null);

        detailInput = (TextView) view.findViewById(R.id.detailss);

        final String output = "\n\t\tEfficient Tasks: " + totalEfficient + "\n\t\tTasks Not Attempted: " + totalNotAttempted +
                "\n\t\tTotal Errors: " + errorTotalsx + "\n\t\tTotal Incomplete Errors: " + totalIncomplete +
                "\n\t\tTotal Inaccurate Errors: " + totalInaccurate + "\n\t\tTotal Inefficient Errors: " + totalInefficient +
                "\n\t\tDouble-Checking: " + misc1Count + "\n\t\tSelf-Correction: " + misc2Count + "\n\t\tNTRA: " +
                misc3Count + "\n\t\tQuestion: " + misc4Count + "\n\t\tMid-Task Planning Total Time: " + midTaskPlanningTotalTime +
                "\n\t\tTotal Interruptions: " + totalInterruptions;

        detailInput.setText(output);
        builder2.setView(view)
                // Add action buttons
                .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // sign in the user ...

                    }
                });
        builder2.create();
        builder2.show();

    }

    public void clearComments(){
        SharedPreferences sharedPref = getSharedPreferences("FILENAME", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putString("recipecomments", "");

        editor.putString("travelcomments", "");

        editor.putString("teacomments", "");

        editor.putString("moviecomments", "");

        editor.putString("moneycomments", "");

        editor.putString("phonecomments", "");

        editor.putString("snackcomments", "");

        editor.putString("exitComments", "");

        editor.apply();
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

    public void exportData(View v){
        String enteredName = dataFileName.getText().toString();

        SharedPreferences sharedPref = getSharedPreferences("FILENAME", Context.MODE_PRIVATE);

        String planningTime = Long.toString(sharedPref.getLong("taskplanning", defaultValue));
        Long totalExecutionx = sharedPref.getLong("totalexecution", defaultValue);
        //String overallQualityx= sharedPref.getString("overallQuality", defaultString);
        int correctSequencingx = sharedPref.getInt("correctSequencing", defaultValue);

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
        teaSeqF = sharedPref.getString("teaSeq", defaultString);
        String snackSeqF = sharedPref.getString("snackSeq", defaultString);
        String changeSeqF = sharedPref.getString("moneySeq", defaultString);
        String phoneSeqF = sharedPref.getString("phoneSeq", defaultString);
        String recipeSeqF = sharedPref.getString("recipeSeq", defaultString);
        String travelSeqF = sharedPref.getString("travelSeq", defaultString);
        String exitSeqF = sharedPref.getString("exitSeq", defaultString);

        if(teaSeqF.equals("0")){
            teaSeqF = "0 - 0";
        }
        if(movieSeqF.equals("0")){
            movieSeqF = "0 - 0";
        }
        if(snackSeqF.equals("0")){
            snackSeqF = "0 - 0";
        }
        if(changeSeqF.equals("0")){
            changeSeqF = "0 - 0";
        }
        if(phoneSeqF.equals("0")){
            phoneSeqF = "0 - 0";
        }
        if(recipeSeqF.equals("0")){
            recipeSeqF = "0 - 0";
        }
        if(travelSeqF.equals("0")){
            travelSeqF = "0 - 0";
        }
        if(exitSeqF.equals("0")){
            exitSeqF = "0 - 0";
        }

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
                    FileOutputStream fos = new FileOutputStream(myExternalFile + ".csv");

                    //Participant ID LINE #1
                    fos.write((enteredName + ",").getBytes());

                    //notes LINE #2
                    fos.write((",").getBytes());

                    //Condition LINE #3
                    fos.write(("-1,").getBytes());

                    //date #4
                    fos.write(((sharedPref.getString("date", "00/00/00")) + ",").getBytes());
                    //String date = sharedPref.getString("date", "00/00/00");
                    //Toast.makeText(SummaryActivity.this, sharedPref.getString("date", "00/00/00"), Toast.LENGTH_SHORT).show();

                    //Total Planning Time LINE #5
                    fos.write((planningTime + ",").getBytes());

                    //Total Execution Time LINE #6
                    fos.write((totalExecutionx + ",").getBytes());

                    //Overall Start Time LINE #7
                    fos.write(((sharedPref.getString("overallStartTime", "00:00:00")) + ",").getBytes());
                    //Toast.makeText(SummaryActivity.this, sharedPref.getString("overallStartTime", "-1"), Toast.LENGTH_SHORT).show();

                    //mid-task planning #8
                    fos.write(((sharedPref.getLong("timeMidTaskPlanning", -1)) + ",").getBytes());

                    //total interruptions #9
                    fos.write(((sharedPref.getInt("totalInterruptions", -1)) + ",").getBytes());

                    //Overall Quality LINE #7?
                    //fos.write((overallQualityx + ",").getBytes());
                    //Toast.makeText(SummaryActivity.this, overallQualityx, Toast.LENGTH_SHORT).show();

                    //total accuracy LINE #10
                    fos.write((totalAccuracyScorex + ",").getBytes());
                    //Toast.makeText(SummaryActivity.this, Integer.toString(totalAccuracyScorex), Toast.LENGTH_SHORT).show();

                    //total Correct Sequencing total LINE #11
                    fos.write((correctSequencingx + ",").getBytes());
                    //Toast.makeText(SummaryActivity.this, Integer.toString(correctSequencingx), Toast.LENGTH_SHORT).show();

                    //total errors LINE #12
                    fos.write((errorTotalsx + ",").getBytes());
                    //Toast.makeText(SummaryActivity.this, Integer.toString(errorTotalsx), Toast.LENGTH_SHORT).show();

                    //total inefficient LINE #13
                    fos.write((totalInefficient + ",").getBytes());
                    //Toast.makeText(SummaryActivity.this, Integer.toString(totalInefficient), Toast.LENGTH_SHORT).show();

                    //total incomplete LINE #14
                    fos.write((totalIncomplete + ",").getBytes());
                    //Toast.makeText(SummaryActivity.this, Integer.toString(totalIncomplete), Toast.LENGTH_SHORT).show();

                    //total inaccurate LINE #15
                    fos.write((totalInaccurate + ",").getBytes());
                    //Toast.makeText(SummaryActivity.this, Integer.toString(totalInaccurate), Toast.LENGTH_SHORT).show();

                    //movie completion score LINE #16
                    fos.write((movieScoreF + ",").getBytes());

                    //movie active time LINE #17
                    fos.write((movieTimeF + ",").getBytes());
                    //Toast.makeText(SummaryActivity.this, movieTimeF, Toast.LENGTH_SHORT).show();

                    //movie total time LINE #18
                    fos.write((movieMTimeF + ",").getBytes());
                    //Toast.makeText(SummaryActivity.this, "Is this the movie start time? "  + movieMTimeF, Toast.LENGTH_SHORT).show();

                    //movie start time LINE #19
                    fos.write(((sharedPref.getString("movieStartTime", "00:00:00")) + ",").getBytes());
                    //Toast.makeText(SummaryActivity.this, "movie start time is: " + sharedPref.getString("movieStartTime", "00:00:00"), Toast.LENGTH_SHORT).show();

                    //MOVIE 20: T1MovieSequenceInitiated
                    fos.write((movieSeqF.charAt(0) + ",").getBytes());

                    //MOVIE 21: T1MovieSequenceStop
                    fos.write((movieSeqF.charAt(4) + ",").getBytes());

                    //movie simultaneous LINE #22
                    fos.write((movieSimF + ",").getBytes());

                    //movie interruptions #23
                    fos.write(((sharedPref.getInt("movieCount", -1)) + ",").getBytes());
                    int qwerty = sharedPref.getInt("movieCount", -1);
                    //Toast.makeText(SummaryActivity.this, qwerty + "is the count for movies", Toast.LENGTH_SHORT).show();

                    //movie ineff LINE #24
                    fos.write((movieIneffF + ",").getBytes());

                    //movie incomp LINE #25
                    fos.write((movieIncomF + ",").getBytes());

                    //movie inac LINE #26
                    fos.write((movieInacF + ",").getBytes());

                    //MOVIE 27: T1MovieChangeRecord LINE #27
                    fos.write(((sharedPref.getString("changeRecorded", "-1")) + ",").getBytes());

                    //MOVIE 28: T1MovieTimeLeave LINE #28
                    String minVal, hrVal;

                    int min = sharedPref.getInt("movieMinutesx", -1);
                    int hr = sharedPref.getInt("movieHoursx", -1);

                    if(min == -1 || hr == -1){

                        minVal = "-0";
                        hrVal = "00";

                    }else{
                        if(hr > 12){
                            hr -= 12;
                        }

                        minVal = Integer.toString(min);
                        hrVal = Integer.toString(hr);

                    }

                    if(minVal.length() == 1){
                        minVal = "0" + minVal;
                    }

                    //Toast.makeText(SummaryActivity.this, "user input time: " + hrVal + ":" + minVal , Toast.LENGTH_LONG).show();
                    fos.write((hrVal + ":" + minVal + ",").getBytes());

                    //movie 1 ineff
                    fos.write(((sharedPref.getInt("movie1b", -1)) + ",").getBytes());

                    //movie 2 ineff
                    fos.write(((sharedPref.getInt("movie9b", -1)) + ",").getBytes());

                    //movie 3 ineff
                    fos.write(((sharedPref.getInt("movie2b", -1)) + ",").getBytes());

                    //movie 4 ineff
                    fos.write(((sharedPref.getInt("movie3b", -1)) + ",").getBytes());

                    //movie 5 ineff
                    fos.write(((sharedPref.getInt("movie4b", -1)) + ",").getBytes());

                    //movie 6 ineff
                    fos.write(((sharedPref.getInt("movie5b", -1)) + ",").getBytes());

                    //movie 7 ineff
                    fos.write(((sharedPref.getInt("movie6b", -1)) + ",").getBytes());

                    //movie 8 ineff
                    fos.write(((sharedPref.getInt("movie7b", -1)) + ",").getBytes());

                    //movie 9 ineff
                    fos.write(((sharedPref.getInt("movie8b", -1)) + ",").getBytes());

                    //tea completion score LINE #38
                    fos.write((teaScoreF + ",").getBytes());

                    //tea active time LINE #39
                    fos.write((teaTimeF + ",").getBytes());

                    //tea total time LINE #40
                    fos.write((teaMTimeF + ",").getBytes());

                    //tea start time LINE #41
                    fos.write(((sharedPref.getString("teaStartTime", "00:00:00")) + ",").getBytes());

                    //Tea: T1TeaSequenceinitiated LINE #42
                    fos.write((teaSeqF.charAt(0) + ",").getBytes());

                    //Tea: T1TeaSequenceended LINE #43
                    fos.write((teaSeqF.charAt(4) + ",").getBytes());

                    //Tea Simultaneous LINE #44
                    fos.write((teaSimF + ",").getBytes());

                    //tea interruptions #45
                    fos.write(((sharedPref.getInt("teaCount", -1)) + ",").getBytes());

                    //Tea ineff LINE #46
                    fos.write((teaIneffF + ",").getBytes());

                    //Tea incom LINE #47
                    fos.write((teaIncomF + ",").getBytes());

                    //Tea inacc LINE #48
                    fos.write((teaInacF + ",").getBytes());

                    //tea 1 ineff LINE #49
                    fos.write(((sharedPref.getInt("tea1b", -1)) + ",").getBytes());

                    //tea 2 ineff
                    fos.write(((sharedPref.getInt("tea2b", -1)) + ",").getBytes());

                    //tea 3 ineff
                    fos.write(((sharedPref.getInt("tea3b", -1)) + ",").getBytes());

                    //tea 4 ineff
                    fos.write(((sharedPref.getInt("tea4b", -1)) + ",").getBytes());

                    //tea 5 ineff
                    fos.write(((sharedPref.getInt("tea5b", -1)) + ",").getBytes());

                    //tea 6 ineff
                    fos.write(((sharedPref.getInt("tea6b", -1)) + ",").getBytes());

                    //tea 7 ineff
                    fos.write(((sharedPref.getInt("tea7b", -1)) + ",").getBytes());

                    //tea 8 ineff
                    fos.write(((sharedPref.getInt("tea8b", -1)) + ",").getBytes());

                    //tea 9 ineff
                    fos.write(((sharedPref.getInt("tea9b", -1)) + ",").getBytes());

                    //tea 10 ineff
                    fos.write(((sharedPref.getInt("tea10b", -1)) + ",").getBytes());

                    //tea 11 ineff
                    fos.write(((sharedPref.getInt("tea11b", -1)) + ",").getBytes());


                    /**/


                    //snack score LINE #60
                    fos.write((snackScoreF + ",").getBytes());


                    //snack active time LINE #61
                    fos.write((snackTimeF + ",").getBytes());

                    //snack total time LINE #62
                    fos.write((snackMTimeF + ",").getBytes());

                    //snack start time LINE #63
                    fos.write(((sharedPref.getString("snackStartTime", "00:00:00")) + ",").getBytes());

                    //SNACK 59: T1MovieSequenceInitiated LINE #64
                    fos.write((snackSeqF.charAt(0) + ",").getBytes());

                    //SNACK 60: T1MovieSequenceStop LINE #65
                    fos.write((snackSeqF.charAt(4) + ",").getBytes());

                    //snack simultaneous LINE #66
                    fos.write((snackSimF + ",").getBytes());

                    // snack interruptions #67
                    fos.write(((sharedPref.getInt("snackCount", -1)) + ",").getBytes());

                    //snack ineff LINE #68
                    fos.write((snackIneffF + ",").getBytes());

                    //snack incomp LINE #69
                    fos.write((snackIncomF + ",").getBytes());

                    //snack inac LINE #70
                    fos.write((snackInacF + ",").getBytes());

                    //snack 1 ineff
                    fos.write(((sharedPref.getInt("snack1b", -1)) + ",").getBytes());
                    //Toast.makeText(SummaryActivity.this, "this error should not be a -1 but it is a: " + sharedPref.getInt("snack1b", 404), Toast.LENGTH_LONG).show();

                    //snack 2 ineff
                    fos.write(((sharedPref.getInt("snack2b", -1)) + ",").getBytes());

                    //snack 3 ineff
                    fos.write(((sharedPref.getInt("snack3b", -1)) + ",").getBytes());

                    //snack 4 ineff
                    fos.write(((sharedPref.getInt("snack4b", -1)) + ",").getBytes());

                    //snack 5 ineff
                    fos.write(((sharedPref.getInt("snack5b", -1)) + ",").getBytes());

                    //snack 6 ineff
                    fos.write(((sharedPref.getInt("snack6b", -1)) + ",").getBytes());

                    //snack 7 ineff
                    fos.write(((sharedPref.getInt("snack7b", -1)) + ",").getBytes());

                    //snack 8 ineff
                    fos.write(((sharedPref.getInt("snack8b", -1)) + ",").getBytes());
                    /**/


                    //money completion score LINE #79
                    fos.write((changeScoreF + ",").getBytes());

                    //money active time LINE #80
                    fos.write((moneyTimeF + ",").getBytes());

                    //money total time LINE #81
                    fos.write((changeMTimeF + ",").getBytes());

                    //money start time LINE #82
                    fos.write(((sharedPref.getString("changeStartTime", "00:00:00")) + ",").getBytes());

                    //change 83: T1MoneySequenceInitiated LINE #83
                    fos.write((changeSeqF.charAt(0) + ",").getBytes());

                    //change 84: T1MoneySequenceStop LINE #84
                    fos.write((changeSeqF.charAt(4) + ",").getBytes());

                    //money simultaneous LINE #85
                    fos.write((changeSimF + ",").getBytes());

                    //change interruptions #86
                    fos.write(((sharedPref.getInt("moneyCount", -1)) + ",").getBytes());


                    //money ineff LINE #87
                    fos.write((changeIneffF + ",").getBytes());

                    //money incomp LINE #88
                    fos.write((changeIncomF + ",").getBytes());

                    //money inac LINE #89
                    fos.write((changeInacF + ",").getBytes());

                    //CHANGE GATHERED LINE #90
                    fos.write(((sharedPref.getString("changeGathered", "-1")) + ",").getBytes());

                    //money 1 ineff LINE #91
                    fos.write(((sharedPref.getInt("money1b", -1)) + ",").getBytes());

                    //money 2 ineff LINE #92
                    fos.write(((sharedPref.getInt("money2b", -1)) + ",").getBytes());

                    //money 3 ineff
                    fos.write(((sharedPref.getInt("money3b", -1)) + ",").getBytes());

                    //money 4 ineff
                    fos.write(((sharedPref.getInt("money4b", -1)) + ",").getBytes());

                    //money 5 ineff
                    fos.write(((sharedPref.getInt("money5b", -1)) + ",").getBytes());

                    //money 6 ineff
                    fos.write(((sharedPref.getInt("money6b", -1)) + ",").getBytes());

                    //money 7 ineff
                    fos.write(((sharedPref.getInt("money7b", -1)) + ",").getBytes());

                    //money 8 ineff
                    fos.write(((sharedPref.getInt("money8b", -1)) + ",").getBytes());

                    /**/

                    //phone completion score LINE #99
                    fos.write((phoneScoreF + ",").getBytes());

                    //phone active time LINE #100
                    fos.write((phoneTimeF + ",").getBytes());

                    //phone total time LINE #101
                    fos.write((phoneMTimeF + ",").getBytes());

                    //phone start time LINE #102
                    fos.write(((sharedPref.getString("phoneStartTime", "00:00:00")) + ",").getBytes());

                    //MOVIE: T1MovieSequenceInitiated LINE #103
                    fos.write((phoneSeqF.charAt(0) + ",").getBytes());

                    //MOVIE: T1MovieSequenceStop LINE #104
                    fos.write((phoneSeqF.charAt(4) + ",").getBytes());

                    //phone simultaneous LINE #105
                    fos.write((phoneSimF + ",").getBytes());

                    //phone interruptions #106
                    fos.write(((sharedPref.getInt("phoneCount", -1)) + ",").getBytes());


                    //phone ineff LINE #107
                    fos.write((phoneIneffF + ",").getBytes());

                    //pnone incomp LINE #108
                    fos.write((phoneIncomF + ",").getBytes());

                    //phone inac LINE #109
                    fos.write((phoneInacF + ",").getBytes());

                    //phone 1 ineff
                    fos.write(((sharedPref.getInt("phone1b", -1)) + ",").getBytes());

                    //phone 2 ineff
                    fos.write(((sharedPref.getInt("phone2b", -1)) + ",").getBytes());

                    //phone 3 ineff
                    fos.write(((sharedPref.getInt("phone3b", -1)) + ",").getBytes());

                    //phone 4 ineff
                    fos.write(((sharedPref.getInt("phone4b", -1)) + ",").getBytes());

                    //phone 5 ineff
                    fos.write(((sharedPref.getInt("phone5b", -1)) + ",").getBytes());

                    //phone 6 ineff
                    fos.write(((sharedPref.getInt("phone6b", -1)) + ",").getBytes());

                    //phone 7 ineff
                    fos.write(((sharedPref.getInt("phone7b", -1)) + ",").getBytes());

                    /**/

                    //recipe completion score LINE #117
                    fos.write((recipeScoreF + ",").getBytes());

                    //recipe active time LINE #118
                    fos.write((recipeTimeF + ",").getBytes());

                    //recipe total time LINE #119
                    fos.write((recipeMTimeF + ",").getBytes());

                    //recipe start time LINE #120
                    fos.write(((sharedPref.getString("recipeStartTime", "00:00:00")) + ",").getBytes());

                    //T1MovieSequenceInitiated LINE #121
                    fos.write((recipeSeqF.charAt(0) + ",").getBytes());

                    //MOVIE 60: T1MovieSequenceStop LINE #122
                    fos.write((recipeSeqF.charAt(4) + ",").getBytes());

                    //recipe simultaneous LINE #123
                    fos.write((recipeSimF + ",").getBytes());

                    //recipe interruptions #124
                    fos.write(((sharedPref.getInt("recipeCount", -1)) + ",").getBytes());


                    //recipe ineff LINE #125
                    fos.write((recipeIneffF + ",").getBytes());

                    //recipe incomp LINE #126
                    fos.write((recipeIncomF + ",").getBytes());

                    //recipe inac LINE #127
                    fos.write((recipeInacF + ",").getBytes());

                    //recipe 1 ineff
                    fos.write(((sharedPref.getInt("recipe1b", -1)) + ",").getBytes());

                    //recipe 2 ineff
                    fos.write(((sharedPref.getInt("recipe2b", -1)) + ",").getBytes());

                    //recipe 3 ineff
                    fos.write(((sharedPref.getInt("recipe3b", -1)) + ",").getBytes());

                    //recipe 4 ineff
                    fos.write(((sharedPref.getInt("recipe4b", -1)) + ",").getBytes());

                    //recipe 5 ineff
                    fos.write(((sharedPref.getInt("recipe5b", -1)) + ",").getBytes());

                    //recipe 6 ineff
                    fos.write(((sharedPref.getInt("recipe6b", -1)) + ",").getBytes());

                    //recipe 7 ineff
                    fos.write(((sharedPref.getInt("recipe7b", -1)) + ",").getBytes());

                    //recipe 8 ineff
                    fos.write(((sharedPref.getInt("recipe8b", -1)) + ",").getBytes());

                    //recipe 9 ineff
                    fos.write(((sharedPref.getInt("recipe9b", -1)) + ",").getBytes());

                    //recipe 10 ineff
                    fos.write(((sharedPref.getInt("recipe10b", -1)) + ",").getBytes());

                    //recipe 11 ineff
                    fos.write(((sharedPref.getInt("recipe11b", -1)) + ",").getBytes());

                    //recipe 12 ineff
                    fos.write(((sharedPref.getInt("recipe12b", -1)) + ",").getBytes());

                    //recipe 13 ineff
                    fos.write(((sharedPref.getInt("recipe13b", -1)) + ",").getBytes());

                    //recipe 14 ineff
                    fos.write(((sharedPref.getInt("recipe14b", -1)) + ",").getBytes());

                    //recipe 15 ineff
                    fos.write(((sharedPref.getInt("recipe15b", -1)) + ",").getBytes());

                    /**/

                    //travel completion score LINE #143
                    fos.write((travelScoreF + ",").getBytes());

                    //travel active time LINE #144
                    fos.write((travelTimeF + ",").getBytes());

                    //travel total time LINE #145
                    fos.write((travelMTimeF + ",").getBytes());

                    //travel start time LINE #146
                    fos.write(((sharedPref.getString("travelStartTime", "00:00:00")) + ",").getBytes());

                    //T1MovieSequenceInitiated LINE #147
                    fos.write((travelSeqF.charAt(0) + ",").getBytes());

                    //MOVIE 60: T1MovieSequenceStop LINE #148
                    fos.write((travelSeqF.charAt(4) + ",").getBytes());

                    //travel simultaneous LINE #149
                    fos.write((travelSimF + ",").getBytes());

                    //travel interruptions #150
                    fos.write(((sharedPref.getInt("travelCount", -1)) + ",").getBytes());


                    //travel ineff LINE #151
                    fos.write((travelIneffF + ",").getBytes());

                    //travel incomp LINE #152
                    fos.write((travelIncomF + ",").getBytes());

                    //travel inac LINE #153
                    fos.write((travelInacF + ",").getBytes());

                    //travel 1 ineff
                    fos.write(((sharedPref.getInt("travel1b", -1)) + ",").getBytes());

                    //travel 2 ineff
                    fos.write(((sharedPref.getInt("travel2b", -1)) + ",").getBytes());

                    //travel 3 ineff
                    fos.write(((sharedPref.getInt("travel3b", -1)) + ",").getBytes());

                    //travel 4 ineff
                    fos.write(((sharedPref.getInt("travel4b", -1)) + ",").getBytes());

                    //travel 5 ineff
                    fos.write(((sharedPref.getInt("travel5b", -1)) + ",").getBytes());


                    /**/

                    //exit completion score LINE #159
                    fos.write((exitScoreF + ",").getBytes());

                    //exit active time LINE #160
                    fos.write((exitTimeF + ",").getBytes());

                    //exit total time LINE #161
                    fos.write((exitMTimeF + ",").getBytes());

                    //exit start time LINE #162
                    fos.write(((sharedPref.getString("exitStartTime", "00:00:00")) + ",").getBytes());

                    //MOVIE 59: T1MovieSequenceInitiated LINE #163
                    fos.write((exitSeqF.charAt(0) + ",").getBytes());

                    //MOVIE 60: T1MovieSequenceStop LINE #164
                    fos.write((exitSeqF.charAt(4) + ",").getBytes());

                    //exit simultaneous LINE #165
                    fos.write((exitSimF + ",").getBytes());

                    //exit interruptions #166
                    fos.write(((sharedPref.getInt("exitCount", -1)) + ",").getBytes());


                    //exit ineff LINE #167
                    fos.write((exitIneffF + ",").getBytes());

                    //exit incomp LINE #168
                    fos.write((exitIncomF + ",").getBytes());

                    //exit inac LINE #169
                    fos.write((exitInacF + ",").getBytes());

                    //exit 1 ineff
                    fos.write(((sharedPref.getInt("exit1b", -1)) + ",").getBytes());

                    //exit 2 ineff
                    fos.write(((sharedPref.getInt("exit2b", -1)) + ",").getBytes());

                    //exit 3 ineff
                    fos.write(((sharedPref.getInt("exit3b", -1)) + ",").getBytes());

                    //exit 4 ineff
                    fos.write(((sharedPref.getInt("exit4b", -1)) + ",").getBytes());

                    //mid-task-planning interruptions #174
                    fos.write(((sharedPref.getInt("midTaskPlanningCount", -1)) + ",").getBytes());

                    //NOT FINISH TIME LINE #175
                    fos.write(((sharedPref.getString("overallFinishTime", "-1")) + ",").getBytes());


                    /**/

                    //LINE #176
                    char z = teaSeqF.charAt(0);
                    if(z == '4' || z == '3' || z == '2' || z == '1'){
                        //Toast.makeText(SummaryActivity.this, "Tea is one of the FIRST 4 activities x: " + z, Toast.LENGTH_LONG).show();
                        fos.write(("1,").getBytes());
                    }else{
                        fos.write(("2,").getBytes());
                        //Toast.makeText(SummaryActivity.this, "Tea is one of the LAST 4 activities x: " + z + " which was pulled from: " + teaSeqF, Toast.LENGTH_LONG).show();

                    }

                    //LINE #177
                    char y = travelSeqF.charAt(0);
                    if(y == '4' || y == '3' || y == '2' || y == '1'){
                        fos.write(("1,").getBytes());
                    }else{
                        fos.write(("2,").getBytes());
                    }

                    //LINE #178
                    fos.write(((sharedPref.getString("costOfMovie", "-1")) + ",").getBytes());

                    //LINE #179
                    fos.write(((sharedPref.getString("recipeRetrieve", "-1")) + ",").getBytes());

                    //LINE #180
                    fos.write(((sharedPref.getString("phoneNearEnd", "-1")) + ",").getBytes());

                    //LINE #181
                    char x = exitSeqF.charAt(0);
                    if(x == '7' || x == '8'){
                        fos.write(("1,").getBytes());
                    }else{
                        fos.write(("2,").getBytes());
                    }

                    //misc totals
                    //LINE #182
                    fos.write(((sharedPref.getInt("misc1Count", -1)) + ",").getBytes());
                    //LINE #183
                    fos.write(((sharedPref.getInt("misc2Count", -1)) + ",").getBytes());
                    //LINE #184
                    fos.write(((sharedPref.getInt("misc3Count", -1)) + ",").getBytes());
                    //LINE #185
                    fos.write(((sharedPref.getInt("misc4Count", -1)) + ",").getBytes());

                    // other error count #186
//                  Toast.makeText(SummaryActivity.this, Integer.toString(sharedPref.getInt("othererrortotal", 0)), Toast.LENGTH_SHORT).show();
                    fos.write((Integer.toString(sharedPref.getInt("othererrortotal", -1))).getBytes());


                    /*//fos.write((" , , Movie, Tea, Snack, Change, Phone, Recipe, Travel, Exit,").getBytes());
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
                    fos.write(("misc buttons" + "," + miscButtons).getBytes());*/

                    //save comments to separate file
                    /*if(sharedPref.getString("moviecomments", "").length() > 0){
                        fos.write((sharedPref.getString("moviecomments", "")).getBytes());
                        fos.write("\n".getBytes());
                    }
                    if(sharedPref.getString("teacomments", "").length() > 0){
                        fos.write((sharedPref.getString("teacomments", "")).getBytes());
                        fos.write("\n".getBytes());
                    }
                    if(sharedPref.getString("snackcomments", "").length() > 0){
                        fos.write((sharedPref.getString("snackcomments", "")).getBytes());
                        fos.write("\n".getBytes());
                    }
                    if(sharedPref.getString("moneycomments", "").length() > 0){
                        fos.write((sharedPref.getString("moneycomments", "")).getBytes());
                        fos.write("\n".getBytes());
                    }
                    if(sharedPref.getString("phonecomments", "").length() > 0){
                        fos.write((sharedPref.getString("phonecomments", "")).getBytes());
                        fos.write("\n".getBytes());
                    }
                    if(sharedPref.getString("recipecomments", "").length() > 0){
                        fos.write((sharedPref.getString("recipecomments", "")).getBytes());
                        fos.write("\n".getBytes());
                    }
                    if(sharedPref.getString("travelcomments", "").length() > 0){
                        fos.write((sharedPref.getString("travelcomments", "")).getBytes());
                        fos.write("\n".getBytes());
                    }
                    if(sharedPref.getString("exitcomments", "").length() > 0){
                        fos.write((sharedPref.getString("exitcomments", "")).getBytes());
                        fos.write("\n".getBytes());
                    }*/

                    //write other errors data to file
                    /*if(!sharedPref.getString("error1", "NOERROR").equals("NOERROR")){

                        fos.write(("Other errors added by clinician:").getBytes());
                        fos.write("\n".getBytes());

                        fos.write((sharedPref.getString("errorone","error category not found") + ": " + sharedPref.getString("error1", "")).getBytes());
                        fos.write("\n".getBytes());
                        if(!sharedPref.getString("error2", "NOERROR").equals("NOERROR")){
                            fos.write((sharedPref.getString("errortwo","error category not found") + ": " + sharedPref.getString("error2", "")).getBytes());
                            fos.write("\n".getBytes());
                            if(!sharedPref.getString("error3", "NOERROR").equals("NOERROR")){
                                fos.write((sharedPref.getString("errorthree","error category not found") + ": " + sharedPref.getString("error3", "")).getBytes());
                                fos.write("\n".getBytes());
                            }
                        }
                    }*/
                    fos.close();

                    Toast.makeText(SummaryActivity.this, "File is written and saved to external storage", Toast.LENGTH_SHORT).show();

                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(SummaryActivity.this, "File did not write", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(SummaryActivity.this, "External storage unavailable", Toast.LENGTH_LONG).show();
            }


            /**/

            if(isExternalStorageAvailable()){
                try {
                    FileOutputStream fos2 = new FileOutputStream(myExternalFile + "Comments " + ".csv");



                    //save comments to separate file

                    if(sharedPref.getString("comments", "").length() > 0){
                        fos2.write(("Summary comments: " + sharedPref.getString("comments", "")).getBytes());
                        fos2.write("\n".getBytes());
                    }

                    if(sharedPref.getString("moviecomments", "").length() > 0){
                        fos2.write(("Movie Comments: " + sharedPref.getString("moviecomments", "")).getBytes());
                        fos2.write("\n".getBytes());
                    }
                    if(sharedPref.getString("teacomments", "").length() > 0){
                        fos2.write(("Tea Comments: " + sharedPref.getString("teacomments", "")).getBytes());
                        fos2.write("\n".getBytes());
                    }
                    if(sharedPref.getString("snackcomments", "").length() > 0){
                        fos2.write(("Snack Comments: " + sharedPref.getString("snackcomments", "")).getBytes());
                        fos2.write("\n".getBytes());
                    }
                    if(sharedPref.getString("moneycomments", "").length() > 0){
                        fos2.write(("Money Comments: " + sharedPref.getString("moneycomments", "")).getBytes());
                        fos2.write("\n".getBytes());
                    }
                    if(sharedPref.getString("phonecomments", "").length() > 0){
                        fos2.write(("Phone Comments: " + sharedPref.getString("phonecomments", "")).getBytes());
                        fos2.write("\n".getBytes());
                    }
                    if(sharedPref.getString("recipecomments", "").length() > 0){
                        fos2.write(("Recipe Comments" + sharedPref.getString("recipecomments", "")).getBytes());
                        fos2.write("\n".getBytes());
                    }
                    if(sharedPref.getString("travelcomments", "").length() > 0){
                        fos2.write(("Travel Comments" + sharedPref.getString("travelcomments", "")).getBytes());
                        fos2.write("\n".getBytes());
                    }
                    if(sharedPref.getString("exitcomments", "").length() > 0){
                        fos2.write(("Exit Comments" + sharedPref.getString("exitcomments", "")).getBytes());
                        fos2.write("\n".getBytes());
                    }


                    fos2.close();

                    //Toast.makeText(SummaryActivity.this, "File  is written and saved to external storage", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(SummaryActivity.this, "Comments File did not write", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(SummaryActivity.this, "External storage unavailable", Toast.LENGTH_LONG).show();
            }





            ////// save errors file
            /*if (!isExternalStorageAvailable()) {
                Toast.makeText(SummaryActivity.this, "Storage space unavailable", Toast.LENGTH_SHORT).show();
            }
            else {
                myExternalFile = new File(getExternalFilesDir(filepath), enteredName + "errors");
            }


            //this code prints out buttons that were pressed during experiment.

            if(isExternalStorageAvailable()){
                try {
                    FileOutputStream fos = new FileOutputStream(myExternalFile + ".csv");

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
                    Toast.makeText(SummaryActivity.this, "File saved", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                    //Toast.makeText(SummaryActivity.this, "File did not write", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(SummaryActivity.this, "External storage unavailable", Toast.LENGTH_LONG).show();
            }*/
        }

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
            return true;
        }
        return false;
    }

}
