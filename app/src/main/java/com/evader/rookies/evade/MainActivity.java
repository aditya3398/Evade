package com.evader.rookies.evade;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //called when button on home screen is clicked
    public void playGame(View view) {
        Intent intent = new Intent(this, Game.class);
        startActivity(intent);
    }

}
