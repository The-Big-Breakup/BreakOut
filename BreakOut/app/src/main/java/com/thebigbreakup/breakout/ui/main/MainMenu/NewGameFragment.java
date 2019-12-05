package com.thebigbreakup.breakout.ui.main.MainMenu;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.thebigbreakup.breakout.ui.main.Levels.LevelSurfaceView;

public class NewGameFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return new LevelSurfaceView(getActivity());
    }
}


