package com.jp.meghana.ispend;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Rushil on 28-Jun-15.
 */

public class DatabaseOperations extends SQLiteOpenHelper {
    String CREATE_TABLE_Details = "CREATE TABLE " + TableInfo.Cust_Details + "(" +
            TableInfo.Cust_Cardno + " TEXT," +
            TableInfo.Cust_UserId + " TEXT," +
            TableInfo.Cust_Name + " TEXT," +
            TableInfo.Cust_Contact + " TEXT," +
            TableInfo.Cust_EmailId + " TEXT);";

    public DatabaseOperations(Context context) {

        super(context, TableInfo.Database_name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_Details);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void putDetails(DatabaseOperations db,String cno, String userid , String name, String contact, String email){
        SQLiteDatabase SQ = db.getWritableDatabase();
        ContentValues CV = new ContentValues();
        CV.put(TableInfo.Cust_Cardno, cno);
        CV.put(TableInfo.Cust_UserId, userid);
        CV.put(TableInfo.Cust_Name, name);
        CV.put(TableInfo.Cust_Contact, contact);
        CV.put(TableInfo.Cust_EmailId, email);

        SQ.insert(TableInfo.Cust_Details,null,CV);
    }

    public Cursor getDetails(DatabaseOperations db){
        SQLiteDatabase SQ = db.getReadableDatabase();
        String[] Columns = {TableInfo.Cust_Cardno, TableInfo.Cust_UserId, TableInfo.Cust_Name, TableInfo.Cust_Contact, TableInfo.Cust_EmailId};
        Cursor CR = SQ.query(TableInfo.Cust_Details, Columns, null, null, null, null, null);
        return CR;
    }

    public void delete(){
        getWritableDatabase().delete(TableInfo.Cust_Details,null,null);
    }
}
