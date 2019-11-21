package com.thebigbreakup.breakout.sprites;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class BallSprite {
    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;//subtrahera storleken på bollen från skärmens dimensioner
    private int xPosition;
    private int yPosition;
    private boolean xDirLeft;
    private boolean yDirUp;
    private Bitmap image;
    private int ballScaleFactor = 25;
    private int ballSide = screenWidth/ballScaleFactor;
    private Rect bounds;

    public BallSprite(int yPos, int xPos) {
        this.xDirLeft = true;
        this.yDirUp = true;
        this.xPosition = xPos;
        this.yPosition = yPos;
        this.bounds = new Rect();
        this.bounds.set(xPosition, yPosition, (xPosition + ballSide), (yPosition + ballSide));
    }

    public int invertSpeed(int speed) {
        return speed *= -1;
    }

    public int checkCollideY(int speed) {
        if (yPosition >= screenHeight || yPosition <= 0) {
            setYDirection();
            return invertSpeed(speed);
        }
        else {
            return speed;
        }
    }

    public int checkCollideX(int speed) {
        if (xPosition >= screenWidth || xPosition <= 0) {
            setXDirection();
            return invertSpeed(speed);
        }
        else {
            return speed;
        }
    }

    public void setXDirection() {
        xDirLeft = !xDirLeft;
    }

    public void setYDirection() {
        yDirUp= !yDirUp;
    }

    public void move(int speedX, int speedY) { //same x and y values must be fed to 'move' continously until brick or paddle changes them by adding/subtracting to a sent variable

        if (yDirUp) {
            speedY *= -1;
        }
        if (xDirLeft) {
            speedX *= -1;
        }

        speedX = checkCollideX(speedX);
        speedY = checkCollideY(speedY);

        xPosition += speedX;
        yPosition += speedY;
    }

    public Rect getBounds() {
        return bounds;
    }

    public void drawBall(Canvas canvas) {
        canvas.drawBitmap(this.image, this.xPosition, this.yPosition, null);
    }

    public void setImage(Bitmap image) {
        this.image = image;
        //make a scaled bitmap from image
        this.image = Bitmap.createScaledBitmap(image, this.ballSide, this.ballSide, false);
    }

    /**
     * Change the size of the ball to a number that divides by screen width
     * @param ballScaleFactor
     */
    public void setBallScaleFactor(int ballScaleFactor) {
        this.ballScaleFactor = ballScaleFactor;
    }
}
