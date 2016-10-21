package com.evader.rookies.evade;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.evader.rookies.evade.R;
import com.evader.rookies.evade.ScoreRow;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Scores extends AppCompatActivity {

    ArrayList<Integer> scores = new ArrayList<>();
    ArrayList<Integer> tempScores = new ArrayList<Integer>();
    //ArrayList<String> names = new ArrayList<>();
    private int NUM_TOP_SCORES = 3;
    //String name = "someone"; //need to ask for/pass
    int score; //= 2324; //need to pass through
    SharedPreferences storedScores; //storedNames; //storedDifficulty;
    StoreScores object;
    private static final String SCORES = "Score";
    //private static final String DIFFICULTY = "dispdifficulty";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);
        //Bundle b = getIntent().getExtras();
        //score = b.getInt("score");
        storedScores = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = storedScores.edit();
        object = new StoreScores(scores, this);
        //sets scores to 0 arraylist
        for(int i=NUM_TOP_SCORES; i>0; i--) {
            scores.add(0);
        }
        //get score from game
        Intent intent = getIntent();
        score = intent.getIntExtra("currScore", 0);
        storedScores = object.storeScores(scores);
        //retreive previous scores
        //if (!(b.getString(SCORES).equals(null))) {
            scores = object.jsonToArrayList();

        //}
        storedScores.getString(SCORES,"DEFAULT");

        boolean resortCall = addScore(score); //adds score to array and if added, sorts list again and reforms the sharedpreference file
        if(resortCall)  {
            Thread sc = new Thread();

            System.out.print("RE-SORT CALL WORKS");
            //arrayListToFile(scores);
            insertionSort();
            //mergeSort(0, NUM_TOP_SCORES-1);
            fillTableRows(); //adds tablerows and textviews
            //add this arraylist to the sharedprefs to store it
            storedScores = object.storeScores(scores);
        }
        editor.apply();
    }
    //checks arraylist to see if new score beats any existing top scores
    //replaces lowest score with new one
    //returns boolean to indicate whether or not a score was appended, which
    //tells if the arrayToFile method needs to be called
    public boolean addScore(int score) {
        int lowIndex = -1;
        boolean added = false;
        for(int x = 0 ; x < NUM_TOP_SCORES; x++) {
            int tempScore = score;
            if(tempScore > scores.get(x)) {
                lowIndex = x;
                tempScore = scores.get(lowIndex);
            }
        }
        if(lowIndex != -1) {
            scores.set(lowIndex, score);
            added = true;
        }
        return added;
    }
    public void playGame(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    private void insertionSort() {
        int n = scores.size();
        for (int j = 0; j < n; j++) {
            int key = scores.get(j);
            int i = j-1;
            while ( (i > -1) && ( scores.get(i) < key ) ) {
                scores.set(i+1,scores.get(i));
                i--;
            }
            scores.set(i+1,key);
        }
    }
    private void mergeSort(int lowIndex, int highIndex) {
        ArrayList<String> tempArray = new ArrayList<String>();
        if(lowIndex<highIndex) {
            int middle = (lowIndex + (highIndex-lowIndex))/2;
            mergeSort(lowIndex, middle);
            mergeSort(middle,highIndex);

            for(int i = lowIndex; i <= highIndex; i++) {
                tempScores.set(i,scores.get(i));
            }
            int i = lowIndex;
            int j = middle + 1;
            int k = lowIndex;
            while (i <= middle && j <= highIndex) {
                if (tempScores.get(i) <= tempScores.get(j)) {
                    scores.set(k, tempScores.get(i));
                    i++;
                } else {
                    scores.set(k, tempScores.get(j));
                    j++;
                }
                k++;
            }
            while (i <= middle) {
                scores.set(k, tempScores.get(i));
                k++;
                i++;
            }
        }
    }
    public void fillTableRows() {
        TableLayout table = (TableLayout)findViewById(R.id.scoreTable);
        for(Integer x:scores) {
            System.out.println("Score exists!: " + x);
            TableRow row = new TableRow(this);
            TableRow.LayoutParams params = new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            row.setLayoutParams(params);
            TextView text = new TextView(this);
            text.setText((x.toString()));
            text.setTextSize(20);
            text.setTextColor(Color.WHITE);
            table.setVisibility(View.VISIBLE);
            row.setVisibility(View.VISIBLE);
            text.setVisibility(View.VISIBLE);
            table.addView(row);
            row.addView(text);
        }
    }
    /*private File scoreToFile(ScoreRow content) {
        try{
            String name = content.name;
            String score = Integer.toString(content.score);

            File file = getFileStreamPath("scores.txt");

            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter writer = new FileWriter(file.getName());

            writer.write(name + " " + score + "\n");
            writer.flush();
            writer.close();
            return file;
        }
        catch (IOException io) {
            System.out.println("e");
        }
    }*/

    /*private File arrayListToFile(ArrayList<ScoreRow> list) {
        File file = getFileStreamPath("scores.txt");
        try {

            FileWriter writer = new FileWriter(file.getName());

            for(ScoreRow row:list) {
                writer.write(row.name + " " + row.score + "\n");
                writer.flush();
            }
            writer.close();
        }
        catch (IOException io){
            System.out.println("e2");

        }
        return file;
    }*/
}