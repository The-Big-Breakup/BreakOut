package com.thebigbreakup.breakout.sprites;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * A class that represents a Brick in the game
 */
public class BrickSprite {

    private Bitmap image;
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


        // scale the bitmap image to the dynamically generated width/height
        this.image = Bitmap.createScaledBitmap(image, this.width, this.height, false);
    }

    // If the Brick is not destroyed - draw the Brick
    public void draw(Canvas canvas) {
        if(!destroyed) {
            canvas.drawBitmap(this.image, this.xPos, this.yPos, null);
        }
    }

    public void destroy() {
        this.destroyed = true;
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

    public int getRewardPoints() {
        return rewardPoints;
    }

    public void setRewardPoints(int rewardPoints) {
        this.rewardPoints = rewardPoints;
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    //TODO: Add Values(DestroyValuePoints etc) and size
    //TODO: Add collision event (Remove the block etc.)

    //TODO: make a shape and load it here to set design


}
