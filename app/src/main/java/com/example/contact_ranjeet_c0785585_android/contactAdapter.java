package com.example.contact_ranjeet_c0785585_android;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.contact_ranjeet_c0785585_android.Room.Contact;
import com.example.contact_ranjeet_c0785585_android.Room.ContactDB;
import com.example.contact_ranjeet_c0785585_android.util.DatabaseHelper;

import java.util.List;

public class contactAdapter extends ArrayAdapter {
    private static final String TAG = "ContactAdapter";

    Context context;
    int layoutRes;
    List<Contact> contactList;
    DatabaseHelper sqLiteDatabase;
    ContactDB contactDB;



    public contactAdapter(@NonNull Context context, int resource, List<Contact> contactList) {
        super(context, resource, contactList);
        this.contactList = contactList;
        this.context = context;
        this.layoutRes = resource;
        contactDB = ContactDB.getInstance(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View v = convertView;
        if (v == null) v = inflater.inflate(layoutRes, null);
        TextView cName = v.findViewById(R.id.cName);
        TextView cLastName = v.findViewById(R.id.clastName);
        TextView cEmail = v.findViewById(R.id.cEmail);
        TextView cPhone = v.findViewById(R.id.cPhone);
        TextView cAddress = v.findViewById(R.id.cAddress);

        final Contact contact = contactList.get(position);
        cName.setText(contact.getFirst_name());
        cLastName.setText(contact.getLast_name());
        cEmail.setText(contact.getEmail());
        cPhone.setText(contact.getPhone_number());
        cAddress.setText(contact.getAddress());

        v.findViewById(R.id.btn_edit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateContact(contact);
            }

            private void updateContact(final Contact contact1) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                LayoutInflater layoutInflater = LayoutInflater.from(context);
                View view = layoutInflater.inflate(R.layout.update_func, null);
                builder.setView(view);
                final AlertDialog alertDialog = builder.create();
                alertDialog.show();

                final EditText etName = view.findViewById(R.id.et_firstName);
                final EditText etlastName = view.findViewById(R.id.lastname1);
                final EditText etEmail = view.findViewById(R.id.et_emailAddress);
                final EditText etPhone = view.findViewById(R.id.et_phone);
                final EditText etAddress = view.findViewById(R.id.e_address);



                etName.setText(contact1.getFirst_name());
                etlastName.setText(contact1.getLast_name());
                etEmail.setText(contact1.getEmail());
                etPhone.setText(contact1.getPhone_number());
                etAddress.setText(contact1.getAddress());


                view.findViewById(R.id.Addcontact).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name = etName.getText().toString().trim();
                        String lastname = etlastName.getText().toString().trim();
                        String email = etEmail.getText().toString().trim();
                        String phone = etPhone.getText().toString().trim();
                        String address = etAddress.getText().toString().trim();

                        if (name.isEmpty()) {
                            etName.setError("name field cannot be empty");
                            etName.requestFocus();
                            return;
                        }

                        if (lastname.isEmpty()) {
                            etName.setError("name field cannot be empty");
                            etName.requestFocus();
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



                        contactDB.contactDao().updateContact(
                                name , lastname, phone , address , email );
                        loadContacts();
                        alertDialog.dismiss();
                    }
                });
            }
        });

        v.findViewById(R.id.btn_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteContact(contact);
            }

            private void deleteContact(final Contact contact1) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Are you sure?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        contactDB.contactDao().deleteContact(contact1.getEmail());
                        loadContacts();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context, "The Contact (" + contact1.getFirst_name() + ") is not deleted", Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
        Log.d(TAG, "getView: " + getCount());
        return v;
    }

    @Override
    public int getCount() {
        return contactList.size();
    }

    private void loadContacts() {


        contactList = contactDB.contactDao().getAllContacts();
        notifyDataSetChanged();
    }
}
