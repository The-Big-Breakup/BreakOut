package com.thebigbreakup.breakout.sprites;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class BallSprite {

    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;//subtrahera storleken på bollen från skärmens dimensioner
    private int xPosition = screenWidth/2; //change to canvas as grid, and should initially be returned from the paddle on first tocuh
    private int yPosition = 0;
    private boolean goRight;
    private boolean goDown;
    private Bitmap image;
    private int ballSide = screenWidth/25;
    private Rect bounds;

    public MutableLiveData getMutable() {
        return mutable;
    }

    private MutableLiveData mutable = new MutableLiveData();
    private LiveData livedata = new LiveData<//gooogla>();

    public BallSprite(int yPos, int xPos) {
        this.goRight = true;
        this.goDown = false;
        this.xPosition = xPos;
        this.yPosition = yPos;
        this.bounds = new Rect();
        this.bounds.set(xPosition, yPosition, (xPosition + ballSide), (yPosition + ballSide));
        this.image = Bitmap.createScaledBitmap(image, ballSide, ballSide, false);
    }

    public void move(int x, int y) { //same x and y values must be fed to 'move' continously until brick or paddle changes them by adding/subtracting to a sent variable

        if (!this.goRight) {
            x *= -1;
        }

        int newX = this.xPosition;
        if (this.goRight) {
            for (int i = 0; i <= x; i++) {
                newX++;
                livedata(bounds);
                if (newX == screenWidth || collide = true){     //&& isFilled(newX)) {
                    x = collideX(x - i);
                }
                updateLiveDataX(newX);
            }
            this.xPosition = newX;
        }

        if (!this.goRight) {
            for (int i = x; i <= 0; i++) {
                newX--;
                if (newX <= 0 || collide = true) {
                    x = collideX(i + x);

                }
                updateLiveDataX(newX);
            }
            this.xPosition = newX;
        }

        if (this.goDown) {
            y *= -1;
        }

        int newY = this.yPosition;

        if (!this.goDown) {
            for (int i = y; i >= 0; i--) {
                newY--;
                if (newY <= 200 || collide = true) {
                    y = collideY(y - i);
                }
                Log.d("New x : ", String.valueOf(y));
            }
            this.yPosition = newY;
        }

        //Loose
        if (this.goDown) {
            for (int i = y; i <= 0; i++) {
                newY++;
                if (newY == screenHeight || collide = true) {
                    y = collideY(i + y);
                }
                updateLiveDataY(newY);
                Log.d("Old : ", String.valueOf(y));
            }
            this.yPosition = newY;

        }
    }

    public Rect getBounds() {
        return bounds;
    }

    public void updateLiveDataX(int x){
        collideX(x);
    }

    public void updateLiveDataY( int y){
       collideY(y);
    }

    public void drawBall(Canvas canvas) {
        canvas.drawBitmap(this.image, this.xPosition, this.yPosition, null);
    }

    private int collideX(int x) {
        x *= -1;
        this.goRight = !this.goRight;
        return x;
    }

    private int collideY(int y) {
        y *= -1;
        this.goDown = !this.goDown;
        return y;
    }

    private static boolean isFilled(int pixel) {
        return pixel != Color.TRANSPARENT;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }


    // TODO: Make the variables for the ball (Size, Speed, shape etc.)

    // TODO: Make the ball move

    //TODO: make a shape and load it here to set design

    // TODO: Set a collider

    // TODO: Get int value that sets angle from SharedViewModel
}
