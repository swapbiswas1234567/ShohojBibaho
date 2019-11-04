package com.example.bkbiswas.shohojbibaho;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
    private SharedPreferences session;
    private int UID;
    private String unique;
    Context context;
    DatabaseReference d;
    String s, name;

    public Login(Context context) {
        this.context = context;
        session = this.context.getApplicationContext().getSharedPreferences("user", context.MODE_PRIVATE);
        d = FirebaseDatabase.getInstance().getReference("idGenerator");
    }


    public void setSignInUser(String userName) {
        session.edit().putString("username", userName).commit();
    }


    public void setSignUpUser(String firstName, String lastName) {
        d.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot i : dataSnapshot.getChildren()) {
                    ID id = i.getValue(ID.class);
                    UID = id.getID();
                }

                s = UID + "";
                session.edit().putString("uid", s).commit();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    public String getUser() {
        String username = session.getString("username", "");
        return username;
    }


    public String getID() {
        String id = session.getString("id", "");
        return id;
    }


    public void clearUser() {
        session.edit().putString("username", "").commit();
        session.edit().putString("e", "").commit();
        session.edit().putString("p", "").commit();
        session.edit().putString("f", "").commit();
        session.edit().putString("l", "").commit();
        session.edit().putString("g", "").commit();
        session.edit().putString("d", "").commit();
        session.edit().putString("m", "").commit();
        session.edit().putString("y", "").commit();
        session.edit().putString("r", "").commit();
        session.edit().putString("c", "").commit();
        session.edit().putString("w", "").commit();
        session.edit().putString("b", "").commit();
        session.edit().putString("hf", "").commit();
        session.edit().putString("hi", "").commit();
        session.edit().putString("cn", "").commit();
        session.edit().putString("wa", "").commit();
        session.edit().putString("des", "").commit();
    }

    public void saveInfo(String e, String p, String f, String l, String g, String d, String m, String y, String w, String b, String hf, String hi,
                         String r, String cn, String c, String wa, String des) {


        session.edit().putString("e", e).commit();
        session.edit().putString("p", p).commit();
        session.edit().putString("f", f).commit();
        session.edit().putString("l", l).commit();
        session.edit().putString("g", g).commit();
        session.edit().putString("d", d).commit();
        session.edit().putString("m", m).commit();
        session.edit().putString("y", y).commit();
        session.edit().putString("w", w).commit();
        session.edit().putString("b", b).commit();
        session.edit().putString("hf", hf).commit();
        session.edit().putString("hi", hi).commit();
        session.edit().putString("r", r).commit();
        session.edit().putString("cn", cn).commit();
        session.edit().putString("c", c).commit();
        session.edit().putString("wa", wa).commit();
        session.edit().putString("des", des).commit();

        setSignUpUser(f, l);
    }

    public void saveInfo1(SharedPreferences ses, String e, String p, String f, String l, String g, String d, String m, String y, String w, String b, String hf, String hi, String r, String cn, String c, String wa, String des) {


        ses.edit().putString("e", e).commit();
        ses.edit().putString("p", p).commit();
        ses.edit().putString("f", f).commit();
        ses.edit().putString("l", l).commit();
        ses.edit().putString("g", g).commit();
        ses.edit().putString("d", d).commit();
        ses.edit().putString("m", m).commit();
        ses.edit().putString("y", y).commit();
        ses.edit().putString("w", w).commit();
        ses.edit().putString("b", b).commit();
        ses.edit().putString("hf", hf).commit();
        ses.edit().putString("hi", hi).commit();
        ses.edit().putString("r", r).commit();
        ses.edit().putString("cn", cn).commit();
        ses.edit().putString("c", c).commit();
        ses.edit().putString("wa", wa).commit();
        ses.edit().putString("des", des).commit();

        setSignUpUser(f, l);
    }
}
