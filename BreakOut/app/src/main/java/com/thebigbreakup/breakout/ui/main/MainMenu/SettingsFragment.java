package com.thebigbreakup.breakout.ui.main.MainMenu;

import android.media.MediaPlayer;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.thebigbreakup.breakout.R;

/**
 * The fragment for the settings in the app
 */
public class SettingsFragment extends Fragment {

    private Button soundPlay;
    private Button soundStop;
    private Button effectPlay;
    private Button effectStop;

    private MediaPlayer mediaSound;
    private MediaPlayer mediaEffectPaddle;
    private MediaPlayer mediaEffectBrick;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_settings, container, false);

        soundPlay = v.findViewById(R.id.sound_play);
        soundStop = v.findViewById(R.id.sound_stop);
        effectPlay = v.findViewById(R.id.effect_play);
        effectStop = v.findViewById(R.id.effect_stop);

        soundPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backgroundSoundPlay();
            }
        });
        soundStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backgroundSoundStop();
            }
        });
        effectPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paddleSoundPlay();
                brickSoundPlay();
            }
        });
        effectStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paddleSoundStop();
                brickSoundStop();
            }
        });

        return v;
    }

    /**
     * Plays the background music
     */
    public void backgroundSoundPlay()
    {
        mediaSound = MediaPlayer.create(this.getContext() ,R.raw.testm);
        mediaSound.start();
        mediaSound.setLooping(true);
    }

    /**
     * Stops the background music
     */
    public void backgroundSoundStop()
    {
        mediaSound.stop();
    }

    /**
     * Starts the sound on the paddlecollision
     */
    public void paddleSoundPlay()
    {
        mediaEffectPaddle = MediaPlayer.create(this.getContext() ,R.raw.testm);
        mediaEffectPaddle.start();
    }

    /**
     * Stops the sound of the paddlecollision
     */
    public void paddleSoundStop()
    {
        mediaEffectPaddle.stop();
    }

    /**
     * Plays the sound on collision of the brick
     */
    public void brickSoundPlay()
    {
        mediaEffectBrick = MediaPlayer.create(this.getContext() ,R.raw.testm);
        mediaEffectBrick.start();
    }

    /**
     * Stops the sound on collision of the brick
     */
    public void brickSoundStop()
    {
        mediaEffectBrick.stop();
    }
}