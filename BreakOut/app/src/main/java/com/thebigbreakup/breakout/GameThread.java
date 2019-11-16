package com.thebigbreakup.breakout;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

import com.thebigbreakup.breakout.ui.main.Levels.LevelSurfaceView;

public class GameThread extends Thread {

    private SurfaceHolder surfaceHolder;
    private LevelSurfaceView levelSurfaceView;
    private boolean running;
    public static Canvas canvas;

    public GameThread(SurfaceHolder surfaceHolder, LevelSurfaceView levelSurfaceView) {

        super();
        this.surfaceHolder = surfaceHolder;
        this.levelSurfaceView = levelSurfaceView;

    }

    @Override
    public void run() {
        while (running) {
            canvas = null;

            try {
                canvas = this.surfaceHolder.lockCanvas();
                synchronized(surfaceHolder) {
                    this.levelSurfaceView.update();
                    this.levelSurfaceView.draw(canvas);
                }
            } catch (Exception e) {} finally {
                if (canvas != null) {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public void draw() {
        levelSurfaceView.update();
    }

    //this is the MainThread for the game

    //TODO: Create the canvas from the LevelSurfaceView

    //TODO: Create a draw method called in the run method

    //TODO: Update the LevelSurfaceView so the animations work


}
