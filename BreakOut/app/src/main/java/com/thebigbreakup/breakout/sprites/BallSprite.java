package com.thebigbreakup.breakout.sprites;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;

public class BallSprite {

    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;//subtrahera storleken på bollen från skärmens dimensioner
    private int xPosition = screenWidth/2; //change to canvas as grid, and should initially be returned from the paddle on first tocuh
    private int yPosition = screenHeight/40;
    private boolean keepxDirection = true;
    private boolean keepyDirection = true;
    private Bitmap image;
    private Canvas canvas;

    public void move(int x, int y) { //same x and y values must be fed to 'move' continously until brick or paddle changes them by adding/subtracting to a sent variable

        if(keepxDirection = false){
            x *= -1;
        }

        if(keepyDirection = false){
            x *= -1;
        }

        while((screenWidth > xPosition && xPosition > 0 || x != 0)){ //detects collision
            if (x > 0){
                xPosition++;
                x--;
                if (isFilled(xPosition)) {
                    collideX();
                }
            }
            else if (x < 0){
                xPosition--;
                x++;
                if (isFilled(xPosition)) {
                    collideX();
                }
            }
        }

        xPosition += x * -1;//inverts the remainder to make ball move in opposite direction after collision, varning om x > screenWidth

        if(x != 0){
            keepxDirection = false;
        }else {
            keepxDirection = true;
        }

        while((screenHeight > yPosition && yPosition > 0 || y != 0)) {
            if (y < 0){
                yPosition++;
                y--;
                if (isFilled(yPosition)) {
                    collideX();
                }
            }
            else if (y > 0){// ändra så att undre delen av skärmen triggar 'lose'
                yPosition--;
                y++;
                if (isFilled(yPosition)) {
                    collideX();
                }
            }
        }

        yPosition += y * -1;

        if(x != 0){
            keepyDirection = false;
        }else {
            keepyDirection = true;
        }

        drawBall(canvas);//här ska canvasen uppdateras
    }

    public void drawBall(Canvas canvas) {
        canvas.drawBitmap(this.image, xPosition, yPosition, null);
    }

    private boolean collideX() {
        return keepxDirection = !keepxDirection;
    }

    private boolean collideY() {
        return keepyDirection = !keepyDirection;
    }

    private static boolean isFilled(int pixel) {
        return pixel != Color.TRANSPARENT;
    }

    // TODO: Make the variables for the ball (Size, Speed, shape etc.)

    // TODO: Make the ball move

    //TODO: make a shape and load it here to set design

    // TODO: Set a collider

    // TODO: Get int value that sets angle from SharedViewModel
}
