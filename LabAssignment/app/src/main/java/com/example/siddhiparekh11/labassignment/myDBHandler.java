package com.example.siddhiparekh11.labassignment;

/**
 * Created by siddhiparekh11 on 9/22/17.
 */

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;

public class myDBHandler extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "commentsdatabase.db";
    public static final String TABLE_NAME = "comments";


    public static final String COLUMN_DESCRIPTION = "commentdes";

    public static final String COLUMN_RATINGS = "commentratings";

    public myDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + TABLE_NAME + "(" + COLUMN_DESCRIPTION + " TEXT, " + COLUMN_RATINGS + " TEXT);";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
        onCreate(db);


    }

    //adding rows to the table
    public void addComment(Comments comment) {
        ContentValues values = new ContentValues();

        values.put(COLUMN_DESCRIPTION, comment.get_commentdescription());
        values.put(COLUMN_RATINGS, comment.get_rating());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public String databaseToString() {
        String dbString = "Results:\n";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + ";";
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        if (c.getCount() == 0) {
            dbString += "No Records exists.";
        }
        while (!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex("commentdes")) != null) {
                dbString += "\nComment: " + c.getString(c.getColumnIndex("commentdes"));
                dbString += "\nRatings: " + c.getString(c.getColumnIndex("commentratings"));


            }

            c.moveToNext();
        }
        db.close();
        return dbString;

    }
}
