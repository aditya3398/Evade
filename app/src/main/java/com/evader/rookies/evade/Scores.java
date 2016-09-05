package com.evader.rookies.evade;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.evader.rookies.evade.R;
import com.evader.rookies.evade.ScoreRow;

import java.io.File;
import java.util.ArrayList;

public class Scores extends AppCompatActivity {

    ArrayList<ScoreRow> scores = new ArrayList<ScoreRow>();
    private int NUM_TOP_SCORES = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);
        //adds however many NUM_TOP_SCORES items to arraylist
        for(int i=NUM_TOP_SCORES; i>0; i--) {
            scores.add(new ScoreRow("",0));
        }

    }
    //checks arraylist to see if new score beats any existing top scores
    //replaces lowest score with new one
    public void addScore(String name, int score) {
        int lowIndex = -1;
        for(int x = 0 ; x < NUM_TOP_SCORES; x++) {
            int tempScore = score;
            if(tempScore > scores.get(x).getScore()) {
                lowIndex = x;
                tempScore = scores.get(lowIndex).getScore();
            }
        }
        scores.set(lowIndex,new ScoreRow(name, score));

    }

    private void scoresToFile(ArrayList<ScoreRow> scores) {

    }
}