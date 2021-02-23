package com.example.contact_ranjeet_c0785585_android.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "contact_database";

    private static final String TABLE_NAME = "Contacts";
    private static final String COLUMN_FIRST_NAME = "first_name";
    private static final String COLUMN_LAST_NAME = "last_name";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PHONE_NUMBER = "phone_number";
    private static final String COLUMN_ADDRESS = "address";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME + "(" +
                COLUMN_FIRST_NAME + " VARCHAR(20) NOT NULL , " +
                COLUMN_LAST_NAME + " VARCHAR(20) NOT NULL, " +
                COLUMN_EMAIL + " VARCHAR(20) NOT NULL, Contacts_pk PRIMARY KEY, "+
                COLUMN_PHONE_NUMBER + " VARCHAR(20) NOT NULL," +
                COLUMN_ADDRESS + " VARCHAR(20) NOT NULL);";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME + ";";
        db.execSQL(sql);
        onCreate(db);
    }


    public boolean addContact(String first_name, String last_name , String email, String phone, String address) {
        // we need a writeable instance of SQLite database
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        // we need to define a content values in order to insert data into our database
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_FIRST_NAME, first_name);
        contentValues.put(COLUMN_FIRST_NAME, last_name);
        contentValues.put(COLUMN_LAST_NAME, email);
        contentValues.put(COLUMN_EMAIL, phone);
        contentValues.put(COLUMN_PHONE_NUMBER, address);


        // the insert method associated to SQLite database instance returns -1 if nothing is inserted
        return sqLiteDatabase.insert(TABLE_NAME, null, contentValues) != -1;
    }

    public Cursor getAllContacts() {
        // we need a readable instance of database
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }


    public boolean updateContacts(String first_name, String last_name , String email, String phone, String address) {
        // we need a writeable instance of database
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_FIRST_NAME, first_name);
        contentValues.put(COLUMN_LAST_NAME, last_name);
        contentValues.put(COLUMN_EMAIL, email);
        contentValues.put(COLUMN_PHONE_NUMBER, phone);
        contentValues.put(COLUMN_ADDRESS, address);

        // update method associated to SQLite database instance returns number of rows affected
        return sqLiteDatabase.update(TABLE_NAME,
                contentValues,
                COLUMN_EMAIL + "=?",
                new String[]{String.valueOf(email)}) > 0;
    }



}
