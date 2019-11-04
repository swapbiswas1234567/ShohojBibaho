package com.example.bkbiswas.shohojbibaho;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class UserInfo {
    private String email, password, firstName, lastName, gender, day, month, year, weight, bloodGroup, heightFt, heightInch, religion, contactNo, country, worksAt, designation, userName, location;

    public UserInfo(String e, String p, String f, String l, String g, String d, String m, String y, String w, String b, String hf, String hi, String r, String cn, String c, String wa, String des, String u) {

        this.email = e;
        this.password = p;
        this.firstName = f;
        this.lastName = l;
        this.gender = g;
        this.day = d;
        this.month = m;
        this.year = y;
        this.weight = w;
        this.bloodGroup = b;
        this.heightFt = hf;
        this.heightInch = hi;
        this.religion = r;
        this.contactNo = cn;
        this.country = c;
        this.worksAt = wa;
        this.designation = des;
        this.userName = u;
        this.location = "https://firebasestorage.googleapis.com/v0/b/shohojbibaho-c63e6.appspot.com/o/Default%20pp%2Fusericon.png?alt=media&token=3e3e9a90-eb5f-4d90-8b6b-eb4427ae5516";
        //this.location = "https://firebasestorage.googleapis.com/v0/b/shohojbibaho.appspot.com/o/Default%20PP%2Fusericon.png?alt=media&token=08279dc5-1144-4a36-9d81-633afaa58066";
    }

    public UserInfo() {
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGender() {
        return gender;
    }

    public String getDay() {
        return day;
    }

    public String getMonth() {
        return month;
    }

    public String getYear() {
        return year;
    }

    public String getReligion() {
        return religion;
    }

    public String getWeight() {
        return weight;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public String getHeightFt() {
        return heightFt;
    }

    public String getHeightInch() {
        return heightInch;
    }

    public String getContactNo() {
        return contactNo;
    }

    public String getWorksAt() {
        return worksAt;
    }

    public String getDesignation() {
        return designation;
    }

    public String getCountry() {
        return country;
    }

    public String getUserName() {
        return userName;
    }

    public String getLocation() {
        return location;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSharedPreferences(Context context) {
//        SharedPreferences session = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences session = context.getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        session.edit().putString("e", email).commit();
        session.edit().putString("p", password).commit();
        session.edit().putString("f", firstName).commit();
        session.edit().putString("l", lastName).commit();
        session.edit().putString("g", gender).commit();
        session.edit().putString("d", day).commit();
        session.edit().putString("m", month).commit();
        session.edit().putString("y", year).commit();
        session.edit().putString("r", religion).commit();
        session.edit().putString("c", country).commit();
        session.edit().putString("w", weight).commit();
        session.edit().putString("b", bloodGroup).commit();
        session.edit().putString("hf", heightFt).commit();
        session.edit().putString("hi", heightInch).commit();
        session.edit().putString("cn", contactNo).commit();
        session.edit().putString("wa", worksAt).commit();
        session.edit().putString("des", designation).commit();
        session.edit().putString("username", userName).commit();
    }
}
