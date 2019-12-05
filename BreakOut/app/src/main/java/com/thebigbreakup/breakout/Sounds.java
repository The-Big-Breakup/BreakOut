package com.thebigbreakup.breakout;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import static android.content.Context.MODE_PRIVATE;

/**
 * A class that handles the sounds for the game
 */
public class Sounds {

    private Context context;
    private SharedPreferences preferences;
    private static final String PREF_KEY = "myPreferences";
    private static final String MUSIC_ON_KEY = "backgroundMusicOn";
    private static final String FX_ON_KEY = "soundFXOn";
    private MediaPlayer paddleSound;
    private MediaPlayer ballSound;
    private MediaPlayer brickSound;
    private MediaPlayer backgroundMusic;
    private boolean backgroundMusicOn;
    private boolean soundFXOn;

    /**
     * Constructor for the Sounds class, calls the init-method
     * @param context the context from the class instance location in which the Sounds will be played
     */
    public Sounds(Context context) {

        this.context = context;
        this.init();
    }

    /**
     * Set up the sound file for each sound
     * And get sound settings from shared preferences
     */
    public void init() {
        this.paddleSound = MediaPlayer.create(this.context ,R.raw.paddle_sound);
        this.ballSound = MediaPlayer.create(this.context ,R.raw.ball_sound);
        this.brickSound = MediaPlayer.create(this.context ,R.raw.paddle_sound);
        this.backgroundMusic = MediaPlayer.create(this.context ,R.raw.background_music);

        // Get shared preferences
        this.preferences = context.getSharedPreferences(PREF_KEY, MODE_PRIVATE);
        // set background music on or off
        this.backgroundMusicOn = preferences.getBoolean(MUSIC_ON_KEY, true);
        // set sound fx on or off
        this.soundFXOn = preferences.getBoolean(FX_ON_KEY, true);

    }

    /**
     * Release the MediaPlayer used to play Sounds to free system resources
     * To start it again, use the restart-method
     */
    public void release() {

        this.paddleSound.release();
        this.ballSound.release();
        this.brickSound.release();
        this.backgroundMusic.release();

        this.paddleSound = null;
        this.ballSound = null;
        this.brickSound = null;
        this.backgroundMusic = null;

    }

    /**
     * Set up all sounds again, after they have been released or stopped
     */
    public void restart() {

        this.init();

    }

    /**
     * Plays background music if shared preferences setting is set to true
     */
    public void playBackgroundMusic() {
        if (preferences.getBoolean(MUSIC_ON_KEY, true)) {
            this.backgroundMusic.start();
            this.backgroundMusic.setLooping(true);
        }
    }

    /**
     * Plays paddle sound if shared preferences setting is set to true
     */
    public void playPaddleSound() {
        if (preferences.getBoolean(FX_ON_KEY, true)) {
            this.paddleSound.start();
        }
    }

    /**
     * Plays brick sound if shared preferences setting is set to true
     */
    public void playBrickSound() {
        if (preferences.getBoolean(FX_ON_KEY, true)) {
            this.brickSound.start();
        }
    }

    public MediaPlayer getPaddleSound() {
        return paddleSound;
    }

    public MediaPlayer getBrickSound() {
        return brickSound;
    }

    public MediaPlayer getBackgroundMusic() {
        return backgroundMusic;
    }
}
