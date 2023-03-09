package com.example.login;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(Context context) {

        super(context, "register.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase d) {
        d.execSQL("create table register(user text,mail text,pass text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase d, int i, int i1) {
        d.execSQL("drop table if exists register");
    }

    public Boolean add(String user,String mail,String pass) {
//get writable database and pass it to db
        SQLiteDatabase db = this.getWritableDatabase();//insert,update,delete
        //to put the values inside table
        ContentValues c = new ContentValues();
        c.put("user", user);
        c.put("mail",mail);
        c.put("pass", pass);
        long result = db.insert("register", null, c);
        return result != -1;
    }


    public Boolean update(String user,String mail,String pass) {
//get writable database and pass it to db
        SQLiteDatabase db = this.getWritableDatabase();
        //to put the values inside table
        ContentValues contentValues = new ContentValues();
        contentValues.put("user", user);
        contentValues.put("mail", mail);
        contentValues.put("pass", pass);
        //cursor is used to select a rows one by one
        Cursor cursor = db.rawQuery("select * from register where user=?", new String[]{user});
        if (cursor.getCount() > 0) {
            long result = db.update("register", contentValues, "user=?", new String[]{user});
            return result != -1;
        } else {
            return false;
        }
    }

    public Boolean remove(String user) {
//get writable database and pass it to db
        SQLiteDatabase db = this.getWritableDatabase();
        //to put the values inside table
        //cursor is used to select a rows one by one
        Cursor cursor = db.rawQuery("select * from register where user=? ", new String[]{user});
        if (cursor.getCount() > 0) {
            long result = db.delete("register", "user=?", new String[]{user});
            return result != -1;
        } else {
            return false;
        }
    }

    public boolean isValuePresent(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM register WHERE user = ? and pass = ? ", new String[] { username , password });
        boolean isPresent = cursor.moveToFirst();
        cursor.close();
        return isPresent;
    }

    public boolean isUserPresent(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM register WHERE user = ?", new String[] { username});
        boolean isPresent = cursor.moveToFirst();
        cursor.close();
        return isPresent;
    }

    public Cursor getdata() {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from register", null);
        return cursor;
    }
}
