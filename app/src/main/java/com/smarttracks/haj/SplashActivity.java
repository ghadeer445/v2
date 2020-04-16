package com.v_2.haj;

import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;

import com.v_2.haj.helper.SQLiteHandler;

import java.util.HashMap;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {


    /**
     * Duration of wait
     **/
    private final int SPLASH_DISPLAY_LENGTH = 2000;
    private SQLiteHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        db = new SQLiteHandler(getApplicationContext());
        final HashMap<String, String> userDetails = db.getUserDetails();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                    Intent mainIntent = new Intent(SplashActivity.this, HomeActivity.class);
                    startActivity(mainIntent);
                    finish();

            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
