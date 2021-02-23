package com.example.contact_ranjeet_c0785585_android;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.contact_ranjeet_c0785585_android.Room.Contact;
import com.example.contact_ranjeet_c0785585_android.Room.ContactDB;
import com.example.contact_ranjeet_c0785585_android.util.DatabaseHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
// Database name
    /*public static final String DATABASE_NAME = "my_database";
    SQLiteDatabase sqLiteDatabase;*/

    // sqLite openHelper instance
    DatabaseHelper sqLiteDatabase;

    private ContactDB contactDB;

    EditText etName,etLastname, etEmail, etPhone,etAddress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.et_firstName);
        etLastname= findViewById(R.id.lastname1);

        etEmail = findViewById(R.id.et_emailAddress);
        etPhone = findViewById(R.id.et_phone);
        etAddress = findViewById(R.id.et_emailAddress);

        findViewById(R.id.Addcontact).setOnClickListener(this);
        findViewById(R.id.viewCOntact).setOnClickListener(this);


        contactDB = ContactDB.getInstance(this);
    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Addcontact:
                addContact();
                break;
            case R.id.viewCOntact:
                startActivity(new Intent(this, contacts.class));
                break;
        }

    }

    private void addContact() {
        String name = etName.getText().toString().trim();
        String last_name = etLastname.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();
        String address = etAddress.getText().toString().trim();




        if (name.isEmpty()) {
            etName.setError("name field cannot be empty");
            etName.requestFocus();
            return;
        }
        if(last_name.isEmpty())
        {
            etLastname.setError("last name field cannot be empty");
            etLastname.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            etEmail.setError("Email field cannot be empty");
            etEmail.requestFocus();
            return;
        }
        if (phone.isEmpty()) {
            etPhone.setError("Phone field cannot be empty");
            etPhone.requestFocus();
            return;
        }
        if (address.isEmpty()) {
            etAddress.setError("address field cannot be empty");
            etAddress.requestFocus();
            return;
        }


        Contact contact = new Contact(name,last_name, email, phone, address);
        contactDB.contactDao().insertcontact(contact);
        Toast.makeText(this, "Contact Added", Toast.LENGTH_SHORT).show();
        clearFields();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        clearFields();
    }

    private void clearFields() {
        etName.setText("");
        etLastname.setText("");
        etEmail.setText("");
        etPhone.setText("");
        etAddress.setText("");
        etName.clearFocus();
        etLastname.clearFocus();
        etEmail.clearFocus();
        etPhone.clearFocus();
        etAddress.clearFocus();

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}