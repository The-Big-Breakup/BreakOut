package com.thebigbreakup.breakout.sprites;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class BrickSprite {

    private Bitmap image;
    private int xPos, yPos;
    private int rewardPoints;
    private boolean destroyed = false;

    public BrickSprite(Bitmap image, int xPos, int yPos, int rewardPoints) {
        this.image = image;
        this.xPos = xPos;
        this.yPos = yPos;
        this.rewardPoints = rewardPoints;
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
