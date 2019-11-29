package com.thebigbreakup.breakout.ui.main.GameResults;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.thebigbreakup.breakout.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class WinFragment extends Fragment {
     Button Game;
     Button Exit;


    public WinFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       // return inflater.inflate(R.layout.fragment_win, container, false);
        View v = inflater.inflate(R.layout.fragment_lose, container, false);
        Exit = getView().findViewById(R.id.Exit);
        Game = getView().findViewById(R.id.Game);



        return v;
    }

}
