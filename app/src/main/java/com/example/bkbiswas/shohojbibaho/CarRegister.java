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

public class CarRegister extends AppCompatActivity {

    private EditText regNo,noOfSeats,driverName, driverLicense,driverPhone,expectedAmount;
    private Spinner carType;
    private Button register,chooseCar;
    private ImageView img;
    int x = 0;
    private ProgressDialog progressDialog;

    private String userName, contactNo, owner,carRegistrationNo;

    private Uri uri;
    private StorageReference mStorageRef;

    private void takeInput(){
        final String cType,seats,driversName,driversLicenseNo,driversPhoneNo,expectedAmountperHour,email;
        Intent intent = getIntent();

        carRegistrationNo = regNo.getText().toString().trim();
        cType = carType.getSelectedItem().toString().trim();
        seats = noOfSeats.getText().toString().trim();
        driversName = driverName.getText().toString().trim();
        driversLicenseNo = driverLicense.getText().toString().trim();
        driversPhoneNo = driverPhone.getText().toString().trim();
        expectedAmountperHour = expectedAmount.getText().toString().trim();
        userName = intent.getStringExtra("userName");
        contactNo = intent.getStringExtra("contactNo");
        owner = intent.getStringExtra("firstName") + " " + intent.getStringExtra("lastName");

        if(carRegistrationNo.isEmpty()){
            regNo.setError("You can't keep this field empty!");
            return;
        }

        if(expectedAmountperHour.isEmpty()){
            expectedAmount.setError("You can't keep this field empty!");
            return;
        }

        final DatabaseReference d = FirebaseDatabase.getInstance().getReference("Car Info");
        email = intent.getStringExtra("email");
        final CarInfo carInfo = new CarInfo(carRegistrationNo, cType,seats,driversName,driversLicenseNo,driversPhoneNo,expectedAmountperHour,contactNo,userName,owner,email);

        AlertDialog.Builder builder1 = new AlertDialog.Builder(CarRegister.this);
        builder1.setMessage("Do you want to proceed?");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if(x==0){
                            progressDialog = ProgressDialog.show(CarRegister.this,"Registering your car!","Please wait...",false,false);
                            d.child(carRegistrationNo).setValue(carInfo);
                            Intent intent = new Intent(CarRegister.this, Homepage.class);
                            startActivity(intent);
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(),"Registration successfull",Toast.LENGTH_SHORT).show();
                        }

                        else{
                            progressDialog = ProgressDialog.show(CarRegister.this,"Registering your car!","Please wait...",false,false);
                            mStorageRef = FirebaseStorage.getInstance().getReference("Car Info");
                            StorageReference imgRef = mStorageRef.child("/"+carRegistrationNo);
                            Log.e("onClick: ", imgRef.getName());

                            imgRef.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                    Uri downloadUrl = taskSnapshot.getDownloadUrl();
                                    String location = downloadUrl.toString();
                                    carInfo.setUri(location);
                                    d.child(carRegistrationNo).setValue(carInfo);
                                    Intent intent = new Intent(CarRegister.this, Homepage.class);
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
        setContentView(R.layout.car_register);

        regNo = findViewById(R.id.cr);
        noOfSeats = findViewById(R.id.n);
        driverName = findViewById(R.id.dn);
        driverLicense = findViewById(R.id.dl);
        driverPhone = findViewById(R.id.dp);
        expectedAmount = findViewById(R.id.ea);
        carType = findViewById(R.id.ct);
        register = findViewById(R.id.rc);
        img = findViewById(R.id.imgCar);
        chooseCar = findViewById(R.id.chooseCar);

        chooseCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                x=1;
                openGallery();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
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




    private void upload(){

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent resultdata) {
        if(requestCode==10 && resultCode== Activity.RESULT_OK){
            if(resultdata != null){
                uri = resultdata.getData();
                img.setImageURI(null);
                img.setImageURI(uri);
            }
        }
    }
}
