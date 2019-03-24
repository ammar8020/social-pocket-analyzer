package com.ammar.socialpocketa.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";

    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "TableTweet";
    private static final String COL1 = "ID"; // SQLite ID

    private static final String COL2 = "Tweet";
    private static final String COL3 = "Image";

    private static final String COL4 = "_ID"; // MongoDB ID
    private static final String COL5 = "IDStr"; // tweet ID
    private static final String COL6 = "Name";
    private static final String COL7 = "ScreenName";

    private static final String COL8 = "CreatedAt";

    private static final String COL9 = "RetweetCount";
    private static final String COL10 = "FavoriteCount";
    private static final String COL11 = "SentimentAnalysisLogreg";
    private static final String COL12 = "SentimentAnalysisNaiveBayes";
    private static final String COL13 = "SentimentAnalysisRnn";

    //    private static final String COL15 = "Retweeted";
//    private static final String COL16 = "Favorited";


    public DatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
              COL2 + " TEXT, " +
                COL3 + " BLOB, " +
                COL4 + " TEXT, " +
                COL5 + " TEXT, " +
                COL6 + " TEXT, " +
                COL7 + " TEXT, " +
                COL8 + " TEXT, " +
                COL9 + " TEXT, " +
                COL10 + " TEXT, " +
                COL11 + " TEXT, " +
                COL12 + " TEXT, " +
                COL13 + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addData(String tweet, byte[] image, String _ID, String IDStr, String Name,
                           String ScreenName, String CreatedAt,
                           String RetweetCount, String FavoriteCount, String SentimentAnalysisLogreg,
                           String SentimentAnalysisNaiveBayes, String SentimentAnalysisRnn) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, tweet);
        contentValues.put(COL3, image);
        contentValues.put(COL4, _ID);
        contentValues.put(COL5, IDStr);
        contentValues.put(COL6, Name);
        contentValues.put(COL7, ScreenName);
        contentValues.put(COL8, CreatedAt);
        contentValues.put(COL9, RetweetCount);
        contentValues.put(COL10, FavoriteCount);
        contentValues.put(COL11, SentimentAnalysisLogreg);
        contentValues.put(COL12, SentimentAnalysisNaiveBayes);
        contentValues.put(COL13, SentimentAnalysisRnn);

        Log.d(TAG, "addData: Adding " + tweet + " and " + image + " to " + TABLE_NAME);

        long result = db.insert(TABLE_NAME, null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    // Returns all the data from database
    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    // Returns only the ID that matches the name passed in
    public Cursor getItemID(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL1 + " FROM " + TABLE_NAME +
                " WHERE " + COL2 + " = '" + name + "' ";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    // Updates the name field
//    public void updateName(String newName, int id, String oldName){
//        SQLiteDatabase db = this.getWritableDatabase();
//        String query = "UPDATE " + TABLE_NAME + " SET " + COL2 +
//                " = '" + newName + "' WHERE " + COL1 + " = '" + id + "'" +
//                " AND " + COL2 + " = '" + oldName + "'";
//        Log.d(TAG, "updateName: query" + query);
//        Log.d(TAG, "updateName: Setting name to " + newName);
//        db.execSQL(query);
//    }

    // Delete from database
    public void deleteName(int id, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE "
                + COL1 + " = '" + id + "'" +
                " AND " + COL2 + " = '" + name + "'";
        Log.d(TAG, "deleteName: query: " + query);
        Log.d(TAG, "deleteName: Deleting " + name + " from database.");
        db.execSQL(query);
    }

}
