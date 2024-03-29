package com.thebigbreakup.breakout.ui.main;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thebigbreakup.breakout.R;
import com.thebigbreakup.breakout.SharedViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class GameFragment extends Fragment {

    private SharedViewModel mViewModel;


    public GameFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SharedViewModel.class);

        //TODO: add LevelSurfaceView and PlayerStatsFragment
        //TODO: Add win. lose, pause fragments on triggers (also LevelCompleteFragment when different levels exist)

    }

}
