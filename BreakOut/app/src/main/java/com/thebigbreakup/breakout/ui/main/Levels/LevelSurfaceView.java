package com.thebigbreakup.breakout.ui.main.Levels;


import android.content.Context;

import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.thebigbreakup.breakout.GameThread;

public class LevelSurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    private GameThread thread;

    public LevelSurfaceView(Context context) {
        super(context);

        getHolder().addCallback(this);
    }




    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        //TODO: Start GameThread
        //TODO: add LevelOneLayout to add blocks
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }
}
