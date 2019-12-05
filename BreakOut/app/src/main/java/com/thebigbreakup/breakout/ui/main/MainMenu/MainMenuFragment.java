package com.thebigbreakup.breakout.ui.main.MainMenu;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.thebigbreakup.breakout.R;

/**
 * The main menu of the app
 */
public class MainMenuFragment extends Fragment {

    Button b1;
    Button b2;
    //Button b3;
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
        // Exitbutton, removed because bugs
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
                    ft.replace(R.id.ActivityMain, new SettingsFragment());
                    ft.commit();
                }
            }
        });

        return v;
    }
}