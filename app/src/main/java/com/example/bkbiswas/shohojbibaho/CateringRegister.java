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

public class CateringRegister extends AppCompatActivity {

    private EditText cateringName,chargePerWaiter, itemName,price;
    private Spinner areaOfService;
    private ImageView imgCat;
    private Button choosePhoto,registerForCatering;
    private Uri uri;
    private int x =0;
    private StorageReference mStorageRef;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.catering_register);

        cateringName = findViewById(R.id.cateringCat);
        chargePerWaiter = findViewById(R.id.chargeCat);
        itemName = findViewById(R.id.itemCat);
        price = findViewById(R.id.priceCat);

        areaOfService = findViewById(R.id.areaCat);
        choosePhoto = findViewById(R.id.chooseCat);
        registerForCatering = findViewById(R.id.registerCat);

        imgCat = findViewById(R.id.imgCat);

        registerForCatering.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takeInput();
            }
        });

        choosePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                x=1;
                openGallery();
            }
        });
    }



















    private void takeInput(){
        final String catName, areaService, chargeWaiter, imName, pr, contNo, usName,owner,email;
        Intent intent = getIntent();

        catName = cateringName.getText().toString().trim();
        areaService =areaOfService.getSelectedItem().toString().trim();
        chargeWaiter = chargePerWaiter.getText().toString().trim();
        imName = itemName.getText().toString().trim();
        pr = price.getText().toString().trim();

        if(catName.isEmpty()){
            cateringName.setError("You can't keep this field empty");
            return;
        }

        if(chargeWaiter.isEmpty()){
            chargePerWaiter.setError("You can't keep this field empty");
            return;
        }

        if(imName.isEmpty()){
            itemName.setError("You can't keep this field empty");
            return;
        }

        if(pr.isEmpty()){
            price.setError("You can't keep this field empty");
            return;
        }

        contNo = intent.getStringExtra("contactNo");
        usName = intent.getStringExtra("userName");
        owner = intent.getStringExtra("firstName") + " " + intent.getStringExtra("lastName");
        email = intent.getStringExtra("email");
        final String s = catName + imName;

        //Toast.makeText(getApplicationContext(),contNo + usName + owner,Toast.LENGTH_SHORT).show();

        final CateringInfo c = new CateringInfo(catName, areaService, chargeWaiter, imName, pr, contNo, usName, owner,email );
        final DatabaseReference d = FirebaseDatabase.getInstance().getReference("Catering Info");

        AlertDialog.Builder builder1 = new AlertDialog.Builder(CateringRegister.this);
        builder1.setMessage("Do you want to proceed?");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if(x==0){
                            progressDialog = ProgressDialog.show(CateringRegister.this,"Registering your catering!","Please wait...",false,false);
                            d.child(s).setValue(c);
                            Intent intent = new Intent(CateringRegister.this, Homepage.class);
                            startActivity(intent);
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(),"Registration successfull",Toast.LENGTH_SHORT).show();
                        }

                        else{
                            progressDialog = ProgressDialog.show(CateringRegister.this,"Registering your catering!","Please wait...",false,false);
                            mStorageRef = FirebaseStorage.getInstance().getReference("Catering Info");
                            StorageReference imgRef = mStorageRef.child("/"+s);
                            Log.e("onClick: ", imgRef.getName());

                            imgRef.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                    Uri downloadUrl = taskSnapshot.getDownloadUrl();
                                    String location = downloadUrl.toString();
                                    c.setUri(location);
                                    d.child(s).setValue(c);
                                    Intent intent = new Intent(CateringRegister.this, Homepage.class);
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
                imgCat.setImageURI(null);
                imgCat.setImageURI(uri);
            }
        }
    }

}
