package com.evader.rookies.evade;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.lang.InterruptedException;

public class Game extends Activity implements View.OnTouchListener {
    int x=0;
    int yDecrement = 50;
    ImageView socguy;
    View view;
    long time;
    boolean firstTimeAround=true;
    DisplayMetrics displayMetrics;
    ArrayList <ImageView> listOImages = new ArrayList<ImageView>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if (firstTimeAround==true){
            socguy = (ImageView)findViewById(R.id.imageView);
            view = findViewById(R.id.clickview);
            time=System.currentTimeMillis();
            firstTimeAround=false;
            displayMetrics=new DisplayMetrics();

        }
        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.gamerelativelayout);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        long currentTime=System.currentTimeMillis();



        time=System.currentTimeMillis();
        listOImages.add(new ImageView(this));
        listOImages.get(listOImages.size()-1).setImageResource(R.drawable.piano);
        //listOImages.get(listOImages.size()-1).setX((int) (Math.random() * displayMetrics.widthPixels));
        //listOImages.get(listOImages.size()-1).setY(30);
        System.out.println(linearLayout);
        listOImages.get(listOImages.size()-1).setLayoutParams(new ViewGroup.LayoutParams(30, 30));
        linearLayout.addView(listOImages.get(listOImages.size() - 1));
        listOImages.get(listOImages.size()-1).setVisibility(View.VISIBLE);




        x++;
        System.out.println(x);

        for(ImageView piano : listOImages){
            piano.setY(50 + piano.getY());
            if(piano.getY()>view.getLayoutParams().height){
                listOImages.remove(piano);
            }
        }
        view.setOnTouchListener(this);
        //loop();


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
            Thread.sleep(30);
        }
        catch(InterruptedException e){
            System.out.println(e);
        }
        Bundle b = new Bundle();
        onCreate(b);

    }

}