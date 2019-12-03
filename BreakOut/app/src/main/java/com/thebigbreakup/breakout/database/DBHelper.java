package com.thebigbreakup.breakout.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.wifi.aware.PublishConfig;

import com.thebigbreakup.breakout.database.Models.HighscoreModel;

public class DBHelper extends SQLiteOpenHelper {

    private static final String LOGTAG = "HighScore";
    private static final String DATABASE_NAME = "breakOut.db";
    private static final int DATABASE_VERSION = 1;
    private Context context;


    public static final int HIGHSCORE = 0;
    public static final String COLUMN_HIGHSCORE = "HighScore";
    public static final String TABLE_HIGHSCORE = "TableHighScore";


    private static final String TABLE_HIGHSCORE_CREATE =
            "CREATE TABLE " + TABLE_HIGHSCORE + " (" +
                    COLUMN_HIGHSCORE + " HIGHSCORE" +
                    ")";




    public DBHelper(Context c) {
        super(c, DATABASE_NAME, null, DATABASE_VERSION);
        context = c;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_HIGHSCORE_CREATE);
        // db.execSQL("INSERT INTO TableHighScore (HighScore) VALUES ('1')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HIGHSCORE);
        onCreate(db);
    }

    public int getHighscore(){
        int highScore = 0;
        HighscoreModel highscoreModel;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = db.query(TABLE_HIGHSCORE, new String[] {COLUMN_HIGHSCORE}, null, null, null, null, null);

        if (c != null) {
            c.moveToFirst();
            highscoreModel = new HighscoreModel(c.getString(0));
            highScore = Integer.parseInt(highscoreModel.getHighScore());
        }

        return highScore;

    }

    public void setHighscore(int highScore){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_HIGHSCORE, highScore);

        db.close();

        // db.execSQL("INSERT INTO " + TABLE_HIGHSCORE + "(" + COLUMN_HIGHSCORE + ")" + " VALUES " + highScore + ";");
    }

}
