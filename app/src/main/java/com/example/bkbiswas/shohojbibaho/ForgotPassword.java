package com.example.bkbiswas.shohojbibaho;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;


public class ForgotPassword extends AppCompatActivity {

    private EditText e;
    private Button submit, back;
    private String uName;
    private ProgressDialog progressDialog;
    private String email;
    private int random;
    private UserInfo u;
    int flag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password);

        e = findViewById(R.id.vercp);
        submit = findViewById(R.id.subcp);
        back = findViewById(R.id.backcp);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uName = e.getText().toString().trim();

                if(uName.isEmpty()){
                    e.setError("You need to provide your username!");
                    return;
                }


                DatabaseReference d = FirebaseDatabase.getInstance().getReference("userInfo");


                d.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot i : dataSnapshot.getChildren()) {
                            UserInfo ui = i.getValue(UserInfo.class);
                            if (ui.getUserName().equals(uName)) {
                                flag = 1;
                                u = ui;
                                email = ui.getEmail();
                                final int min = 20;
                                final int max = 80;
                                random = new Random().nextInt((max - min) + 1) + min;
                                SendMail sm = new SendMail(ForgotPassword.this, email, "Change Your Password", "Your username is: " + uName + "\nYour new password is: " + random + "" +"123456" , "Your new password is being sent to your email", "Your password is sent to your email" );
                                sm.execute();
                                return;
                            }
                        }
                        //progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), "Invalid Username or Internet Failure", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(getApplicationContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });







        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference d = FirebaseDatabase.getInstance().getReference("userInfo");
                u.setPassword(random + "" +"123456");
                d.child(uName).setValue(u);

                Intent intent = new Intent(ForgotPassword.this, Log_in.class);
                startActivity(intent);
            }
        });
    }






    @Override
    public void onBackPressed() {

        if(flag==0){
            Intent intent = new Intent(ForgotPassword.this, Log_in.class);
            startActivity(intent);
        }

        else{
            DatabaseReference d = FirebaseDatabase.getInstance().getReference("userInfo");
            u.setPassword(random + "" +"123456");
            d.child(uName).setValue(u);

            Intent intent = new Intent(ForgotPassword.this, Log_in.class);
            startActivity(intent);
        }
    }
}
