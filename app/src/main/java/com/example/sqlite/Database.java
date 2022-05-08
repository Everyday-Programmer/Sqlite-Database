package com.example.sqlite;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {
    private final String TABLE_NAME = "notes";
    private final String ID_COL = "id";
    private final String TITLE_COL = "title";
    private final String CONTENT_COL = "content";

    public Database(Context context) {
        super(context, "notes-database", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TABLE_NAME + "(" + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TITLE_COL + " TEXT, " + CONTENT_COL + " TEXT)";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void insert(String title, String content) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TITLE_COL, title);
        contentValues.put(CONTENT_COL, content);
        sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        sqLiteDatabase.close();
    }

    public ArrayList<Note> getNotes(){
        ArrayList<Note> arrayList = new ArrayList<>();
        String query = "SELECT " + ID_COL + ", " + TITLE_COL + ", " + CONTENT_COL + " FROM " + TABLE_NAME;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        @SuppressLint("Recycle") Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            arrayList.add(new Note(cursor.getString(1), cursor.getString(2), cursor.getInt(0)));
            cursor.moveToNext();
        }
        cursor.isAfterLast();
        sqLiteDatabase.close();
        return arrayList;
    }
}