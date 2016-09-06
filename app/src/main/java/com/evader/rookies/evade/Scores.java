package com.evader.rookies.evade;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.evader.rookies.evade.R;
import com.evader.rookies.evade.ScoreRow;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Scores extends AppCompatActivity {

    ArrayList<ScoreRow> scores = new ArrayList<ScoreRow>();
    private int NUM_TOP_SCORES = 10;
    String name = "someone";
    int score = 2324;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);

        ScoreRow row = new ScoreRow(name,score);
        boolean fileCall = addScore(name,score);
        if(fileCall)  {
            arrayListToFile(scores);
        }

        //adds however many NUM_TOP_SCORES items to arraylist
        /*for(int i=NUM_TOP_SCORES; i>0; i--) {
            scores.add(new ScoreRow("",0));
        }*/



    }
    //checks arraylist to see if new score beats any existing top scores
    //replaces lowest score with new one
    //returns boolean to indicate whether or not a score was appended, which
    //tells if the arrayToFile method needs to be called
    public boolean addScore(String name, int score) {
        int lowIndex = -1;
        boolean added = false;
        for(int x = 0 ; x < NUM_TOP_SCORES; x++) {
            int tempScore = score;
            if(tempScore > scores.get(x).getScore()) {
                lowIndex = x;
                tempScore = scores.get(lowIndex).getScore();
            }
        }
        scores.set(lowIndex,new ScoreRow(name, score));
        if(lowIndex != -1) {
            added = true;
        }
        return added;
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

    private File arrayListToFile(ArrayList<ScoreRow> list) {
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
    }

    public void fillTableRow() {

    }
}