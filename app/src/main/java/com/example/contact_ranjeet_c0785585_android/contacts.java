package com.example.contact_ranjeet_c0785585_android;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.contact_ranjeet_c0785585_android.Room.Contact;
import com.example.contact_ranjeet_c0785585_android.Room.ContactDB;
import com.example.contact_ranjeet_c0785585_android.util.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class contacts extends AppCompatActivity {

    DatabaseHelper sqLiteDatabase;

    // Room db instance
    private ContactDB contactDB;

    List<Contact> contactList;
    ListView contactListView;
    SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        contactListView = findViewById(R.id.lv_contacts);
        contactList = new ArrayList<>();
        contactListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                 Contact contact = contactList.get(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                builder.setTitle("Title");
                builder.setItems(new CharSequence[]
                                {"email", "phone", "message"},
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // The 'which' argument contains the index position
                                // of the selected item
                                switch (which) {
                                    case 0:
                                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("mail to "+ contact.getEmail()));
                                        intent.putExtra(Intent.EXTRA_SUBJECT, "email_subject");
                                        intent.putExtra(Intent.EXTRA_TEXT, "email_body");
                                        startActivity(intent);

                                        break;
                                    case 1:
                                        Intent intent1 = new Intent(Intent.ACTION_DIAL);
                                        intent1.setData(Uri.parse("tel:"+contact.getPhone_number()));
                                        startActivity(intent1);

                                        break;
                                    case 2:
                                        Uri sms_uri = Uri.parse("smsto:"+contact.getPhone_number());
                                        Intent sms_intent = new Intent(Intent.ACTION_SENDTO, sms_uri);
                                        sms_intent.putExtra("sms_body", "Good Morning ! how r U ?");
                                        startActivity(sms_intent);
                                        break;

                                }
                            }
                        });
                builder.create().show();

                return false;
            }
        });
        searchView= findViewById(R.id.btn_search);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                String gettingText =  searchView.getQuery().toString();
                searchContacts(gettingText);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                String gettingText =  searchView.getQuery().toString();
                searchContacts(gettingText);
                return false;
            }
        });
        contactDB = ContactDB.getInstance(this);
        loadContacts();
    }

    private void loadContacts() {

        contactList = contactDB.contactDao().getAllContacts();


        contactAdapter contact = new contactAdapter(this, R.layout.list_view, contactList);
        contactListView.setAdapter(contact);
    }
    private void searchContacts(String name) {


        contactList = contactDB.contactDao().searchContacts(name);



        contactAdapter contact = new contactAdapter(this, R.layout.list_view, contactList);
        contactListView.setAdapter(contact);
    }
}