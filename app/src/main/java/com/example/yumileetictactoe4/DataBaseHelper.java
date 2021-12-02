package com.example.yumileetictactoe4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper{

    // creating a constant variables for our database
    public static final String GAME_TABLE = "GAME_TABLE";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_NAME = "NAME";
    public static final String COLUMN_WINS = "WINS";
    public static final String COLUMN_LOSSES = "LOSSES";
    public static final String COLUMN_TIES = "TIES";

    // Constructor for Database Helper

    public DataBaseHelper(@Nullable Context context) { super(context, "game.db", null, 1); }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //String createStudentTable = "CREATE TABLE " + STUDENT_TABLE + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,FIRST_NAME TEXT,LAST_NAME TEXT,AGE INTEGER,EMAIL TEXT)";

        String createGameTable = "CREATE TABLE " + GAME_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_NAME + " TEXT," + COLUMN_WINS + " INTEGER," + COLUMN_LOSSES + " INTEGER," + COLUMN_TIES + " INTEGER)";

        db.execSQL(createGameTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + GAME_TABLE);
        onCreate(db);
    }

    // add new Game Record to SQLite Database
    public void addRecord(GameModel gameModel){

        // calling writable method as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // ContentValues stores the Key Value pair for column name and its data
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME,gameModel.getName());
        cv.put(COLUMN_WINS,gameModel.getWins());
        cv.put(COLUMN_LOSSES,gameModel.getLosses());
        cv.put(COLUMN_TIES,gameModel.getTies());

        // insert content values to our Game table.
        db.insert(GAME_TABLE, null, cv);

        // close the Database connection
        db.close();
    }


    // Read data from Game Table
    public List<GameModel> viewRecords(){

        List<GameModel> viewList = new ArrayList<>();

        String queryString = "SELECT * FROM " + GAME_TABLE + " ORDER BY " + COLUMN_WINS + " DESC";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString,null);

        if(cursor.moveToFirst())
        {
            do {
                int gameID = cursor.getInt(0);
                String name = cursor.getString(1);
                int wins = cursor.getInt(2);
                int losses = cursor.getInt(3);
                int ties = cursor.getInt(4);

                GameModel gameModel = new GameModel(gameID, name, wins, losses, ties);
                viewList.add(gameModel);

            }while (cursor.moveToNext());

        }
        return viewList;
    }

    // Delete a record from Game Table
    public void deleteRecord(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        // String queryString = "DELETE FROM " + STUDENT_TABLE + " WHERE " + COLUMN_ID + " = " + id;

        // db.rawQuery(queryString, null);

        db.delete(GAME_TABLE, "ID=?", new String[]{String.valueOf(id)});
        db.close();
    }

    // Update a record from Game Table
    public void updateRecord(int id, String name)
    {
        // calling a method to get writable database.
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        // on below line we are passing all values along with its key and value pair.
        values.put(COLUMN_NAME, name);

        String where = COLUMN_ID +"= ?";
        String[] whereArgs = {String.valueOf(id)};

        // on below line we are calling a update method to update our database and passing our values.
        db.update(GAME_TABLE, values,where,whereArgs);
        db.close();
    }


    public List<GameModel> viewNames(){

        List<GameModel> viewList = new ArrayList<>();

        String queryString = " SELECT " + COLUMN_ID + "," +COLUMN_NAME+ " FROM " + GAME_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString,null);

        if(cursor.moveToFirst())
        {
            do {

                int gameID = cursor.getInt(0);
                String name = cursor.getString(1);

                GameModel gameModel = new GameModel(gameID, name);
                viewList.add(gameModel);

            }while (cursor.moveToNext());

        }
        return viewList;
    }

    // Update a Game score wins Record
    public void updateRecordWins(int ID) {

        String queryString = " SELECT " + COLUMN_WINS + " FROM " + GAME_TABLE + " WHERE " + COLUMN_ID + "=" + ID;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(queryString,null);

        int wins = 0;
        if(cursor.moveToFirst())
        {
            do {
                wins = cursor.getInt(0);
            }while (cursor.moveToNext());
        }

        wins = wins + 1;
        // calling a method to get writable database.
        ContentValues values = new ContentValues();

        // on below line we are passing all values along with its key and value pair.
        values.put(COLUMN_WINS, wins);

        String where = COLUMN_ID +"= ?";
        String[] whereArgs = {String.valueOf(ID)};

        // on below line we are calling a update method to update our database and passing our values.
        db.update(GAME_TABLE, values,where,whereArgs);
        db.close();
    }

    // Update a Game score Record
    public void updateRecordTies(int ID) {

        String queryString = " SELECT " + COLUMN_TIES + " FROM " + GAME_TABLE + " WHERE " + COLUMN_ID + "=" + ID;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(queryString,null);

        int ties = 0;
        if(cursor.moveToFirst())
        {
            do {
                ties = cursor.getInt(0);
            }while (cursor.moveToNext());
        }

        ties = ties + 1;
        // calling a method to get writable database.
        ContentValues values = new ContentValues();

        // on below line we are passing all values along with its key and value pair.
        values.put(COLUMN_TIES, ties);

        String where = COLUMN_ID +"= ?";
        String[] whereArgs = {String.valueOf(ID)};

        // on below line we are calling a update method to update our database and passing our values.
        db.update(GAME_TABLE, values,where,whereArgs);
        db.close();
    }

    // Update a Game score wins Record
    public void updateRecordLosses(int ID) {

        String queryString = " SELECT " + COLUMN_LOSSES + " FROM " + GAME_TABLE + " WHERE " + COLUMN_ID + "=" + ID;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(queryString,null);

        int losses = 0;
        if(cursor.moveToFirst())
        {
            do {
                losses = cursor.getInt(0);
            }while (cursor.moveToNext());
        }

        losses = losses + 1;
        // calling a method to get writable database.
        ContentValues values = new ContentValues();

        // on below line we are passing all values along with its key and value pair.
        values.put(COLUMN_LOSSES, losses);

        String where = COLUMN_ID +"= ?";
        String[] whereArgs = {String.valueOf(ID)};

        // on below line we are calling a update method to update our database and passing our values.
        db.update(GAME_TABLE, values,where,whereArgs);
        db.close();
    }

}
