package com.example.wesle.wsuuioption1;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import java.util.prefs.Preferences;

public class SummaryActivity extends AppCompatActivity {

    private TextView movieScore, teaScore, snackScore, changeScore, phoneScore, recipeScore, travelScore, exitScore,
        movieTime, teaTime, snackTime, changeTime, phoneTime, recipeTime, travelTime, exitTime,
        movieMTime, teaMTime, snackMTime, changeMTime, phoneMTime, recipeMTime, travelMTime, exitMTime,
        movieSeq, teaSeq, snackSeq, changeSeq, phoneSeq, recipeSeq, travelSeq, exitSeq,
        movieSimu, teaSimu, snackSimu, changeSimu, phoneSimu, recipeSimu, travelSimu, exitSimu,
        movieIneff, teaIneff, snackIneff, changeIneff, phoneIneff, recipeIneff, travelIneff, exitIneff,
        movieIncom, teaIncom, snackIncom, changeIncom, phoneIncom, recipeIncom, travelIncom, exitIncom,
        movieInac, teaInac, snackInac, changeInac, phoneInac, recipeInac, travelInac, exitInac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_summary);

        int defaultValue = 100;
        String defaultString = "not found";

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

        SharedPreferences sharedPref = getSharedPreferences("FILENAME", Context.MODE_PRIVATE);

        movieScore.setText(Integer.toString(sharedPref.getInt("movieScore", defaultValue)));
        teaScore.setText(Integer.toString(sharedPref.getInt("teaScore", defaultValue)));
        snackScore.setText(Integer.toString(sharedPref.getInt("snackScore", defaultValue)));
        changeScore.setText(Integer.toString(sharedPref.getInt("moneyScore", defaultValue)));
        phoneScore.setText(Integer.toString(sharedPref.getInt("phoneScore", defaultValue)));
        recipeScore.setText(Integer.toString(sharedPref.getInt("recipeScore", defaultValue)));
        travelScore.setText(Integer.toString(sharedPref.getInt("travelScore", defaultValue)));
        exitScore.setText(Integer.toString(sharedPref.getInt("exitScore", defaultValue)));

        movieTime.setText(Long.toString(sharedPref.getLong("movieActive", defaultValue)));
        teaTime.setText(Long.toString(sharedPref.getLong("teaActive", defaultValue)));
        snackTime.setText(Long.toString(sharedPref.getLong("snackActive", defaultValue)));
        changeTime.setText(Long.toString(sharedPref.getLong("moneyActive", defaultValue)));
        phoneTime.setText(Long.toString(sharedPref.getLong("phoneActive", defaultValue)));
        recipeTime.setText(Long.toString(sharedPref.getLong("recipeActive", defaultValue)));
        travelTime.setText(Long.toString(sharedPref.getLong("travelActive", defaultValue)));
        exitTime.setText(Long.toString(sharedPref.getLong("exitActive", defaultValue)));

        movieMTime.setText(Long.toString(sharedPref.getLong("multiMovie", defaultValue)));
        teaMTime.setText(Long.toString(sharedPref.getLong("multiTea", defaultValue)));
        snackMTime.setText(Long.toString(sharedPref.getLong("multiSnack", defaultValue)));
        changeMTime.setText(Long.toString(sharedPref.getLong("multiMoney", defaultValue)));
        phoneMTime.setText(Long.toString(sharedPref.getLong("multiPhone", defaultValue)));
        recipeMTime.setText(Long.toString(sharedPref.getLong("multiRecipe", defaultValue)));
        travelMTime.setText(Long.toString(sharedPref.getLong("multiTravel", defaultValue)));
        exitMTime.setText(Long.toString(sharedPref.getLong("multiExit", defaultValue)));

        movieSeq.setText(sharedPref.getString("movieSeq", defaultString));
        teaSeq.setText(sharedPref.getString("teaSeq", defaultString));
        snackSeq.setText(sharedPref.getString("snackSeq", defaultString));
        changeSeq.setText(sharedPref.getString("moneySeq", defaultString));
        phoneSeq.setText(sharedPref.getString("phoneSeq", defaultString));
        recipeSeq.setText(sharedPref.getString("recipeSeq", defaultString));
        travelSeq.setText(sharedPref.getString("travelSeq", defaultString));
        exitSeq.setText(sharedPref.getString("exitSeq", defaultString));

        movieSimu.setText(Integer.toString(sharedPref.getInt("movieSim", defaultValue)));
        teaSimu.setText(Integer.toString(sharedPref.getInt("teaSim", defaultValue)));
        snackSimu.setText(Integer.toString(sharedPref.getInt("snackSim", defaultValue)));
        changeSimu.setText(Integer.toString(sharedPref.getInt("moneySim", defaultValue)));
        phoneSimu.setText(Integer.toString(sharedPref.getInt("phoneSim", defaultValue)));
        recipeSimu.setText(Integer.toString(sharedPref.getInt("recipeSim", defaultValue)));
        travelSimu.setText(Integer.toString(sharedPref.getInt("travelSim", defaultValue)));
        exitSimu.setText(Integer.toString(sharedPref.getInt("exitSim", defaultValue)));

        movieIneff.setText(Integer.toString(sharedPref.getInt("movineff", defaultValue)));
        teaIneff.setText(Integer.toString(sharedPref.getInt("teaineff", defaultValue)));
        snackIneff.setText(Integer.toString(sharedPref.getInt("snaineff", defaultValue)));
        changeIneff.setText(Integer.toString(sharedPref.getInt("monineff", defaultValue)));
        phoneIneff.setText(Integer.toString(sharedPref.getInt("phoineff", defaultValue)));
        recipeIneff.setText(Integer.toString(sharedPref.getInt("recineff", defaultValue)));
        travelIneff.setText(Integer.toString(sharedPref.getInt("traineff", defaultValue)));
        exitIneff.setText(Integer.toString(sharedPref.getInt("exiineff", defaultValue)));

        movieIncom.setText(Integer.toString(sharedPref.getInt("movincom", defaultValue)));
        teaIncom.setText(Integer.toString(sharedPref.getInt("teaincom", defaultValue)));
        snackIncom.setText(Integer.toString(sharedPref.getInt("snaincom", defaultValue)));
        changeIncom.setText(Integer.toString(sharedPref.getInt("monincom", defaultValue)));
        phoneIncom.setText(Integer.toString(sharedPref.getInt("phoincom", defaultValue)));
        recipeIncom.setText(Integer.toString(sharedPref.getInt("recincom", defaultValue)));
        travelIncom.setText(Integer.toString(sharedPref.getInt("traincom", defaultValue)));
        exitIncom.setText(Integer.toString(sharedPref.getInt("exiincom", defaultValue)));

        movieInac.setText(Integer.toString(sharedPref.getInt("movinac", defaultValue)));
        teaInac.setText(Integer.toString(sharedPref.getInt("teainac", defaultValue)));
        snackInac.setText(Integer.toString(sharedPref.getInt("snainac", defaultValue)));
        changeInac.setText(Integer.toString(sharedPref.getInt("moninac", defaultValue)));
        phoneInac.setText(Integer.toString(sharedPref.getInt("phoinac", defaultValue)));
        recipeInac.setText(Integer.toString(sharedPref.getInt("recinac", defaultValue)));
        travelInac.setText(Integer.toString(sharedPref.getInt("trainac", defaultValue)));
        exitInac.setText(Integer.toString(sharedPref.getInt("exiinac", defaultValue)));
    }
}
