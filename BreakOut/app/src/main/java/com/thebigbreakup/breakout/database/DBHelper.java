package com.thebigbreakup.breakout.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.wifi.aware.PublishConfig;

public class DBHelper extends SQLiteOpenHelper {

    private static final String LOGTAG = "HighScore";
    private static final String DATABASE_NAME = "breakOut.db";
    private static final int DATABASE_VERSION = 1;


    public static final int HIGHSCORE = 0;
    public static final String COLUMN_HIGHSCORE = "HighScore";
    public static final String TABLE_HIGHSCORE = "TableHighScore";


    private static final String TABLE_HIGHSCORE_CREATE =
            "CREATE TABLE " + TABLE_HIGHSCORE + " (" +
                    COLUMN_HIGHSCORE + " HIGHSCORE" +
                    ")";



    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_HIGHSCORE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HIGHSCORE);
    }

    public int getHighscore(){
        int highScore = 0;

        SQLiteDatabase db = getReadableDatabase();

        String query = "SELECT * FROM TABLE_HIGHSCORE;";

        Cursor c = db.rawQuery(query, null);

        highScore = c.getInt(c.getColumnIndex(DBHelper.COLUMN_HIGHSCORE));

        return highScore;

    }
    public void setHighscore(int highScore){
        SQLiteDatabase db = getReadableDatabase();
        db.execSQL("INSERT INTO " + TABLE_HIGHSCORE + "(" + COLUMN_HIGHSCORE + ")" + " VALUES " + highScore + ";");
    }

}
