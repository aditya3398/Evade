package com.evader.rookies.evade;

import java.util.ArrayList;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.app.Activity;
import android.os.Looper;
import android.os.SystemClock;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.InterruptedException;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;

public class Game extends Activity implements View.OnTouchListener, Runnable {

    int x=0;
    int yIncrement = 50;
    long newEnemyTime=1000;
    ImageView socguy, pianoObstacle;
    View view;
    long time;
    boolean firstTimeAround=true;
    DisplayMetrics displayMetrics;
    ArrayList <Piano> listOImages = new ArrayList<Piano>();
    RelativeLayout relativeLayout;
    int y=0;
    int pianoWidth;
    int pianoHeight;
    int initialYPos = 20;
    int stickmanWidth;
    boolean gameOver;
    int score=0;
    TextView scoreView;
    String difficulty;


    public void run(){ //ensures additional threads don't conflict with main UI thread
        android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_BACKGROUND);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.activity_game);
        super.onCreate(savedInstanceState);
        Bundle b = getIntent().getExtras();
        difficulty = b.getString("DifficultyLevel");

        if (difficulty.equals("Easy")){
            yIncrement = yIncrement - 30;
        }
        else if (difficulty.equals("Medium")){
            yIncrement = 50;
        }
        else if(difficulty.equals("Hard")){
            yIncrement = yIncrement + 30;
        }
        else{
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }





        socguy = (ImageView) findViewById(R.id.imageView);
        view = findViewById(R.id.clickview);
        scoreView = (TextView)(findViewById(R.id.scoreView));
        time = System.currentTimeMillis();
        firstTimeAround = false;
        displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        relativeLayout= (RelativeLayout) findViewById(R.id.gamerelativelayout);
        stickmanWidth = socguy.getWidth();
        pianoWidth = displayMetrics.widthPixels/10;
        pianoHeight = displayMetrics.heightPixels/10;


//        listOImages.add(pianoObstacle); //IMPORTANT: added preset imageview instead of creating a new imageview dynamically (as Aditya did before) --> CHANGE LATER!
//        listOImages.get(listOImages.size() - 1).setImageResource(R.drawable.piano);
//        listOImages.get(listOImages.size() - 1).setY(20);
//        listOImages.get(listOImages.size() - 1).setX(200);
        time = System.currentTimeMillis();

        //if (currentTime - time == newEnemyTime) {


        Timer timer = new Timer();
        TimerTask timerTask= new TimerTask() {
            @Override
            public void run() {
                new Thread(new Runnable() {
                    public void run() {
                        socguy.post(new Runnable() {
                            @Override
                            public void run() {
                                letItRain();
                                view.setOnTouchListener(Game.this);
                            }
                        });
                    }
                }).start();

                try {
                    Thread.sleep(300);
                }
                catch (InterruptedException e){
                    System.out.println(e);
                }
            }
        };
        timer.schedule(timerTask, 1000, 100);

//        while(whatever<1000){


            //listOImages.get(listOImages.size() - 1).setX((int) (Math.random() * displayMetrics.widthPixels));
            //pianoObstacle.setX((float)(Math.random()*displayMetrics.widthPixels)); // DOES NOT WORK!!

             //max value is 700
