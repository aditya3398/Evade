package com.evader.rookies.evade;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.prefs.Preferences;

/**
 * Created by vishwa on 9/8/2016.
 */
public class StoreScores extends Activity {
    public static final String NAME = "dispscores";
    public static final String SCORES_STRING = "Score";
    public ArrayList<Integer> scores;
    public Context context;

    public StoreScores(ArrayList<Integer> scores, Context context) {
        super();
        this.scores = scores;
        this.context = context;

    }

    public void storeScores(List scores) { //arraylist in JSON string
        //creates sharedpreferences object (which holds primitive data types) and its editor
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        //utilizes the gson library to convert array to string
        Gson gson = new Gson();
        String scoreJson = gson.toJson(scores);
        //assigns json string to sharedpreferences variable
        editor.putString(SCORES_STRING, scoreJson);
        editor.apply();
        //return prefs;
    }
    public ArrayList<Integer> jsonToArrayList() { //json to arraylist
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        ArrayList<Integer> scores;
        if (prefs.contains(SCORES_STRING)) {
            String scoreJson = prefs.getString(SCORES_STRING, null);
            Gson gson = new Gson();
            Integer[] scoreList = gson.fromJson(scoreJson, Integer[].class);
            List<Integer> temp = Arrays.asList(scoreList);
            ArrayList<Integer> realTemp = new ArrayList<Integer>();
            for(Integer x:temp) {
                realTemp.add(x);
            }
            scores = realTemp;
        }
        else {
            return null;
        }
        return (ArrayList<Integer>) scores;
    }
    public void addScore(int score) {
        ArrayList<Integer> scores = jsonToArrayList();
        if(scores == null) {
            scores = new ArrayList<Integer>();
        }
        scores.add(score);
        storeScores(scores);
    }

    public void removeScore(int score) {
        List scores = jsonToArrayList();
        if(scores != null) {
            scores.remove(score);
            storeScores(scores);
        }
    }
}
