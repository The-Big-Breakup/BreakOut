package com.thebigbreakup.breakout.ui.main.GameResults;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.thebigbreakup.breakout.R;
import com.thebigbreakup.breakout.ui.main.MainMenuFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class WinFragment extends Fragment {

    Button exit;
    Button replay;

    public WinFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_win, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        exit = getView().findViewById(R.id.exit_button);
        replay = getView().findViewById(R.id.replay_button);


        replay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.fragmentContainerID, new MainMenuFragment()).commit();
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });
    }
}
