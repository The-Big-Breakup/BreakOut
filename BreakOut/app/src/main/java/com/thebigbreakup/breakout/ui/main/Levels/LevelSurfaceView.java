package com.thebigbreakup.breakout.ui.main.Levels;


import android.app.Activity;
import android.content.Context;

//import android.content.res.Resources;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.thebigbreakup.breakout.GameThread;
import com.thebigbreakup.breakout.R;
import com.thebigbreakup.breakout.sprites.BallSprite;
import com.thebigbreakup.breakout.sprites.BrickSprite;
import com.thebigbreakup.breakout.sprites.PaddleSprite;
import com.thebigbreakup.breakout.ui.main.GameResults.WinFragment;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.List;

public class LevelSurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    private GameThread thread;
    private BallSprite ballSprite;
    private PaddleSprite paddleSprite;
    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
<<<<<<< HEAD
    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
    private int speedX = 10;
    private int speedY = 11;
    private int paddleYPosition = (int)Math.round(screenHeight*0.9);
=======
    //private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
    private int speedX = 5;
    private int speedY = 20;
>>>>>>> master
    private BrickSprite[] bricks;
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
        ballSprite.setImage(BitmapFactory.decodeResource(getResources(), R.drawable.ball));


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
<<<<<<< HEAD

         */

        checkPaddleCollision(paddleSprite, ballSprite);

        paddleSprite.update(60);

=======
        
        paddleSprite.update(60, screenWidth);
>>>>>>> master
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
                // TODO paddleBounds seem to be triggered always, probably they are badly set in paddle constructor
                ballSprite.invertYDirection();
                Log.d("christian", "checkPaddleCollision: true");
                speedX += i - 2;
                if(ballSprite.getyPosition() >= screenHeight){
                    //losefragment
                }
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
                return true;
            }
        }
        if(bricksDestroyed >= bricks.length){

            Fragment winFragment;
            FragmentManager fm = new FragmentManager() {
                @NonNull
                @Override
                public FragmentTransaction beginTransaction() {
                    return null;
                }

                @Override
                public boolean executePendingTransactions() {
                    return false;
                }

                @Nullable
                @Override
                public Fragment findFragmentById(int id) {
                    return null;
                }

                @Nullable
                @Override
                public Fragment findFragmentByTag(@Nullable String tag) {
                    return null;
                }

                @Override
                public void popBackStack() {

                }

                @Override
                public boolean popBackStackImmediate() {
                    return false;
                }

                @Override
                public void popBackStack(@Nullable String name, int flags) {

                }

                @Override
                public boolean popBackStackImmediate(@Nullable String name, int flags) {
                    return false;
                }

                @Override
                public void popBackStack(int id, int flags) {

                }

                @Override
                public boolean popBackStackImmediate(int id, int flags) {
                    return false;
                }

                @Override
                public int getBackStackEntryCount() {
                    return 0;
                }

                @NonNull
                @Override
                public BackStackEntry getBackStackEntryAt(int index) {
                    return null;
                }

                @Override
                public void addOnBackStackChangedListener(@NonNull OnBackStackChangedListener listener) {

                }

                @Override
                public void removeOnBackStackChangedListener(@NonNull OnBackStackChangedListener listener) {

                }

                @Override
                public void putFragment(@NonNull Bundle bundle, @NonNull String key, @NonNull Fragment fragment) {

                }

                @Nullable
                @Override
                public Fragment getFragment(@NonNull Bundle bundle, @NonNull String key) {
                    return null;
                }

                @NonNull
                @Override
                public List<Fragment> getFragments() {
                    return null;
                }

                @Nullable
                @Override
                public Fragment.SavedState saveFragmentInstanceState(@NonNull Fragment f) {
                    return null;
                }

                @Override
                public boolean isDestroyed() {
                    return false;
                }

                @Override
                public void registerFragmentLifecycleCallbacks(@NonNull FragmentLifecycleCallbacks cb, boolean recursive) {

                }

                @Override
                public void unregisterFragmentLifecycleCallbacks(@NonNull FragmentLifecycleCallbacks cb) {

                }

                @Nullable
                @Override
                public Fragment getPrimaryNavigationFragment() {
                    return null;
                }

                @Override
                public void dump(@NonNull String prefix, @Nullable FileDescriptor fd, @NonNull PrintWriter writer, @Nullable String[] args) {

                }

                @Override
                public boolean isStateSaved() {
                    return false;
                }
            };
            fm.beginTransaction().replace(R.id.fragmentContainerID, new WinFragment()).commit();
            //winFragment = fm.findFragmentById(R.id.fragmentContainerID);
            //fm.beginTransaction().add(R.id.fragmentContainerID, winFragment).commit();
            //trigga win

        }

        return false;

    }

    public boolean onTouchEvent(MotionEvent motion){

        switch(motion.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (motion.getX() == paddleSprite.getX()) {
                    paddleSprite.setMovementState(paddleSprite.stopped);
                } else if(motion.getX() < paddleSprite.getX()){
                    paddleSprite.setMovementState(paddleSprite.left);
                }
                else if(motion.getX() > paddleSprite.getX()){
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

