package com.evader.rookies.evade;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String difficulty = "";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();


        difficulty = preferences.getString("DifficultyLevel", "");

        if(difficulty.equals("")){

            editor.putString("DifficultyLevel","Medium");
            editor.apply();
            difficulty = preferences.getString("DifficultyLevel", "");



        }

    }

    //called when button on home screen is clicked
    public void playGame(View view) {


        Intent startingGameIntent = new Intent(this, Game.class);
        startingGameIntent.putExtra("DifficultyLevel", difficulty);
        startActivity(startingGameIntent);
    }

    public void changeDifficulty(View view){
        Intent changingDifficultyIntent = new Intent(this, Difficulty.class);
        final int result = 1;

        startActivityForResult(changingDifficultyIntent, result);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        difficulty = data.getStringExtra("DifficultyLevel");
        Toast.makeText(this, "Changed difficulty to " + difficulty, Toast.LENGTH_SHORT).show();

    }






}
