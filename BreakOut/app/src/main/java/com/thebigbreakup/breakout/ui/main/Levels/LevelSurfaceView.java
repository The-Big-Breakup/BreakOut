package com.thebigbreakup.breakout.ui.main.Levels;


import android.content.Context;

//import android.content.res.Resources;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import com.thebigbreakup.breakout.GameThread;
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
    private int speedX = 10;
    private int speedY = 20;
    private int paddleYPosition = (int)Math.round(screenHeight*0.7);
    private BrickSprite[] bricks;
    private MotionEvent paddleMotion;
    private int bricksDestroyed = 0;
    private DBHelper db = new DBHelper(getContext());
    private int score;
    private HighscoreModel highscoreModel = new HighscoreModel();
    private Paint scoreStyle = new Paint();

    public LevelSurfaceView(Context context) {
        super(context);

        getHolder().addCallback(this);

        thread = new GameThread(getHolder(), this);
        setFocusable(true);

        scoreStyle.setTextSize(16);
        scoreStyle.setColor(getResources().getColor(R.color.colorAccent));

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

        sounds = new Sounds(getContext());
        sounds.getBackgroundMusic().start();
        sounds.getBackgroundMusic().setLooping(true);
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

        if(paddleMotion != null){
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

            canvas.drawText(String.valueOf(score), 500, 500, scoreStyle);

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

                // play paddle sound
                sounds.getPaddleSound().start();
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
                score += bricks[i].getRewardPoints();
                bricksDestroyed++;
                Log.d("christian2", "checkCollision: true");
                Log.d("christian2", "jahs" + score);
                // play brick sound
                sounds.getBrickSound().start();
                if(bricksDestroyed >= bricks.length){
                    Toast toast = Toast.makeText(getContext(), score, Toast.LENGTH_LONG);
                    toast.show();
                    bricksDestroyed = 0;
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
                break;
        }
        return true;
    }

}

