package com.thebigbreakup.breakout.database.Models;

public class HighscoreModel {

    private String highScore;

    public HighscoreModel() {

    }

    public HighscoreModel(String s) {
        this.highScore = s;
    }

    public String getHighScore() {
        return highScore;
    }

    public void setHighScore(String highScore) {
        this.highScore = highScore;
    }

}
