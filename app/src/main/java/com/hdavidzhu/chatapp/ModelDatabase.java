package com.hdavidzhu.chatapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

//
public class ModelDatabase extends SQLiteOpenHelper{
    // Names a table
    public static final String TABLE_NAME = "ChatLogs";

    // Table fields
    public static final String CHAT_ID = "ID";
    public static final String CHAT_NAME = "name";
    public static final String CHAT_TIMESTAMP = "timestamp";
    public static final String CHAT_MESSAGE = "message";
    public static final String CHAT_PICTURE = "picture";

    // Database information
    private static final String DATABASE_NAME = "ChatAppDatabase";
    private static final int DATABASE_VERSION = 1;

    // Creates the statement that will make the table.
    private static final String DATABASE_CREATE = "create table "
            + TABLE_NAME + "("
            + CHAT_ID + " TEXT NOT NULL UNIQUE, "
            + CHAT_NAME + " TEXT NOT NULL, "
            + CHAT_TIMESTAMP + " TEXT NOT NULL, "
            + CHAT_MESSAGE + " TEXT NOT NULL, "
            + CHAT_PICTURE + " BLOB );";

    // Default constructor. [???]
    public ModelDatabase(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Makes the actual database.
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    // Upgrades the class if needed. [???]
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(ModelDatabase.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}