//
//            letItRain();
//            whatever++;
//        }
            //listOImages.get(listOImages.size() - 1).setY(30);
            //
            //
        //}


        //x++;
        //System.out.println(x);

        x++;
        System.out.println(x);

        /*(for (ImageView piano : listOImages) {
            piano.setY(yDecrement + piano.getY());
            if (piano.getY() > view.getLayoutParams().height) {
                listOImages.remove(piano);
            }
        }*/

    }

   public void letItRain(){
            if (firstTimeAround==true){
                listOImages.add(new Piano(this)); //new imageView is added to array list of imageviews
                listOImages.get(listOImages.size() - 1).setImageResource(R.drawable.piano);
                //listOImages.get(listOImages.size() - 1).setLayoutParams(new RelativeLayout.LayoutParams(pianoWidth, pianoHeight));
                //Change This Hardcoding value*****
                listOImages.get(listOImages.size() - 1).setY(initialYPos);
                listOImages.get(listOImages.size()-1).setMaxWidth(100);
                listOImages.get(listOImages.size()-1).setMaxHeight(100);
                relativeLayout.addView(listOImages.get(listOImages.size() - 1), pianoWidth, pianoHeight);
                listOImages.get(listOImages.size() - 1).setVisibility(View.VISIBLE);
                listOImages.get(listOImages.size() - 1).setX((float) (Math.random() * displayMetrics.widthPixels));
                firstTimeAround=false;
            }
            int xPosition= (int) (Math.random() * displayMetrics.widthPixels);

            System.out.println(displayMetrics.widthPixels); //NOT PRINTING!
            //listOImages.get(listOImages.size() - 1).setLayoutParams(new RelativeLayout.LayoutParams(200, 200));
            //listOImages.get(listOImages.size() - 1).setX((int) (Math.random() * displayMetrics.widthPixels));
            //pianoObstacle.setX((float)(Math.random()*displayMetrics.widthPixels)); // DOES NOT WORK!!

             //Change This Hardcoding value*****
             //max value is 700

            long currentTime = System.currentTimeMillis();
            if (currentTime-time>=newEnemyTime){ //every second a new imageView is created
                time = System.currentTimeMillis();
                listOImages.add(new Piano(this)); //new imageView is added to array list of imageviews
                listOImages.get(listOImages.size() - 1).setImageResource(R.drawable.piano);
                //listOImages.get(listOImages.size() - 1).setLayoutParams(new RelativeLayout.LayoutParams(pianoWidth, pianoHeight));
                 //Change This Hardcoding value*****
                listOImages.get(listOImages.size() - 1).setY(initialYPos);
                listOImages.get(listOImages.size()-1).setMaxWidth(100);
                listOImages.get(listOImages.size()-1).setMaxHeight(100);
                relativeLayout.addView(listOImages.get(listOImages.size() - 1), pianoWidth, pianoHeight);
                listOImages.get(listOImages.size() - 1).setVisibility(View.VISIBLE);
                listOImages.get(listOImages.size() - 1).setX((float) (Math.random() * displayMetrics.widthPixels));
            }

            for (Piano piano : listOImages) {
                piano.setY(piano.getY() + yIncrement);
                if (hasCollided(piano)==true){
                    gameOver=true;
                }
                if(piano.getY()>displayMetrics.heightPixels){
                    piano.setOffScreen(true);
                }
            }
            if(gameOver) {
                //gameEnd();
            }
       incrementScore(listOImages);
       String scoreText = "Score:" + String.valueOf(score);
       scoreView.setText(scoreText);
       scoreView.setVisibility(View.VISIBLE);
    }

    public boolean hasCollided(ImageView piano) {
        boolean collided=false;
        int socguyLocation []= new int [2];
        socguy.getLocationOnScreen(socguyLocation);
        int topRightSocguyCorner=socguyLocation[0]+stickmanWidth;
        int pianoLocation [] = new int [2];
        piano.getLocationOnScreen(pianoLocation);
        if(socguyLocation[1] <= pianoLocation[1]+pianoHeight && displayMetrics.heightPixels>=pianoLocation[1]){
            if(pianoLocation[0] <= topRightSocguyCorner && pianoLocation[0]+pianoWidth >= socguyLocation[0]) {
                collided = true;
                System.out.println("COLLISION");
                System.out.println("Socguy X: " + socguyLocation[0]);
                System.out.println("Socguy y: " + socguyLocation[1]);
                System.out.println("piano X: " + pianoLocation[0]);
                System.out.println("piano Y: " + pianoLocation[1]);
                Toast t= Toast.makeText(this,"Collision" ,Toast.LENGTH_SHORT);
                t.show();
                //gameEnd();
            }
        }
        return collided;
    }

    public void incrementScore(ArrayList<Piano> pianoList){
        for (Piano piano : pianoList){
            if(piano.getAlreadySetOffScreen()==false && piano.isOffScreen()==true){
                System.out.println(piano);
                score++;
                piano.setAlreadySetOffScreen(true);
            }
        }
    }

    //public void gameEnd() {
    //    startActivity(new Intent(this, Scores.class));
    //}


    public boolean onTouch(View view, MotionEvent event) {
        System.out.println(event.getRawX());
        System.out.println("Socguy: " + socguy.getX());
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (event.getRawX() >= socguy.getX()) {
                socguy.setX(socguy.getX() + 50.0f);
            } else {
                socguy.setX(socguy.getX() - 50.0f);
            }
        }
        return true;
    }


}