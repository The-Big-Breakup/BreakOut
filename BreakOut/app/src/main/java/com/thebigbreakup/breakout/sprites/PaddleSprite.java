package com.thebigbreakup.breakout.sprites;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.MotionEvent;

public class PaddleSprite {

public final int stopped = 0;
public final int left = 1;
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
    paddleBounds = new Rect[3];
    int boundWidth = this.width / paddleBounds.length;
    for (int i = 0; i < paddleBounds.length; i++) {
        int currentPosX = this.posX + i * boundWidth;
        paddleBounds[i] = new Rect();
        paddleBounds[i].set(currentPosX, this.posY, (currentPosX + boundWidth), (posY + this.height));
    }
}

    public void drawPaddle(Canvas canvas) {
        if(posX > screenWidth - width){
            posX = screenWidth - width;
        }else if(posX < 0){
            posX = screenWidth + width;
        }
        canvas.drawBitmap(this.bitmap, posX, posY, null);

        int boundWidth = this.width / paddleBounds.length;
        for (int i = 0; i < paddleBounds.length; i++) {
            int currentPosX = this.posX + i * boundWidth;
            paddleBounds[i].set(currentPosX, this.posY, (currentPosX + boundWidth), (posY + this.height));
        }
    }

    public void update (MotionEvent m){
        int paddlePosition;
        if((int)m.getX() > width / 2) {
            paddlePosition = (int)m.getX() - width / 2;
        } else {
            paddlePosition = 0;
        }

        if(paddlePosition > screenWidth - width){
            paddlePosition = screenWidth - width;
        }else if(paddlePosition + width / 2 < 0){
            paddlePosition = screenWidth + width;
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

    public void setMovementState(int state){
        paddleMoving = state;
    }

    public int getX() {
        return posX;
    }

    public Rect[] getPaddleBounds() {
        return paddleBounds;
    }

    public int getWidth() {
        return width;
    }
}
