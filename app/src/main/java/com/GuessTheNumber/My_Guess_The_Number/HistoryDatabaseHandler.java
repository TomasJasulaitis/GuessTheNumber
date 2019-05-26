package com.GuessTheNumber.My_Guess_The_Number;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by super on 10/6/2016.
 */
public class HistoryDatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "leaderboard.db";

    private static String SCORES_TABLE_NAME = "scores";

    private final static String KEY_ID = "id";
    private final static String KEY_TYPE = "type";
    private final static String KEY_NAME = "name";
    private final static String KEY_SCORE = "score";

    public HistoryDatabaseHandler(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String query = "CREATE TABLE " + SCORES_TABLE_NAME + "(" +
                KEY_ID + " INTEGER PRIMARY KEY," +
                KEY_TYPE + " TEXT," +
                KEY_NAME + " TEXT," +
                KEY_SCORE + " INTEGER" +
                ")";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        String query = "DROP TABLE IF EXISTS " + SCORES_TABLE_NAME;
        db.execSQL(query);
        onCreate(db);
    }

    public long addEntry(HistoryEntry entry)
    {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(KEY_TYPE, entry.getType());
        cv.put(KEY_NAME, entry.getName());
        cv.put(KEY_SCORE, entry.getScore());

        long id = db.insert(SCORES_TABLE_NAME, null, cv);
        db.close();
        return id;
    }

    public HistoryEntry getEntry(int id)
    {
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = null;

        cursor = db.query(SCORES_TABLE_NAME, new String[] { KEY_ID, KEY_TYPE, KEY_NAME, KEY_SCORE}, KEY_ID + "=?", new String[] { Integer.toString(id) }, null, null, null);

        HistoryEntry entry = new HistoryEntry();
        if(cursor != null)
        {
            if(cursor.moveToFirst())
            {
                entry.setID(cursor.getInt(0));
                entry.setType(cursor.getString(1));
                entry.setName(cursor.getString(2));
                entry.setScore(cursor.getInt(3));
            }
        }

        cursor.close();
        db.close();

        return entry;
    }

    public ArrayList<HistoryEntry> getAllEntries()
    {
        ArrayList<HistoryEntry> entries = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();

        String query = "SELECT * FROM " + SCORES_TABLE_NAME + " ORDER BY " + KEY_ID + " DESC";

        Cursor cursor = db.rawQuery(query, null);

        if(cursor != null)
        {
            if(cursor.moveToFirst())
            {
                do {
                    HistoryEntry entry = new HistoryEntry();
                    entry.setID(cursor.getInt(0));
                    entry.setType(cursor.getString(1));
                    entry.setName(cursor.getString(2));
                    entry.setScore(cursor.getInt(3));
                    entries.add(entry);
                } while(cursor.moveToNext());
            }
        }

        cursor.close();
        db.close();

        return entries;
    }

    public void deleteEntry(int id)
    {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(SCORES_TABLE_NAME, KEY_ID + "=?", new String[]{Integer.toString(id)});
        db.close();
    }

    public void updateEntry(HistoryEntry entry)
    {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(KEY_TYPE, entry.getType());
        cv.put(KEY_NAME, entry.getName());
        cv.put(KEY_SCORE, entry.getScore());

        db.update(SCORES_TABLE_NAME, cv, KEY_ID + "=?", new String[] { Integer.toString(entry.getID()) });

        db.close();
    }
}
