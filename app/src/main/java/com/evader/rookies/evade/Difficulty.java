package com.evader.rookies.evade;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;

/**
 * Created by shuka on 9/7/2016.
 */
public class Difficulty extends Activity {

    String difficulty = "";

    //Error recording commits for this file as mine (not linked to my account)
    //Changes can be seen in the previous commit history under the name 'Shukan Shah'
    //Created/Modified the file Difficulty.java




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.activity_change_difficulty);
        super.onCreate(savedInstanceState);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);



        difficulty = preferences.getString("DifficultyLevel", "");

    }

    public void onEasy(View view){

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString("DifficultyLevel","Easy");
        editor.apply();

        goingBack();

    }
    public void onMedium(View view){

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString("DifficultyLevel","Medium");
        editor.apply();

        goingBack();

    }
    public void onHard(View view){

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString("DifficultyLevel","Hard");
        editor.apply();

        goingBack();

    }

    public void goingBack(){

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();

        difficulty = preferences.getString("DifficultyLevel", "");

        Intent goingBack = new Intent();

        goingBack.putExtra("DifficultyLevel", difficulty);
        setResult(RESULT_OK, goingBack);

        finish();

    }
}
