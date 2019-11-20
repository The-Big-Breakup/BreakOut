package com.thebigbreakup.breakout.ui.main.Levels;


import android.content.Context;

import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.thebigbreakup.breakout.GameThread;
import com.thebigbreakup.breakout.R;
import com.thebigbreakup.breakout.sprites.BallSprite;

import java.util.Observable;
import java.util.Observer;

import static com.thebigbreakup.breakout.GameThread.canvas;

public class LevelSurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    private GameThread thread;
    private BallSprite ballSprite;
    private BallSprite boll;
    private int x = 10;
    private int y = 20;

    private Observer observer = new Observer() {
        @Override
        public void update(Observable o, Object arg) {
            LevelOneLayout.checkCollision(ballSprite.updateLiveDataX(), ballSprite.updateLiveDataY());
        }
    }

    public LevelSurfaceView(Context context) {
        super(context);

        getHolder().addCallback(this);

        thread = new GameThread(getHolder(), this);
        setFocusable(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {

        //Set running to true to use in GameThread and start the new thread
        thread.setRunning(true);
        thread.start();

        //TODO: add livedata with boolean, x and y
        //TODO: add LevelOneLayout to add blocks
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
        ballSprite = new BallSprite(700, 300);
        boll = new BallSprite(100, 600);
        ballSprite.setImage(BitmapFactory.decodeResource(getResources(), R.drawable.ballpng));
        boll.setImage(BitmapFactory.decodeResource(getResources(), R.drawable.ball));
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        //Destroy the thread, retry until destroyed
        boolean retry = true;
        while (retry) {
            try {
                thread.setRunning(false);
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            retry = false;
        }
    }

    public void update() {
        ballSprite.move(this.x, this.y);
        boll.move(10, 10);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (canvas != null) {

            //Set image/graphics for the ball and draw on canvas

            ballSprite.drawBall(canvas);
            boll.drawBall(canvas);
        }
    }
}
