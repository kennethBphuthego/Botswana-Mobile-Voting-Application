package com.example.bwsmobilevoting;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper2 extends SQLiteOpenHelper {
    public DBHelper2( Context context) {
        super(context, "Voting_App_Database.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db2) {
        db2.execSQL("CREATE TABLE IF NOT EXISTS Registrar_Account (Names VARCHAR(50) NOT NULL,Surname VARCHAR(50) NOT NULL,ID_Number INTEGER(10) NOT NULL PRIMARY KEY,Location VARCHAR(60) NOT NULL,Registration_Date NUMERIC(7) NOT NULL)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db2, int oldVersion, int newVersion) {
        db2.execSQL("drop Table if exists Registrar_Account");


    }
    public Boolean insertRegistrarData(String Names, String Surname, String ID_Number, String Location, String Registration_Date) {
        SQLiteDatabase db2 = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Names", Names);
        contentValues.put("Surname", Surname);
        contentValues.put("ID_Number", ID_Number);
        contentValues.put("Location", Location);
        contentValues.put("Registration_Date", Registration_Date);
        long result = db2.insert("Registrar_Account", null, contentValues);
        return result != -1;
    }

    public Boolean checkOfficerLog(String Names,String ID_Number){
        SQLiteDatabase db2 = this.getReadableDatabase();
        @SuppressLint("Recycle") Cursor cursor4 = db2.rawQuery("Select * from REgistrar_Account where Names =? and ID_Number =?",new String[] {Names,ID_Number});
        return cursor4.getCount() > 0;
    }
}
