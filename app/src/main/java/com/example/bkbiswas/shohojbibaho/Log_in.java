package com.example.bkbiswas.shohojbibaho;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
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

public class Log_in extends AppCompatActivity {

    private TextView forgot;
    private EditText userName, password;
    private Button signin, signup;
    DatabaseReference d;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        SharedPreferences session = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String ss = session.getString("currentUser", "");
        //Toast.makeText(getApplicationContext(),ss,Toast.LENGTH_SHORT).show();

        if (!ss.isEmpty()) {
            Intent intent = new Intent(Log_in.this, Homepage.class);
            startActivity(intent);
        }

        forgot = findViewById(R.id.forgot);
        signin = findViewById(R.id.signin);
        signup = findViewById(R.id.signup);
        userName = findViewById(R.id.userName);
        password = findViewById(R.id.password);


        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Log_in.this, ForgotPassword.class);
                startActivity(intent);
            }
        });


        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checking();

            }
        });


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Log_in.this, Registration3.class);///////////////////////////
                startActivity(intent);
            }
        });
    }








    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }





    public void checking() {
        final String u = userName.getText().toString().trim();
        final String p = password.getText().toString().trim();

        if (u.isEmpty() || p.isEmpty()) {
            Toast.makeText(getApplicationContext(), "You cannot keep the fields empty", Toast.LENGTH_SHORT).show();
            userName.setError("Username Required");
            return;
        }

        d = FirebaseDatabase.getInstance().getReference("userInfo");


        d.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                progressDialog = ProgressDialog.show(Log_in.this,"Verifying your username & password!","Please wait...",false,false);
                for (DataSnapshot i : dataSnapshot.getChildren()) {
                    UserInfo ui = i.getValue(UserInfo.class);
                    if (ui.getUserName().equals(u) && ui.getPassword().equals(p)) {
                        SharedPreferences session = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                        session.edit().putString("currentUser",ui.getUserName()).commit();
                        Intent intent = new Intent(Log_in.this, Homepage.class);
                        startActivity(intent);
                        progressDialog.dismiss();
                        return;
                    }
                }
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Invalid Username or Password", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
