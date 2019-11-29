package com.thebigbreakup.breakout;

import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.thebigbreakup.breakout.ui.main.Levels.LevelSurfaceView;

public class MainActivity extends AppCompatActivity {


    @RequiresApi(api = Build.VERSION_CODES.N)


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(new LevelSurfaceView(this));

        /*
        setContentView(R.layout.main_activity);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainerID, MainMenuFragment.newInstance())
                    .commitNow();
        }


         */


    }
}
