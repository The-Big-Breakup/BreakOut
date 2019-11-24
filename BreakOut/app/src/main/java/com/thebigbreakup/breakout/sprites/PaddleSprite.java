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

public class PaddleSprite implements View.OnTouchListener {



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
private int paddleScaleFactor = 5;




private int touchedPixel;
private int screenwidth = Resources.getSystem().getDisplayMetrics().widthPixels;
private int paddleSide =screenwidth/paddleScaleFactor;
private int paddleLength = screenwidth/5;
private int paddlePosition;
private int leftSide = paddlePosition - (screenwidth/paddleLength);
private int c0 = paddleLength - paddlePosition;
private int c1 = paddleLength - paddlePosition + 1 * paddleLength/5;
private int c2 = paddleLength - paddlePosition + 2 * paddleLength/5;
private int c3 = paddleLength - paddlePosition + 3 * paddleLength/5;
private int c4 = paddleLength - paddlePosition + 4 * paddleLength/5;
private int c5 = paddleLength - paddlePosition + 5 * paddleLength/5;


public int updateTouchMove() {
    if(paddleLength - paddlePosition <0){
        paddlePosition = paddleLength/2;
    }
    else if(paddleLength - paddlePosition > screenwidth){
        paddlePosition = screenwidth - paddleLength/2;
    }
    else{
        paddlePosition = touchedPixel - paddleLength/2;
    }
    return paddlePosition;
}

public int paddleCollision(int x,int y) {
    if (x >= c0 && x <= c1) {
        x = -2;
        return y *= -1;
    } else if(x == c1 && x<= c2) {
        x = -1;
        return y *= -1;
    } else if(x == c2 && x<= c3) {
        x = 0;
        return y *= -1;
    } else if(x == c3 && x<= c4) {
        x = 1;
        return y *= -1;
    } else if(x == c4 && x<= c5) {
        x = 2;
        return y *= -1;
    }else {
        return x;
    }
}


public PaddleSprite(int screenX, int screenY, Bitmap bmPlayer){
    this.bitmap = bmPlayer;
    this.width = 10;
    this.height = 120;
    //bitmap.getHeight();
    this.x = screenX/2;
    this.y = screenY-100;


   // this.posY=posY;
    //this.posX=posX;
    //this.bounds = new Rect();
    //this.bounds.set(posX, posY, (posX+this.width),(posY+this.height));
    //this.bitmap=Bitmap.createScaledBitmap(bitmap,this.width,this.height,false);
    //height = 20;
    // x = screenX/2;
    //y = screenY - 20;
    // y*=-1;

    this.rect = new Rect(x, y, x+width, y+height);

    this.paddleSpeed = 800;


}

    public void drawPaddle(Canvas canvas) {
        canvas.drawBitmap(this.bitmap, x, y, null);
    }

    public void setMovementState(int state){
    paddleMoving = state;
    }
    public void update (int fps, int screenX){
     if (paddleMoving==left){
         if(x>=0)
         x = x - paddleSpeed/fps;

         else
             x=0;
     }
     if (paddleMoving==right){
         if(x+width<=screenX)
         x = x + paddleSpeed/fps;
         else
             x=0;
     }
     /*if(rect.left<0){
         x=0;
     }
     if(rect.right>screenX){
         x=(int)(screenX-(rect.right-rect.left));
     }*/
     rect.left = x;
     rect.right = x + width;
    }
    public Rect getRect(){
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
    public void movmentX(int speedx) {

    }
    public void movmentY(int speedy){

    }
    @Override
    public boolean onTouch(View v, MotionEvent event) {

        switch(event.getAction()& MotionEvent.ACTION_MASK){
            case MotionEvent.ACTION_DOWN:

                break;

            case MotionEvent.ACTION_UP:
                break;

        }

        return true;
    }
    public void setImage(Bitmap image) {
        this.bitmap = image;
        //make a scaled bitmap from image
        this.bitmap = Bitmap.createScaledBitmap(image, this.paddleSide , this.paddleSide, false);
    }


}
