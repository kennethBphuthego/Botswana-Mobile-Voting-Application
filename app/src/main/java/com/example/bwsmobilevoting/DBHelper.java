package com.example.bwsmobilevoting;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "Voting_App_Database.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS Voter_Account (Names VARCHAR(50) NOT NULL,Surname VARCHAR(50) NOT NULL,ID_Number INTEGER(10) NOT NULL PRIMARY KEY,Location VARCHAR(60) NOT NULL,Registration_Date NUMERIC(7) NOT NULL)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists Voter_Account");

    }

    public Boolean insertVoterData(String Names, String Surname, String ID_Number, String Location, String Registration_Date) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Names", Names);
        contentValues.put("Surname", Surname);
        contentValues.put("ID_Number", ID_Number);
        contentValues.put("Location", Location);
        contentValues.put("Registration_Date", Registration_Date);
        long result = db.insert("Voter_Account", null, contentValues);
        return result != -1;
    }

    public Boolean updateVoterData(String Names, String Surname, String ID_Number, String Location, String Registration_Date) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Names", Names);
        contentValues.put("Surname", Surname);
        contentValues.put("ID_Number", ID_Number);
        contentValues.put("Location", Location);
        contentValues.put("Registration_Date", Registration_Date);
        @SuppressLint("Recycle") Cursor cursor2 = db.rawQuery("Select * from Voter_Account where ID_Number = ?", new String[]{ID_Number});
        if (cursor2.getCount() > 0) {
            long result = db.update("Voter_Account", contentValues, "ID_Number=?", new String[]{ID_Number});
            return result != -1;
        } else {
            return true;
        }
    }

    public Boolean deleteVoterData(String ID_Number) {
        SQLiteDatabase db = this.getReadableDatabase();
        @SuppressLint("Recycle") Cursor cursor3 = db.rawQuery("Select * from Voter_Account where ID_Number = ?", new String[]{ID_Number});
        if (cursor3.getCount() > 0) {
            long result = db.delete("Voter_Account", "ID_Number=?", new String[]{ID_Number});
            return result != -1;
        } else {
            return true;
        }
    }


    public Cursor get_data() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("Select * from Voter_Account",null);

    }

    public Boolean checkVoterLog(String Names,String ID_Number){
        SQLiteDatabase db = this.getReadableDatabase();
        @SuppressLint("Recycle") Cursor cursor4 = db.rawQuery("Select * from Voter_Account where Names =? and ID_Number =?",new String[] {Names,ID_Number});
        return cursor4.getCount() > 0;
    }


}
