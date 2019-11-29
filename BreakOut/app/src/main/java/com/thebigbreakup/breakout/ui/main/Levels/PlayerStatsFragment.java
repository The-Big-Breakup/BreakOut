package com.thebigbreakup.breakout.ui.main.Levels;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thebigbreakup.breakout.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlayerStatsFragment extends Fragment {


    public PlayerStatsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_player_stats, container, false);



    }

}
