package com.thebigbreakup.breakout;


import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class SoundFragment extends Fragment {

    Button soundPlay;
    Button soundStop;
    Button effectPlay;
    Button effectStop;

    MediaPlayer mediaSound;
    MediaPlayer mediaEffectPaddle;
    MediaPlayer mediaEffectBrick;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_sound, container, false);


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

    public void backgroundSoundPlay()
    {
        mediaSound = MediaPlayer.create(this.getContext() ,R.raw.testm);
        mediaSound.start();
        mediaSound.setLooping(true);

    }

    public void backgroundSoundStop()
    {
        mediaSound.stop();
    }

    public void paddleSoundPlay()
    {
        mediaEffectPaddle = MediaPlayer.create(this.getContext() ,R.raw.testm);
        mediaEffectPaddle.start();
    }

    public void paddleSoundStop()
    {
        mediaEffectPaddle.stop();
    }
    public void brickSoundPlay()
    {
        mediaEffectBrick = MediaPlayer.create(this.getContext() ,R.raw.testm);
        mediaEffectBrick.start();
    }

    public void brickSoundStop()
    {
        mediaEffectBrick.stop();
    }
}