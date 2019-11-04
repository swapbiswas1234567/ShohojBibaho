package com.example.bkbiswas.shohojbibaho;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class ConventionRegistration extends AppCompatActivity {

    private EditText conventionName,area,capacityPeople,capacityParking,decorationCharge,floorCost;
    private Spinner location;
    private ImageView conventionPhoto;
    private Button choosePhoto,registerForConvention;
    private String email;
    private ProgressDialog progressDialog;

    private Uri uri;
    private int x =0;
    private StorageReference mStorageRef;

    private void takeInput(){
        final String convName, loc, ar, capPeople, capParking, decCharge, flrCost,contNo, usName,owner;
        final DatabaseReference d = FirebaseDatabase.getInstance().getReference("Convention Info");
        Intent intent = getIntent();

        convName = conventionName.getText().toString().trim();
        loc = location.getSelectedItem().toString().trim();
        ar = area.getText().toString().trim();
        capPeople = capacityPeople.getText().toString().trim();
        capParking = capacityParking.getText().toString().trim();
        decCharge = decorationCharge.getText().toString().trim();
        flrCost =floorCost.getText().toString().trim();
        contNo = intent.getStringExtra("contactNo");
        usName = intent.getStringExtra("userName");
        owner = intent.getStringExtra("firstName") + " " + intent.getStringExtra("lastName");
        final String s = convName + loc + usName;

        if(convName.isEmpty()){
            conventionName.setError("You can't keep this field empty!");
            return;
        }

        if(ar.isEmpty()){
            area.setError("You can't keep this field empty!");
            return;
        }

        if(capPeople.isEmpty()){
            capacityPeople.setError("You can't keep this field empty!");
            return;
        }

        if(capParking.isEmpty()){
            capacityParking.setError("You can't keep this field empty!");
            return;
        }

        if(decCharge.isEmpty()){
            decorationCharge.setError("You can't keep this field empty!");
            return;
        }

        if(flrCost.isEmpty()){
            floorCost.setError("You can't keep this field empty!");
            return;
        }

        //Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();
        email = intent.getStringExtra("email");
        final ConventionInfo c = new ConventionInfo(convName, loc, ar, capPeople, capParking, decCharge, flrCost,contNo, usName, owner, email);

        AlertDialog.Builder builder1 = new AlertDialog.Builder(ConventionRegistration.this);
        builder1.setMessage("Do you want to proceed?");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if(x==0){
                            progressDialog = ProgressDialog.show(ConventionRegistration.this,"Registering your convention!","Please wait...",false,false);
                            d.child(s).setValue(c);
                            Intent intent = new Intent(ConventionRegistration.this, Homepage.class);
                            startActivity(intent);
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(),"Registration successfull",Toast.LENGTH_SHORT).show();
                        }

                        else{
                            progressDialog = ProgressDialog.show(ConventionRegistration.this,"Registering your convention!","Please wait...",false,false);
                            mStorageRef = FirebaseStorage.getInstance().getReference("Convention Info");
                            StorageReference imgRef = mStorageRef.child("/"+s);
                            Log.e("onClick: ", imgRef.getName());

                            imgRef.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                    Uri downloadUrl = taskSnapshot.getDownloadUrl();
                                    String location = downloadUrl.toString();
                                    c.setUri(location);
                                    d.child(s).setValue(c);
                                    Intent intent = new Intent(ConventionRegistration.this, Homepage.class);
                                    startActivity(intent);
                                    progressDialog.dismiss();
                                    Toast.makeText(getApplicationContext(),"Registration successfull",Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.convention_registration);

        conventionName = findViewById(R.id.convnameC);
        area = findViewById(R.id.areaC);
        capacityPeople = findViewById(R.id.capacitypeopleC);
        capacityParking = findViewById(R.id.capacityparkingC);
        decorationCharge = findViewById(R.id.decorationC);
        floorCost = findViewById(R.id.costC);

        location = findViewById(R.id.locationC);
        conventionPhoto = findViewById(R.id.imgC);

        choosePhoto = findViewById(R.id.chooseC);
        registerForConvention = findViewById(R.id.registerC);

        choosePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                x=1;
                openGallery();
            }
        });

        registerForConvention.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takeInput();
            }
        });
    }























    private void openGallery(){
        Intent pickImage = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        pickImage.addCategory(Intent.CATEGORY_OPENABLE);
        pickImage.setType("image/*");
        startActivityForResult(pickImage,10);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent resultdata) {
        if(requestCode==10 && resultCode== Activity.RESULT_OK){
            if(resultdata != null){
                uri = resultdata.getData();
                conventionPhoto.setImageURI(null);
                conventionPhoto.setImageURI(uri);
            }
        }
    }
}
