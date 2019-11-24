package com.thebigbreakup.breakout;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class MenuFragment extends Fragment {


    Button b1;
    Button b2;
    Button b3;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_menu, container, false);

        b1 = v.findViewById(R.id.new_game_btn);
        b2 = v.findViewById(R.id.about_us_btn);
        b3 = v.findViewById(R.id.exit_btn);



        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.replace(R.id.ActivityMain, new AboutUsFragment());
                    ft.commit();

            }

        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getActivity() != null) {
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.replace(R.id.ActivityMain, new AboutUsFragment());
                                        ft.addToBackStack(null).commit();
                }
            }
        });


        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    System.exit(0);
            }
        });

        return v;



    }
}