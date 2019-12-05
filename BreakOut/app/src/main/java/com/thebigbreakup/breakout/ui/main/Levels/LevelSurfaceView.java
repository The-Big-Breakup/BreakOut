package com.thebigbreakup.breakout.ui.main.Levels;


import android.content.Context;

//import android.content.res.Resources;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.thebigbreakup.breakout.GameThread;
import com.thebigbreakup.breakout.Player;
import com.thebigbreakup.breakout.R;
import com.thebigbreakup.breakout.Sounds;
import com.thebigbreakup.breakout.database.DBHelper;
import com.thebigbreakup.breakout.database.Models.HighscoreModel;
import com.thebigbreakup.breakout.sprites.BallSprite;
import com.thebigbreakup.breakout.sprites.BrickSprite;
import com.thebigbreakup.breakout.sprites.PaddleSprite;

public class LevelSurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    private GameThread thread;
    private BallSprite ballSprite;
    private PaddleSprite paddleSprite;
    private Sounds sounds;
    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
    private int speedX = screenWidth / 50;
    private int speedY = screenHeight / 100;
    private int paddleYPosition = (int)Math.round(screenHeight * 0.7);
    private int paddleXPosition = (int)Math.round(screenWidth * 0.5);
    private BrickSprite[] bricks;
    private MotionEvent paddleMotion;
    private int bricksDestroyed = 0;
    private DBHelper db = new DBHelper(getContext());
    private Player player = new Player();
    private HighscoreModel highscoreModel = new HighscoreModel();
    private Paint scoreStyle = new Paint();
    private Paint winStyle = new Paint();
    private Paint loseStyle = new Paint();
    private boolean win;
    private boolean lose;
    private boolean start;
    private boolean newHighscore;

    public LevelSurfaceView(Context context) {
        super(context);

        getHolder().addCallback(this);

        thread = new GameThread(getHolder(), this);
        setFocusable(true);

        scoreStyle.setTextSize(screenWidth / 10);
        scoreStyle.setColor(getResources().getColor(R.color.colorAccent));

        winStyle.setTextSize(screenWidth / 10);
        winStyle.setColor(getResources().getColor(R.color.colorWinText));

        loseStyle.setTextSize(screenWidth / 10);
        loseStyle.setColor(getResources().getColor(R.color.colorLoseText));


    }


    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {

        //Set running to true to use in GameThread and start the new thread
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
        ballSprite = new BallSprite(paddleYPosition - 50, paddleXPosition);
        ballSprite.setImage(BitmapFactory.decodeResource(getResources(), R.drawable.ballpng));


        paddleSprite = new PaddleSprite(paddleXPosition, paddleYPosition, BitmapFactory.decodeResource(getResources(), R.drawable.paddle) );

        LevelOneLayout levelOneLayout = new LevelOneLayout();
        bricks = levelOneLayout.getBricks(getResources());

        sounds = new Sounds(getContext());
        sounds.playBackgroundMusic();
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
        // release sounds
        sounds.release();
    }

    public void update() {

        lose = ballSprite.isLose();

        if (!win && !lose && start) {
            checkPaddleCollision(paddleSprite, ballSprite);


            ballSprite.moveY(speedY);
            if (checkCollision(ballSprite, bricks, player)) {
                ballSprite.invertYDirection();
                //destroy current brick
            }

            ballSprite.moveX(speedX);
            if (checkCollision(ballSprite, bricks, player)) {
                ballSprite.invertXDirection();
                //destroy current brick
            }
        }

        if(!start){
            ballSprite.setxPosition(paddleSprite.getX()  + paddleSprite.getWidth() / 2);
        }

        if (paddleMotion != null && !win &&!lose) {
            paddleSprite.update(paddleMotion);
        }
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

            canvas.drawText(String.valueOf(player.getScore()), (screenWidth / 2) - (screenWidth / 20), (int)Math.round(screenHeight * 0.045), scoreStyle);

            if (win) {
                canvas.drawText(getContext().getString(R.string.text_winmessage), (screenWidth / 4), screenHeight / 2, winStyle);
                updateHighscore(player.getScore());
            }
            if (lose) {
                canvas.drawText(getContext().getString(R.string.text_losemessage), (screenWidth / 4), screenHeight / 2, loseStyle);
                updateHighscore(player.getScore());
            }
            if (newHighscore) {
                canvas.drawText(getContext().getString(R.string.text_newhighscore), (screenWidth / 4), (screenHeight / 2) + (screenHeight / 10), winStyle);
            }

        }
    }

    public void checkPaddleCollision(PaddleSprite paddle, BallSprite ball) {
        Rect ballBounds = ball.getBounds();
        Rect[] paddleBoundsList = paddle.getPaddleBounds();

        for (int i = 0; i < paddleBoundsList.length; i++) {

            if (ballBounds.intersect(paddleBoundsList[i]) || ballBounds.contains(paddleBoundsList[i]) || paddleBoundsList[i].contains(ballBounds)) {
                if (i == 0) {
                    ball.invertYDirection();
                    if (!ball.isxDirLeft()) {
                        ball.invertXDirection();
                    }
                } else if (i == 1) {
                    ball.invertYDirection();
                } else if (i == 2){
                    ball.invertYDirection();
                    if (ball.isxDirLeft()) {
                        ball.invertXDirection();
                    }
                }

                // play paddle sound
                sounds.playPaddleSound();
            }
        }
    }

    public boolean checkCollision(BallSprite ball, BrickSprite[] bricks, Player p) {

        for (int i = 0; i < bricks.length; i++) {
            BrickSprite brick = bricks[i];
            Rect ballBounds = ball.getBounds();
            Rect brickBounds = brick.getBounds();

            if (ballBounds.intersect(brickBounds) || ballBounds.contains(brickBounds) || brickBounds.contains(ballBounds)) {
                brick.destroy();
                p.setScore(p.getScore() + bricks[i].getRewardPoints());
                bricksDestroyed++;
                // play brick sound
                sounds.playBrickSound();
                 if(bricksDestroyed >= bricks.length){
                     Log.d("christian", "checkCollision: win");
                    bricksDestroyed = 0;
                    win = true;
                }

                return true;
            }
        }
        //highscoreModel.setHighScore(highscoreModel.getHighScore() + bricks[i].getRewardPoints());
        // save new highscore to database
        //db.setHighscore(highscoreModel.getHighScore());

        return false;

    }

    public boolean onTouchEvent(MotionEvent motion){
        paddleMotion = motion;

        switch(paddleMotion.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (paddleMotion.getX() == paddleSprite.getX()) {
                    paddleSprite.setMovementState(paddleSprite.stopped);
                } else if(paddleMotion.getX() < paddleSprite.getX())
                {
                    paddleSprite.setMovementState(paddleSprite.left);
                }
                else if(paddleMotion.getX() > paddleSprite.getX()){
                    paddleSprite.setMovementState(paddleSprite.right);
                    break;
                }
                break;

            case MotionEvent.ACTION_UP:
                paddleSprite.setMovementState(paddleSprite.stopped);
                start = true;
                break;
        }
        return true;
    }

    public void updateHighscore(int score) {
        if (score > db.getHighscore()) {
            db.setHighscore(score);
            newHighscore = true;
        }
    }

}

