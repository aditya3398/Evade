package com.evader.rookies.evade;

import android.content.Context;
import android.widget.ImageView;

/**
 * Created by adityanadkarni on 9/8/16.
 */
public class Piano extends ImageView {
    private boolean offScreen;
    private boolean alreadySetOffScreen;
    private boolean collided;

    public Piano(Context context){
        super(context);
        offScreen=false;
        collided=false;
        alreadySetOffScreen=false;
    }

    public boolean isOffScreen() {
        return offScreen;
    }

    public void setOffScreen(boolean offScreen) {
        this.offScreen = offScreen;
    }
    public boolean isCollided() {
        return collided;
    }

    public void setCollided(boolean collided) {
        this.collided = collided;
    }
    public boolean getAlreadySetOffScreen() {
        return alreadySetOffScreen;
    }

    public void setAlreadySetOffScreen(boolean alreadySetOffScreen) {
        this.alreadySetOffScreen = alreadySetOffScreen;
    }

}
