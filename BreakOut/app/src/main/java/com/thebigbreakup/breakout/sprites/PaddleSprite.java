package com.thebigbreakup.breakout.sprites;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.MotionEvent;

public class PaddleSprite {



    //TODO: set values to paddle (Width, height etc)
    //TODO: make a shape and load it here to set design
    //TODO: Move the paddle after the touchevent
    //TODO: make the paddle return a value depending on where we hit it

public final int stopped = 0;
public final int left=1;
public final int right = 2;
private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
private int width = screenWidth / 5;
private int height = width / 5;
private int posX;
private int posY;
private int paddleSpeedFactor;
private int paddleMoving = stopped;
private Bitmap bitmap;
private Rect[] paddleBounds;


public PaddleSprite(int posX, int posY, Bitmap bitmap){
    this.posX = posX;
    this.posY = posY;

    // set scaled bitmap
    this.bitmap=Bitmap.createScaledBitmap(bitmap,this.width,this.height,false);

    this.paddleSpeedFactor = 2500;

    // set up boundsArray
    paddleBounds = new Rect[5];
    int boundWidth = this.width / paddleBounds.length;
    for (int i = 0; i < paddleBounds.length; i++) {
        int currentPosX = this.posX + i * boundWidth;
        paddleBounds[i] = new Rect();
        paddleBounds[i].set(currentPosX, this.posY, (currentPosX + boundWidth), (posY + this.height));
    }

}

    public void drawPaddle(Canvas canvas) {
        if(posX >= screenWidth){
            posX = screenWidth - width;
        }
        canvas.drawBitmap(this.bitmap, posX, posY, null);

        int boundWidth = this.width / paddleBounds.length;
        for (int i = 0; i < paddleBounds.length; i++) {
            int currentPosX = this.posX + i * boundWidth;
            paddleBounds[i].set(currentPosX, this.posY, (currentPosX + boundWidth), (posY + this.height));
        }

    }

    public void setMovementState(int state){
    paddleMoving = state;
    }


    public void update (MotionEvent m){

        int paddlePosition = (int)m.getX();
        if(paddlePosition >= screenWidth){
            paddlePosition -= width;
        }else if(paddlePosition <= 0){
            paddlePosition += width;
        }
        if (paddleMoving == left) {
            if (posX >= 0) {
                posX = paddlePosition;//paddleSpeedFactor / fps;
            }
            else {
                posX = 0;
            }
        }
        if (paddleMoving == right) {
            if(posX + width <= screenWidth) {
                posX = paddlePosition;
            }
            else {
             posX = 0;
            }
        }
    }

    public int getX() {
        return posX;
    }

    public Rect[] getPaddleBounds() {
        return paddleBounds;
    }
}
