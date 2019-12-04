package com.thebigbreakup.breakout;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
    }

    public void showmenu(View v) {
        PopupMenu popup = new PopupMenu(this,v);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.popup_menu);
        popup.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.new_game_btn:
                //Toast.makeText(this,"New Game clicked",Toast.LENGTH_SHORT).show();
                NewGameFragment newgamefragment = new NewGameFragment();
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.ActivityMain, newgamefragment);
                ft.commit();
                return true;
            case R.id.score_btn:
                //Toast.makeText(this,"New Game clicked",Toast.LENGTH_SHORT).show();
                ScoreFragment scoreFragment = new ScoreFragment(getBaseContext());
                FragmentManager fm2 = getSupportFragmentManager();
                FragmentTransaction ft2 = fm2.beginTransaction();
                ft2.replace(R.id.ActivityMain, scoreFragment);
                ft2.commit();
                return true;
            case R.id.about_us_btn:
                AboutUsFragment aboutusfargment = new AboutUsFragment();
                FragmentManager fm1 = getSupportFragmentManager();
                FragmentTransaction ft1 = fm1.beginTransaction();
                ft1.replace(R.id.ActivityMain, aboutusfargment);
                ft1.commit();
                return true;
            case R.id.sound_setting_btn:
                SoundFragment soundFragment = new SoundFragment();
                FragmentManager fm4 = getSupportFragmentManager();
                FragmentTransaction ft4 = fm4.beginTransaction();
                ft4.replace(R.id.ActivityMain, soundFragment);
                ft4.commit();
                return true;
            case R.id.exit_btn:
                System.exit(0);
                return true;
            default:
                return false;

        }}

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}