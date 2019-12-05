package com.thebigbreakup.breakout.ui.main.MainMenu;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.thebigbreakup.breakout.R;
import com.thebigbreakup.breakout.database.DBHelper;

/**
 * A fragment that shows the highscore from the database
 */
public class ScoreFragment extends Fragment {

    private TextView highScoreTV;
    private DBHelper db;
    private int highscoreholder;

    /**
     * Constructor for the scoreFragment
     * @param c
     */
    public ScoreFragment(Context c){
        db = new DBHelper(c);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_score, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        highScoreTV = getActivity().findViewById(R.id.textViewHighScoreID);
        highscoreholder = db.getHighscore();
        highScoreTV.setText(String.valueOf(highscoreholder));
    }
}
