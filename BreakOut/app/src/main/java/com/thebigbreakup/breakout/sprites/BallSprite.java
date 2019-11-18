package com.thebigbreakup.breakout.sprites;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class BallSprite {

    private Bitmap image;
    private int x, y;

    public BallSprite(Bitmap bmp) {
        image = bmp;
        x = 100;
        y = 100;
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(image, x, y, null);
    }

    public void update() {
        y++;
    }


    // TODO: Make the variables for the ball (Size, Speed, shape etc.)

    // TODO: Make the ball move

    //TODO: make a shape and load it here to set design

    // TODO: Set a collider
    // TODO: Get int value that sets angle from SharedViewModel
}
