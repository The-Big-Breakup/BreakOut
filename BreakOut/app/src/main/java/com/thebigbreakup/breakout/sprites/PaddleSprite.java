package com.thebigbreakup.breakout.sprites;

import android.app.ActionBar;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.thebigbreakup.breakout.R;

public class PaddleSprite {



    //TODO: set values to paddle (Width, height etc)
    //TODO: make a shape and load it here to set design
    //TODO: Move the paddle after the touchevent
    //TODO: make the paddle return a value depending on where we hit it

public final int stopped = 0;
public final int left=1;
public final int right = 2;
private Rect rect;
private int width;
private int height;
private int x;
private int y;
private int paddleSpeed;
private int paddleMoving = stopped;
private Canvas canvas;
private Bitmap bitmap;
private int posY, posX;
private Rect bounds;
private int paddleScaleFactor = 10;
private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
private int paddleSide =screenWidth/paddleScaleFactor;
private int paddleLength = screenWidth/5;
private int paddlePosition;
private int leftSide = paddlePosition - (screenWidth/paddleLength);



public PaddleSprite(int screenX, int screenY, Bitmap bmPlayer){
    this.bitmap = bmPlayer;
    this.width = 10;
    this.height = 120;
    //bitmap.getHeight();
    this.x = screenX/2;
    this.y = screenY-100;


    this.posY=posY;
    this.posX=posX;
    this.bounds = new Rect();
    this.bounds.set(posX, posY, (posX+this.width),(posY+this.height));
    this.bitmap=Bitmap.createScaledBitmap(bitmap,this.width,this.height,false);
    height = 20;

    this.rect = new Rect(x, y, x+width, y+height);

    this.paddleSpeed = 2500;


}

    public void drawPaddle(Canvas canvas) {
        canvas.drawBitmap(this.bitmap, x, y, null);
    }

    public void setMovementState(int state){
    paddleMoving = state;
    }
    public void update (int fps){
     if (paddleMoving == left) {
         if(x >= 0) {
             x -= paddleSpeed / fps;
         }
         else {
             x = 0;
         }
     }
     if (paddleMoving == right) {

         if(x + width <= screenWidth) {
             x += paddleSpeed / fps;
         }
         else {
             x = 0;
         }
     }
     rect.left = x;
     rect.right = x + width;
    }
    public Rect getRect(){
        return rect;
    }
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public void setImage(Bitmap image) {
        this.bitmap = image;
        //make a scaled bitmap from image
        this.bitmap = Bitmap.createScaledBitmap(image, this.paddleSide , this.paddleSide, false);
    }


}
