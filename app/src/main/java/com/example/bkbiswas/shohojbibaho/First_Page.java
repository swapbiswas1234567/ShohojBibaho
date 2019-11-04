package com.example.bkbiswas.shohojbibaho;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class First_Page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_page);
        /*DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("idGenerator");
        String unique = databaseReference.push().getKey();
        ID id = new ID(0,unique);
        databaseReference.child("-LMsydJ-jiHwXFDakOEx").setValue(id);*/
        //SharedPreferences session = this.getApplicationContext().getSharedPreferences("control", MODE_PRIVATE);
        //session.edit().putString("ep","0").commit();
        //session.edit().putString("flag","0").commit();

        new CountDownTimer(1000, 1000) {

            public void onTick(long millisUntilFinished) {

            }

            public void onFinish() {
                /*Intent intent2 = new Intent(Intent.ACTION_SEND);
                intent2.putExtra(Intent.EXTRA_EMAIL,"swapnilbiswas6@gmail.com");
                intent2.putExtra(Intent.EXTRA_SUBJECT,"Subject");
                intent2.putExtra(Intent.EXTRA_TEXT,"Text");
                intent2.setType("message/rfc822");
                startActivity(Intent.createChooser(intent2, "Choose an user"));

                Intent mailIntent = new Intent(Intent.ACTION_VIEW);
                Uri data = Uri.parse("mailto:?subject=" + "subject text"+ "&body=" + "body text " + "&to=" + "undertaker1156133@gmail.com");
                mailIntent.setData(data);
                startActivity(Intent.createChooser(mailIntent, "Send mail..."));*/

                    Intent intent = new Intent(First_Page.this, Log_in.class);
                    //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    //intent.putExtra("EXIT", true);
                    startActivity(intent);
            }
        }.start();



        
    }
}
