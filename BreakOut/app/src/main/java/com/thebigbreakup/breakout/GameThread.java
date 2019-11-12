package com.thebigbreakup.breakout;

import android.view.SurfaceHolder;

import com.thebigbreakup.breakout.ui.main.Levels.LevelSurfaceView;

public class GameThread extends Thread {

    private SurfaceHolder surfaceHolder;
    private LevelSurfaceView levelSurfaceView;

    public GameThread(SurfaceHolder surfaceHolder, LevelSurfaceView levelSurfaceView) {

        super();
        this.surfaceHolder = surfaceHolder;
        this.levelSurfaceView = levelSurfaceView;

    }

    //this is the MainThread for the game

    //TODO: Create the canvas from the LevelSurfaceView

    //TODO: Create a draw method called in the run method

    //TODO: Update the LevelSurfaceView so the animations work


}
