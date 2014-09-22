package com.hdavidzhu.chatapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Calendar;


public class DatabaseHandler {

    // The database model
    private ModelDatabase model;

    // Actual database
    private SQLiteDatabase database;


    // All data fields
    private String[] allColumns = {
            ModelDatabase.CHAT_ID,
            ModelDatabase.CHAT_NAME,
            ModelDatabase.CHAT_MESSAGE,
            ModelDatabase.CHAT_PICTURE
    };

    // Public constructor which creates the connection to the database.
    // Context interfaces global information about an application environment.
    // It's an abstract class whose implementation is provided by the Android system.
    public DatabaseHandler(Context context) {
        model = new ModelDatabase(context);
    }

    // ADD

    // This method adds the chat messages to the database.
    public void addChatToDatabase(String id, String name, String message, byte[] picture){
        // Creates a 'values' instance which can store our stuff.
        ContentValues values = new ContentValues();

        // Put those things into the table here.
        values.put(ModelDatabase.CHAT_ID, id);
        values.put(ModelDatabase.CHAT_NAME, name);
        values.put(ModelDatabase.CHAT_TIMESTAMP, Calendar.getInstance().get(Calendar.SECOND));
        values.put(ModelDatabase.CHAT_MESSAGE, message);
        values.put(ModelDatabase.CHAT_PICTURE, picture);
    }

    // Now we can also update our table.
    public void updateChat(ChatHolder chat){
        ContentValues values = new ContentValues();
        values.put(ModelDatabase.CHAT_ID, chat.id);
        values.put(ModelDatabase.CHAT_NAME, chat.name);
        values.put(ModelDatabase.CHAT_TIMESTAMP, chat.timestamp);
        values.put(ModelDatabase.CHAT_MESSAGE, chat.message);
        values.put(ModelDatabase.CHAT_PICTURE, chat.picture);
        database.update(ModelDatabase.TABLE_NAME, values, ModelDatabase.CHAT_ID, null);
    }

    // GET

    public ArrayList<ChatHolder> getAllChats(){
        return sweepCursor(database.query(ModelDatabase.TABLE_NAME, allColumns,null, null, null, null, null));
    }

    public ChatHolder getChatByID(String id){
        return sweepCursor(database.query(
                ModelDatabase.TABLE_NAME,
                allColumns,
                ModelDatabase.CHAT_ID + " like '%" + id + "%'",
                null, null, null, null
        )).get(0);
    }

    // DELETE

    public void deleteChatByID(String id){
        database.delete(
                ModelDatabase.TABLE_NAME,
                ModelDatabase.CHAT_ID + " like '%" + id + "%'",
                null
        );
    }

    // [???] What does sweepCursor do?
    private ArrayList<ChatHolder> sweepCursor(Cursor cursor) {
        ArrayList<ChatHolder> myChats = new ArrayList<ChatHolder>();

        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            ChatHolder myChat = new ChatHolder(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3)
            );
            myChats.add(myChat);
            cursor.moveToNext();
        }
        return myChats;
    }

    //Get Writable Database - open the database
    public void open(){
        database = model.getWritableDatabase();
    }
}