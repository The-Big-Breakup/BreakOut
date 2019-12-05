package com.thebigbreakup.breakout.ui.main.Levels;

import android.content.res.Resources;
import android.graphics.BitmapFactory;
import com.thebigbreakup.breakout.R;
import com.thebigbreakup.breakout.sprites.BrickSprite;

public class LevelOneLayout {

    private BrickSprite[] bricks;
    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
    private int xPos = screenWidth / 10;
    private int yPos = (screenHeight / 40) * 3;
    private int column = 8;
    private int row = 8;
    private int padding = 2;
    private int currentBrickId = 0;

    public BrickSprite[] getBricks(Resources resources) {
        bricks = new BrickSprite[64];

        for (int j = 0; j < row; j++) {
            for (int c = 0; c < column; c++) {
                bricks[currentBrickId] = new BrickSprite(BitmapFactory.decodeResource(resources, R.drawable.brick), xPos, yPos, 10);
                xPos += bricks[currentBrickId].getWidth() + padding;
                currentBrickId++;
            }
            yPos += screenHeight / 40 + padding; // jump down to new row
            xPos = screenWidth / 10; // reset x for the new row
        }

        return bricks;
    }
}
