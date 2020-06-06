package com.example.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

public static final String DATABASE_NAME = "Runners.db";
public static final String TABLE_NAME = "Runner_table";
public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "LASTNAME";
    public static final String COL_4 = "AGE";
    public static final String COL_5 = "PERSONALBEST";

    public DatabaseHelper( Context context) {
        super(context, DATABASE_NAME, null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" CREATE TABLE "+TABLE_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,LASTNAME TEXT, AGE INTEGER,PERSONALBEST INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }
    public boolean insertData( String name, String lastname, String age, String personalbest){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,lastname);
        contentValues.put(COL_4,personalbest);
        contentValues.put(COL_5,age);
        long result = db.insert(TABLE_NAME,null,contentValues);
        if(result == -1)
            return false;
            else
            return true;
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery(" SELECT * FROM " + TABLE_NAME,null);
        return res;
    }

    public boolean updateData(String id, String name, String lastname, String age, String personalbest){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,lastname);
        contentValues.put(COL_4,age);
        contentValues.put(COL_5,personalbest);
        db.update(TABLE_NAME,contentValues, "ID=?", new String[]{id});
        return true;
    }

    public Integer delteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,"ID=?",new String[]{id});
    }
}
