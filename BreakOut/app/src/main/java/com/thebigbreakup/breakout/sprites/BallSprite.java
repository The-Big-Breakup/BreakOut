package com.thebigbreakup.breakout.sprites;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * A class to create an animated ball object that moves on the canvas
 */
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

    /**
     * Create the ball and set the starting position on the screen
     * @param yPos The y-position on the screen in pixels
     * @param xPos The x-position on the screen in pixels
     */
    public BallSprite(int yPos, int xPos) {
        this.xDirLeft = true;
        this.yDirUp = true;
        this.xPosition = xPos;
        this.yPosition = yPos;
        this.bounds = new Rect();
        this.bounds.set(xPosition, yPosition, (xPosition + ballSide), (yPosition + ballSide));
    }

    private int invertSpeed(int speed) {
        return speed *= -1;
    }

    /**
     * Checks if the ball collides with the top or bottom of the screen
     */
    private int checkCollideY(int speed) {
        if (yPosition >= screenHeight || yPosition <= 0) {
            invertYDirection();
            return speed = invertSpeed(speed);
        }
        else {
            return speed;
        }
    }

    /**
     * Checks if the ball collides with the sides of the screen
     */
    private int checkCollideX(int speed) {
        if (xPosition >= screenWidth || xPosition <= 0) {
            invertXDirection();
            return speed = invertSpeed(speed);
        }
        else {
            return speed;
        }
    }

    /**
     * Inverts the direction of the ball in X
     */
    public void invertXDirection() {
        xDirLeft = !xDirLeft;
    }

    /**
     * Inverts the direction of the ball in Y
     */
    public void invertYDirection() {
        yDirUp= !yDirUp;
    }

    /**
     * Move the ball in X and run checkCollideX
     * @param speedX The amount of pixels to move in one frame
     */
    public void moveX(int speedX) { //same x and y values must be fed to 'move' continously until brick or paddle changes them by adding/subtracting to a sent variable

        if (xDirLeft) {
            speedX = invertSpeed(speedX);
        }

        speedX = checkCollideX(speedX);

        xPosition += speedX;

    }

    /**
     * Move the ball in Y and run checkCollideY
     * @param speedY The amount of pixels to move in one frame
     */
    public void moveY(int speedY) { //same x and y values must be fed to 'move' continously until brick or paddle changes them by adding/subtracting to a sent variable

        if (yDirUp) {
            speedY = invertSpeed(speedY);
        }

        speedY = checkCollideY(speedY);

        yPosition += speedY;
    }

    /**
     * Get the Rect to use with intersection
     * @return
     */
    public Rect getBounds() {
        return bounds;
    }

    /**
     * Draw the ball to canvas
     * @param canvas
     */
    public void drawBall(Canvas canvas) {
        canvas.drawBitmap(this.image, this.xPosition, this.yPosition, null);
    }

    /**
     * Set the image/sprite of the ball and scale to ballScaleFactor
     * @param image
     */
    public void setImage(Bitmap image) {
        this.image = image;
        //make a scaled bitmap from image
        this.image = Bitmap.createScaledBitmap(image, this.ballSide, this.ballSide, false);
    }

    /**
     * Change the size of the ball to a number that divides by screen width
     * @param ballScaleFactor Integer to divide with screen size
     */
    public void setBallScaleFactor(int ballScaleFactor) {
        this.ballScaleFactor = ballScaleFactor;
    }

    public int getyPosition() {
        return yPosition;
    }
}
