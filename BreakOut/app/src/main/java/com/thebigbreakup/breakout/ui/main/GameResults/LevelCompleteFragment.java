package com.thebigbreakup.breakout.ui.main.GameResults;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thebigbreakup.breakout.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LevelCompleteFragment extends Fragment {


    public LevelCompleteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_level_complete, container, false);
    }

}
