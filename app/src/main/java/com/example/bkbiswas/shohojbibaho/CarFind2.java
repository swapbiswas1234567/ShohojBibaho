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

public class CarFind2 extends AppCompatActivity {

    private ListView listV;
    private List<CarInfo> carinfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.car_find2);

        listV = findViewById(R.id.listv);
        Intent intent=getIntent();
        final String currentUser = intent.getStringExtra("currentUser");

        carinfo = new ArrayList<>();
        DatabaseReference d = FirebaseDatabase.getInstance().getReference("Car Info");

        d.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot i: dataSnapshot.getChildren()){
                    CarInfo c = i.getValue(CarInfo.class);
                    if(!c.getUserName().equals(currentUser)) carinfo.add(c);
                }
                CustomAdapterCar c = new CustomAdapterCar(CarFind2.this,carinfo);
                listV.setAdapter(c);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
