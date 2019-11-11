package com.thebigbreakup.breakout.sprites;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class BrickSprite {

    private Bitmap image;
    private int xPos, yPos;
    private int rewardPoints;
    private boolean destroyed = false;

    public BrickSprite(Bitmap image, int rewardPoints, int xPos, int yPos) {
        this.image = image;
        this.rewardPoints = rewardPoints;
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(this.image, this.xPos, this.yPos, null);
    }

    //TODO: Add Values(DestroyValuePoints etc) and size
    //TODO: Add collision event (Remove the block etc.)

    //TODO: make a shape and load it here to set design


}
