package com.thebigbreakup.breakout.sprites;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class BallSprite {

    // TODO: Make the variables for the ball (Size, Speed, shape etc.)

    // TODO: Make the ball move

    //TODO: make a shape and load it here to set design

    // TODO: Set a collider
    // TODO: Get int value that sets angle from SharedViewModel



    private Bitmap image;

    int x = 100;
    int y = 100;

    public BallSprite(Bitmap bmp) {
        image = bmp;
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(image, x, y, null);
    }

    public void update() {
        y++;
        x++;
    }
}
