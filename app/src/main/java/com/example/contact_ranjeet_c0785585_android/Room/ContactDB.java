package com.example.contact_ranjeet_c0785585_android.Room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;



@Database(entities = {Contact.class}, version = 1, exportSchema = false)
public abstract class ContactDB extends RoomDatabase {

    private static final String DB_NAME = "contact_room_db";

    public abstract ContactDao contactDao();

    private static volatile ContactDB INSTANCE;

    public static ContactDB getInstance(Context context) {
        if (INSTANCE == null)
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), ContactDB.class, DB_NAME)
                    .allowMainThreadQueries()
                    .build();
        return INSTANCE;
    }
}
