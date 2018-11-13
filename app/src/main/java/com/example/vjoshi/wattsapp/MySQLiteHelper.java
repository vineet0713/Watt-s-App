package com.example.vjoshi.wattsapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "data.db";
    private static final String TABLE_NAME = "username_table";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createQuery = "CREATE TABLE " + TABLE_NAME + "(USERNAME TEXT)";
        db.execSQL(createQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // drops the table if exists
        String dropQuery = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(dropQuery);

        // re-creates the table
        onCreate(db);
    }

    public String getUsername() {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if (res.getCount() == 0) {
            return null;
        } else {
            res.moveToNext();
            String username = res.getString(0);
            setUsername(username);
            return username;
        }
    }

    public boolean setUsername(String username) {
        // gets an instance of our database (writable)
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("DELETE FROM " + TABLE_NAME);

        // adds the values to insert
        ContentValues contentValues = new ContentValues();
        contentValues.put("USERNAME", username);
        long result = db.insert(TABLE_NAME, null, contentValues);
        db.close();

        // result will be -1 if the values were not inserted
        return (result != -1);
    }

    public void clearUsername() {
        // gets an instance of our database (writable)
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("DELETE FROM " + TABLE_NAME);

        db.close();
    }
}
