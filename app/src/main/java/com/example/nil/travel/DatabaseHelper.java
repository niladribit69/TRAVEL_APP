package com.example.nil.travel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="register.db";
    public static final String TABLE_NAME="registeruser";
    public static final String COL_1="ID";
    public static final String COL_2="USER_NAME";
    public static final String COL_3="PASSWORD";


    public DatabaseHelper(Context context) {
        super(context,DATABASE_NAME,null,1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE registeruser(ID INTEGER PRIMARY KEY AUTOINCREMENT,USER_NAME VARCHAR,PASSWORD VARCHAR)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);

    }
    public long addUser(String user,String password){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_2,user);
        contentValues.put(COL_3,password);
        long res=db.insert("registeruser",null,contentValues);
        db.close();
        return res;
    }
    public boolean checkUser(String USER,String password ){
        String[] columns={ COL_1 };
        SQLiteDatabase db=this.getReadableDatabase();
        String selection=COL_2 + "=?" +  " and " + COL_3 + "=?";
        String[] selectionArgs={ USER,password };
        Cursor cursor=db.query(TABLE_NAME,columns,selection,selectionArgs,null,null,null);
        int count=cursor.getCount();
        cursor.close();
        db.close();

        if(count>0) {
            return true;
        }
            return false;
    }
}
