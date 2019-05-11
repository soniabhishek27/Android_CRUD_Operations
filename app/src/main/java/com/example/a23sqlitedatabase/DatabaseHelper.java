package com.example.a23sqlitedatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.lang.annotation.Target;

public class DatabaseHelper extends SQLiteOpenHelper {

    public final static String DATABASE_NAME = "MyStudent.db";
    public final static String TABLE_NAME = "mystudent_table";
    public final static String COL_1 = "ID";
    public final static String COL_2 = "NAME";
    public final static String COL_3 = "EMAIL";
    public final static String COL_4 = "COURSE_COUNT";


    public DatabaseHelper(Context context)  {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS "
                + TABLE_NAME +
                "(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                " NAME TEXT," +
                " EMAIL TEXT," +
                " COUSE_COUNT INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insetData(String name, String email, String courseCount) {
        SQLiteDatabase ds = this.getWritableDatabase();
        //object in which we can store multiple values
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_2, name);
        contentValues.put(COL_3, email);
        contentValues.put(COL_4, courseCount);

        long row = ds.insert(TABLE_NAME, null, contentValues);
        if (row== -1) {
            return false;
        } else {
            return true;
        }

    }

    public boolean updateData(String id, String name, String email, String courseCount)
    {
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_1,id);
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,email);
        contentValues.put(COL_4,courseCount);

        db.update(TABLE_NAME,contentValues,"ID=?", new String[]{id});
        return true;


    }
    // Cursor can store anything that is coming from the database
    public Cursor getData(String id)
    {
        SQLiteDatabase db= this.getWritableDatabase();
        String query="SELECT * FROM "+TABLE_NAME+" WHERE ID='"+id+"'";
        Cursor cursor= db.rawQuery(query,null);

        return cursor;


    }

    public Integer DeletData(String id)
    {
        SQLiteDatabase db= this.getWritableDatabase();

             return db.delete(TABLE_NAME,"ID=?",new String[]{id});

    }

    public Cursor getAllData()
    {
        SQLiteDatabase db= this.getWritableDatabase();
        String query="SELECT * FROM "+TABLE_NAME+"";
        Cursor cursor=db.rawQuery("SELECT * FROM " +TABLE_NAME, null);
      //  Cursor cursor= db.rawQuery(query,null);
        return cursor;
    }
}
