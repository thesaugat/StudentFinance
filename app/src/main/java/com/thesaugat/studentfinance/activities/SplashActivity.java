package com.thesaugat.studentfinance.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.thesaugat.studentfinance.HomeActivity;
import com.thesaugat.studentfinance.R;
import com.thesaugat.studentfinance.helper.Constants;
import com.thesaugat.studentfinance.utils.SharedPreferencesUtils;


public class SplashActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable(){
            public void run() {
                Intent loginSignup = new Intent(SplashActivity.this, LoginActivity.class);
                Intent homescreen = new Intent(SplashActivity.this, HomeActivity.class);

                if (!SharedPreferencesUtils.getBooleanPreference(SplashActivity.this, Constants.ISLOGGEDIN, false)) {
                    startActivity(loginSignup);
                    finish();
                } else {
                    startActivity(homescreen);
                    finish();
                }
            }}, 1100);



    }


}
