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

public class ConventionFind2 extends AppCompatActivity {

    private ListView listV;
    private List<ConventionInfo> coninfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.convention_find2);

        listV = findViewById(R.id.listCon);
        Intent intent=getIntent();
        final String currentUser = intent.getStringExtra("currentUser");

        coninfo = new ArrayList<>();
        DatabaseReference d = FirebaseDatabase.getInstance().getReference("Convention Info");

        d.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot i: dataSnapshot.getChildren()){
                    ConventionInfo c = i.getValue(ConventionInfo.class);
                    if(!c.getUserName().equals(currentUser)) coninfo.add(c);
                }
                CustomAdapterConvention c = new CustomAdapterConvention(ConventionFind2.this,coninfo);
                listV.setAdapter(c);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
