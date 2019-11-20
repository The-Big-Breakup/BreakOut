package com.thebigbreakup.breakout.ui.main.Levels;


import android.content.Context;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.thebigbreakup.breakout.GameThread;
import com.thebigbreakup.breakout.R;
import com.thebigbreakup.breakout.sprites.BallSprite;
import com.thebigbreakup.breakout.sprites.PaddleSprite;

import java.util.ArrayList;

import java.util.Observable;
import java.util.Observer;

import static com.thebigbreakup.breakout.GameThread.canvas;

public class LevelSurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    private GameThread thread;
    private BallSprite ballSprite;
    private PaddleSprite paddleSprite;
    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    private int screenHight = Resources.getSystem().getDisplayMetrics().heightPixels;
    private int speedX = 2;
    private int speedY = 1;

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
        ballSprite = new BallSprite(BitmapFactory.decodeResource(getResources(), R.drawable.ball));
        paddleSprite = new PaddleSprite(500, 500, BitmapFactory.decodeResource(getResources(), R.drawable.paddle));

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
        ballSprite.move(speedX, speedY);
        //paddleSprite.update(60, screenWidth);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (canvas != null) {

            //Set image/graphics for the ball and draw on canvas

            ballSprite.drawBall(canvas);
            paddleSprite.drawPaddle(canvas);

        }
    }

    public void collide(Rect ballRect, Rect brickRect) {
        if(ballRect.intersect(brickRect) || ballRect.contains(brickRect) || brickRect.contains(ballRect)){
            //Collide x or collide y
            for (int i = 0; i < ; i++) {
                
            }
            if (collideX) {
                ballSprite.collideX(ballSprite.getNewX());
            }
            else if (collideY) {
                ballSprite.collideY(ballSprite.getNewY());
            }
        }
    }
}
