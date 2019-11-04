package com.example.bkbiswas.shohojbibaho;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Successfull extends AppCompatActivity {

    String userName = "";
    String location;
    int uid=0;
    int k =0;
    private String email;
    private EditText verification;
    private Button submit,back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.successfull);

        verification=findViewById(R.id.ver);
        submit = findViewById(R.id.sub);
        back = findViewById(R.id.back);

        final String password;
        final String firstName;
        final String lastName;
        final String gender;
        final String day;
        final String month;
        final String year;
        final String weight;
        final String bloodGroup;
        final String heightFt;
        final String heightInch;
        final String religion;
        final String contactNo;
        final String country;
        final String worksAt;
        final String designation;


        final DatabaseReference d1 = FirebaseDatabase.getInstance().getReference("idGenerator");
        final DatabaseReference d2 = FirebaseDatabase.getInstance().getReference("userInfo");
        final Intent intent = getIntent();

        email = intent.getStringExtra("email");
        password = intent.getStringExtra("password");
        firstName = intent.getStringExtra("firstName");
        lastName=intent.getStringExtra("lastName");
        gender = intent.getStringExtra("gender");
        religion=intent.getStringExtra("religion");
        day =intent.getStringExtra("day");
        month=intent.getStringExtra("month");
        year=intent.getStringExtra("year");
        weight=intent.getStringExtra("weight");
        heightFt=intent.getStringExtra("heightFt");
        heightInch=intent.getStringExtra("heightInch");
        contactNo=intent.getStringExtra("contactNo");
        worksAt=intent.getStringExtra("worksAt");
        designation=intent.getStringExtra("designation");
        bloodGroup=intent.getStringExtra("bloodGroup");
        country=intent.getStringExtra("country");
        userName=intent.getStringExtra("userName");
        uid= Integer.parseInt(intent.getStringExtra("uid"));

        sendEmail();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String x = verification.getText().toString().trim();
                if(x.equals(userName)){
                    SharedPreferences session = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    session.edit().putString("currentUser",userName).commit();
                    UserInfo u = new UserInfo(email, password, firstName, lastName, gender, day, month, year, weight, bloodGroup, heightFt, heightInch, religion, contactNo, country, worksAt, designation, userName);
                    d2.child(userName).setValue(u);

                    ID newID = new ID(uid+1, "-LMsydJ-jiHwXFDakOEx");
                    d1.child("-LMsydJ-jiHwXFDakOEx").setValue(newID);
                    Intent intent = new Intent(Successfull.this, Homepage.class);
                    startActivity(intent);
                }

                else{
                    verification.setError("Invalid verification code");
                }
            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(Successfull.this, Log_in.class);
                startActivity(intent1);
            }
        });



       /*new CountDownTimer(1000, 1000) {

            public void onTick(long millisUntilFinished) {

            }

            public void onFinish() {
                UserInfo u = new UserInfo(email, password, firstName, lastName, gender, day, month, year, weight, bloodGroup, heightFt, heightInch, religion, contactNo, country, worksAt, designation, userName);
                d2.child(userName).setValue(u);

                ID newID = new ID(uid+1, "-LMsydJ-jiHwXFDakOEx");
                d1.child("-LMsydJ-jiHwXFDakOEx").setValue(newID);
                Intent intent = new Intent(Successfull.this, Homepage.class);
                startActivity(intent);
            }
        }.start();*/
    }














    private void sendEmail() {
        //Getting content for email
        //String email = "kanak113331@gmail.com";
        String subject = "Shohoj Bibaho Account Verification Code";
        String message = "Your username is your verification code.\nYour username & verification code is: " + userName;
        String mail = email;
        //Creating SendMail object
        SendMail sm = new SendMail(this, mail, subject, message, "Your verification code is being sent!", "Your verification code is sent!");

        sm.execute();
    }





    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
