package com.evader.rookies.evade;

import java.util.ArrayList;

import android.content.Intent;
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

import java.lang.InterruptedException;
import java.util.Timer;
import java.util.TimerTask;

public class Game extends Activity implements View.OnTouchListener, Runnable {
    int x=0;
    int whatever = 0;
    int yDecrement = 50;
    long newEnemyTime=3000;
    ImageView socguy, pianoObstacle;
    View view;
    long time;
    boolean firstTimeAround=true;
    DisplayMetrics displayMetrics;
    ArrayList <ImageView> listOImages = new ArrayList<ImageView>();
    RelativeLayout linearLayout;
    int y=0;


    public void run(){
        android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_BACKGROUND);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_game);
        super.onCreate(savedInstanceState);
        if (firstTimeAround == true) {
            socguy = (ImageView) findViewById(R.id.imageView);
            pianoObstacle = (ImageView) findViewById(R.id.obstacle);
            view = findViewById(R.id.clickview);
            time = System.currentTimeMillis();
            firstTimeAround = false;
            displayMetrics = new DisplayMetrics();
            linearLayout= (RelativeLayout) findViewById(R.id.gamerelativelayout);
            view.setOnTouchListener(this);

        }

        listOImages.add(pianoObstacle); //IMPORTANT: added preset imageview instead of creating a new imageview dynamically (as Aditya did before) --> CHANGE LATER!
        listOImages.get(listOImages.size() - 1).setImageResource(R.drawable.piano);
        listOImages.get(listOImages.size() - 1).setY(20);
        listOImages.get(listOImages.size() - 1).setX(200);

        //long currentTime = System.currentTimeMillis();
        //if (currentTime - time == newEnemyTime) {


        Timer timer = new Timer();
        TimerTask timerTask= new TimerTask() {
            @Override
            public void run() {
                new Thread(new Runnable() {
                    public void run() {
                        pianoObstacle.post(new Runnable() {
                            @Override
                            public void run() {
                                letItRain();
                            }
                        });
                    }
                }).start();
                try {
                    Thread.sleep(100);
                }
                catch (InterruptedException e){
                    System.out.println(e);
                }
            }
        };
        timer.schedule(timerTask, 1000,100);

//        while(whatever<1000){
//            time = System.currentTimeMillis();
//            listOImages.add(pianoObstacle);
//            listOImages.get(listOImages.size() - 1).setImageResource(R.drawable.piano);
//            //listOImages.get(listOImages.size() - 1).setLayoutParams(new RelativeLayout.LayoutParams(200, 200));
//            //listOImages.get(listOImages.size() - 1).setX((int) (Math.random() * displayMetrics.widthPixels));
//            //pianoObstacle.setX((float)(Math.random()*displayMetrics.widthPixels)); // DOES NOT WORK!!
//
//            listOImages.get(listOImages.size() - 1).setX((float) (Math.random() * 400)); //Change This Hardcoding value*****
//            listOImages.get(listOImages.size() - 1).setY(20); //max value is 700
//
//            letItRain();
//            whatever++;
//        }
            //listOImages.get(listOImages.size() - 1).setY(30);
            //linearLayout.addView(listOImages.get(listOImages.size() - 1), (int) (Math.random() * displayMetrics.widthPixels), 200);
            //listOImages.get(listOImages.size() - 1).setVisibility(View.VISIBLE);
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

   public void letItRain()
    {


            System.out.println("hello!"); //NOT PRINTING!
            //listOImages.get(listOImages.size() - 1).setLayoutParams(new RelativeLayout.LayoutParams(200, 200));
            //listOImages.get(listOImages.size() - 1).setX((int) (Math.random() * displayMetrics.widthPixels));
            //pianoObstacle.setX((float)(Math.random()*displayMetrics.widthPixels)); // DOES NOT WORK!!

             //Change This Hardcoding value*****
             //max value is 700


            listOImages.get(listOImages.size() - 1).setY(listOImages.get((listOImages.size()-1)).getY()+20);



    }


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


    protected void loop(){
        try {
            Thread.sleep(300);
        }
        catch(InterruptedException e){
            System.out.println(e);
        }
        Bundle b = new Bundle();
        onCreate(b);

    }

}