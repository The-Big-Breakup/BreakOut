package com.thebigbreakup.breakout.sprites;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import androidx.constraintlayout.solver.widgets.Rectangle;

/**
 * A class that represents a Brick in the game
 */
public class BrickSprite {

    private Bitmap image;
    private Rect bounds;
    private int xPos, yPos, width, height, rewardPoints;
    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
    private boolean destroyed = false;

    /**
     * Constructor method for the Brick class.
     * Dynamically generates the bricks width/height from the screen dimensions
     * and scales the Bitmap image to these values.
     * @param image A Bitmap value to use as the graphic for the Brick object
     * @param xPos The x-position of the Brick object
     * @param yPos The y-position of the Brick object
     * @param rewardPoints The amount of points a Player gets when a brick is destroyed
     */
    public BrickSprite(Bitmap image, int xPos, int yPos, int rewardPoints) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = screenWidth / 10; // set brick width depending on screenwidth
        this.height = screenHeight / 40; // set brick height depending on screenheight
        this.rewardPoints = rewardPoints;

        // set bounds for the brick
        this.bounds = new Rect();
        this.bounds.set(xPos, yPos, (xPos + this.width), (yPos + this.height));

        // scale the bitmap image to the dynamically generated width/height
        this.image = Bitmap.createScaledBitmap(image, this.width, this.height, false);
    }

    // If the Brick is not destroyed - draw the Brick
    public void draw(Canvas canvas) {
        if(!destroyed) {
            canvas.drawBitmap(this.image, this.xPos, this.yPos, null);
        }

    }

    public boolean checkCollision(int pixelX, int pixelY) {

        if (pixelX >= xPos
                && pixelX <= xPos + width
                && pixelY >= yPos
                && pixelY <= yPos + height) {
            this.destroyed = true;
            return true;
        }
        return false;
    }

    /*
    public int checkCollision(int pixelX, int pixelY) {
        if(checkCollisionX(pixelX)) {
            this.destroyed = true;
            return 1;
        }

        if (checkCollisionY(pixelY)) {
            this.destroyed = true;
            return 2;
        }

        return 0;
    }

    public boolean checkCollisionX(int pixelX) {

        if (pixelX >= xPos && pixelX <= xPos + width) {
            return true;
        }
        return false;
    }

    public boolean checkCollisionY(int pixelY) {

        if (pixelY >= yPos && pixelY <= yPos + height) {
            return true;
        }
        return false;

    }

     */

    public void destroy() {
        this.destroyed = true;
        this.bounds.setEmpty();
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Rect getBounds() {
        return bounds;
    }

    public int getRewardPoints() {
        return rewardPoints;
    }

    public void setRewardPoints(int rewardPoints) {
        this.rewardPoints = rewardPoints;
    }

    public boolean isDestroyed() {
        return destroyed;
    }

}
