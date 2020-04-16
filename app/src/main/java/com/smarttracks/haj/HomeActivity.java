package com.v_2.haj;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import com.androidnetworking.AndroidNetworking;
import com.v_2.haj.helper.SQLiteHandler;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    Button sosButton;
    Button translateButton;
    Button exploreButton;
    Button logoutButton;

    ImageView homeImageView;


    String[] languages;
    private SQLiteHandler db;
    String selected_lang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        sosButton = findViewById(R.id.sosButton);
        translateButton = findViewById(R.id.translateButton);
        homeImageView = findViewById(R.id.homeImageView);
        exploreButton = findViewById(R.id.exploreButton);
        logoutButton = findViewById(R.id.logoutButton);


        AndroidNetworking.initialize(getApplicationContext());
        db = new SQLiteHandler(getApplicationContext());

        sosButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(getApplicationContext(), SOSActivity.class);
                startActivity(mainIntent);
                finish();
            }
        });

        translateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(getApplicationContext(), TranslateActivity.class);
                startActivity(mainIntent);
                finish();
            }
        });


        exploreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("http://maps.google.com/maps?q=loc:21.3891,39.8579"));
                startActivity(intent);
//                Intent intent = new Intent();
//                intent.setAction(Intent.ACTION_VIEW);
//                intent.setPackage("com.google.android.apps.translate");
//
//                Uri uri = new Uri.Builder()
//                        .scheme("http")
//                        .authority("translate.google.com")
//                        .path("/m/translate")
//                        .appendQueryParameter("q", "مرحبا بك")
//                        .appendQueryParameter("tl", "en")
//                        .appendQueryParameter("sl", "ar")
//                        .build();
//                intent.setType("text/plain");
//                intent.setData(uri);
//                startActivity(intent);
            }
        });


        homeImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(mainIntent);
                finish();
            }
        });

    }


    @Override
    public void onBackPressed() {

    }
}
