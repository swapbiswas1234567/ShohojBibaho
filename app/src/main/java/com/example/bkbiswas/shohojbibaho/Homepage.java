package com.example.bkbiswas.shohojbibaho;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Homepage extends AppCompatActivity {

    private ImageView iprovidecar,iprovideconvention,iprovidecatering;
    private TextView tprovidecar,tprovideconvention,tprovidecatering;
    private Button car,convention,catering;


    private void provideCar(){
        Intent intent = new Intent(Homepage.this,CarRegister.class);
        startActivity(intent);
    }

    private void provideConvention(){
        Intent intent = new Intent(Homepage.this,ConventionRegistration.class);
        startActivity(intent);
    }


    private void provideCatering(){
        Intent intent = new Intent(Homepage.this,CateringRegister.class);
        startActivity(intent);
    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage2);

        iprovidecar = findViewById(R.id.iprovidecar);
        iprovideconvention = findViewById(R.id.iprovideconvention);
        iprovidecatering = findViewById(R.id.iprovidecatering);


        tprovidecar = findViewById(R.id.tprovidecar);
        tprovideconvention = findViewById(R.id.tprovideconvention);
        tprovidecatering = findViewById(R.id.tprovidecatering);


        car = findViewById(R.id.car);
        convention = findViewById(R.id.convention);
        catering = findViewById(R.id.catering);


        iprovidecar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                provideCar();
            }
        });

        tprovidecar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                provideCar();
            }
        });







        iprovideconvention.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                provideConvention();
            }
        });



        tprovideconvention.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                provideConvention();
            }
        });









        iprovidecatering.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                provideCatering();
            }
        });



        tprovidecatering.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                provideCatering();
            }
        });



        car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Homepage.this,CarFind.class);
                startActivity(intent);
            }
        });

        convention.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Homepage.this,ConventionFind.class);
                startActivity(intent);
            }
        });

        catering.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Homepage.this,CateringFind.class);
                startActivity(intent);
            }
        });
    }
}
