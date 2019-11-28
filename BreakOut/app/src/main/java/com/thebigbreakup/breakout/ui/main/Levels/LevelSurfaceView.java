package com.thebigbreakup.breakout.ui.main.Levels;


import android.content.Context;

//import android.content.res.Resources;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;
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
    private int speedX = 10;
    private int speedY = 11;
    private int paddleYPosition = (int)Math.round(screenHeight*0.7);
    private BrickSprite[] bricks;
    private MotionEvent m;
    private int bricksDestroyed = 0;

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

    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
        ballSprite = new BallSprite(1000, 500);
        ballSprite.setImage(BitmapFactory.decodeResource(getResources(), R.drawable.ballpng));


        paddleSprite = new PaddleSprite(500,paddleYPosition, BitmapFactory.decodeResource(getResources(), R.drawable.paddle) );

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

        checkPaddleCollision(paddleSprite, ballSprite);

        ballSprite.moveX(speedX);
        if (checkCollision(ballSprite, bricks)) {
            ballSprite.invertXDirection();
            //destroy current brick
        }

        ballSprite.moveY(speedY);
        if (checkCollision(ballSprite, bricks)) {
            ballSprite.invertYDirection();
            //destroy current brick
        }


        paddleSprite.update(m);

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

    public void checkPaddleCollision(PaddleSprite paddle, BallSprite ball) {
        Rect ballBounds = ball.getBounds();
        Rect[] paddleBoundsList = paddle.getPaddleBounds();

        for (int i = 0; i < paddleBoundsList.length; i++) {

            if (ballBounds.intersect(paddleBoundsList[i]) || ballBounds.contains(paddleBoundsList[i]) || paddleBoundsList[i].contains(ballBounds)) {
                // TODO fix the speedX
                ballSprite.invertYDirection();
                Log.d("christian", "checkPaddleCollision: true");
                speedX = i - 2;
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
                bricksDestroyed++;
                Log.d("christian2", "checkCollision: true");
                return true;
            }
            if(bricksDestroyed >= bricks.length){

            }
        }

        return false;

    }

    public boolean onTouchEvent(MotionEvent motion){
        m = motion;

        switch(m.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (m.getX() == paddleSprite.getX()) {
                    paddleSprite.setMovementState(paddleSprite.stopped);
                } else if(m.getX() < paddleSprite.getX())
                {
                    paddleSprite.setMovementState(paddleSprite.left);
                }
                else if(m.getX() > paddleSprite.getX()){
                    paddleSprite.setMovementState(paddleSprite.right);
                    break;
                }
                break;

            case MotionEvent.ACTION_UP:
                paddleSprite.setMovementState(paddleSprite.stopped);
                break;
        }
        return true;
    }

}

