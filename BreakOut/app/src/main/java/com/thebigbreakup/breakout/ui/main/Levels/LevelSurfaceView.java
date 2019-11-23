package com.thebigbreakup.breakout.ui.main.Levels;


import android.content.Context;

//import android.content.res.Resources;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.thebigbreakup.breakout.GameThread;
import com.thebigbreakup.breakout.R;
import com.thebigbreakup.breakout.sprites.BallSprite;
import com.thebigbreakup.breakout.sprites.BrickSprite;
import com.thebigbreakup.breakout.sprites.PaddleSprite;

public class LevelSurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    private GameThread thread;
    private BallSprite ballSprite;
    private PaddleSprite paddleSprite;
    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
    private int speedX = 50;
    private int speedY = 11;
    private int a = (int)Math.round(screenHeight*0.9);
    private BrickSprite[] bricks;

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
        ballSprite = new BallSprite(500, 500);
        ballSprite.setImage(BitmapFactory.decodeResource(getResources(), R.drawable.ball));
        paddleSprite = new PaddleSprite(500,a, BitmapFactory.decodeResource(getResources(), R.drawable.paddle) );
        LevelOneLayout levelOneLayout = new LevelOneLayout();
        bricks = levelOneLayout.getBricks(getResources());
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
        ballSprite.moveX(speedX);
        /*if (checkCollision(ballSprite, bricks)) {
            ballSprite.invertXDirection();
        }

         */

        ballSprite.moveY(speedY);
        /*if (checkCollision(ballSprite, bricks)) {
            ballSprite.invertYDirection();
        }

         */
        paddleSprite.update(60, screenWidth);

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (canvas != null) {

            //Set image/graphics for the ball and draw on canvas

            ballSprite.drawBall(canvas);
            paddleSprite.drawPaddle(canvas);
            for(int i = 0; i < bricks.length; i++) {
                bricks[i].draw(canvas);
            }


        }
    }

    public boolean checkCollision(BallSprite ball, BrickSprite[] bricks) {

        for (int i = 0; i < bricks.length; i++) {
            BrickSprite brick = bricks[i];
            Rect ballBounds = ball.getBounds();
            Rect brickBounds = brick.getBounds();

            if (ballBounds.intersect(brickBounds) || ballBounds.contains(brickBounds) || brickBounds.contains(ballBounds)) {
                brick.destroy();
                return true;
            }
        }

        return false;

    }
    public boolean onTouchEvent(MotionEvent motion){

        switch(motion.getAction()& MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                paddleSprite = new PaddleSprite(500, a, BitmapFactory.decodeResource(getResources(), R.drawable.paddle));
                if (motion.getX() > screenWidth / 2) {
                    paddleSprite.setMovementState(paddleSprite.right);
                } else {
                    paddleSprite.setMovementState(paddleSprite.left);
                }
                break;

            case MotionEvent.ACTION_UP:
                paddleSprite.setMovementState(paddleSprite.stopped);
                break;

        }
        return true;
    }
}

