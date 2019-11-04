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

public class CateringFind2 extends AppCompatActivity {

    private ListView listV;
    private List<CateringInfo> catinfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.catering_find2);

        listV = findViewById(R.id.listcat);
        Intent intent=getIntent();
        final String currentUser = intent.getStringExtra("currentUser");

        catinfo = new ArrayList<>();
        DatabaseReference d = FirebaseDatabase.getInstance().getReference("Catering Info");

        d.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot i: dataSnapshot.getChildren()){
                    CateringInfo c = i.getValue(CateringInfo.class);

                    if(!c.getUserName().equals(currentUser))    catinfo.add(c);
                }
                CustomAdapterCatering c = new CustomAdapterCatering(CateringFind2.this,catinfo);
                listV.setAdapter(c);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
