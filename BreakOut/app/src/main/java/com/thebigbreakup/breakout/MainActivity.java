package com.thebigbreakup.breakout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.thebigbreakup.breakout.ui.main.MainMenuFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainerID, MainMenuFragment.newInstance())
                    .commitNow();
        }



    }
}
