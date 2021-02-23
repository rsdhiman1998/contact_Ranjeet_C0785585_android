package com.example.contact_ranjeet_c0785585_android.Room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ContactDao {

    @Insert
    void insertcontact(Contact contact);

    @Query("DELETE FROM contacts")
    void deleteAllContacts();

    @Query("DELETE FROM contacts WHERE email = :email" )
    void deleteContact(String email);

    @Query("UPDATE contacts SET first_name = :name, last_name = :lastname, phone_number= :phone , address = :address WHERE email= :email")
    int updateContact(String name, String lastname, String phone, String address, String email);

    @Query("SELECT * FROM contacts ORDER BY email")
    List<Contact> getAllContacts();

    @Query("SELECT * FROM contacts WHERE first_name = :name")
    List<Contact>searchContacts(String name);




}
