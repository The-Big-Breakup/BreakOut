package com.thebigbreakup.breakout;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

import com.thebigbreakup.breakout.ui.main.Levels.LevelSurfaceView;

public class GameThread extends Thread {

    private SurfaceHolder surfaceHolder;
    private LevelSurfaceView levelSurfaceView;
    private boolean running;
    public static Canvas canvas;
    private int targetFPS = 60;
    private double averageFPS;

    public GameThread(SurfaceHolder surfaceHolder, LevelSurfaceView levelSurfaceView) {

        super();
        this.surfaceHolder = surfaceHolder;
        this.levelSurfaceView = levelSurfaceView;

    }

    //Start a new thread to run the game in, try to run the thread in 60fps,
    //keep track of the fps to know if to slow down or speed up to make it right
    @Override
    public void run() {
        long startTime;
        long timeMillis;
        long waitTime;
        long totalTime = 0;
        int frameCount = 0;
        long targetTime = 1000 / targetFPS;

        while (running) {
            startTime = System.nanoTime();
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

            timeMillis = (System.nanoTime() - startTime) / 1000000;
            waitTime = targetTime - timeMillis;

            try {
                this.sleep(waitTime);
            } catch (Exception e) {

            }

            totalTime += System.nanoTime() - startTime;
            frameCount++;
            if (frameCount == targetFPS) {
                averageFPS = 1000 / ((totalTime / frameCount) / 1000000);
                frameCount = 0;
                totalTime = 0;

                System.out.println(averageFPS);
            }

        }
    }

    public void setRunning(boolean isRunning) {
        running = isRunning;
    }

    //this is the MainThread for the game




}
