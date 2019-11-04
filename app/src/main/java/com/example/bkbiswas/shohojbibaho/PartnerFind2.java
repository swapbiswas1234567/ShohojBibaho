package com.example.bkbiswas.shohojbibaho;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PartnerFind2 extends AppCompatActivity {

    private ListView listV;
    private List<UserInfo> partnerinfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.partner_find2);

        listV = findViewById(R.id.listpartner);

        Intent intent=getIntent();
        final String currentUser = intent.getStringExtra("currentUser");
        final String gender = intent.getStringExtra("gender");

        partnerinfo = new ArrayList<>();
        DatabaseReference d = FirebaseDatabase.getInstance().getReference("userInfo");

        d.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot i: dataSnapshot.getChildren()){
                    UserInfo u = i.getValue(UserInfo.class);

                    if(u.getUserName().equals(currentUser) || u.getGender().equals(gender)){

                    }
                    else{
                        partnerinfo.add(u);
                    }
                }
                CustomAdapterPartner c = new CustomAdapterPartner(PartnerFind2.this,partnerinfo);
                listV.setAdapter(c);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
