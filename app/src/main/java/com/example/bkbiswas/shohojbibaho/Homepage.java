package com.example.bkbiswas.shohojbibaho;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class Homepage extends AppCompatActivity {

    private ImageView iprovidecar, iprovideconvention, iprovidecatering, ilogout, ipp, ieditprofile;
    private TextView tprovidecar, tprovideconvention, tprovidecatering, tname, tworksat, teditprofile, tlogout, tchangepp;
    private Button car, convention, catering, partner;
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
    private ProgressDialog progressDialog;

    private Uri uri;
    private StorageReference mStorageRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage2);
        /*FirebaseMessaging.getInstance().subscribeToTopic("catering");
        getApplicationContext().startService(new Intent(getApplicationContext(),FcmService.class));*/

        iprovidecar = findViewById(R.id.iprovidecar2);
        iprovideconvention = findViewById(R.id.iprovideconvention2);
        iprovidecatering = findViewById(R.id.iprovidecatering2);
        ilogout = findViewById(R.id.ilogout2);
        ipp = findViewById(R.id.ipp2);
        ieditprofile = findViewById(R.id.ieditprofile2);
        teditprofile = findViewById(R.id.teditprofile2);
        tprovidecar = findViewById(R.id.tprovidecar2);
        tprovideconvention = findViewById(R.id.tprovideconvention2);
        tprovidecatering = findViewById(R.id.tprovidecatering2);
        tname = findViewById(R.id.tname2);
        tworksat = findViewById(R.id.tworksat2);
        teditprofile = findViewById(R.id.teditprofile2);
        tlogout = findViewById(R.id.tlogout2);
        tchangepp = findViewById(R.id.tchangepp);
        car = findViewById(R.id.car2);
        convention = findViewById(R.id.convention2);
        catering = findViewById(R.id.catering2);
        partner = findViewById(R.id.partner);

        final SharedPreferences session = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        currentUser = session.getString("currentUser","");
        DatabaseReference d = FirebaseDatabase.getInstance().getReference("userInfo");


        progressDialog = ProgressDialog.show(Homepage.this,"Profile Loading!","Please wait...",false,false);
        d.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot i: dataSnapshot.getChildren()){
                    UserInfo u = i.getValue(UserInfo.class);
                    if(u.getUserName().equals(currentUser)){
                        input(u);
                        tname.setText(firstName + " " + lastName);
                        tworksat.setText(currentUser);
                        session.edit().putString("contactNo",contactNo).commit();
                        session.edit().putString("name",firstName + " " + lastName).commit();
                        session.edit().putString("emailid",email).commit();
                        session.edit().putString("worksAt",worksAt + " (" + designation + ")").commit();
                        session.edit().putString("dob",day + " " + month + " " + year).commit();
                        session.edit().putString("religion",religion).commit();
                        session.edit().putString("country",country).commit();
                        session.edit().putString("link",location).commit();
                        Picasso.get().load(location).into(ipp);
                        progressDialog.dismiss();
                        break;
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        iprovidecar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                provideCar();
            }
        });

        tprovidecar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                provideCar();
            }
        });

        iprovideconvention.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                provideConvention();
            }
        });

        tprovideconvention.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                provideConvention();
            }
        });

        iprovidecatering.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                provideCatering();
            }
        });

        tprovidecatering.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                provideCatering();
            }
        });

        ilogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });

        tlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });


        ipp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        ieditprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editProfile();
            }
        });

        teditprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editProfile();
            }
        });

        tname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Homepage.this, Profile.class);
                startActivity(intent);
            }
        });

        tchangepp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();

                AlertDialog.Builder builder1 = new AlertDialog.Builder(Homepage.this);
                builder1.setMessage("Are you sure want to change your profile picture?");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                upload();
                            }
                        });

                builder1.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        });


        car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Homepage.this, CarFind2.class);
                intent.putExtra("currentUser",currentUser);
                //intent.putExtra("gender", gender);
                startActivity(intent);
            }
        });

        convention.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Homepage.this, ConventionFind2.class);
                intent.putExtra("currentUser",currentUser);
                //intent.putExtra("gender", gender);
                startActivity(intent);
            }
        });

        catering.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Homepage.this, CateringFind2.class);
                intent.putExtra("currentUser",currentUser);
                //intent.putExtra("gender", gender);
                startActivity(intent);
            }
        });

        partner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Homepage.this, PartnerFind2.class);
                intent.putExtra("currentUser",currentUser);
                intent.putExtra("gender", gender);
                startActivity(intent);
            }
        });
    }














    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    private void editProfile() {
        Intent intent = new Intent(Homepage.this, EditProfile.class);
        intent.putExtra("email",email);
        intent.putExtra("password",password);
        intent.putExtra("firstName",firstName);
        intent.putExtra("lastName",lastName);
        intent.putExtra("gender",gender);
        intent.putExtra("religion",religion);
        intent.putExtra("day",day);
        intent.putExtra("month",month);
        intent.putExtra("year",year);
        intent.putExtra("weight",weight);
        intent.putExtra("heightFt",heightFt);
        intent.putExtra("heightInch",heightInch);
        intent.putExtra("contactNo",contactNo);
        intent.putExtra("worksAt",worksAt);
        intent.putExtra("designation",designation);
        intent.putExtra("bloodGroup",bloodGroup);
        intent.putExtra("country",country);
        intent.putExtra("userName",currentUser);
        intent.putExtra("location",location);
        startActivity(intent);
    }

    private void provideCar() {
        Intent intent = new Intent(Homepage.this, CarRegister.class);
        intent.putExtra("contactNo",contactNo);
        intent.putExtra("userName",currentUser);
        intent.putExtra("firstName",firstName);
        intent.putExtra("lastName",lastName);
        intent.putExtra("email",email);
        startActivity(intent);
    }

    private void provideConvention() {
        Intent intent = new Intent(Homepage.this, ConventionRegistration.class);
        intent.putExtra("contactNo",contactNo);
        intent.putExtra("userName",currentUser);
        intent.putExtra("firstName",firstName);
        intent.putExtra("lastName",lastName);
        intent.putExtra("email",email);
        startActivity(intent);
    }


    private void provideCatering() {
        Intent intent = new Intent(Homepage.this, CateringRegister.class);
        intent.putExtra("contactNo",contactNo);
        intent.putExtra("userName",currentUser);
        intent.putExtra("firstName",firstName);
        intent.putExtra("lastName",lastName);
        intent.putExtra("email",email);
        startActivity(intent);
    }


    private void logout() {
        SharedPreferences mySPrefs =PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = mySPrefs.edit();
        editor.remove("currentUser");
        editor.apply();
        editor.remove("contactNo");
        editor.apply();
        editor.remove("name");
        editor.apply();
        editor.remove("emailid");
        editor.apply();
        editor.remove("worksAt");
        editor.apply();
        editor.remove("dob");
        editor.apply();
        editor.remove("religion");
        editor.apply();
        editor.remove("country");
        editor.apply();
        editor.remove("link");
        editor.apply();

        Intent intent = new Intent(Homepage.this, Log_in.class);
        startActivity(intent);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent resultdata) {
        if(requestCode==10 && resultCode== Activity.RESULT_OK){
            if(resultdata != null){
                uri = resultdata.getData();
            }
        }
    }


    private void openGallery(){
        Intent pickImage = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        pickImage.addCategory(Intent.CATEGORY_OPENABLE);
        pickImage.setType("image/*");
        startActivityForResult(pickImage,10);
    }
    
    
    
    private void upload(){
        ipp.setImageURI(null);
        ipp.setImageURI(uri);
        mStorageRef = FirebaseStorage.getInstance().getReference("Profile Picture");
        StorageReference imgRef = mStorageRef.child("/" + currentUser);
        Log.e("onClick: ", imgRef.getName());

        imgRef.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Uri downloadUrl = taskSnapshot.getDownloadUrl();
                location = downloadUrl.toString();
                UserInfo u = new UserInfo(email, password, firstName, lastName, gender, day, month, year, weight, bloodGroup, heightFt, heightInch, religion, contactNo, country, worksAt, designation, currentUser);
                u.setLocation(location);
                DatabaseReference d = FirebaseDatabase.getInstance().getReference("userInfo");
                d.child(currentUser).setValue(u);
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
