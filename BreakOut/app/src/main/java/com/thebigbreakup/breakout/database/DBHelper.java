package com.thebigbreakup.breakout.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.thebigbreakup.breakout.database.Models.HighscoreModel;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "breakOut.db";
    private static final int DATABASE_VERSION = 1;


    private static final int HIGHSCORE = 0;
    private static final String COLUMN_HIGHSCORE = "HighScore";
    private static final String TABLE_HIGHSCORE = "TableHighScore";


    private static final String TABLE_HIGHSCORE_CREATE =
            "CREATE TABLE " + TABLE_HIGHSCORE + " (" +
                    COLUMN_HIGHSCORE + " INTEGER" +
                    ")";

    private static final String COLUMN_HIGHSCORE_SET_VALUE =
            "INSERT INTO " + TABLE_HIGHSCORE + " (" +
                    COLUMN_HIGHSCORE +
                    ")" + " VALUES (" + HIGHSCORE + ");";

    /**
     * Constructor
     * @param c The context where the instance is created
     */
    public DBHelper(Context c) {
        super(c, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Creates the table and column and adds default data
     * @param db The database
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_HIGHSCORE_CREATE);
        db.execSQL(COLUMN_HIGHSCORE_SET_VALUE);
    }

    /**
     * Drops the tables from database and adds new
     * @param db The database
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HIGHSCORE);
        onCreate(db);
    }

    /**
     * Reads highscore from the database
     * @return The highscore
     */
    public int getHighscore(){
        int highScore = 0;
        HighscoreModel highscoreModel;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = db.query(TABLE_HIGHSCORE, new String[] {COLUMN_HIGHSCORE}, null, null, null, null, null);

        if (c != null) {
            c.moveToFirst();
            highscoreModel = new HighscoreModel(c.getString(c.getColumnIndex(COLUMN_HIGHSCORE)));
            highScore = Integer.parseInt(highscoreModel.getHighScore());
        }

        return highScore;

    }

    /**
     * Set a new value to highscore in the database
     * @param highScore The value to be inserted
     */
    public void setHighscore(int highScore){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor c = db.query(TABLE_HIGHSCORE, new String[] {COLUMN_HIGHSCORE}, null, null, null, null, null);

        if (c != null) {
            db.execSQL("UPDATE " + TABLE_HIGHSCORE + " SET " + COLUMN_HIGHSCORE + " = " + highScore + ";");
        } else {
            db.execSQL("INSERT INTO " + TABLE_HIGHSCORE + "(" + COLUMN_HIGHSCORE + ")" + " VALUES (" + highScore + ");");
        }

    }

}
