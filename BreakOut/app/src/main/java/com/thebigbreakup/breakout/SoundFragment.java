package com.thebigbreakup.breakout;


import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class SoundFragment extends Fragment {

    Button soundPlay;
    Button soundStop;
    Button effectPlay;
    Button effectStop;

MediaPlayer mediaPlayer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_sound, container, false);





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
        mediaPlayer = MediaPlayer.create(this.getContext() ,R.raw.testm);
        mediaPlayer.start();
        mediaPlayer.setLooping(true);

    }

    public void backgroundSoundStop()
    {
        mediaPlayer = MediaPlayer.create(this.getContext() ,R.raw.testm);
        mediaPlayer.stop();
    }

    public void paddleSoundPlay()
    {
        mediaPlayer = MediaPlayer.create(this.getContext() ,R.raw.testm);
        mediaPlayer.start();
    }

    public void paddleSoundStop()
    {
        mediaPlayer = MediaPlayer.create(this.getContext() ,R.raw.testm);
        mediaPlayer.stop();
    }
    public void brickSoundPlay()
    {
        mediaPlayer = MediaPlayer.create(this.getContext() ,R.raw.testm);
        mediaPlayer.start();
    }

    public void brickSoundStop()
    {
        mediaPlayer = MediaPlayer.create(this.getContext() ,R.raw.testm);
        mediaPlayer.stop();
    }
}