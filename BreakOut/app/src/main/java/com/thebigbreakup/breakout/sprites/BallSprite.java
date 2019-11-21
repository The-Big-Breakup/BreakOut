package com.thebigbreakup.breakout.sprites;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

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
    private int newX;
    private int newY;

    public BallSprite(int yPos, int xPos) {
        this.goRight = true;
        this.goDown = false;
        this.xPosition = xPos;
        this.yPosition = yPos;
        this.bounds = new Rect();
        this.bounds.set(xPosition, yPosition, (xPosition + ballSide), (yPosition + ballSide));
    }

    /*
    private void changePosition(boolean direction, int steps, int position, int screenSpan) {
        if (!direction) {
            steps *= -1;
        }
        position += steps;
        if (position == screenSpan) {
            direction = !direction;
        }
    }

    public boolean collide(boolean direction) {
        return direction = !direction;
    }


    for (int i = 0; i <= Math.abs(steps); i++) {
        newPosition++;
    }

     */

    public void invertY() {
        yPosition *= -1;
    }

    public void invertX() {
        xPosition *= -1;
    }

    public void checkCollide() {
        if (xPosition >= screenWidth || xPosition <= 0) {
            invertX();
        }
        else if (yPosition >= screenHeight || yPosition <= 0) {
            invertY();
        }
    }

    public void move(int x, int y) { //same x and y values must be fed to 'move' continously until brick or paddle changes them by adding/subtracting to a sent variable

        checkCollide();

        xPosition += x;
        yPosition += y;

        /*

        if (!this.goRight) {
            x *= -1;
        }


        newX = this.xPosition;
        if (this.goRight) {
            for (int i = 0; i <= x; i++) {
                newX++;
                if (newX == screenWidth){     //&& isFilled(newX)) {
                    x = collideX(x - i);
                }
            }
            this.xPosition = newX;
        }


        if (!this.goRight) {
            for (int i = x; i <= 0; i++) {
                newX--;
                if (newX <= 0) {
                    x = collideX(x + i);
                }
            }
            this.xPosition = newX;
        }

        if (this.goDown) {
            y *= -1;
        }

        newY = this.yPosition;
        if (!this.goDown) {
            for (int i = y; i >= 0; i--) {
                newY--;
                if (newY <= 200) {
                    y = collideY(y - i);
                }
            }
            this.yPosition = newY;
        }

        //Lose
        if (this.goDown) {
            for (int i = y; i <= 0; i++) {
                newY++;
                if (newY == screenHeight) {
                    y = collideY(i + y);
                }
            }
            this.yPosition = newY;
        }

         */
    }

    public void drawBall(Canvas canvas) {
        canvas.drawBitmap(this.image, this.xPosition, this.yPosition, null);
    }

    /*

    public int collideY(int x) {
        x *= -1;
        this.goRight = !this.goRight;
        return x;
    }

    public int collideY(int y) {
        y *= -1;
        this.goDown = !this.goDown;
        return y;
    }

     */

    public void setImage(Bitmap image) {
        this.image = image;
    }


    // TODO: Make the variables for the ball (Size, Speed, shape etc.)

    // TODO: Make the ball move

    //TODO: make a shape and load it here to set design

    // TODO: Set a collider

    // TODO: Get int value that sets angle from SharedViewModel
}
