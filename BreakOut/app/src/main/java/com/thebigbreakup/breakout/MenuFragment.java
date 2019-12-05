package com.thebigbreakup.breakout;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.thebigbreakup.breakout.ui.main.Levels.LevelSurfaceView;


public class MenuFragment extends Fragment {

SoundFragment soundFragment;
    Button b1;
    Button b2;
    Button b3;
    Button b4;
    Button b5;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_menu, container, false);

        b1 = v.findViewById(R.id.new_game_btn);
        b2 = v.findViewById(R.id.about_us_btn);
        // b3 = v.findViewById(R.id.exit_btn);
        b4 = v.findViewById(R.id.score_btn);
        b5 = v.findViewById(R.id.sound_setting_btn);



        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.replace(R.id.ActivityMain, new NewGameFragment());
                    ft.commit();



            }

        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getActivity() != null) {
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.replace(R.id.ActivityMain, new AboutUsFragment());
                                        ft.commit();
                }
            }
        });


        /*
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    System.exit(0);
            }
        });

         */


        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getActivity() != null) {
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.replace(R.id.ActivityMain, new ScoreFragment(getContext()));
                    ft.commit();
                }
            }
        });

        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getActivity() != null) {
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.replace(R.id.ActivityMain, new SoundFragment());
                    ft.commit();
                }
            }
        });

        return v;



    }

    public void showmenu(View view) {
    }
}