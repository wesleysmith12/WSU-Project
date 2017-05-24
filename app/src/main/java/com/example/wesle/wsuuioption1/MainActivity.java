package com.example.wesle.wsuuioption1;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.LightingColorFilter;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.content.Intent;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

//changed from appcompat to Activity remove titlebar
public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    //Button btnOnClick;
    private Button mainStart, movieStart, phoneStart, recipeStart, snackStart, teaStart, travelStart, exitStart,
            moneyStart, midTaskPlanningStart, money1, movie9, money2, money3, money4, money5, money6, money7, money8, exit1, exit2, exit3, exit4,
            movie1, movie2, movie3, movie4, movie5, movie6, movie7, movie8, phone1, phone2, phone3, phone4, phone5,
            phone6, phone7, recipe1, recipe2, recipe3, recipe4, recipe5, recipe6, recipe7, recipe8, recipe9, recipe10,
            recipe11, recipe12, recipe13, recipe14, recipe15, snack1, snack2, snack3, snack4, snack5, snack6, snack7, snack8,
            tea1, tea2, tea3, tea4, tea5, tea6, tea7, tea8, tea9, tea10, tea11, travel1, travel2, travel3, travel4,
            travel5, misc1, misc2, misc3, misc4,
            continueBtn;

    ToggleButton customButton;

    AlertDialog dialog;
    AlertDialog dialog1;

    //time variables
    private String overallStartTime;
    private String overallFinishTime;
    private String NOTdate;
    private String recipeStartTime;
    private String exitStartTime;
    private String movieStartTime;
    private String movieFinishTime;
    private String phoneStartTime;
    private String snackStartTime;
    private String changeStartTime;
    private String travelStartTime;
    private String teaStartTime;

    //completion scores
    private int movieScore = 4;
    private int phoneScore = 4;
    private int recipeScore = 4;
    private int snackScore = 4;
    private int teaScore = 4;
    private int travelScore = 4;
    private int exitScore = 4;
    private int moneyScore = 4;

    private long totalExecutionTime = 0;
    private long planningTime = 0;
    private long timeMoney = 0;
    private long multitaskMoney = 0;
    private long moneyTemp = 0;
    private long moneyTemp2 = 0;
    private long timeExit = 0;
    private long multitaskExit = 0;
    private long exitTemp = 0;
    private long exitTemp2 = 0;
    private long timeMovie = 0;
    private long multitaskMovie = 0;
    private long movieTemp = 0;
    private long movieTemp2 = 0;
    private long timePhone = 0;
    private long multitaskPhone = 0;
    private long phoneTemp = 0;
    private long phoneTemp2 = 0;
    private long timeRecipe = 0;
    private long multitaskRecipe = 0;
    private long recipeTemp = 0;
    private long recipeTemp2 = 0;
    private long timeSnack = 0;
    private long multitaskSnack = 0;
    private long snackTemp = 0;
    private long snackTemp2 = 0;
    private long timeTea = 0;
    private long multitaskTea = 0;
    private long teaTemp = 0;
    private long teaTemp2 = 0;
    private long timeTravel = 0;
    private long multitaskTravel = 0;
    private long travelTemp = 0;
    private long travelTemp2 = 0;
    private long timeMidTaskPlanning = 0;
    private long midTaskPlanningTemp = 0;

    private int recipeCount = 0;
    private int exitCount = 0;
    private int movieCount = 0;
    private int phoneCount = 0;
    private int snackCount = 0;
    private int moneyCount = 0;
    private int travelCount = 0;
    private int teaCount = 0;
    private int midTaskPlanningCount = 0;


    private String movieSequencing, phoneSequencing, recipeSequencing, snackSequencing, teaSequencing, travelSequencing, exitSequencing,
            moneySequencing;

    private String recipeCom, movieCom, phoneCom, snackCom, teaCom, travelCom, exitCom, moneyCom;

    int totalInefficient, movieInefficient, phoneInefficient, recipeInefficient, snackInefficient, teaInefficient, travelInefficient,
            exitInefficient, moneyInefficient;

    int totalIncomplete, movieIncomplete, phoneIncomplete, recipeIncomplete, snackIncomplete, teaIncomplete, travelIncomplete, exitIncomplete,
            moneyIncomplete;

    int totalInaccurate, movieInaccurate, phoneInaccurate, recipeInaccurate, snackInaccurate, teaInaccurate, travelInaccurate, exitInaccurate,
            moneyInaccurate;

    private int otherErrors;
    private String[] otherErrorList = new String[3];

    //arrays for sequential score
    int startIndex = 0;
    int ARRAY_SIZE = 8;
    String[] startOrder = new String[ARRAY_SIZE];
    String[] finishOrder = new String[ARRAY_SIZE];
    int simultaneousIndex = 0;
    ArrayList<String> simultaneousOrder = new ArrayList<String>();

    private int start = 1;

    private boolean planFinished = false;
    private boolean NOTstarted = false;

    private boolean money = false;
    private boolean exit = false;
    private boolean movie = false;
    private boolean phone = false;
    private boolean recipe = false;
    private boolean snack = false;
    private boolean tea = false;
    private boolean travel = false;
    private boolean midTaskPlanning = false;

    private boolean movie1b = false;
    private boolean movie9b = false;
    private boolean movie2b = false;
    private boolean movie3b = false;
    private boolean movie4b = false;
    private boolean movie5b = false;
    private boolean movie6b = false;
    private boolean movie7b = false;
    private boolean movie8b = false;

    private boolean money1b = false;
    private boolean money2b = false;
    private boolean money3b = false;
    private boolean money4b = false;
    private boolean money5b = false;
    private boolean money6b = false;
    private boolean money7b = false;
    private boolean money8b = false;

    public long money1T = 0;
    public long money2T = 0;
    public long money3T = 0;
    public long money4T = 0;
    public long money5T = 0;
    public long money6T = 0;
    public long money7T = 0;
    public long money8T = 0;

    private boolean exit1b = false;
    private boolean exit2b = false;
    private boolean exit3b = false;
    private boolean exit4b = false;

    private boolean phone1b = false;
    private boolean phone2b = false;
    private boolean phone3b = false;
    private boolean phone4b = false;
    private boolean phone5b = false;
    private boolean phone6b = false;
    private boolean phone7b = false;

    private boolean recipe1b = false;
    private boolean recipe2b = false;
    private boolean recipe3b = false;
    private boolean recipe4b = false;
    private boolean recipe5b = false;
    private boolean recipe6b = false;
    private boolean recipe7b = false;
    private boolean recipe8b = false;
    private boolean recipe9b = false;
    private boolean recipe10b = false;
    private boolean recipe11b = false;
    private boolean recipe12b = false;
    private boolean recipe13b = false;
    private boolean recipe14b = false;
    private boolean recipe15b = false;

    private boolean snack1b = false;
    private boolean snack2b = false;
    private boolean snack3b = false;
    private boolean snack4b = false;
    private boolean snack5b = false;
    private boolean snack6b = false;
    private boolean snack7b = false;
    private boolean snack8b = false;

    private boolean tea1b = false;
    private boolean tea2b = false;
    private boolean tea3b = false;
    private boolean tea4b = false;
    private boolean tea5b = false;
    private boolean tea6b = false;
    private boolean tea7b = false;
    private boolean tea8b = false;
    private boolean tea9b = false;
    private boolean tea10b = false;
    private boolean tea11b = false;

    private boolean travel1b = false;
    private boolean travel2b = false;
    private boolean travel3b = false;
    private boolean travel4b = false;
    private boolean travel5b = false;

    private boolean misc1b = false;
    private boolean misc2b = false;
    private boolean misc3b = false;
    private boolean misc4b = false;

    private int misc1Count = 0;
    private int misc2Count = 0;
    private int misc3Count = 0;
    private int misc4Count = 0;

    private boolean moreMoney = false;
    private boolean lessMoney = false;
    private boolean movieEarly = false;
    private boolean movieLate = false;
    private boolean phoneCallEnd = false;

    private boolean noMoreTiming = false;

    Button lastButtonClicked;

    AlertDialog.Builder builder1;
    AlertDialog.Builder builder3;

    private GoogleApiClient client;

    boolean[] recipeSimultaneous = {false, false, false, false, false, false, false, false};
    boolean[] movieSimultaneous = {false, false, false, false, false, false, false, false};
    boolean[] snackSimultaneous = {false, false, false, false, false, false, false, false};
    boolean[] travelSimultaneous = {false, false, false, false, false, false, false, false};
    boolean[] exitSimultaneous = {false, false, false, false, false, false, false, false};
    boolean[] phoneSimultaneous = {false, false, false, false, false, false, false, false};
    boolean[] moneySimultaneous = {false, false, false, false, false, false, false, false};
    boolean[] teaSimultaneous = {false, false, false, false, false, false, false, false};
    boolean[] currentSimultaneous = {false, false, false, false, false, false, false, false};


    DateFormat dfDate;
    DateFormat df;
    Date dateobj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        //remove title bar to create more space for this activity
        try{
            getSupportActionBar().hide(); //<< this
        }catch(NullPointerException e){}

        dfDate = new SimpleDateFormat("dd/MM/yy");

        df = new SimpleDateFormat("HH:mm:ss");

        dateobj = new Date();
        NOTdate = dfDate.format(dateobj);

        setContentView(R.layout.activity_main);

        // Assign button variables
        defineButtons();

        otherErrors = 0;

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();


    }

    public void defineButtons() {

        final ToggleButton customButton = (ToggleButton)findViewById(R.id.doiwork);

        //start/stop buttons
        mainStart = (Button) findViewById(R.id.mainstart);
        try{mainStart.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0x006600));}catch(NullPointerException e){}
        movieStart = (Button) findViewById(R.id.moviestart);
        moneyStart = (Button) findViewById(R.id.moneystart);
        exitStart = (Button) findViewById(R.id.exitstart);
        phoneStart = (Button) findViewById(R.id.phonestart);
        recipeStart = (Button) findViewById(R.id.recipestart);
        snackStart = (Button) findViewById(R.id.snackstart);
        teaStart = (Button) findViewById(R.id.teastart);
        travelStart = (Button) findViewById(R.id.travelstart);
        midTaskPlanningStart = (Button) findViewById(R.id.midtaskplanningstart);

        //Define error buttons
        money1 = (Button) findViewById(R.id.money1);
        money2 = (Button) findViewById(R.id.money2);
        money3 = (Button) findViewById(R.id.money3);
        money4 = (Button) findViewById(R.id.money4);
        money5 = (Button) findViewById(R.id.money5);
        money6 = (Button) findViewById(R.id.money6);
        money7 = (Button) findViewById(R.id.money7);
        money8 = (Button) findViewById(R.id.money8);

        exit1 = (Button) findViewById(R.id.exit1);
        exit2 = (Button) findViewById(R.id.exit2);
        exit3 = (Button) findViewById(R.id.exit3);
        exit4 = (Button) findViewById(R.id.exit4);

        movie1 = (Button) findViewById(R.id.movie1);
        movie9 = (Button) findViewById(R.id.movie9);
        movie2 = (Button) findViewById(R.id.movie2);
        movie3 = (Button) findViewById(R.id.movie3);
        movie4 = (Button) findViewById(R.id.movie4);
        movie5 = (Button) findViewById(R.id.movie5);
        movie6 = (Button) findViewById(R.id.movie6);
        movie7 = (Button) findViewById(R.id.movie7);
        movie8 = (Button) findViewById(R.id.movie8);
        continueBtn = (Button) findViewById(R.id.Continue);

        phone1 = (Button) findViewById(R.id.phone1);
        phone2 = (Button) findViewById(R.id.phone2);
        phone3 = (Button) findViewById(R.id.phone3);
        phone4 = (Button) findViewById(R.id.phone4);
        phone5 = (Button) findViewById(R.id.phone5);
        phone6 = (Button) findViewById(R.id.phone6);
        phone7 = (Button) findViewById(R.id.phone7);

        recipe1 = (Button) findViewById(R.id.recipe1);
        recipe2 = (Button) findViewById(R.id.recipe2);
        recipe3 = (Button) findViewById(R.id.recipe3);
        recipe4 = (Button) findViewById(R.id.recipe4);
        recipe5 = (Button) findViewById(R.id.recipe5);
        recipe6 = (Button) findViewById(R.id.recipe6);
        recipe7 = (Button) findViewById(R.id.recipe7);
        recipe8 = (Button) findViewById(R.id.recipe8);
        recipe9 = (Button) findViewById(R.id.recipe9);
        recipe10 = (Button) findViewById(R.id.recipe10);
        recipe11 = (Button) findViewById(R.id.recipe11);
        recipe12 = (Button) findViewById(R.id.recipe12);
        recipe13 = (Button) findViewById(R.id.recipe13);
        recipe14 = (Button) findViewById(R.id.recipe14);
        recipe15 = (Button) findViewById(R.id.recipe15);

        snack1 = (Button) findViewById(R.id.snack1);
        snack2 = (Button) findViewById(R.id.snack2);
        snack3 = (Button) findViewById(R.id.snack3);
        snack4 = (Button) findViewById(R.id.snack4);
        snack5 = (Button) findViewById(R.id.snack5);
        snack6 = (Button) findViewById(R.id.snack6);
        snack7 = (Button) findViewById(R.id.snack7);
        snack8 = (Button) findViewById(R.id.snack8);

        tea1 = (Button) findViewById(R.id.tea1);
        tea2 = (Button) findViewById(R.id.tea2);
        tea3 = (Button) findViewById(R.id.tea3);
        tea4 = (Button) findViewById(R.id.tea4);
        tea5 = (Button) findViewById(R.id.tea5);
        tea6 = (Button) findViewById(R.id.tea6);
        tea7 = (Button) findViewById(R.id.tea7);
        tea8 = (Button) findViewById(R.id.tea8);
        tea9 = (Button) findViewById(R.id.tea9);
        tea10 = (Button) findViewById(R.id.tea10);
        tea11 = (Button) findViewById(R.id.tea11);

        travel1 = (Button) findViewById(R.id.travel1);
        travel2 = (Button) findViewById(R.id.travel2);
        travel3 = (Button) findViewById(R.id.travel3);
        travel4 = (Button) findViewById(R.id.travel4);
        travel5 = (Button) findViewById(R.id.travel5);

        misc1 = (Button) findViewById(R.id.misc1);
        misc2 = (Button) findViewById(R.id.misc2);
        misc3 = (Button) findViewById(R.id.misc3);
        misc4 = (Button) findViewById(R.id.misc4);

        try{
            customButton.setOnClickListener(MainActivity.this);
        }catch(NullPointerException e){

        }

        //Set on click listeners
        movieStart.setOnClickListener(MainActivity.this);
        moneyStart.setOnClickListener(MainActivity.this);
        exitStart.setOnClickListener(MainActivity.this);
        phoneStart.setOnClickListener(MainActivity.this);
        recipeStart.setOnClickListener(MainActivity.this);
        snackStart.setOnClickListener(MainActivity.this);
        travelStart.setOnClickListener(MainActivity.this);
        teaStart.setOnClickListener(MainActivity.this);
        midTaskPlanningStart.setOnClickListener(MainActivity.this);

        money1.setOnClickListener(MainActivity.this);
        money2.setOnClickListener(MainActivity.this);
        money3.setOnClickListener(MainActivity.this);
        money4.setOnClickListener(MainActivity.this);
        money5.setOnClickListener(MainActivity.this);
        money6.setOnClickListener(MainActivity.this);
        money7.setOnClickListener(MainActivity.this);
        money8.setOnClickListener(MainActivity.this);

        exit1.setOnClickListener(MainActivity.this);
        exit2.setOnClickListener(MainActivity.this);
        exit3.setOnClickListener(MainActivity.this);
        exit4.setOnClickListener(MainActivity.this);

        movie1.setOnClickListener(MainActivity.this);
        movie9.setOnClickListener(MainActivity.this);
        movie2.setOnClickListener(MainActivity.this);
        movie3.setOnClickListener(MainActivity.this);
        movie4.setOnClickListener(MainActivity.this);
        movie5.setOnClickListener(MainActivity.this);
        movie6.setOnClickListener(MainActivity.this);
        movie7.setOnClickListener(MainActivity.this);
        movie8.setOnClickListener(MainActivity.this);
        continueBtn.setOnClickListener(MainActivity.this);

        phone1.setOnClickListener(MainActivity.this);
        phone2.setOnClickListener(MainActivity.this);
        phone3.setOnClickListener(MainActivity.this);
        phone4.setOnClickListener(MainActivity.this);
        phone5.setOnClickListener(MainActivity.this);
        phone6.setOnClickListener(MainActivity.this);
        phone7.setOnClickListener(MainActivity.this);

        recipe1.setOnClickListener(MainActivity.this);
        recipe2.setOnClickListener(MainActivity.this);
        recipe3.setOnClickListener(MainActivity.this);
        recipe4.setOnClickListener(MainActivity.this);
        recipe5.setOnClickListener(MainActivity.this);
        recipe6.setOnClickListener(MainActivity.this);
        recipe7.setOnClickListener(MainActivity.this);
        recipe8.setOnClickListener(MainActivity.this);
        recipe9.setOnClickListener(MainActivity.this);
        recipe10.setOnClickListener(MainActivity.this);
        recipe11.setOnClickListener(MainActivity.this);
        recipe12.setOnClickListener(MainActivity.this);
        recipe13.setOnClickListener(MainActivity.this);
        recipe14.setOnClickListener(MainActivity.this);
        recipe15.setOnClickListener(MainActivity.this);

        snack1.setOnClickListener(MainActivity.this);
        snack2.setOnClickListener(MainActivity.this);
        snack3.setOnClickListener(MainActivity.this);
        snack4.setOnClickListener(MainActivity.this);
        snack5.setOnClickListener(MainActivity.this);
        snack6.setOnClickListener(MainActivity.this);
        snack7.setOnClickListener(MainActivity.this);
        snack8.setOnClickListener(MainActivity.this);

        tea1.setOnClickListener(MainActivity.this);
        tea2.setOnClickListener(MainActivity.this);
        tea3.setOnClickListener(MainActivity.this);
        tea4.setOnClickListener(MainActivity.this);
        tea5.setOnClickListener(MainActivity.this);
        tea6.setOnClickListener(MainActivity.this);
        tea7.setOnClickListener(MainActivity.this);
        tea8.setOnClickListener(MainActivity.this);
        tea9.setOnClickListener(MainActivity.this);
        tea10.setOnClickListener(MainActivity.this);
        tea11.setOnClickListener(MainActivity.this);

        travel1.setOnClickListener(MainActivity.this);
        travel2.setOnClickListener(MainActivity.this);
        travel3.setOnClickListener(MainActivity.this);
        travel4.setOnClickListener(MainActivity.this);
        travel5.setOnClickListener(MainActivity.this);

        misc1.setOnClickListener(MainActivity.this);
        misc2.setOnClickListener(MainActivity.this);
        misc3.setOnClickListener(MainActivity.this);
        misc4.setOnClickListener(MainActivity.this);

        // change default color of inaccurate buttons
        movie5.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xA9A9A9));
        movie6.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xA9A9A9));
        movie7.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xA9A9A9));
        movie8.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xA9A9A9));

        tea7.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xA9A9A9));
        tea8.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xA9A9A9));
        tea9.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xA9A9A9));
        tea10.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xA9A9A9));
        tea11.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xA9A9A9));

        snack5.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xA9A9A9));
        snack6.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xA9A9A9));
        snack7.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xA9A9A9));
        snack8.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xA9A9A9));

        money5.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xA9A9A9));
        money6.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xA9A9A9));
        money7.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xA9A9A9));
        money8.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xA9A9A9));

        phone5.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xA9A9A9));
        phone6.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xA9A9A9));
        phone7.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xA9A9A9));

        recipe10.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xA9A9A9));
        recipe11.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xA9A9A9));
        recipe12.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xA9A9A9));
        recipe13.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xA9A9A9));
        recipe14.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xA9A9A9));
        recipe15.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xA9A9A9));

        travel4.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xA9A9A9));
        travel5.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xA9A9A9));

        exit3.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xA9A9A9));
        exit4.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xA9A9A9));

        // this creates the dialog but does not display it. That way when checked boxes are checked off,
        // and user closes the window, the checks are saved on the dialog box
        builder1 = new AlertDialog.Builder(this);

        LayoutInflater inflater = this.getLayoutInflater();
        builder1.setView(inflater.inflate(R.layout.fragment_ingredients, null))
                // Add action buttons
                .setPositiveButton("okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // sign in the user ...
                    }
                });
        dialog1 = builder1.create();
    }

    public void startNOT(View v) {

        if (start == 1) {

            totalExecutionTime = System.currentTimeMillis();

            // get start timestamp
            dateobj = new Date();
            overallStartTime = df.format(dateobj);
            //Toast.makeText(MainActivity.this, overallStartTime, Toast.LENGTH_SHORT).show();

            mainStart.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xf9152f));
            mainStart.setText("Stop NOT");
            start = 2;
            NOTstarted = true;

            //this is for the toggle button class
            ToggleButton.started = true;

        } else if (start == 2) {

            //get finish time of eperinment
            dateobj = new Date();
            overallFinishTime = df.format(dateobj);
            //Toast.makeText(MainActivity.this, overallFinishTime, Toast.LENGTH_SHORT).show();

            //stops experinment
            AlertDialog.Builder a_builder = new AlertDialog.Builder(MainActivity.this);
            a_builder.setMessage("Test will end. Are you sure you want to Stop NOT??")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //stop all timers
                            stopTimers();
                            totalExecutionTime = System.currentTimeMillis() - totalExecutionTime;
                            mainStart.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0x006600));
                            mainStart.setText("Reset");
                            continueBtn.setVisibility(View.VISIBLE);
                            start = 3;
                            noMoreTiming = true;

                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = a_builder.create();
            alert.setTitle("WARNING");
            alert.show();

        } else if (start == 3) {
            // restart activity
            AlertDialog.Builder a_builder = new AlertDialog.Builder(MainActivity.this);
            a_builder.setMessage("Are you sure you want to restart? All data will be lost")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //restart activity
                            clearComments();
                            restartMainActivity();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = a_builder.create();
            alert.setTitle("WARNING");
            alert.show();
        }
    }

    public int calculateSequencing() {
        int[] scoresArray = {movieScore, moneyScore, travelScore, teaScore, exitScore, phoneScore, recipeScore, snackScore};
        int correctSequencing = 0;
        int totalTasksAttempted = 0;
        for (int i = 0; i < 8; i++) {
            switch (scoresArray[i]) {
                case 1:
                    totalTasksAttempted++;
                    break;
                case 2:
                    totalTasksAttempted++;
                    break;
                case 3:
                    totalTasksAttempted++;
                    break;
                case 4:
                    break;
            }
        }
        if (teaScore != 4 && Character.getNumericValue(teaSequencing.charAt(0)) <= 4) {

            correctSequencing++;

        }
        if (travelScore != 4 && Character.getNumericValue(travelSequencing.charAt(0)) <= 4) {

            correctSequencing++;

        }

        // the code below is now entered manually in the next activity
        /*if (phoneScore != 4 && (Character.getNumericValue(phoneSequencing.charAt(0)) == totalTasksAttempted ||
                (exitScore != 4 && Character.getNumericValue(phoneSequencing.charAt(0)) == (totalTasksAttempted - 1)
                        && Character.getNumericValue(exitSequencing.charAt(0)) == totalTasksAttempted))) {
            phoneCallEnd = true;
            correctSequencing++;
        }*/

        if (travelScore != 4 && (Character.getNumericValue(travelSequencing.charAt(4)) == (totalTasksAttempted - 1) ||
                Character.getNumericValue(travelSequencing.charAt(4)) == totalTasksAttempted)) {

            correctSequencing++;

        }
        return correctSequencing;
    }

    public String calculateQuality() {

        int[] scoresArray = {movieScore, moneyScore, travelScore, teaScore, exitScore, phoneScore, recipeScore, snackScore};
        int incomplete = 0;
        int inefficient = 0;
        int unattempted = 0;

        for (int i = 0; i < 8; i++) {

            switch (scoresArray[i]) {

                case 1:
                    break;
                case 2:
                    inefficient++;
                    break;
                case 3:
                    incomplete++;
                    break;
                case 4:
                    unattempted++;
                    break;
            }
        }
        if (unattempted + incomplete > 4) {

            return "Poor";

        } else if (unattempted + incomplete > 2) {

            return "Fair";

        } else if (unattempted + incomplete > 0) {

            return "Good";

        } else if (unattempted + incomplete == 0 && inefficient > 0) {

            return "Very Good";

        } else {

            return "Excellent";

        }

    }

    public void calculatePlanning() {

        planningTime = System.currentTimeMillis() - totalExecutionTime;

        totalExecutionTime = System.currentTimeMillis();

    }

    @Override
    public void onClick(View v) {

        if (!NOTstarted) {

            Toast.makeText(MainActivity.this, "Please select start button before starting any tasks", Toast.LENGTH_SHORT).show();

            return;
        }
        if (start == 1) {

            startNOT(v);

        }
        switch (v.getId()) {

            case R.id.doiwork:
                int state = ((ToggleButton)v).getState();
                switch(state)
                {
                    case 0:
                        //((ToggleButton)v).setText("CASE 0");
                        break;
                    case 1: // Do whatever is needed when the button changes to state 1
                        //((ToggleButton)v).setText("CASE 1");
                        break;
                    case 2: // Do whatever is needed when the button changes to state 2
                        ((ToggleButton)v).setText("CASE 2");
                        v.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0x981e32));
                        break;
                    default:break; // Should never occur
                }
                break;
            case R.id.money1:
                //Toast.makeText(MainActivity.this, "onClickListener working", Toast.LENGTH_SHORT).show();
                if (moneyTemp <= 0) {
                    return;
                }
                if (!money1b) {
                    money1T = System.currentTimeMillis() - totalExecutionTime;
                    money1.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0x981e32));
                    //money1.setTypeface(Typeface.DEFAULT);
                    moneyInefficient++;
                    money1b = true;
                    if (moneyScore == 1) {
                        moneyScore = 2;
                    }
                } else if (money1b) {
                    money1.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xD7D7D7));
                    //money1.setTypeface(null, Typeface.BOLD);
                    moneyInefficient--;
                    money1b = false;
                    //lower score based on which other buttons have already been pressed
                    if (moneyScore == 2 && moneyInefficient == 0) {
                        moneyScore = 1;
                    }
                }
                //lastButtonClicked = money1;
                break;
            case R.id.money2:
                if (moneyTemp <= 0) {
                    return;
                }
                if (!money2b) {
                    money2T = System.currentTimeMillis() - totalExecutionTime;
                    money2.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0x981e32));
                    //money2.setTypeface(Typeface.DEFAULT);
                    moneyInefficient++;
                    money2b = true;
                    if (moneyScore == 1) {
                        moneyScore = 2;
                    }
                } else if (money2b) {
                    money2.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xD7D7D7));
                    //money2.setTypeface(null, Typeface.BOLD);
                    moneyInefficient--;
                    money2b = false;
                    //lower score based on which other buttons have already been pressed
                    if (moneyScore == 2 && moneyInefficient == 0) {
                        moneyScore = 1;
                    }
                }
                //lastButtonClicked = money2;
                break;
            case R.id.money3:
                if (moneyTemp <= 0) {
                    return;
                }
                if (!money3b) {
                    money3T = System.currentTimeMillis() - totalExecutionTime;
                    money3.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0x981e32));
                    //money3.setTypeface(Typeface.DEFAULT);
                    moneyInefficient++;
                    money3b = true;
                    moreMoney = true;
                    if (moneyScore == 1) {
                        moneyScore = 2;
                    }
                    //if opposite button is pressed
                    if (money5b) {
                        money5.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xA9A9A9));
                        //money5.setTypeface(null, Typeface.BOLD);
                        moneyInaccurate--;
                        money5b = false;
                        lessMoney = false;
                        //lower score based on which other buttons have already been pressed
                        if (moneyScore == 3 && moneyIncomplete == 0 && moneyInaccurate == 0 && moneyInefficient == 0) {
                            moneyScore = 1;
                        } else if (moneyScore == 3 && moneyIncomplete == 0 && moneyInaccurate == 0 && moneyInefficient > 0) {
                            moneyScore = 2;
                        }
                    }
                } else if (money3b) {
                    money3.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xD7D7D7));
                    //money3.setTypeface(null, Typeface.BOLD);
                    moneyInefficient--;
                    money3b = false;
                    moreMoney = false;
                    //lower score based on which other buttons have already been pressed
                    if (moneyScore == 2 && moneyInefficient == 0) {
                        moneyScore = 1;
                    }
                }
                //lastButtonClicked = money3;
                break;
            case R.id.money4:
                if (moneyTemp <= 0) {
                    return;
                }
                if (!money4b) {
                    money4T = System.currentTimeMillis() - totalExecutionTime;
                    money4.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0x981e32));
                    //money4.setTypeface(Typeface.DEFAULT);
                    moneyInefficient++;
                    money4b = true;
                    if (moneyScore == 1) {
                        moneyScore = 2;
                    }
                } else if (money4b) {
                    money4.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xD7D7D7));
                    //money4.setTypeface(null, Typeface.BOLD);
                    moneyInefficient--;
                    money4b = false;
                    //lower score based on which other buttons have already been pressed
                    if (moneyScore == 2 && moneyInefficient == 0) {
                        moneyScore = 1;
                    }
                }
                //lastButtonClicked = money4;
                break;
            case R.id.money5:
                if (moneyTemp <= 0) {
                    return;
                }
                if (!money5b) {
                    money5T = System.currentTimeMillis() - totalExecutionTime;
                    money5.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0x981e32));
                    //money5.setTypeface(Typeface.DEFAULT);
                    moneyInaccurate++;
                    money5b = true;
                    lessMoney = true;
                    if (moneyScore != 3) {
                        moneyScore = 3;
                    }
                    if (money3b) {
                        money3.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xD7D7D7));
                        //money3.setTypeface(null, Typeface.BOLD);
                        moneyInefficient--;
                        money3b = false;
                        moreMoney = false;
                        //lower score based on which other buttons have already been pressed
                        if (moneyScore == 2 && moneyInefficient == 0) {
                            moneyScore = 1;
                        }
                    }
                } else if (money5b) {
                    money5.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xA9A9A9));
                    //money5.setTypeface(null, Typeface.BOLD);
                    moneyInaccurate--;
                    money5b = false;
                    lessMoney = false;
                    //lower score based on which other buttons have already been pressed
                    if (moneyScore == 3 && moneyIncomplete == 0 && moneyInaccurate == 0 && moneyInefficient == 0) {
                        moneyScore = 1;
                    } else if (moneyScore == 3 && moneyIncomplete == 0 && moneyInaccurate == 0 && moneyInefficient > 0) {
                        moneyScore = 2;
                    }
                }
                //lastButtonClicked = money5;
                break;
            case R.id.money6:
                if (moneyTemp <= 0) {
                    return;
                }
                //Toast.makeText(MainActivity.this, "onClickListener working", Toast.LENGTH_SHORT).show();
                if (!money6b) {
                    money6T = System.currentTimeMillis() - totalExecutionTime;
                    money6.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0x981e32));
                    //money6.setTypeface(Typeface.DEFAULT);
                    moneyIncomplete++;
                    money6b = true;
                    if (moneyScore != 3) {
                        moneyScore = 3;
                    }
                } else if (money6b) {
                    money6.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xA9A9A9));
                    //money6.setTypeface(null, Typeface.BOLD);
                    moneyIncomplete--;
                    money6b = false;
                    //lower score based on which other buttons have already been pressed
                    if (moneyScore == 3 && moneyIncomplete == 0 && moneyInaccurate == 0 && moneyInefficient == 0) {
                        moneyScore = 1;
                    } else if (moneyScore == 3 && moneyIncomplete == 0 && moneyInaccurate == 0 && moneyInefficient > 0) {
                        moneyScore = 2;
                    }
                }
                //lastButtonClicked = money6;
                break;
            case R.id.money7:
                if (moneyTemp <= 0) {
                    return;
                }
                if (!money7b) {
                    money7T = System.currentTimeMillis() - totalExecutionTime;
                    money7.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0x981e32));
                    //money7.setTypeface(Typeface.DEFAULT);
                    moneyIncomplete++;
                    money7b = true;
                    if (moneyScore != 3) {
                        moneyScore = 3;
                    }
                } else if (money7b) {
                    money7.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xA9A9A9));
                    //money7.setTypeface(null, Typeface.BOLD);
                    moneyIncomplete--;
                    money7b = false;
                    //lower score based on which other buttons have already been pressed
                    if (moneyScore == 3 && moneyIncomplete == 0 && moneyInaccurate == 0 && moneyInefficient == 0) {
                        moneyScore = 1;
                    } else if (moneyScore == 3 && moneyIncomplete == 0 && moneyInaccurate == 0 && moneyInefficient > 0) {
                        moneyScore = 2;
                    }
                }
                //lastButtonClicked = money7;
                break;
            case R.id.money8:
                if (moneyTemp <= 0) {
                    return;
                }
                if (!money8b) {
                    money8T = System.currentTimeMillis() - totalExecutionTime;
                    money8.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0x981e32));
                    //money8.setTypeface(Typeface.DEFAULT);
                    moneyIncomplete++;
                    money8b = true;
                    if (moneyScore != 3) {
                        moneyScore = 3;
                    }
                } else if (money8b) {
                    money8.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xA9A9A9));
                    //money8.setTypeface(null, Typeface.BOLD);
                    moneyIncomplete--;
                    money8b = false;
                    //lower score based on which other buttons have already been pressed
                    if (moneyScore == 3 && moneyIncomplete == 0 && moneyInaccurate == 0 && moneyInefficient == 0) {
                        moneyScore = 1;
                    } else if (moneyScore == 3 && moneyIncomplete == 0 && moneyInaccurate == 0 && moneyInefficient > 0) {
                        moneyScore = 2;
                    }
                }
                //lastButtonClicked = money8;
                break;
            case R.id.exit1:
                if (exitTemp <= 0) {
                    return;
                }
                //Toast.makeText(MainActivity.this, "onClickListener working", Toast.LENGTH_SHORT).show();
                if (!exit1b) {
                    exit1.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0x981e32));
                    //exit1.setTypeface(Typeface.DEFAULT);
                    exitInefficient++;
                    exit1b = true;
                    if (exitScore == 1) {
                        exitScore = 2;
                    }
                } else if (exit1b) {
                    exit1.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xD7D7D7));
                    //exit1.setTypeface(null, Typeface.BOLD);
                    exitInefficient--;
                    exit1b = false;
                    //lower score based on which other buttons have already been pressed
                    if (exitScore == 2 && exitInefficient == 0) {
                        exitScore = 1;
                    }
                }
                //lastButtonClicked = exit1;
                break;
            case R.id.exit2:
                if (exitTemp <= 0) {
                    return;
                }
                if (!exit2b) {
                    exit2.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0x981e32));
                    //exit2.setTypeface(Typeface.DEFAULT);
                    exitInefficient++;
                    exit2b = true;
                    if (exitScore == 1) {
                        exitScore = 2;
                    }
                } else if (exit2b) {
                    exit2.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xD7D7D7));
                    //exit2.setTypeface(null, Typeface.BOLD);
                    exitInefficient--;
                    exit2b = false;
                    //lower score based on which other buttons have already been pressed
                    if (exitScore == 2 && exitInefficient == 0) {
                        exitScore = 1;
                    }
                }
                //lastButtonClicked = exit2;
                break;
            case R.id.exit3:
                if (exitTemp <= 0) {
                    return;
                }
                if (!exit3b) {
                    exit3.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0x981e32));
                    //exit3.setTypeface(Typeface.DEFAULT);
                    exitIncomplete++;
                    exit3b = true;
                    if (exitScore != 3) {
                        exitScore = 3;
                    }
                } else if (exit3b) {
                    exit3.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xA9A9A9));
                    //exit3.setTypeface(null, Typeface.BOLD);
                    exitIncomplete--;
                    exit3b = false;
                    //lower score based on which other buttons have already been pressed
                    if (exitScore == 3 && exitIncomplete == 0 && exitInaccurate == 0 && exitInefficient == 0) {
                        exitScore = 1;
                    } else if (exitScore == 3 && exitIncomplete == 0 && exitInaccurate == 0 && exitInefficient > 0) {
                        exitScore = 2;
                    }
                }
                //lastButtonClicked = exit3;
                break;
            case R.id.exit4:
                if (exitTemp <= 0) {
                    return;
                }
                if (!exit4b) {
                    exit4.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0x981e32));
                    //exit4.setTypeface(Typeface.DEFAULT);
                    exitIncomplete++;
                    exit4b = true;
                    if (exitScore != 3) {
                        exitScore = 3;
                    }
                } else if (exit4b) {
                    exit4.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xA9A9A9));
                    //exit4.setTypeface(null, Typeface.BOLD);
                    exitIncomplete--;
                    exit4b = false;
                    //lower score based on which other buttons have already been pressed
                    if (exitScore == 3 && exitIncomplete == 0 && exitInaccurate == 0 && exitInefficient == 0) {
                        exitScore = 1;
                    } else if (exitScore == 3 && exitIncomplete == 0 && exitInaccurate == 0 && exitInefficient > 0) {
                        exitScore = 2;
                    }
                }
                //lastButtonClicked = exit4;
                break;
            case R.id.movie1:
                if (movieTemp <= 0) {
                    return;
                }
                //Toast.makeText(MainActivity.this, "onClickListener working", Toast.LENGTH_SHORT).show();
                if (!movie1b) {
                    movie1.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0x981e32));
                    //movie1.setTypeface(Typeface.DEFAULT);
                    movieInefficient++;
                    movie1b = true;
                    if (movieScore == 1) {
                        movieScore = 2;
                    }
                } else if (movie1b) {
                    movie1.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xD7D7D7));
                    //movie1.setTypeface(null, Typeface.BOLD);
                    movieInefficient--;
                    movie1b = false;
                    //lower score based on which other buttons have already been pressed
                    if (movieScore == 2 && movieInefficient == 0) {
                        movieScore = 1;
                    }
                }
                break;
            case R.id.movie9:
                if (movieTemp <= 0) {
                    return;
                }
                //Toast.makeText(MainActivity.this, "onClickListener working", Toast.LENGTH_SHORT).show();
                if (!movie9b) {
                    movie9.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0x981e32));
                    //movie9.setTypeface(Typeface.DEFAULT);
                    movieInefficient++;
                    movie9b = true;
                    movieEarly = true;
                    if (movieScore == 1) {
                        movieScore = 2;
                    }
                    //in case the opposite button is pressed we need to undo it
                    if (movie7b) {
                        movie7.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xA9A9A9));
                        //movie7.setTypeface(null, Typeface.BOLD);
                        movieInaccurate--;
                        movie7b = false;
                        movieLate = false;
                        //lower score based on which other buttons have already been pressed
                        if (movieScore == 3 && movieIncomplete == 0 && movieInaccurate == 0 && movieInefficient == 0) {
                            movieScore = 1;
                        } else if (movieScore == 3 && movieIncomplete == 0 && movieInaccurate == 0 && movieInefficient > 0) {
                            movieScore = 2;
                        }
                    }
                } else if (movie9b) {
                    movie9.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xD7D7D7));
                    //movie9.setTypeface(null, Typeface.BOLD);
                    movieInefficient--;
                    movie9b = false;
                    movieEarly = false;
                    //lower score based on which other buttons have already been pressed
                    if (movieScore == 2 && movieInefficient == 0) {
                        movieScore = 1;
                    }
                }
                //lastButtonClicked = movie1;
                break;
            case R.id.movie2:
                if (movieTemp <= 0) {
                    return;
                }
                if (!movie2b) {
                    movie2.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0x981e32));
                    //movie2.setTypeface(Typeface.DEFAULT);
                    movieInefficient++;
                    movie2b = true;
                    if (movieScore == 1) {
                        movieScore = 2;
                    }
                } else if (movie2b) {
                    movie2.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xD7D7D7));
                    //movie2.setTypeface(null, Typeface.BOLD);
                    movieInefficient--;
                    movie2b = false;
                    //lower score based on which other buttons have already been pressed
                    if (movieScore == 2 && movieInefficient == 0) {
                        movieScore = 1;
                    }
                }
                //lastButtonClicked = movie2;
                break;
            case R.id.movie3:
                if (movieTemp <= 0) {
                    return;
                }
                if (!movie3b) {
                    movie3.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0x981e32));
                    //movie3.setTypeface(Typeface.DEFAULT);
                    movieInefficient++;
                    movie3b = true;
                    if (movieScore == 1) {
                        movieScore = 2;
                    }
                } else if (movie3b) {
                    movie3.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xD7D7D7));
                    //movie3.setTypeface(null, Typeface.BOLD);
                    movieInefficient--;
                    movie3b = false;
                    //lower score based on which other buttons have already been pressed
                    if (movieScore == 2 && movieInefficient == 0) {
                        movieScore = 1;
                    }
                }
                //lastButtonClicked = movie3;
                break;
            case R.id.movie4:
                if (movieTemp <= 0) {
                    return;
                }
                if (!movie4b) {
                    movie4.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0x981e32));
                    //movie4.setTypeface(Typeface.DEFAULT);
                    movieInefficient++;
                    movie4b = true;
                    if (movieScore == 1) {
                        movieScore = 2;
                    }
                } else if (movie4b) {
                    movie4.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xD7D7D7));
                    //movie4.setTypeface(null, Typeface.BOLD);
                    movieInefficient--;
                    movie4b = false;
                    //lower score based on which other buttons have already been pressed
                    if (movieScore == 2 && movieInefficient == 0) {
                        movieScore = 1;
                    }
                }
                //lastButtonClicked = movie4;
                break;
            case R.id.movie5:
                if (movieTemp <= 0) {
                    return;
                }
                if (!movie5b) {
                    movie5.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0x981e32));
                    //movie5.setTypeface(Typeface.DEFAULT);
                    movieIncomplete++;
                    movie5b = true;
                    if (movieScore != 3) {
                        movieScore = 3;
                    }
                } else if (movie5b) {
                    movie5.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xA9A9A9));
                    //movie5.setTypeface(null, Typeface.BOLD);
                    movieIncomplete--;
                    movie5b = false;
                    //lower score based on which other buttons have already been pressed
                    if (movieScore == 3 && movieIncomplete == 0 && movieInaccurate == 0 && movieInefficient == 0) {
                        movieScore = 1;
                    } else if (movieScore == 3 && movieIncomplete == 0 && movieInaccurate == 0 && movieInefficient > 0) {
                        movieScore = 2;
                    }
                }
                //lastButtonClicked = movie5;
                break;
            case R.id.movie6:
                if (movieTemp <= 0) {
                    return;
                }
                if (!movie6b) {
                    movie6.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0x981e32));
                    //movie6.setTypeface(Typeface.DEFAULT);
                    movieInaccurate++;
                    movie6b = true;
                    if (movieScore != 3) {
                        movieScore = 3;
                    }
                } else if (movie6b) {
                    movie6.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xA9A9A9));
                    //movie6.setTypeface(null, Typeface.BOLD);
                    movieInaccurate--;
                    movie6b = false;
                    //lower score based on which other buttons have already been pressed
                    if (movieScore == 3 && movieIncomplete == 0 && exitInaccurate == 0 && movieInefficient == 0) {
                        movieScore = 1;
                    } else if (movieScore == 3 && movieIncomplete == 0 && exitInaccurate == 0 && movieInefficient > 0) {
                        movieScore = 2;
                    }
                }
                //lastButtonClicked = movie6;
                break;
            case R.id.movie7:
                if (movieTemp <= 0) {
                    return;
                }
                if (!movie7b) {
                    movie7.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0x981e32));
                    //movie7.setTypeface(Typeface.DEFAULT);
                    movieInaccurate++;
                    movie7b = true;
                    movieLate = true;
                    if (movieScore != 3) {
                        movieScore = 3;
                    }
                    if (movie9b) {
                        movie9.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xA9A9A9));
                        //movie9.setTypeface(null, Typeface.BOLD);
                        movieInefficient--;
                        movie9b = false;
                        movieEarly = false;
                        //lower score based on which other buttons have already been pressed
                        if (movieScore == 2 && movieInefficient == 0) {
                            movieScore = 1;
                        }
                    }
                } else if (movie7b) {
                    movie7.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xA9A9A9));
                    //movie7.setTypeface(null, Typeface.BOLD);
                    movieInaccurate--;
                    movie7b = false;
                    movieLate = false;
                    //lower score based on which other buttons have already been pressed
                    if (movieScore == 3 && movieIncomplete == 0 && exitInaccurate == 0 && movieInefficient == 0) {
                        movieScore = 1;
                    } else if (movieScore == 3 && movieIncomplete == 0 && exitInaccurate == 0 && movieInefficient > 0) {
                        movieScore = 2;
                    }
                }
                //lastButtonClicked = movie7;
                break;
            case R.id.movie8:
                if (movieTemp <= 0) {
                    return;
                }
                if (!movie8b) {
                    movie8.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0x981e32));
                    //movie8.setTypeface(Typeface.DEFAULT);
                    movieIncomplete++;
                    movie8b = true;
                    if (movieScore != 3) {
                        movieScore = 3;
                    }
                } else if (movie8b) {
                    movie8.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xA9A9A9));
                    //movie8.setTypeface(null, Typeface.BOLD);
                    movieIncomplete--;
                    movie8b = false;
                    //lower score based on which other buttons have already been pressed
                    if (movieScore == 3 && movieIncomplete == 0 && exitInaccurate == 0 && movieInefficient == 0) {
                        movieScore = 1;
                    } else if (movieScore == 3 && movieIncomplete == 0 && exitInaccurate == 0 && movieInefficient > 0) {
                        movieScore = 2;
                    }
                }
                //lastButtonClicked = movie8;
                break;
            case R.id.phone1:
                if (phoneTemp <= 0) {
                    return;
                }
                if (!phone1b) {
                    phone1.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0x981e32));
                    //phone1.setTypeface(Typeface.DEFAULT);
                    phoneInefficient++;
                    phone1b = true;
                    if (phoneScore == 1) {
                        phoneScore = 2;
                    }
                } else if (phone1b) {
                    phone1.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xD7D7D7));
                    //phone1.setTypeface(null, Typeface.BOLD);
                    phoneInefficient--;
                    phone1b = false;
                    //lower score based on which other buttons have already been pressed
                    if (phoneScore == 2 && phoneInefficient == 0) {
                        phoneScore = 1;
                    }
                }
                //lastButtonClicked = phone1;
                break;
            case R.id.phone2:
                if (phoneTemp <= 0) {
                    return;
                }
                if (!phone2b) {
                    phone2.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0x981e32));
                    //phone2.setTypeface(Typeface.DEFAULT);
                    phoneInefficient++;
                    phone2b = true;
                    if (phoneScore == 1) {
                        phoneScore = 2;
                    }
                } else if (phone2b) {
                    phone2.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xD7D7D7));
                    //phone2.setTypeface(null, Typeface.BOLD);
                    phoneInefficient--;
                    phone2b = false;
                    //lower score based on which other buttons have already been pressed
                    if (phoneScore == 2 && phoneInefficient == 0) {
                        phoneScore = 1;
                    }
                }
                //lastButtonClicked = phone2;
                break;
            case R.id.phone3:
                if (phoneTemp <= 0) {
                    return;
                }
                if (!phone3b) {
                    phone3.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0x981e32));
                    //phone3.setTypeface(Typeface.DEFAULT);
                    phoneInefficient++;
                    phone3b = true;
                    if (phoneScore == 1) {
                        phoneScore = 2;
                    }
                } else if (phone3b) {
                    phone3.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xD7D7D7));
                    //phone3.setTypeface(null, Typeface.BOLD);
                    phoneInefficient--;
                    phone3b = false;
                    //lower score based on which other buttons have already been pressed
                    if (phoneScore == 2 && phoneInefficient == 0) {
                        phoneScore = 1;
                    }
                }
                //lastButtonClicked = phone3;
                break;
            case R.id.phone4:
                if (phoneTemp <= 0) {
                    return;
                }
                if (!phone4b) {
                    phone4.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0x981e32));
                    //phone4.setTypeface(Typeface.DEFAULT);
                    phoneInefficient++;
                    phone4b = true;
                    if (phoneScore == 1) {
                        phoneScore = 2;
                    }
                } else if (phone4b) {
                    phone4.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xD7D7D7));
                    //phone4.setTypeface(null, Typeface.BOLD);
                    phoneInefficient--;
                    phone4b = false;
                    //lower score based on which other buttons have already been pressed
                    if (phoneScore == 2 && phoneInefficient == 0) {
                        phoneScore = 1;
                    }
                }
                //lastButtonClicked = phone4;
                break;
            case R.id.phone5:
                if (phoneTemp <= 0) {
                    return;
                }
                if (!phone5b) {
                    phone5.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0x981e32));
                    //phone5.setTypeface(Typeface.DEFAULT);
                    phoneIncomplete++;
                    phone5b = true;
                    if (phoneScore != 3) {
                        phoneScore = 3;
                    }
                } else if (phone5b) {
                    phone5.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xA9A9A9));
                    //phone5.setTypeface(null, Typeface.BOLD);
                    phoneIncomplete--;
                    phone5b = false;
                    //lower score based on which other buttons have already been pressed
                    if (phoneScore == 3 && phoneIncomplete == 0 && phoneInaccurate == 0 && phoneInefficient == 0) {
                        phoneScore = 1;
                    } else if (phoneScore == 3 && phoneIncomplete == 0 && phoneInaccurate == 0 && phoneInefficient > 0) {
                        phoneScore = 2;
                    }
                }
                //lastButtonClicked = phone5;
                break;
            case R.id.phone6:
                if (phoneTemp <= 0) {
                    return;
                }
                if (!phone6b) {
                    phone6.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0x981e32));
                    //phone6.setTypeface(Typeface.DEFAULT);
                    phoneIncomplete++;
                    phone6b = true;
                    if (phoneScore != 3) {
                        phoneScore = 3;
                    }
                } else if (phone6b) {
                    phone6.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xA9A9A9));
                    //phone6.setTypeface(null, Typeface.BOLD);
                    phoneIncomplete--;
                    phone6b = false;
                    //lower score based on which other buttons have already been pressed
                    if (phoneScore == 3 && phoneIncomplete == 0 && phoneInaccurate == 0 && phoneInefficient == 0) {
                        phoneScore = 1;
                    } else if (phoneScore == 3 && phoneIncomplete == 0 && phoneInaccurate == 0 && phoneInefficient > 0) {
                        phoneScore = 2;
                    }
                }
                //lastButtonClicked = phone6;
                break;
            case R.id.phone7:
                if (phoneTemp <= 0) {
                    return;
                }
                if (!phone7b) {
                    phone7.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0x981e32));
                    //phone7.setTypeface(Typeface.DEFAULT);
                    phoneIncomplete++;
                    phone7b = true;
                    if (phoneScore != 3) {
                        phoneScore = 3;
                    }
                } else if (phone7b) {
                    phone7.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xA9A9A9));
                    //phone7.setTypeface(null, Typeface.BOLD);
                    phoneIncomplete--;
                    phone7b = false;
                    //lower score based on which other buttons have already been pressed
                    if (phoneScore == 3 && phoneIncomplete == 0 && phoneInaccurate == 0 && phoneInefficient == 0) {
                        phoneScore = 1;
                    } else if (phoneScore == 3 && phoneIncomplete == 0 && phoneInaccurate == 0 && phoneInefficient > 0) {
                        phoneScore = 2;
                    }
                }
                //lastButtonClicked = phone7;
                break;
            case R.id.recipe1:
                if (recipeTemp <= 0) {
                    return;
                }
                if (!recipe1b) {
                    recipe1.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0x981e32));
                    //recipe1.setTypeface(Typeface.DEFAULT);
                    recipeInefficient++;
                    recipe1b = true;
                    if (recipeScore == 1) {
                        recipeScore = 2;
                    }
                } else if (recipe1b) {
                    recipe1.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xD7D7D7));
                    //recipe1.setTypeface(null, Typeface.BOLD);
                    recipeInefficient--;
                    recipe1b = false;
                    //lower score based on which other buttons have already been pressed
                    if (recipeScore == 2 && recipeInefficient == 0) {
                        recipeScore = 1;
                    }
                }
                //lastButtonClicked = recipe1;
                break;
            case R.id.recipe2:
                if (recipeTemp <= 0) {
                    return;
                }
                if (!recipe2b) {
                    recipe2.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0x981e32));
                    //recipe2.setTypeface(Typeface.DEFAULT);
                    recipeInefficient++;
                    recipe2b = true;
                    if (recipeScore == 1) {
                        recipeScore = 2;
                    }
                } else if (recipe2b) {
                    recipe2.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xD7D7D7));
                    //recipe2.setTypeface(null, Typeface.BOLD);
                    recipeInefficient--;
                    recipe2b = false;
                    //lower score based on which other buttons have already been pressed
                    if (recipeScore == 2 && recipeInefficient == 0) {
                        recipeScore = 1;
                    }
                }
                //lastButtonClicked = recipe2;
                break;
            case R.id.recipe3:
                if (recipeTemp <= 0) {
                    return;
                }
                if (!recipe3b) {
                    recipe3.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0x981e32));
                    //recipe3.setTypeface(Typeface.DEFAULT);
                    recipeInefficient++;
                    recipe3b = true;
                    if (recipeScore == 1) {
                        recipeScore = 2;
                    }
                } else if (recipe3b) {
                    recipe3.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xD7D7D7));
                    //recipe3.setTypeface(null, Typeface.BOLD);
                    recipeInefficient--;
                    recipe3b = false;
                    //lower score based on which other buttons have already been pressed
                    if (recipeScore == 2 && recipeInefficient == 0) {
                        recipeScore = 1;
                    }
                }
                //lastButtonClicked = recipe3;
                break;
            case R.id.recipe4:
                if (recipeTemp <= 0) {
                    return;
                }
                if (!recipe4b) {
                    recipe4.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0x981e32));
                    //recipe4.setTypeface(Typeface.DEFAULT);
                    recipeInefficient++;
                    recipe4b = true;
                    if (recipeScore == 1) {
                        recipeScore = 2;
                    }
                } else if (recipe4b) {
                    recipe4.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xD7D7D7));
                    //recipe4.setTypeface(null, Typeface.BOLD);
                    recipeInefficient--;
                    recipe4b = false;
                    //lower score based on which other buttons have already been pressed
                    if (recipeScore == 2 && recipeInefficient == 0) {
                        recipeScore = 1;
                    }
                }
                //lastButtonClicked = recipe4;
                break;
            case R.id.recipe5:
                if (recipeTemp <= 0) {
                    return;
                }
                if (!recipe5b) {
                    recipe5.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0x981e32));
                    //recipe5.setTypeface(Typeface.DEFAULT);
                    recipeInefficient++;
                    recipe5b = true;
                    if (recipeScore == 1) {
                        recipeScore = 2;
                    }
                } else if (recipe5b) {
                    recipe5.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xD7D7D7));
                    //recipe5.setTypeface(null, Typeface.BOLD);
                    recipeInefficient--;
                    recipe5b = false;
                    //lower score based on which other buttons have already been pressed
                    if (recipeScore == 2 && recipeInefficient == 0) {
                        recipeScore = 1;
                    }
                }
                //lastButtonClicked = recipe5;
                break;
            case R.id.recipe6:
                if (recipeTemp <= 0) {
                    return;
                }
                if (!recipe6b) {
                    recipe6.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0x981e32));
                    //recipe6.setTypeface(Typeface.DEFAULT);
                    recipeInefficient++;
                    recipe6b = true;
                    if (recipeScore == 1) {
                        recipeScore = 2;
                    }
                } else if (recipe6b) {
                    recipe6.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xD7D7D7));
                    //recipe6.setTypeface(null, Typeface.BOLD);
                    recipeInefficient--;
                    recipe6b = false;
                    //lower score based on which other buttons have already been pressed
                    if (recipeScore == 2 && recipeInefficient == 0) {
                        recipeScore = 1;
                    }
                }
                break;
            case R.id.recipe7:
                if (recipeTemp <= 0) {
                    return;
                }
                if (!recipe7b) {
                    recipe7.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0x981e32));
                    //recipe7.setTypeface(Typeface.DEFAULT);
                    recipeInefficient++;
                    recipe7b = true;
                    if (recipeScore == 1) {
                        recipeScore = 2;
                    }
                } else if (recipe7b) {
                    recipe7.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xD7D7D7));
                    //recipe7.setTypeface(null, Typeface.BOLD);
                    recipeInefficient--;
                    recipe7b = false;
                    //lower score based on which other buttons have already been pressed
                    if (recipeScore == 2 && recipeInefficient == 0) {
                        recipeScore = 1;
                    }
                }
                //lastButtonClicked = recipe7;
                break;
            case R.id.recipe8:
                if (recipeTemp <= 0) {
                    return;
                }
                if (!recipe8b) {
                    recipe8.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0x981e32));
                    //recipe8.setTypeface(Typeface.DEFAULT);
                    recipeInefficient++;
                    recipe8b = true;
                    if (recipeScore == 1) {
                        recipeScore = 2;
                    }
                } else if (recipe8b) {
                    recipe8.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xD7D7D7));
                    //recipe8.setTypeface(null, Typeface.BOLD);
                    recipeInefficient--;
                    recipe8b = false;
                    //lower score based on which other buttons have already been pressed
                    if (recipeScore == 2 && recipeInefficient == 0) {
                        recipeScore = 1;
                    }
                }
                //lastButtonClicked = recipe8;
                break;
            case R.id.recipe9:
                if (recipeTemp <= 0) {
                    return;
                }
                if (!recipe9b) {
                    recipe9.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0x981e32));
                    //recipe9.setTypeface(Typeface.DEFAULT);
                    recipeInefficient++;
                    recipe9b = true;
                    if (recipeScore == 1) {
                        recipeScore = 2;
                    }
                } else if (recipe9b) {
                    recipe9.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xD7D7D7));
                    //recipe9.setTypeface(null, Typeface.BOLD);
                    recipeInefficient--;
                    recipe9b = false;
                    //lower score based on which other buttons have already been pressed
                    if (recipeScore == 2 && recipeInefficient == 0) {
                        recipeScore = 1;
                    }
                }
                //lastButtonClicked = recipe9;
                break;
            case R.id.recipe10:
                if (recipeTemp <= 0) {
                    return;
                }
                //lastButtonClicked = recipe10;
                if (!recipe10b) {
                    recipe10.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0x981e32));
                    //recipe10.setTypeface(Typeface.DEFAULT);
                    recipeInefficient++;
                    recipe10b = true;
                    if (recipeScore != 3) {
                        recipeScore = 3;
                    }
                } else if (recipe10b) {
                    recipe10.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xD7D7D7));
                    //recipe10.setTypeface(null, Typeface.BOLD);
                    recipeInefficient--;
                    recipe10b = false;
                    //lower score based on which other buttons have already been pressed
                    if (recipeScore == 2 && recipeInefficient == 0) {
                        recipeScore = 1;
                    }
                }
                break;
            case R.id.recipe15:
                if (recipeTemp <= 0) {
                    return;
                }
                //lastButtonClicked = recipe10;
                if (!recipe15b) {
                    recipe15.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0x981e32));
                    //recipe15.setTypeface(Typeface.DEFAULT);
                    recipeInefficient++;
                    recipe15b = true;
                    if (recipeScore != 3) {
                        recipeScore = 3;
                    }
                } else if (recipe15b) {
                    recipe15.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xA9A9A9));
                    //recipe15.setTypeface(null, Typeface.BOLD);
                    recipeInefficient--;
                    recipe15b = false;
                    //lower score based on which other buttons have already been pressed
                    if (recipeScore == 2 && recipeInefficient == 0) {
                        recipeScore = 1;
                    }
                }
                break;
            case R.id.recipe11:
                if (recipeTemp <= 0) {
                    return;
                }
                if (!recipe11b) {
                    recipe11.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0x981e32));
                    //recipe11.setTypeface(Typeface.DEFAULT);
                    recipeInaccurate++;
                    recipe11b = true;
                    if (recipeScore != 3) {
                        recipeScore = 3;
                    }
                } else if (recipe11b) {
                    recipe11.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xA9A9A9));
                    //recipe11.setTypeface(null, Typeface.BOLD);
                    recipeInaccurate--;
                    recipe11b = false;
                    if (recipeScore == 3 && recipeIncomplete == 0 && recipeInefficient == 0) {
                        recipeScore = 1;
                    } else if (recipeScore == 3 && recipeIncomplete == 0 && recipeInefficient > 0) {
                        recipeScore = 2;
                    }
                }
                //lastButtonClicked = recipe11;
                break;
            case R.id.recipe12:
                if (recipeTemp <= 0) {
                    return;
                }
                if (!recipe12b) {
                    recipe12.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0x981e32));
                    //recipe12.setTypeface(Typeface.DEFAULT);
                    recipeInaccurate++;
                    recipe12b = true;
                    if (recipeScore != 3) {
                        recipeScore = 3;
                    }
                } else if (recipe12b) {
                    recipe12.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xA9A9A9));
                    //recipe12.setTypeface(null, Typeface.BOLD);
                    recipeInaccurate--;
                    recipe12b = false;
                    if (recipeScore == 3 && recipeIncomplete == 0 && recipeInefficient == 0) {
                        recipeScore = 1;
                    } else if (recipeScore == 3 && recipeIncomplete == 0 && recipeInefficient > 0) {
                        recipeScore = 2;
                    }
                }
                //lastButtonClicked = recipe12;
                break;
            case R.id.recipe13:
                if (recipeTemp <= 0) {
                    return;
                }
                if (!recipe13b) {
                    recipe13.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0x981e32));
                    //recipe13.setTypeface(Typeface.DEFAULT);
                    recipeInaccurate++;
                    recipe13b = true;
                    if (recipeScore != 3) {
                        recipeScore = 3;
                    }
                } else if (recipe13b) {
                    recipe13.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xA9A9A9));
                    //recipe13.setTypeface(null, Typeface.BOLD);
                    recipeInaccurate--;
                    recipe13b = false;
                    if (recipeScore == 3 && recipeIncomplete == 0 && recipeInefficient == 0) {
                        recipeScore = 1;
                    } else if (recipeScore == 3 && recipeIncomplete == 0 && recipeInefficient > 0) {
                        recipeScore = 2;
                    }
                }
                //lastButtonClicked = recipe13;
                break;
            case R.id.recipe14:
                if (recipeTemp <= 0) {
                    return;
                }
                if (!recipe14b) {
                    recipe14.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0x981e32));
                    //recipe14.setTypeface(Typeface.DEFAULT);
                    recipeIncomplete++;
                    recipe14b = true;
                    if (recipeScore != 3) {
                        recipeScore = 3;
                    }
                } else if (recipe14b) {
                    recipe14.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xA9A9A9));
                    //recipe14.setTypeface(null, Typeface.BOLD);
                    recipeIncomplete--;
                    recipe14b = false;
                    if (recipeScore == 3 && recipeIncomplete == 0 && recipeInefficient == 0) {
                        recipeScore = 1;
                    } else if (recipeScore == 3 && recipeIncomplete == 0 && recipeInefficient > 0) {
                        recipeScore = 2;
                    }
                }
                //lastButtonClicked = recipe14;
                break;
            case R.id.snack1:
                if (snackTemp <= 0) {
                    return;
                }
                if (!snack1b) {
                    snack1.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0x981e32));
                    //snack1.setTypeface(Typeface.DEFAULT);
                    snackInefficient++;
                    snack1b = true;
                    if (snackScore == 1) {
                        snackScore = 2;
                    }
                } else if (snack1b) {
                    snack1.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xD7D7D7));
                    //snack1.setTypeface(null, Typeface.BOLD);
                    snackInefficient--;
                    snack1b = false;
                    //lower score based on which other buttons have already been pressed
                    if (snackScore == 2 && snackInefficient == 0) {
                        snackScore = 1;
                    }
                }
                //lastButtonClicked = snack1;
                break;
            case R.id.snack2:
                if (snackTemp <= 0) {
                    return;
                }
                if (!snack2b) {
                    snack2.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0x981e32));
                    //snack2.setTypeface(Typeface.DEFAULT);
                    snackInefficient++;
                    snack2b = true;
                    if (snackScore == 1) {
                        snackScore = 2;
                    }
                } else if (snack2b) {
                    snack2.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xD7D7D7));
                    //snack2.setTypeface(null, Typeface.BOLD);
                    snackInefficient--;
                    snack2b = false;
                    //lower score based on which other buttons have already been pressed
                    if (snackScore == 2 && snackInefficient == 0) {
                        snackScore = 1;
                    }
                }
                //lastButtonClicked = phone2;
                break;
            case R.id.snack3:
                if (snackTemp <= 0) {
                    return;
                }
                if (!snack3b) {
                    snack3.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0x981e32));
                    //snack3.setTypeface(Typeface.DEFAULT);
                    snackInefficient++;
                    snack3b = true;
                    if (snackScore == 1) {
                        snackScore = 2;
                    }
                } else if (snack3b) {
                    snack3.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xD7D7D7));
                    //snack3.setTypeface(null, Typeface.BOLD);
                    snackInefficient--;
                    snack3b = false;
                    //lower score based on which other buttons have already been pressed
                    if (snackScore == 2 && snackInefficient == 0) {
                        snackScore = 1;
                    }
                }
                // lastButtonClicked = snack3;
                break;
            case R.id.snack4:
                if (snackTemp <= 0) {
                    return;
                }
                if (!snack4b) {
                    snack4.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0x981e32));
                    //snack4.setTypeface(Typeface.DEFAULT);
                    snackInefficient++;
                    snack4b = true;
                    if (snackScore == 1) {
                        snackScore = 2;
                    }
                } else if (snack4b) {
                    snack4.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xD7D7D7));
                    //snack4.setTypeface(null, Typeface.BOLD);
                    snackInefficient--;
                    snack4b = false;
                    //lower score based on which other buttons have already been pressed
                    if (snackScore == 2 && snackInefficient == 0) {
                        snackScore = 1;
                    }
                }
                //lastButtonClicked = snack4;
                break;
            case R.id.snack5:
                if (snackTemp <= 0) {
                    return;
                }
                if (!snack5b) {
                    snack5.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0x981e32));
                    //snack5.setTypeface(Typeface.DEFAULT);
                    snackInaccurate++;
                    snack5b = true;
                    if (snackScore != 3) {
                        snackScore = 3;
                    }
                } else if (snack5b) {
                    snack5.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xA9A9A9));
                    //snack5.setTypeface(null, Typeface.BOLD);
                    snackInaccurate--;
                    snack5b = false;
                    //lower score based on which other buttons have already been pressed
                    if (snackScore == 3 && snackIncomplete == 0 && snackInefficient == 0) {
                        snackScore = 1;
                    } else if (snackScore == 3 && snackIncomplete == 0 && snackInefficient > 0) {
                        snackScore = 2;
                    }
                }
                //lastButtonClicked = snack5;
                break;
            case R.id.snack6:
                if (snackTemp <= 0) {
                    return;
                }
                if (!snack6b) {
                    snack6.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0x981e32));
                    //snack6.setTypeface(Typeface.DEFAULT);
                    snackInaccurate++;
                    snack6b = true;
                    if (snackScore != 3) {
                        snackScore = 3;
                    }
                } else if (snack6b) {
                    snack6.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xA9A9A9));
                    //snack6.setTypeface(null, Typeface.BOLD);
                    snackInaccurate--;
                    snack6b = false;
                    //lower score based on which other buttons have already been pressed
                    if (snackScore == 3 && snackIncomplete == 0 && snackInefficient == 0) {
                        snackScore = 1;
                    } else if (snackScore == 3 && snackIncomplete == 0 && snackInefficient > 0) {
                        snackScore = 2;
                    }
                }
                //lastButtonClicked = snack6;
                break;
            case R.id.snack7:
                if (snackTemp <= 0) {
                    return;
                }
                if (!snack7b) {
                    snack7.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0x981e32));
                    //snack7.setTypeface(Typeface.DEFAULT);
                    snackIncomplete++;
                    snack7b = true;
                    if (snackScore != 3) {
                        snackScore = 3;
                    }
                } else if (snack7b) {
                    snack7.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xA9A9A9));
                    //snack7.setTypeface(null, Typeface.BOLD);
                    snackIncomplete--;
                    snack7b = false;
                    //lower score based on which other buttons have already been pressed
                    if (snackScore == 3 && snackIncomplete == 0 && snackInefficient == 0) {
                        snackScore = 1;
                    } else if (snackScore == 3 && snackIncomplete == 0 && snackInefficient > 0) {
                        snackScore = 2;
                    }
                }
                //lastButtonClicked = snack7;
                break;
            case R.id.snack8:
                if (snackTemp <= 0) {
                    return;
                }
                if (!snack8b) {
                    snack8.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0x981e32));
                    //snack8.setTypeface(Typeface.DEFAULT);
                    snackIncomplete++;
                    snack8b = true;
                    if (snackScore != 3) {
                        snackScore = 3;
                    }
                } else if (snack8b) {
                    snack8.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xA9A9A9));
                    //snack8.setTypeface(null, Typeface.BOLD);
                    snackIncomplete--;
                    snack8b = false;
                    //lower score based on which other buttons have already been pressed
                    if (snackScore == 3 && snackIncomplete == 0 && snackInefficient == 0) {
                        snackScore = 1;
                    } else if (snackScore == 3 && snackIncomplete == 0 && snackInefficient > 0) {
                        snackScore = 2;
                    }
                }
                //lastButtonClicked = snack8;
                break;
            case R.id.tea1:
                if (teaTemp <= 0) {
                    return;
                }
                if (!tea1b) {
                    tea1.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0x981e32));
                    //tea1.setTypeface(Typeface.DEFAULT);
                    teaInefficient++;
                    tea1b = true;
                    if (teaScore == 1) {
                        teaScore = 2;
                    }
                } else if (tea1b) {
                    tea1.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xD7D7D7));
                    //tea1.setTypeface(null, Typeface.BOLD);
                    teaInefficient--;
                    tea1b = false;
                    //lower score based on which other buttons have already been pressed
                    if (teaScore == 2 && teaInefficient == 0) {
                        teaScore = 1;
                    }
                }
                //lastButtonClicked = tea1;
                break;
            case R.id.tea2:
                if (teaTemp <= 0) {
                    return;
                }
                if (!tea2b) {
                    tea2.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0x981e32));
                    //tea2.setTypeface(Typeface.DEFAULT);
                    teaInefficient++;
                    tea2b = true;
                    if (teaScore == 1) {
                        teaScore = 2;
                    }
                } else if (tea2b) {
                    tea2.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xD7D7D7));
                    //tea2.setTypeface(null, Typeface.BOLD);
                    teaInefficient--;
                    tea2b = false;
                    //lower score based on which other buttons have already been pressed
                    if (teaScore == 2 && teaInefficient == 0) {
                        teaScore = 1;
                    }
                }
                //lastButtonClicked = tea2;
                break;
            case R.id.tea3:
                if (teaTemp <= 0) {
                    return;
                }
                if (!tea3b) {
                    tea3.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0x981e32));
                    //tea3.setTypeface(Typeface.DEFAULT);
                    teaInefficient++;
                    tea3b = true;
                    if (teaScore == 1) {
                        teaScore = 2;
                    }
                } else if (tea3b) {
                    tea3.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xD7D7D7));
                    //tea3.setTypeface(null, Typeface.BOLD);
                    teaInefficient--;
                    tea3b = false;
                    //lower score based on which other buttons have already been pressed
                    if (teaScore == 2 && teaInefficient == 0) {
                        teaScore = 1;
                    }
                }
                //lastButtonClicked = tea3;
                break;
            case R.id.tea4:
                if (teaTemp <= 0) {
                    return;
                }
                if (!tea4b) {
                    tea4.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0x981e32));
                    //tea4.setTypeface(Typeface.DEFAULT);
                    teaInefficient++;
                    tea4b = true;
                    if (teaScore == 1) {
                        teaScore = 2;
                    }
                } else if (tea4b) {
                    tea4.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xD7D7D7));
                    //tea4.setTypeface(null, Typeface.BOLD);
                    teaInefficient--;
                    tea1b = false;
                    //lower score based on which other buttons have already been pressed
                    if (teaScore == 2 && teaInefficient == 0) {
                        teaScore = 1;
                    }
                }
                //lastButtonClicked = tea4;
                break;
            case R.id.tea5:
                if (teaTemp <= 0) {
                    return;
                }
                if (!tea5b) {
                    tea5.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0x981e32));
                    //tea5.setTypeface(Typeface.DEFAULT);
                    teaInefficient++;
                    tea5b = true;
                    if (teaScore == 1) {
                        teaScore = 2;
                    }
                } else if (tea5b) {
                    tea5.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xD7D7D7));
                    //tea5.setTypeface(null, Typeface.BOLD);
                    teaInefficient--;
                    tea5b = false;
                    //lower score based on which other buttons have already been pressed
                    if (teaScore == 2 && teaInefficient == 0) {
                        teaScore = 1;
                    }
                }
                //lastButtonClicked = tea5;
                break;
            case R.id.tea6:
                if (teaTemp <= 0) {
                    return;
                }
                if (!tea6b) {
                    //tea6T = System.currentTimeMillis() - totalExecutionTime;
                    tea6.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0x981e32));
                    //tea6.setTypeface(Typeface.DEFAULT);
                    teaInefficient++;
                    tea6b = true;
                    if (teaScore == 1) {
                        teaScore = 2;
                    }
                } else if (tea6b) {
                    tea6.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xD7D7D7));
                    //tea6.setTypeface(null, Typeface.BOLD);
                    teaInefficient--;
                    tea6b = false;
                    //lower score based on which other buttons have already been pressed
                    if (teaScore == 2 && teaInefficient == 0) {
                        teaScore = 1;
                    }
                }
                //lastButtonClicked = tea6;
                break;
            case R.id.tea7:
                if (teaTemp <= 0) {
                    return;
                }
                if (!tea7b) {
                    tea7.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0x981e32));
                    //tea7.setTypeface(Typeface.DEFAULT);
                    teaIncomplete++;
                    tea7b = true;
                    if (teaScore != 3) {
                        teaScore = 3;
                    }
                } else if (tea7b) {
                    tea7.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xA9A9A9));
                    //tea7.setTypeface(null, Typeface.BOLD);
                    teaIncomplete--;
                    tea7b = false;
                    //lower score based on which other buttons have already been pressed
                    //lower score based on which other buttons have already been pressed
                    if (teaScore == 3 && teaIncomplete == 0 && teaInefficient == 0) {
                        teaScore = 1;
                    } else if (teaScore == 3 && teaIncomplete == 0 && teaInefficient > 0) {
                        teaScore = 2;
                    }
                }
                //lastButtonClicked = tea7;
                break;
            case R.id.tea8:
                if (teaTemp <= 0) {
                    return;
                }
                if (!tea8b) {
                    tea8.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0x981e32));
                    //tea8.setTypeface(Typeface.DEFAULT);
                    teaIncomplete++;
                    tea8b = true;
                    if (teaScore != 3) {
                        teaScore = 3;
                    }
                } else if (tea8b) {
                    tea8.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xA9A9A9));
                    //tea8.setTypeface(null, Typeface.BOLD);
                    teaIncomplete--;
                    tea8b = false;
                    //lower score based on which other buttons have already been pressed
                    //lower score based on which other buttons have already been pressed
                    if (teaScore == 3 && teaIncomplete == 0 && teaInefficient == 0) {
                        teaScore = 1;
                    } else if (teaScore == 3 && teaIncomplete == 0 && teaInefficient > 0) {
                        teaScore = 2;
                    }
                }
                //lastButtonClicked = tea7;
                break;
            case R.id.tea9:
                if (teaTemp <= 0) {
                    return;
                }
                if (!tea9b) {
                    tea9.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0x981e32));
                    //tea9.setTypeface(Typeface.DEFAULT);
                    teaInaccurate++;
                    tea9b = true;
                    if (teaScore != 3) {
                        teaScore = 3;
                    }
                } else if (tea9b) {
                    tea9.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xA9A9A9));
                    //tea9.setTypeface(null, Typeface.BOLD);
                    teaInaccurate--;
                    tea9b = false;
                    //lower score based on which other buttons have already been pressed
                    //lower score based on which other buttons have already been pressed
                    if (teaScore == 3 && teaIncomplete == 0 && teaInefficient == 0 && teaInaccurate == 0) {
                        teaScore = 1;
                    } else if (teaScore == 3 && teaIncomplete == 0 && teaInaccurate == 0 && teaInefficient > 0) {
                        teaScore = 2;
                    }
                }
                //lastButtonClicked = tea7;
                break;
            case R.id.tea10:
                if (teaTemp <= 0) {
                    return;
                }
                if (!tea10b) {
                    tea10.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0x981e32));
                    //tea10.setTypeface(Typeface.DEFAULT);
                    teaIncomplete++;
                    tea10b = true;
                    if (teaScore != 3) {
                        teaScore = 3;
                    }
                } else if (tea10b) {
                    tea10.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xA9A9A9));
                    //tea10.setTypeface(null, Typeface.BOLD);
                    teaIncomplete--;
                    tea10b = false;
                    //lower score based on which other buttons have already been pressed
                    //lower score based on which other buttons have already been pressed
                    if (teaScore == 3 && teaIncomplete == 0 && teaInaccurate == 0 && teaInefficient == 0) {
                        teaScore = 1;
                    } else if (teaScore == 3 && teaIncomplete == 0 && teaInaccurate == 0 && teaInefficient > 0) {
                        teaScore = 2;
                    }
                }
                //lastButtonClicked = tea7;
                break;
            case R.id.tea11:
                if (teaTemp <= 0) {
                    return;
                }
                if (!tea11b) {
                    tea11.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0x981e32));
                    //tea11.setTypeface(Typeface.DEFAULT);
                    teaIncomplete++;
                    tea11b = true;
                    if (teaScore != 3) {
                        teaScore = 3;
                    }
                } else if (tea11b) {
                    tea11.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xA9A9A9));
                    //tea11.setTypeface(null, Typeface.BOLD);
                    teaIncomplete--;
                    tea11b = false;
                    //lower score based on which other buttons have already been pressed
                    if (teaScore == 3 && teaIncomplete == 0 && teaInaccurate == 0 && teaInefficient == 0) {
                        teaScore = 1;
                    } else if (teaScore == 3 && teaIncomplete == 0 && teaInaccurate == 0 && teaInefficient > 0) {
                        teaScore = 2;
                    }
                }
                //lastButtonClicked = tea7;
                break;
            case R.id.travel1:
                if (travelTemp <= 0) {
                    return;
                }
                if (!travel1b) {
                    travel1.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0x981e32));
                    //travel1.setTypeface(Typeface.DEFAULT);
                    travelInefficient++;
                    travel1b = true;
                    if (travelScore == 1) {
                        travelScore = 2;
                    }
                } else if (travel1b) {
                    travel1.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xD7D7D7));
                    //travel1.setTypeface(null, Typeface.BOLD);
                    travelInefficient--;
                    travel1b = false;
                    //lower score based on which other buttons have already been pressed
                    if (travelScore == 2 && travelInefficient == 0) {
                        travelScore = 1;
                    }
                }
                //lastButtonClicked = travel1;
                break;
            case R.id.travel2:
                if (travelTemp <= 0) {
                    return;
                }
                if (!travel2b) {
                    travel2.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0x981e32));
                    //travel2.setTypeface(Typeface.DEFAULT);
                    travelInefficient++;
                    travel2b = true;
                    if (travelScore == 1) {
                        travelScore = 2;
                    }
                } else if (travel2b) {
                    travel2.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xD7D7D7));
                    //travel2.setTypeface(null, Typeface.BOLD);
                    travelInefficient--;
                    travel2b = false;
                    //lower score based on which other buttons have already been pressed
                    if (travelScore == 2 && travelInefficient == 0) {
                        travelScore = 1;
                    }
                }
                //lastButtonClicked = travel2;
                break;
            case R.id.travel3:
                if (travelTemp <= 0) {
                    return;
                }
                if (!travel3b) {
                    travel3.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0x981e32));
                    //travel3.setTypeface(Typeface.DEFAULT);
                    travelInefficient++;
                    travel3b = true;
                    if (travelScore == 1) {
                        travelScore = 2;
                    }
                } else if (travel3b) {
                    travel3.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xD7D7D7));
                    //travel3.setTypeface(null, Typeface.BOLD);
                    travelInefficient--;
                    travel3b = false;
                    //lower score based on which other buttons have already been pressed
                    if (travelScore == 2 && travelInefficient == 0) {
                        travelScore = 1;
                    }
                }
                //lastButtonClicked = travel3;
                break;
            case R.id.travel4:
                if (travelTemp <= 0) {
                    return;
                }
                if (!travel4b) {
                    travel4.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0x981e32));
                    //travel4.setTypeface(Typeface.DEFAULT);
                    travelIncomplete++;
                    travel4b = true;
                    if (travelScore != 3) {
                        travelScore = 3;
                    }
                } else if (travel4b) {
                    travel4.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xA9A9A9));
                    //travel4.setTypeface(null, Typeface.BOLD);
                    travelIncomplete--;
                    travel4b = false;
                    //lower score based on which other buttons have already been pressed
                    if (travelScore == 3 && travelIncomplete == 0 && travelInaccurate == 0 && travelInefficient == 0) {
                        travelScore = 1;
                    } else if (travelScore == 3 && travelIncomplete == 0 && travelInaccurate == 0 && travelInefficient > 0) {
                        travelScore = 2;
                    }
                }
                //lastButtonClicked = travel4;
                break;
            case R.id.travel5:
                if (travelTemp <= 0) {
                    return;
                }
                if (!travel5b) {
                    travel5.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0x981e32));
                    //travel5.setTypeface(Typeface.DEFAULT);
                    travelIncomplete++;
                    travel5b = true;
                    if (travelScore != 3) {
                        travelScore = 3;
                    }
                } else if (travel5b) {
                    travel5.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xA9A9A9));
                    //travel5.setTypeface(null, Typeface.BOLD);
                    travelIncomplete--;
                    travel5b = false;
                    //lower score based on which other buttons have already been pressed
                    if (travelScore == 3 && travelIncomplete == 0 && travelInaccurate == 0 && travelInefficient == 0) {
                        travelScore = 1;
                    } else if (travelScore == 3 && travelIncomplete == 0 && travelInaccurate == 0 && travelInefficient > 0) {
                        travelScore = 2;
                    }
                }
                //lastButtonClicked = travel5;
                break;
            /*case R.id.mainstart:
                if (!start) {
                    if (mainStart.getText() == "Reset") {
                        Intent i = new Intent(this, MainActivity.class);
                        startActivity(i);
                    } else {
                        totalExecutionTime = System.currentTimeMillis();
                        Toast.makeText(MainActivity.this, "NOT started", Toast.LENGTH_SHORT).show();
                        mainStart.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xf9152f));
                        mainStart.setText("Stop");
                        start = true;
                    }
                } else {
                    //restart activity
                    totalExecutionTime = System.currentTimeMillis() - totalExecutionTime;
                    mainStart.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0x006600));
                    mainStart.setText("Reset");
                    start = false;
                }

                break;*/
            /*case R.id.moviepause:
                pauseContinue();
                break;*/
            case R.id.misc1:
                // get misc button count
                misc1Count++;
                Toast.makeText(MainActivity.this, "Misc1 Count: " + misc1Count, Toast.LENGTH_SHORT).show();

                if (!misc1b) {
                    //Toast.makeText(MainActivity.this, "onClickListener working", Toast.LENGTH_SHORT).show();
                    misc1.setTextColor(Color.parseColor("#000000"));
                    misc1b = true;
                } else if (misc1b) {
                    //misc1.setTextColor(Color.parseColor("#FFFFFF"));
                    misc1b = false;
                }
                break;
            case R.id.misc2:

                // get misc button count
                misc2Count++;
                Toast.makeText(MainActivity.this, "Misc2 Count: " + misc2Count, Toast.LENGTH_SHORT).show();

                if (!misc2b) {
                    misc2.setTextColor(Color.parseColor("#000000"));
                    misc2b = true;
                } else if (misc2b) {
                    //misc2.setTextColor(Color.parseColor("#FFFFFF"));
                    misc2b = false;
                }
                break;
            case R.id.misc3:

                // misc button count
                misc3Count++;
                Toast.makeText(MainActivity.this, "Misc3 Count: " + misc3Count, Toast.LENGTH_SHORT).show();

                if (!misc3b) {
                    misc3.setTextColor(Color.parseColor("#000000"));
                    misc3b = true;
                } else if (misc3b) {
                    //misc3.setTextColor(Color.parseColor("#FFFFFF"));
                    misc3b = false;
                }
                break;
            case R.id.misc4:

                // misc button count
                misc4Count++;
                Toast.makeText(MainActivity.this, "Misc4 Count: " + misc4Count, Toast.LENGTH_SHORT).show();

                if (!misc4b) {
                    misc4.setTextColor(Color.parseColor("#000000"));
                    misc4b = true;
                } else if (misc4b) {
                    //misc4.setTextColor(Color.parseColor("#FFFFFF"));
                    misc4b = false;
                }
                break;
            case R.id.moviestart:
                //stopLastTimer();
                timerMovie();
                lastButtonClicked = movieStart;
                break;
            case R.id.moneystart:
                //stopLastTimer();
                timerMoney();
                lastButtonClicked = moneyStart;
                break;
            case R.id.exitstart:
                //stopLastTimer();
                timerExit();
                lastButtonClicked = exitStart;
                break;
            case R.id.phonestart:
                //stopLastTimer();
                timerPhone();
                lastButtonClicked = phoneStart;
                break;
            case R.id.recipestart:
                //stopLastTimer();
                timerRecipe();
                lastButtonClicked = recipeStart;
                break;
            case R.id.snackstart:
                //stopLastTimer();
                timerSnack();
                lastButtonClicked = snackStart;
                break;
            case R.id.teastart:
                //stopLastTimer();
                timerTea();
                lastButtonClicked = teaStart;
                break;
            case R.id.travelstart:
                //stopLastTimer();
                timerTravel();
                lastButtonClicked = travelStart;
                break;
            case R.id.midtaskplanningstart:
                //stopLastTimer();
                timerMidTaskPlanning();
                //lastButtonClicked = travelStart;
                break;
            case R.id.Continue:
                /*this code saves buttons that were pressed, which is saved to file in the summary activity
                saveButtons();*/
                saveData();
                saveOtherErrors();
                saveButtons();
                nextActivity();

                break;


        }

    }

    public void comments(final View v){

        //recipeCom, movieCom, phoneCom, snackCom, teaCom, travelCom, exitCom, moneyCom;
        //recipeCom = "this is working mate";
        AlertDialog.Builder builder2;

        builder2 = new AlertDialog.Builder(this);
        // Get the layout inflater
        LayoutInflater inflater = getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        final View view = inflater.inflate(R.layout.fragment_comments, null);

        // Make connection her before setting view.
        final EditText text = (EditText)view.findViewById(R.id.comment);
        //text.setText(recipeCom);

        switch(v.getId()) {
            case R.id.recipecomments:
                text.setText(recipeCom);
                builder2.setTitle("Recipe Comments");
                break;
            case R.id.moviecomments:
                text.setText(movieCom);
                builder2.setTitle("Movie Comments");
                break;
            case R.id.moneycomments:
                text.setText(moneyCom);
                builder2.setTitle("Money Comments");
                break;
            case R.id.snackcomments:
                text.setText(snackCom);
                builder2.setTitle("Snack Comments");
                break;
            case R.id.travelcomments:
                text.setText(travelCom);
                builder2.setTitle("Travel Comments");
                break;
            case R.id.teacomments:
                text.setText(teaCom);
                builder2.setTitle("Tea Comments");
                break;
            case R.id.exitcomments:
                text.setText(exitCom);
                builder2.setTitle("Exit Comments");
                break;
            case R.id.phonecomments:
                text.setText(phoneCom);
                builder2.setTitle("Phone Comments");
                break;
        }
        builder2.setView(view)
                // Add action buttons
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        // sign in the user ...
                        SharedPreferences sharedPref = getSharedPreferences("FILENAME", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPref.edit();

                        String temp = text.getText().toString();

                        //Toast.makeText(MainActivity.this, temp, Toast.LENGTH_LONG).show();
                        switch(v.getId()) {

                            case R.id.recipecomments:

                                recipeCom = text.getText().toString();
                                editor.putString("recipecomments", recipeCom);
                                break;

                            case R.id.travelcomments:

                                travelCom = text.getText().toString();
                                editor.putString("travelcomments", travelCom);
                                break;

                            case R.id.teacomments:

                                teaCom = text.getText().toString();
                                editor.putString("teacomments", teaCom);
                                break;

                            case R.id.moviecomments:

                                movieCom = text.getText().toString();
                                editor.putString("moviecomments", movieCom);
                                break;

                            case R.id.moneycomments:

                                moneyCom = text.getText().toString();
                                editor.putString("moneycomments", moneyCom);
                                break;

                            case R.id.phonecomments:

                                phoneCom = text.getText().toString();
                                editor.putString("phonecomments", phoneCom);
                                break;

                            case R.id.snackcomments:

                                snackCom = text.getText().toString();
                                editor.putString("snackcomments", snackCom);
                                break;

                            case R.id.exitcomments:

                                exitCom = text.getText().toString();
                                editor.putString("exitComments", exitCom);
                                break;

                        }

                        editor.apply();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        //LoginDialogFragment.this.getDialog().cancel();

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

    public void restartMainActivity() {

        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);

    }

    public void nextActivity() {

        Intent i = new Intent(this, FinalActivity.class);
        startActivity(i);

    }

    public void ingredients(View v) {
        dialog1.show();
    }


    public void saveData() {

        SharedPreferences sharedPref = getSharedPreferences("FILENAME", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putBoolean("moreMoney", moreMoney);
        editor.putBoolean("lessMoney", lessMoney);

        editor.putBoolean("movieLate", movieLate);
        editor.putBoolean("movieEarly", movieEarly);

        //SharedPreferences.Editor.clear();
        editor.apply();

        totalIncomplete = movieIncomplete + moneyIncomplete + travelIncomplete + teaIncomplete + exitIncomplete + phoneIncomplete
                + recipeIncomplete + snackIncomplete;
        totalInaccurate = movieInaccurate + moneyInaccurate + travelInaccurate + teaInaccurate + exitInaccurate + phoneInaccurate
                + recipeInaccurate + snackInaccurate;
        totalInefficient = movieInefficient + moneyInefficient + travelInefficient + teaInefficient + exitInefficient
                + phoneInefficient + recipeInefficient + snackInefficient;

        int totalErrors = totalIncomplete + totalInaccurate + totalInefficient;

        editor.putInt("movieScore", movieScore);
        editor.putLong("movieActive", timeMovie / 1000);
        editor.putLong("multiMovie", multitaskMovie / 1000);
        movieSequencing = sequence("movie");
        editor.putString("movieSeq", movieSequencing);
        editor.putInt("movieSim", calculateIndividualSimultaneous(1));
        editor.putInt("movineff", movieInefficient);
        editor.putInt("movincom", movieIncomplete);
        editor.putInt("movinac", movieInaccurate);

        editor.putInt("moneyScore", moneyScore);
        editor.putLong("moneyActive", timeMoney / 1000);
        editor.putLong("multiMoney", multitaskMoney / 1000);
        moneySequencing = sequence("money");
        editor.putString("moneySeq", moneySequencing);
        editor.putInt("moneySim", calculateIndividualSimultaneous(6));
        editor.putInt("monineff", moneyInefficient);
        editor.putInt("monincom", moneyIncomplete);
        editor.putInt("moninac", moneyInaccurate);

        editor.putInt("phoneScore", phoneScore);
        editor.putLong("phoneActive", timePhone / 1000);
        editor.putLong("multiPhone", multitaskPhone / 1000);
        phoneSequencing = sequence("phone");
        editor.putString("phoneSeq", phoneSequencing);
        editor.putInt("phoneSim", calculateIndividualSimultaneous(5));
        editor.putInt("phoineff", phoneInefficient);
        editor.putInt("phoincom", phoneIncomplete);
        editor.putInt("phoinac", phoneInaccurate);

        editor.putInt("teaScore", teaScore);
        editor.putLong("teaActive", timeTea / 1000);
        editor.putLong("multiTea", multitaskTea / 1000);
        teaSequencing = sequence("tea");
        editor.putString("teaSeq", teaSequencing);
        editor.putInt("teaSim", calculateIndividualSimultaneous(7));
        editor.putInt("teaineff", teaInefficient);
        editor.putInt("teaincom", teaIncomplete);
        editor.putInt("teainac", teaInaccurate);

        editor.putInt("snackScore", snackScore);
        editor.putLong("snackActive", timeSnack / 1000);
        editor.putLong("multiSnack", multitaskSnack / 1000);
        snackSequencing = sequence("snack");
        editor.putString("snackSeq", snackSequencing);
        editor.putInt("snackSim", calculateIndividualSimultaneous(2));
        editor.putInt("snaineff", snackInefficient);
        editor.putInt("snaincom", snackIncomplete);
        editor.putInt("snainac", snackInaccurate);

        editor.putInt("travelScore", travelScore);
        editor.putLong("travelActive", timeTravel / 1000);
        editor.putLong("multiTravel", multitaskTravel / 1000);
        travelSequencing = sequence("travel");
        editor.putString("travelSeq", travelSequencing);
        editor.putInt("travelSim", calculateIndividualSimultaneous(3));
        editor.putInt("traineff", travelInefficient);
        editor.putInt("traincom", travelIncomplete);
        editor.putInt("trainac", travelInaccurate);

        editor.putInt("recipeScore", recipeScore);
        editor.putLong("recipeActive", timeRecipe / 1000);
        editor.putLong("multiRecipe", multitaskRecipe / 1000);
        recipeSequencing = sequence("recipe");
        editor.putString("recipeSeq", recipeSequencing);
        editor.putInt("recipeSim", calculateIndividualSimultaneous(0));
        editor.putInt("recineff", recipeInefficient);
        editor.putInt("recincom", recipeIncomplete);
        editor.putInt("recinac", recipeInaccurate);

        editor.putInt("exitScore", exitScore);
        editor.putLong("exitActive", timeExit / 1000);
        editor.putLong("multiExit", multitaskExit / 1000);
        exitSequencing = sequence("exit");
        editor.putString("exitSeq", exitSequencing);
        editor.putInt("exitSim", calculateIndividualSimultaneous(4));
        editor.putInt("exiineff", exitInefficient);
        editor.putInt("exiincom", exitIncomplete);
        editor.putInt("exiinac", exitInaccurate);

        editor.putBoolean("additionalNTRA", misc1b);
        editor.putBoolean("perseveration", misc2b);
        editor.putBoolean("wandering", misc3b);
        editor.putBoolean("requests help", misc4b);

        editor.putLong("taskplanning", planningTime / 1000);
        editor.putLong("totalexecution", totalExecutionTime / 1000);
        //editor.putInt("overallquality", overallQuality);

        editor.putString("overallQuality", calculateQuality());
        editor.putInt("correctSequencing", calculateSequencing());
        editor.putInt("errorTotals", totalErrors);
        editor.putInt("totalIncomplete", totalIncomplete);
        editor.putInt("totalInaccurate", totalInaccurate);
        editor.putInt("totalInefficient", totalInefficient);
        //totalIncomplete + totalInaccurate + totalInefficient;

        //this variable is changed in calculateSequencing()
        editor.putBoolean("phoneCallEnd", phoneCallEnd);

        // save start times for tasks
        editor.putString("movieStartTime", movieStartTime);

        // Save task End times
        editor.putString("movieFinishTime", movieFinishTime);

        // save misc counts
        editor.putInt("misc1Count", misc1Count);
        editor.putInt("misc2Count", misc2Count);
        editor.putInt("misc3Count", misc3Count);
        editor.putInt("misc4Count", misc4Count);

        // save task counts
        editor.putInt("recipeCount", recipeCount);
        editor.putInt("exitCount", exitCount);
        editor.putInt("movieCount", movieCount);
        editor.putInt("phoneCount", phoneCount);
        editor.putInt("snackCount", snackCount);
        editor.putInt("moneyCount", moneyCount);
        editor.putInt("travelCount", travelCount);
        editor.putInt("teaCount", teaCount);
        editor.putInt("midTaskPlanningCount", midTaskPlanningCount);

        editor.putInt("totalInterruptions", recipeCount + exitCount + movieCount + phoneCount + snackCount + moneyCount + travelCount + teaCount);

        // total midTaskPlanningCount
        editor.putLong("timeMidTaskPlanning", timeMidTaskPlanning/1000);

        // save start times
        editor.putString("overallStartTime", overallStartTime);
        editor.putString("overallFinishTime", overallFinishTime);
        //private String overallFinishTime;
        editor.putString("recipeStartTime", recipeStartTime);
        //private String recipeFinishTime;
        editor.putString("exitStartTime", exitStartTime);
        //private String exitFinishTime;
        editor.putString("movieStartTime", movieStartTime);
        //private String movieFinishTime;
        editor.putString("phoneStartTime", phoneStartTime);
        //private String phoneFinishTime;
        editor.putString("snackStartTime", snackStartTime);
        //private String snackFinishTime;
        editor.putString("changeStartTime", changeStartTime);
        //private String changeFinishTime;
        editor.putString("travelStartTime", travelStartTime);
        //private String travelFinishTime;
        editor.putString("teaStartTime", teaStartTime);
        //private String teaFinishTime;

        editor.putString("date", NOTdate);
        //Toast.makeText(getBaseContext(), NOTdate, Toast.LENGTH_SHORT).show();

        editor.apply();
    }

    public void stopLastTimer() {
        // not used
    }

    public void stopTimers() {

        if (movie) {
            timerMovie();
        }
        if (phone) {
            timerPhone();
        }
        if (snack) {
            timerSnack();
        }
        if (recipe) {
            timerRecipe();
        }
        if (money) {
            timerMoney();
        }
        if (tea) {
            timerTea();
        }
        if (travel) {
            timerTravel();
        }
        if (exit) {
            timerExit();
        }
        if(midTaskPlanning){
            timerMidTaskPlanning();
        }
    }

    /*
    public void undo(View v){
        //ColorDrawable buttonColor = (ColorDrawable) movie1.getColor();
        //int btncolor = (int) ColorDrawable;
        Toast.makeText(MainActivity.this, "onClickListener working", Toast.LENGTH_SHORT).show();
        lastButtonClicked.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xD7D7D7));
        //use get color method to set original filter
    }*/

    public void timerMovie() {

        if (noMoreTiming) {
            return;
        }
        if (lastButtonClicked != movieStart) {
            stopLastTimer();
            lastButtonClicked = movieStart;
        }
        if (movieScore == 4) {
            //start time for movie
            //Toast.makeText(MainActivity.this, "Movie first Started", Toast.LENGTH_SHORT).show();
            if (!planFinished) {
                calculatePlanning();
                planFinished = true;
            }
            movieTemp = System.currentTimeMillis();
            movieScore = 1;
            startSequence("movie");

            // get start timetimestamp
            dateobj = new Date();
            movieStartTime = df.format(dateobj);
            //Toast.makeText(MainActivity.this, movieStartTime, Toast.LENGTH_SHORT).show();

        }
        if (!movie) {

            movieCount++;
            movieTemp2 = System.currentTimeMillis();
            movie = true;
            movieStart.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xf9152f));
            movieStart.setText("Stop");
            simultaneousOrder.add("movie");
            simultaneousIndex++;
            currentSimultaneous[1] = true;
            calculateSimultaneous();
            //Toast.makeText(MainActivity.this, "Start Button Presseddddd!!!", Toast.LENGTH_SHORT).show();
        } else {

            // update finish time
            dateobj = new Date();
            movieFinishTime = df.format(dateobj);
            //Toast.makeText(MainActivity.this, movieFinishTime, Toast.LENGTH_SHORT).show();

            //stop timer
            //moneyFinish will be subtracted from multitaskMoney during the summary activity to get total time of completion
            long temp = System.currentTimeMillis();
            multitaskMovie = temp - movieTemp;
            timeMovie += (temp - movieTemp2);
            //timeMovie /= 1000;
            movieStart.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0x006600));
            movieStart.setText("Continue");
            movie = false;
            finishSequence("movie");
            simultaneousOrder.add("movie");
            currentSimultaneous[1] = false;
        }

    }

    public void timerMoney() {

        if (noMoreTiming) {
            return;
        }
        if (lastButtonClicked != moneyStart) {
            stopLastTimer();
            lastButtonClicked = moneyStart;
        }
        if (moneyScore == 4) {
            //start time for movie
            moneyTemp = System.currentTimeMillis();
            if (!planFinished) {
                calculatePlanning();
                planFinished = true;
            }
            moneyScore = 1;
            startSequence("money");

            // get start timetimestamp
            dateobj = new Date();
            changeStartTime = df.format(dateobj);

        }
        if (!money) {
            moneyCount++;
            moneyTemp2 = System.currentTimeMillis();
            money = true;
            moneyStart.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xf9152f));
            moneyStart.setText("Stop");
            simultaneousOrder.add("money");
            simultaneousIndex++;
            currentSimultaneous[6] = true;
            calculateSimultaneous();
        } else {
            //stop timer
            //moneyFinish will be subtracted from multitaskMoney during the summary activity to get total time of completion
            long temp = System.currentTimeMillis();
            multitaskMoney = temp - moneyTemp;
            timeMoney += (temp - moneyTemp2);
            //timeTravel /= 1000;
            moneyStart.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0x006600));
            moneyStart.setText("Continue");
            money = false;
            finishSequence("money");
            simultaneousOrder.add("money");
            currentSimultaneous[6] = false;
        }

    }

    public void timerExit() {

        if (noMoreTiming) {
            return;
        }
        if (lastButtonClicked != exitStart) {
            stopLastTimer();
            lastButtonClicked = exitStart;
        }
        if (exitScore == 4) {
            //start time for movie
            //Toast.makeText(MainActivity.this, "IS ZERO", Toast.LENGTH_SHORT).show();
            exitTemp = System.currentTimeMillis();
            if (!planFinished) {
                calculatePlanning();
                planFinished = true;
            }
            exitScore = 1;
            startSequence("exit");

            // get start timetimestamp
            dateobj = new Date();
            exitStartTime = df.format(dateobj);

        }
        if (!exit) {
            exitCount++;
            exitTemp2 = System.currentTimeMillis();
            exit = true;
            exitStart.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xf9152f));
            exitStart.setText("Stop");
            simultaneousOrder.add("exit");
            simultaneousIndex++;
            currentSimultaneous[4] = true;
            calculateSimultaneous();
        } else {
            //stop timer
            //moneyFinish will be subtracted from multitaskMoney during the summary activity to get total time of completion
            long temp = System.currentTimeMillis();
            multitaskExit = temp - exitTemp;
            timeExit += (temp - exitTemp2);
            //timeMovie /= 1000;
            exitStart.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0x006600));
            exitStart.setText("Continue");
            exit = false;
            finishSequence("exit");
            simultaneousOrder.add("exit");
            currentSimultaneous[4] = false;
        }

    }

    public void timerPhone() {

        if (noMoreTiming) {
            return;
        }
        if (lastButtonClicked != phoneStart) {
            stopLastTimer();
            lastButtonClicked = phoneStart;
        }
        if (phoneScore == 4) {
            //start time for movie
            //Toast.makeText(MainActivity.this, "IS ZERO", Toast.LENGTH_SHORT).show();
            phoneTemp = System.currentTimeMillis();
            if (!planFinished) {
                calculatePlanning();
                planFinished = true;
            }
            phoneScore = 1;
            startSequence("phone");

            // get start timetimestamp
            dateobj = new Date();
            phoneStartTime = df.format(dateobj);

        }
        if (!phone) {
            phoneCount++;
            phoneTemp2 = System.currentTimeMillis();
            phone = true;
            phoneStart.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xf9152f));
            phoneStart.setText("Stop");
            simultaneousOrder.add("phone");
            simultaneousIndex++;
            currentSimultaneous[5] = true;
            calculateSimultaneous();
        } else {
            //stop timer
            //moneyFinish will be subtracted from multitaskMoney during the summary activity to get total time of completion
            long temp = System.currentTimeMillis();
            multitaskPhone = temp - phoneTemp;
            timePhone += (temp - phoneTemp2);
            //timeMovie /= 1000;
            phoneStart.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0x006600));
            phoneStart.setText("Continue");
            phone = false;
            finishSequence("phone");
            simultaneousOrder.add("phone");
            currentSimultaneous[5] = false;
        }
    }

    public void timerRecipe() {

        if (noMoreTiming) {
            return;
        }
        if (lastButtonClicked != recipeStart) {
            stopLastTimer();
            lastButtonClicked = recipeStart;
        }
        if (recipeScore == 4) {
            //start time for movie
            recipeTemp = System.currentTimeMillis();
            if (!planFinished) {
                calculatePlanning();
                planFinished = true;
            }
            recipeScore = 1;
            startSequence("recipe");

            // get start timetimestamp
            dateobj = new Date();
            recipeStartTime = df.format(dateobj);

        }
        if (!recipe) {
            recipeCount++;
            recipeTemp2 = System.currentTimeMillis();
            recipe = true;
            recipeStart.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xf9152f));
            recipeStart.setText("Stop");
            simultaneousOrder.add("recipe");
            simultaneousIndex++;
            currentSimultaneous[0] = true;
            calculateSimultaneous();
        } else {
            //stop timer
            //moneyFinish will be subtracted from multitaskMoney during the summary activity to get total time of completion
            long temp = System.currentTimeMillis();
            multitaskRecipe = temp - recipeTemp;
            timeRecipe += (temp - recipeTemp2);
            //timeRecipe /= 1000;
            recipeStart.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0x006600));
            recipeStart.setText("Continue");
            recipe = false;
            finishSequence("recipe");
            simultaneousOrder.add("recipe");
            currentSimultaneous[0] = false;

        }
    }

    public void timerSnack() {
        if (noMoreTiming) {
            return;
        }
        if (lastButtonClicked != snackStart) {
            stopLastTimer();
            lastButtonClicked = snackStart;
        }
        if (snackScore == 4) {
            //start time for movie
            snackTemp = System.currentTimeMillis();
            if (!planFinished) {
                calculatePlanning();
                planFinished = true;
            }
            snackScore = 1;
            startSequence("snack");

            // get start timetimestamp
            dateobj = new Date();
            snackStartTime = df.format(dateobj);

        }
        if (!snack) {

            snackCount++;
            snackTemp2 = System.currentTimeMillis();
            snack = true;
            snackStart.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xf9152f));
            snackStart.setText("Stop");
            simultaneousOrder.add("snack");
            simultaneousIndex++;
            currentSimultaneous[2] = true;
            calculateSimultaneous();
        } else {
            //stop timer
            //moneyFinish will be subtracted from multitaskMoney during the summary activity to get total time of completion
            long temp = System.currentTimeMillis();
            multitaskSnack = temp - snackTemp;
            timeSnack += (temp - snackTemp2);
            //timeTravel /= 1000;
            snackStart.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0x006600));
            snackStart.setText("Continue");
            snack = false;
            finishSequence("snack");
            simultaneousOrder.add("snack");
            currentSimultaneous[2] = false;
        }
    }

    public void timerTea() {
        if (noMoreTiming) {
            return;
        }
        if (lastButtonClicked != teaStart) {
            stopLastTimer();
            lastButtonClicked = teaStart;
        }
        if (teaScore == 4) {
            //start time for movie
            teaTemp = System.currentTimeMillis();
            if (!planFinished) {
                calculatePlanning();
                planFinished = true;
            }
            teaScore = 1;
            startSequence("tea");

            // get start timetimestamp
            dateobj = new Date();
            teaStartTime = df.format(dateobj);

        }
        if (!tea) {
            teaCount++;
            teaTemp2 = System.currentTimeMillis();
            tea = true;
            teaStart.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xf9152f));
            teaStart.setText("Stop");
            simultaneousOrder.add("tea");
            simultaneousIndex++;
            currentSimultaneous[7] = true;
            calculateSimultaneous();
        } else {
            //stop timer
            //moneyFinish will be subtracted from multitaskMoney during the summary activity to get total time of completion
            long temp = System.currentTimeMillis();
            multitaskTea = temp - teaTemp;
            timeTea += (temp - teaTemp2);
            //timeTea /= 1000;
            teaStart.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0x006600));
            teaStart.setText("Continue");
            tea = false;
            finishSequence("tea");
            simultaneousOrder.add("tea");
            currentSimultaneous[7] = false;
        }
    }

    public void timerTravel() {
        if (noMoreTiming) {
            return;
        }
        if (lastButtonClicked != travelStart) {
            stopLastTimer();
            lastButtonClicked = travelStart;
        }
        if (travelScore == 4) {
            //start time for movie
            travelTemp = System.currentTimeMillis();
            if (!planFinished) {
                calculatePlanning();
                planFinished = true;
            }
            travelScore = 1;
            startSequence("travel");

            // get start timetimestamp
            dateobj = new Date();
            travelStartTime = df.format(dateobj);

        }
        if (!travel) {
            travelCount++;
            travelTemp2 = System.currentTimeMillis();
            travel = true;
            travelStart.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xf9152f));
            travelStart.setText("Stop");
            simultaneousOrder.add("travel");
            simultaneousIndex++;
            currentSimultaneous[3] = true;
            calculateSimultaneous();
        } else {
            //stop timer
            //moneyFinish will be subtracted from multitaskMoney during the summary activity to get total time of completion
            long temp = System.currentTimeMillis();
            multitaskTravel = temp - travelTemp;
            timeTravel += (temp - travelTemp2);
            //timeTravel /= 1000;
            travelStart.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0x006600));
            travelStart.setText("Continue");
            travel = false;
            finishSequence("travel");
            simultaneousOrder.add("travel");
            currentSimultaneous[3] = false;
        }
    }

    public void timerMidTaskPlanning(){
        if (noMoreTiming) {
            return;
        }

        if (!midTaskPlanning) {

            midTaskPlanningCount++;
            midTaskPlanningTemp = System.currentTimeMillis();
            midTaskPlanning = true;
            midTaskPlanningStart.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xf9152f));
            midTaskPlanningStart.setText("Stop");

        } else {
            //stop timer
            //moneyFinish will be subtracted from multitaskMoney during the summary activity to get total time of completion
            long temp = System.currentTimeMillis();
            long temp2 = (temp - midTaskPlanningTemp);
            timeMidTaskPlanning += temp2;
            midTaskPlanningStart.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0x006600));
            midTaskPlanningStart.setText("Continue");
            midTaskPlanning = false;

        }
    }


    public void startSequence(String category) {

        for (int i = 0; i < 8; i++) {
            if (startOrder[i] != null && startOrder[i].equals(category)) {
                return;
            }
        }

        startOrder[startIndex] = category;
        startIndex++;
    }

    public void finishSequence(String category) {

        /*current attempt*/
        int index = 0;
        while(index < 8){
            if(finishOrder[index] == null){
                finishOrder[index] = category;
                break;
            }
            if(finishOrder[index].equals(category)){
                if(index == 7 || finishOrder[index+1] == null){
                    break;
                }
                finishOrder[index] = finishOrder[index + 1];
                finishOrder[index + 1] = category;
            }
            index++;
        }

    }

    public String sequence(String category) {
        int start = 0;
        int finish = 0;
        int i = 0;
        int z = 0;
        while (i < 8 && !category.equals(startOrder[i])) {
            i++;
            start = i;
        }

        while (z < 8 && !category.equals(finishOrder[z])) {
            z++;
            finish = z;
        }
        if (finish == 8 || start == 8) {
            return "0 - 0";
        }
        return Integer.toString(start + 1) + " - " + Integer.toString(finish + 1);


    }

    public int calculateIndividualSimultaneous(int category){

        int total = 0;
        switch(category){
            case 0:
                for(int i = 0; i < 8; i++){
                    if(recipeSimultaneous[i] && i!=0){
                        total++;
                    }
                    //key:
                    //movie 1
                    //recipe 0
                    //money 6
                    //snack 2
                    //phone 5
                    //exit 4
                    //tea 7
                    //travel 3
                }
                break;
            case 1:
                for(int i = 0; i < 8; i++){
                    if(movieSimultaneous[i] && i!=1){
                        total++;
                    }
                }
                break;
            case 2:
                for(int i = 0; i < 8; i++){
                    if(snackSimultaneous[i] && i!=2){
                        total++;
                    }
                }
                break;
            case 3:
                for(int i = 0; i < 8; i++){
                    if(travelSimultaneous[i] && i!=3){
                        total++;
                    }
                }
                break;
            case 4:
                for(int i = 0; i < 8; i++){
                    if(exitSimultaneous[i] && i!=4){
                        total++;
                    }
                }
                break;
            case 5:
                for(int i = 0; i < 8; i++){
                    if(phoneSimultaneous[i] && i!=5){
                        total++;
                    }
                }
                break;
            case 6:
                for(int i = 0; i < 8; i++){
                    if(moneySimultaneous[i] && i!=6){
                        total++;
                    }
                }
                break;
            case 7:
                for(int i = 0; i < 8; i++){
                    if(teaSimultaneous[i] && i!=7){
                        total++;
                    }
                }
                break;
        }

        return total;
    }

    public void calculateSimultaneous(){
        for(int i = 0; i < 8; i++){
            if(currentSimultaneous[i]){
                if(i == 0){
                   for(int z = 0; z < 8; z++){
                       if(currentSimultaneous[z] && !recipeSimultaneous[z]){
                           recipeSimultaneous[z] = true;
                       }
                   }
                }else if(i == 1){
                    for(int z = 0; z < 8; z++){
                        if(currentSimultaneous[z] && !movieSimultaneous[z]){
                            movieSimultaneous[z] = true;
                        }
                    }
                }else if(i == 2){
                    for(int z = 0; z < 8; z++){
                        if(currentSimultaneous[z] && !snackSimultaneous[z]){
                            snackSimultaneous[z] = true;
                        }
                    }
                }else if(i == 3){
                    for(int z = 0; z < 8; z++){
                        if(currentSimultaneous[z] && !travelSimultaneous[z]){
                            travelSimultaneous[z] = true;
                        }
                    }
                }else if(i == 4){
                    for(int z = 0; z < 8; z++){
                        if(currentSimultaneous[z] && !exitSimultaneous[z]){
                            exitSimultaneous[z] = true;
                        }
                    }
                }else if(i == 5){
                    for(int z = 0; z < 8; z++){
                        if(currentSimultaneous[z] && !phoneSimultaneous[z]){
                            phoneSimultaneous[z] = true;
                        }
                    }
                }else if(i == 6){
                    for(int z = 0; z < 8; z++){
                        if(currentSimultaneous[z] && !moneySimultaneous[z]){
                            moneySimultaneous[z] = true;
                        }
                    }
                }else if(i == 7){
                    for(int z = 0; z < 8; z++){
                        if(currentSimultaneous[z] && !teaSimultaneous[z]){
                            teaSimultaneous[z] = true;
                        }
                    }
                }
            }
        }
    }

    public void saveButtons() {

        SharedPreferences sharedPref = getSharedPreferences("FILENAME", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        if (movie1b)
            editor.putInt("movie1b", 1);//"Movie: Looks in multiple locations " + Long.toString(movie1T / 1000)
        else
            editor.putInt("movie1b", 2);
        if (movie9b)
            editor.putInt("movie9b", 1);//Movie: Records leaving before 6:25
        else
            editor.putInt("movie9b", 2);
        if (movie1b)
            editor.putInt("movie2b", 1);//"Movie: Looks in multiple locations " + Long.toString(movie1T / 1000)
        else
            editor.putInt("movie2b", 2);
        if (movie1b)
            editor.putInt("movie3b", 1);//"Movie: Looks in multiple locations " + Long.toString(movie1T / 1000)
        else
            editor.putInt("movie3b", 2);
        if (movie1b)
            editor.putInt("movie4b", 1);//"Movie: Looks in multiple locations " + Long.toString(movie1T / 1000)
        else
            editor.putInt("movie4b", 2);
        if (movie1b)
            editor.putInt("movie5b", 1);//"Movie: Looks in multiple locations " + Long.toString(movie1T / 1000)
        else
            editor.putInt("movie5b", 2);
        if (movie1b)
            editor.putInt("movie6b", 1);//"Movie: Looks in multiple locations " + Long.toString(movie1T / 1000)
        else
            editor.putInt("movie6b", 2);
        if (movie2b)
            editor.putInt("movie7b", 1); //Movie: Records leaving after 6:35
        else
            editor.putInt("movie7b", 2);
        if (movie8b)
            editor.putInt("movie8b", 1);//Movie: Does not finish task
        else
            editor.putInt("movie8b", 2);


        if (money1b)
            editor.putInt("money1b", 1);//Money: Looks in multiple locations
        else
            editor.putInt("money1b", 2);
        if (money2b)
            editor.putInt("money2b", 1);//Money: Gathers $ before checking schedule
        else
            editor.putInt("money2b", 2);
        if (money3b)
            editor.putInt("money3b", 1); //Money: Gathers more $ than recorded
        else
            editor.putInt("money3b", 2);
        if (money4b)
            editor.putInt("money4b", 1);//Money: Adjusts $ after finishing task
        else
            editor.putInt("money4b", 2);
        if (money5b)
            editor.putInt("money5b", 1);//Money: Gathers less $ than recorded
        else
            editor.putInt("money5b", 2);
        if (money6b)
            editor.putInt("money6b", 1);//Money: Does not take $ out of pocket at end
        else
            editor.putInt("money6b", 2);
        if (money7b)
            editor.putInt("money7b", 1);//Money: Does not bring $ to door
        else
            editor.putInt("money7b", 2);
        if (money8b)
            editor.putInt("money8b", 1);//Money: Money: Does not finish task
        else
            editor.putInt("money8b", 2);


        if (exit1b)
            editor.putInt("exit1b", 1);//Exit: Makes multiple trips to door
        else
            editor.putInt("exit1b", 2);
        if (exit2b)
            editor.putInt("exit2b", 1);//Exit: Not one of last 2 tasks
        else
            editor.putInt("exit2b", 2);
        if (exit3b)
            editor.putInt("exit3b", 1);//Exit: Does not bring bag to door
        else
            editor.putInt("exit3b", 2);
        if (exit4b)
            editor.putInt("exit4b", 1);//Exit: Does not finish task
        else
            editor.putInt("exit4b", 2);


        if (phone1b)
            editor.putInt("phone1b", 1);//Phone: Looks in multiple locations
        else
            editor.putInt("phone1b", 2);
        if (phone2b)
            editor.putInt("phone2b", 1);//Phone: Call is not last task before exit
        else
            editor.putInt("phone2b", 2);
        if (phone3b)
            editor.putInt("phone3b", 1);//Phone: Calls but does not mention leaving
        else
            editor.putInt("phone3b", 2);
        if (phone4b)
            editor.putInt("phone4b", 1);//Phone: Makes call more than once
        else
            editor.putInt("phone4b", 2);
        if (phone5b)
            editor.putInt("phone5b", 1);//Phone: Does not call
        else
            editor.putInt("phone5b", 2);
        if (phone6b)
            editor.putInt("phone6b", 1);//Phone: Gets phone but does not call
        else
            editor.putInt("phone6b", 2);
        if (phone7b)
            editor.putInt("phone7b", 1);//Phone: Phone Does not finish task
        else
            editor.putInt("phone7b", 2);


        if (recipe1b)
            editor.putInt("recipe1b", 1);//Recipe: Gathers items before reading recipe
        else
            editor.putInt("recipe1b", 2);
        if (recipe2b)
            editor.putInt("recipe2b", 1);//Recipe: Retrieval inefficient (>2 trips each cup.)
        else
            editor.putInt("recipe2b", 2);
        if (recipe3b)
            editor.putInt("recipe3b", 1);//Recipe: Does not efficiently locate recipe (index/TOC)
        else
            editor.putInt("recipe3b", 2);
        if (recipe4b)
            editor.putInt("recipe4b", 1);//Recipe: Gathers creamy PB, not chunky
        else
            editor.putInt("recipe4b", 2);
        if (recipe5b)
            editor.putInt("recipe5b", 1);//Recipe: Gathers table salt, not kosher
        else
            editor.putInt("recipe5b", 2);
        if (recipe6b)
            editor.putInt("recipe6b", 1);//Recipe: Gathers espresso, not coffee
        else
            editor.putInt("recipe6b", 2);
        if (recipe7b)
            editor.putInt("recipe7b", 1);//Recipe: Gathers extra items
        else
            editor.putInt("recipe7b", 2);
        if (recipe8b)
            editor.putInt("recipe8b", 1);//Recipe: Does not gather 1-2 nonessential items
        else
            editor.putInt("recipe8b", 2);
        if (recipe9b)
            editor.putInt("recipe9b", 1);//Recipe: Carries all items to bag, not bag to items
        else
            editor.putInt("recipe9b", 2);
        if (recipe10b)
            editor.putInt("recipe10b", 1);//Recipe: Makes change after finishing task
        else
            editor.putInt("recipe10b", 2);
        if (recipe11b)
            editor.putInt("recipe11b", 1);//Recipe: Does not gather 1+ essential items
        else
            editor.putInt("recipe11b", 2);
        if (recipe12b)
            editor.putInt("recipe12b", 1);//Recipe: Does not gather 3+ nonessential items
        else
            editor.putInt("recipe12b", 2);
        if (recipe13b)
            editor.putInt("recipe13b", 1);//Recipe: Locates wrong recipe and gathers items
        else
            editor.putInt("recipe13b", 2);
        if (recipe14b)
            editor.putInt("recipe14b", 1);//Recipe: Does not finish task
        else
            editor.putInt("recipe14b", 2);
        if (recipe15b)
            editor.putInt("recipe15b", 1);//Gathers granulated sugar, not powdered
        else
            editor.putInt("recipe15b", 2);


        if (snack1b)
            editor.putInt("snack1b", 1);//Snack: Looks in multiple locations
        else
            editor.putInt("snack1b", 2);
        if (snack2b)
            editor.putInt("snack2b", 1);
        else
            editor.putInt("snack2b", 2);
        if (snack3b)
            editor.putInt("snack3b", 1);
        else
            editor.putInt("snack3b", 2);
        if (snack4b)
            editor.putInt("snack4b", 1);
        else
            editor.putInt("snack4b", 2);
        if (snack5b)
            editor.putInt("snack5b", 1);
        else
            editor.putInt("snack5b", 2);
        if (snack6b)
            editor.putInt("snack6b", 1);
        else
            editor.putInt("snack6b", 2);
        if (snack7b)
            editor.putInt("snack7b", 1);
        else
            editor.putInt("snack7b", 2);
        if (snack8b)
            editor.putInt("snack8b", 1);
        else
            editor.putInt("snack8b", 2);


        if (tea1b)
            editor.putInt("tea1b", 1);
        else
            editor.putInt("tea1b", 2);
        if (tea2b)
            editor.putInt("tea2b", 1);
        else
            editor.putInt("tea2b", 2);
        if (tea3b)
            editor.putInt("tea3b", 1);
        else
            editor.putInt("tea3b", 2);
        if (tea4b)
            editor.putInt("tea4b", 1);
        else
            editor.putInt("tea4b", 2);
        if (tea5b)
            editor.putInt("tea5b", 1);
        else
            editor.putInt("tea5b", 2);
        if (tea6b)
            editor.putInt("tea6b", 1);
        else
            editor.putInt("tea6b", 2);
        if (tea7b)
            editor.putInt("tea7b", 1);
        else
            editor.putInt("tea7b", 2);
        if (tea8b)
            editor.putInt("tea8b", 1);
        else
            editor.putInt("tea8b", 2);
        if (tea9b)
            editor.putInt("tea9b", 1);
        else
            editor.putInt("tea9b", 2);
        if (tea10b)
            editor.putInt("tea10b", 1);
        else
            editor.putInt("tea10b", 2);
        if (tea11b)
            editor.putInt("tea11b", 1);
        else
            editor.putInt("tea11b", 2);


        if (travel1b)
            editor.putInt("travel1b", 1);
        else
            editor.putInt("travel1b", 2);
        if (travel2b)
            editor.putInt("travel2b", 1);
        else
            editor.putInt("travel2b", 2);
        if (travel3b)
            editor.putInt("travel3b", 1);
        else
            editor.putInt("travel3b", 2);
        if (travel4b)
            editor.putInt("travel4b", 1);
        else
            editor.putInt("travel4b", 2);
        if (travel5b)
            editor.putInt("travel5b", 1);
        else
            editor.putInt("travel5b", 2);


        if (misc1b)
            editor.putInt("misc1b", 1);
        else
            editor.putInt("misc1b", 2);
        if (misc2b)
            editor.putInt("misc2b", 1);
        else
            editor.putInt("misc2b", 2);
        if (misc3b)
            editor.putInt("misc3b", 1);
        else
            editor.putInt("misc3b", 2);
        if (misc4b)
            editor.putInt("misc4b", 1);
        else
            editor.putInt("misc3b", 2);

        editor.apply();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.wesle.wsuuioption1/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.wesle.wsuuioption1/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

    public void otherButton(final View v){

        if(otherErrors >= 3){
            Toast.makeText(MainActivity.this, "This app does not allow any more than 3 other error selections", Toast.LENGTH_SHORT).show();
            return;
        }
        builder3 = new AlertDialog.Builder(this);
        //builder.setTitle("Ingredients List");
        LayoutInflater inflater = this.getLayoutInflater();
        final View view = inflater.inflate(R.layout.fragment_other_button, null);
        builder3.setView(view)
                // Add action buttons
                .setTitle("Which task the error belongs to?")
                .setPositiveButton("Add Error", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // sign in the user ...

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //LoginDialogFragment.this.getDialog().cancel();
                    }
                });

        dialog = builder3.create();
        dialog.show();
        //Overriding the handler
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Boolean wantToCloseDialog = false;
                //Do stuff, possibly set wantToCloseDialog to true then...

                RadioGroup b = (RadioGroup) view.findViewById(R.id.radiogroup);
                if(otherErrors < 3){

                    switch(b.getCheckedRadioButtonId()){

                        case -1:
                            Toast.makeText(MainActivity.this, "No task selected", Toast.LENGTH_SHORT).show();
                            //call function again so that window is still up for user to select category
                            break;
                        case R.id.recipebutton:
                            Toast.makeText(MainActivity.this, "Other recipe error added", Toast.LENGTH_SHORT).show();
                            otherErrorList[otherErrors] = "recipe";
                            break;
                        case R.id.travelbutton:
                            Toast.makeText(MainActivity.this, "Other travel error added", Toast.LENGTH_SHORT).show();
                            otherErrorList[otherErrors] = "travel";
                            break;
                        case R.id.teabutton:
                            Toast.makeText(MainActivity.this, "Other tea error added", Toast.LENGTH_SHORT).show();
                            otherErrorList[otherErrors] = "tea";
                            break;
                        case R.id.snackbutton:
                            Toast.makeText(MainActivity.this, "Other snack error added", Toast.LENGTH_SHORT).show();
                            otherErrorList[otherErrors] = "snack";
                            break;
                        case R.id.moviebutton:
                            Toast.makeText(MainActivity.this, "Other movie error added", Toast.LENGTH_SHORT).show();
                            otherErrorList[otherErrors] = "movie";
                            break;
                        case R.id.moneybutton:
                            Toast.makeText(MainActivity.this, "Other money error added", Toast.LENGTH_SHORT).show();
                            otherErrorList[otherErrors] = "money";
                            break;
                        case R.id.phonebutton:
                            Toast.makeText(MainActivity.this, "Other phone error added", Toast.LENGTH_SHORT).show();
                            otherErrorList[otherErrors] = "phone";
                            break;
                        case R.id.exitbutton:
                            Toast.makeText(MainActivity.this, "Other exit error added", Toast.LENGTH_SHORT).show();
                            otherErrorList[otherErrors] = "exit";
                            break;
                    }
                    otherErrors++;
                    if(b.getCheckedRadioButtonId() != -1){
                        wantToCloseDialog = true;
                    }
                }

                if(wantToCloseDialog)
                    dialog.dismiss();
                //else dialog stays open. Make sure you have an obvious way to close the dialog especially if you set cancellable to false.

            }
        });
    }

    public void saveOtherErrors(){
        SharedPreferences sharedPref = getSharedPreferences("FILENAME", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        for(int i = 0; i < otherErrors; i++){
            switch(i){
                case 0:
                    editor.putString("errorone", otherErrorList[i]);
                    break;
                case 1:
                    editor.putString("errortwo", otherErrorList[i]);
                    break;
                case 2:
                    editor.putString("errorthree", otherErrorList[i]);
                    break;
            }
        }
        editor.putInt("othererrortotal", otherErrors);
        editor.apply();
    }

    // method to make toasts easier
    public void message(String x){
        Toast.makeText(MainActivity.this, x, Toast.LENGTH_SHORT).show();
    }

}
