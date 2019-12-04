package com.thebigbreakup.breakout;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * A class that handles the sounds for the game
 */
public class Sounds {

    Context context;
    private MediaPlayer paddleSound;
    private MediaPlayer ballSound;
    private MediaPlayer brickSound;
    private MediaPlayer backgroundMusic;


    /**
     * Constructor for the Sounds class, calls the init-method
     * @param context the context from the class instance location in which the Sounds will be played
     */
    public Sounds(Context context) {

        this.context = context;
        init();

    }

    /**
     * Set up the sound file for each sound
     */
    public void init() {
        paddleSound = MediaPlayer.create(this.context ,R.raw.paddle_sound);
        ballSound = MediaPlayer.create(this.context ,R.raw.ball_sound);
        brickSound = MediaPlayer.create(this.context ,R.raw.paddle_sound);
        backgroundMusic = MediaPlayer.create(this.context ,R.raw.background_music);
    }

    /**
     * Release the MediaPlayer used to play Sounds to free system resources
     * To start it again, use the restart-method
     */
    public void release() {

        paddleSound.release();
        ballSound.release();
        brickSound.release();
        backgroundMusic.release();

        paddleSound = null;
        ballSound = null;
        brickSound = null;
        backgroundMusic = null;

    }

    /**
     * Set up all sounds again, after they have been released or stopped
     */
    public void restart() {

        init();

    }

    public MediaPlayer getPaddleSound() {
        return paddleSound;
    }

    public MediaPlayer getBallSound() {
        return ballSound;
    }

    public MediaPlayer getBrickSound() {
        return brickSound;
    }

    public MediaPlayer getBackgroundMusic() {
        return backgroundMusic;
    }
}
