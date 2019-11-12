package com.thebigbreakup.breakout.sprites;

import android.graphics.RectF;

public class PaddleSprite {



    //TODO: set values to paddle (Width, height etc)
    //TODO: make a shape and load it here to set design

    //TODO: make the paddle return a value depending on where we hit it

    //TODO: Move the paddle after the touchevent
private RectF rect;
private float length;
private float height;
private float x;
private float y;
private float paddleSpeed;
public final int stopped = 0;
public final int left=1;
public final int right = 1;

private int paddleMoving = stopped;

public PaddleSprite(int screenX, int screenY){
    length = 130;
    height = 20;

    x = screenX/2;
    y = screenY - 20;

    rect = new RectF(x, y, x+length, y+height);

    paddleSpeed = 350;

}
public RectF getRect(){
    return rect;
}
public void setMovementState(int state){
    paddleMoving = state;
}
public void update (long fps){
     if (paddleMoving==left){
         x = x - paddleSpeed/fps;
     }
     if (paddleMoving==right){
         x = x + paddleSpeed/fps;
     }

     rect.left = x;
     rect.right = x + length;
}

}
