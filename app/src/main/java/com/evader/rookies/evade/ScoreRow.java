package com.evader.rookies.evade;

/**
 * Created by vishwa on 9/5/2016.
 *
 * item score row has name and score
 * get and set methods included
 */
public class ScoreRow {

    String name;
    int score;

    public ScoreRow (String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return this.name;
    }

    public int getScore() {
        return this.score;
    }

    public void setName(String name) { name = this.name; }

    public void setScore(int score) { score = this.score; }
}