package com.evader.rookies.evade;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.lang.InterruptedException;

public class Game extends Activity implements View.OnTouchListener {
    int x=0;
    int yDecrement = 50;
    long newEnemyTime=3000;
    ImageView socguy;
    View view;
    long time;
    boolean firstTimeAround=true;
    DisplayMetrics displayMetrics;
    ArrayList <ImageView> listOImages = new ArrayList<ImageView>();
    RelativeLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_game);
        super.onCreate(savedInstanceState);
        if (firstTimeAround == true) {
            socguy = (ImageView) findViewById(R.id.imageView);
            view = findViewById(R.id.clickview);
            time = System.currentTimeMillis();
            firstTimeAround = false;
            displayMetrics = new DisplayMetrics();
            linearLayout= (RelativeLayout) findViewById(R.id.gamerelativelayout);

        }

        long currentTime = System.currentTimeMillis();
        //if (currentTime - time == newEnemyTime) {
            time = System.currentTimeMillis();
            listOImages.add(new ImageView(Game.this));
            listOImages.get(listOImages.size() - 1).setImageResource(R.drawable.piano);
            listOImages.get(listOImages.size() - 1).setLayoutParams(new RelativeLayout.LayoutParams(200, 200));
            listOImages.get(listOImages.size() - 1).setX((int) (Math.random() * displayMetrics.widthPixels));
            listOImages.get(listOImages.size() - 1).setY(30);
            linearLayout.addView(listOImages.get(listOImages.size() - 1), (int) (Math.random() * displayMetrics.widthPixels), 200);
            listOImages.get(listOImages.size() - 1).setVisibility(View.VISIBLE);
        //}

        x++;
        System.out.println(x);
        /*(for (ImageView piano : listOImages) {
            piano.setY(yDecrement + piano.getY());
            if (piano.getY() > view.getLayoutParams().height) {
                listOImages.remove(piano);
            }
        }*/
        view.setOnTouchListener(this);
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