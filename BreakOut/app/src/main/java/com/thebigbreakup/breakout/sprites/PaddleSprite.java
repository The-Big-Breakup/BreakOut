package com.thebigbreakup.breakout.sprites;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

public class PaddleSprite {



    //TODO: set values to paddle (Width, height etc)
    //TODO: make a shape and load it here to set design
    //TODO: Move the paddle after the touchevent
    //TODO: make the paddle return a value depending on where we hit it

public final int stopped = 0;
public final int left=1;
public final int right = 2;
private RectF rect;
private float width;
private float height;
private float x;
private float y;
private float paddleSpeed;
private int paddleMoving = stopped;
private Canvas canvas;

public PaddleSprite(int screenX, int screenY, Bitmap bmPlayer){
    this.width = bmPlayer.getWidth();
    this.height = bmPlayer.getHeight();
    this.x = (screenX/2)-(width/2);
    this.y = screenY-height;
    //height = 20;

   // x = screenX/2;
    //y = screenY - 20;

    this.rect = new RectF(x, y, x+width, y+height);

    this.paddleSpeed = 350;

}

public void setMovementState(int state){
    paddleMoving = state;
}
public void update (long fps, int screenX){
     if (paddleMoving==left){
         x = x - paddleSpeed/fps;
     }
     if (paddleMoving==right){
         x = x + paddleSpeed/fps;
     }

     rect.left = x;
     rect.right = x + width;
}
    public RectF getRect(){
        return rect;
    }
    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
