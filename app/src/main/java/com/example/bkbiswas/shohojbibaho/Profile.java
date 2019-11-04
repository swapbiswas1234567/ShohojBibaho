package com.example.bkbiswas.shohojbibaho;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class Profile extends AppCompatActivity {

    private TextView tname,tuname,temailid,tgender,tbirthday,treligion,tcountry,tweight,tblood,theight,tcontact,tworksat,tdesignation;
    private ImageView ipp;

    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String gender;
    private String day;
    private String month;
    private String year;
    private String weight;
    private String bloodGroup;
    private String heightFt;
    private String heightInch;
    private String religion;
    private String contactNo;
    private String country;
    private String worksAt;
    private String designation;
    private String currentUser;
    private String location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        tname = findViewById(R.id.name);
        tuname = findViewById(R.id.username);
        temailid = findViewById(R.id.emailid);
        tgender = findViewById(R.id.gen);
        tbirthday = findViewById(R.id.birthday);
        treligion = findViewById(R.id.rel);
        tcountry = findViewById(R.id.country);
        tweight = findViewById(R.id.weightp);
        tblood = findViewById(R.id.bloodp);
        theight = findViewById(R.id.heightp);
        tcontact = findViewById(R.id.contactnop);
        tworksat = findViewById(R.id.worksatp);
        tdesignation = findViewById(R.id.designationp);
        ipp = findViewById(R.id.pipp);

        /*SharedPreferences session = this.getApplicationContext().getSharedPreferences("user", MODE_PRIVATE);
        name.setText(session.getString("f","") + " " + session.getString("l",""));
        username.setText(session.getString("username",""));
        emailid.setText(session.getString("e",""));
        gender.setText(session.getString("g",""));
        birthday.setText(session.getString("d","") + " " + session.getString("m","") + " " + session.getString("y",""));
        religion.setText(session.getString("r",""));
        country.setText(session.getString("c",""));

        weight.setText(session.getString("w","") + " kg");
        blood.setText(session.getString("b",""));
        height.setText(session.getString("hf","") + "'" + session.getString("hi","")+"''");
        contact.setText(session.getString("cn",""));
        worksat.setText(session.getString("wa",""));
        designation.setText(session.getString("des",""));*/


        SharedPreferences session = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        currentUser = session.getString("currentUser","");
        DatabaseReference d = FirebaseDatabase.getInstance().getReference("userInfo");

        d.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot i: dataSnapshot.getChildren()){
                    UserInfo u = i.getValue(UserInfo.class);
                    if(u.getUserName().equals(currentUser)){
                        input(u);
                        tname.setText(firstName + " " + lastName);
                        tuname.setText(currentUser);
                        temailid.setText(email);
                        tbirthday.setText(day + " " + month + " " + year);
                        treligion.setText(religion);
                        tcountry.setText(country);
                        tweight.setText(weight + "kg");
                        tblood.setText(bloodGroup);
                        theight.setText(heightFt + "' " + heightInch + "''" );
                        tcontact.setText(contactNo);
                        tworksat.setText(worksAt);
                        tdesignation.setText(designation);
                        tgender.setText(gender);
                        Picasso.get().load(location).into(ipp);
                        break;
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }












    private void input(UserInfo u){
        email = u.getEmail();
        password = u.getPassword();
        firstName = u.getFirstName();
        lastName = u.getLastName();
        gender = u.getGender();
        day = u.getDay();
        month = u.getMonth();
        year = u.getYear();
        weight = u.getWeight();
        bloodGroup = u.getBloodGroup();
        heightFt = u.getHeightFt();
        heightInch = u.getHeightInch();
        religion = u.getReligion();
        contactNo = u.getContactNo();
        country = u.getCountry();
        designation = u.getDesignation();
        location = u.getLocation();
        worksAt = u.getWorksAt();
    }
}
