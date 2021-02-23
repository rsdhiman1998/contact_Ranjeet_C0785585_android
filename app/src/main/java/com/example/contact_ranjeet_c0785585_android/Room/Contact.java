package com.example.contact_ranjeet_c0785585_android.Room;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Contacts")
public class Contact {

    @NonNull
    @ColumnInfo(name="first_name")
    private String first_name;

    @NonNull
    @ColumnInfo(name="last_name")
    private String last_name;

   @NonNull
    @PrimaryKey
    private String email;

    @NonNull
    @ColumnInfo(name="phone_number")
    private String phone_number;

    @NonNull
    @ColumnInfo(name="address")
    private String address;

    public Contact(@NonNull String first_name, @NonNull String last_name, @NonNull String email, @NonNull String phone_number, @NonNull String address) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.phone_number = phone_number;
        this.address = address;
    }

    @NonNull
    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(@NonNull String first_name) {
        this.first_name = first_name;
    }

    @NonNull
    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(@NonNull String last_name) {
        this.last_name = last_name;
    }

    @NonNull
    public String getEmail() {
        return email;
    }

    public void setEmail(@NonNull String email) {
        this.email = email;
    }

    @NonNull
    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(@NonNull String phone_number) {
        this.phone_number = phone_number;
    }

    @NonNull
    public String getAddress() {
        return address;
    }

    public void setAddress(@NonNull String address) {
        this.address = address;
    }
}
