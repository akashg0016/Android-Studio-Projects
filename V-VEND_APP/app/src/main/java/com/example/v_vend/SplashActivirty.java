package com.example.v_vend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;

public class SplashActivirty extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        SystemClock.sleep(3000);
        Intent loginIntent = new Intent(SplashActivirty.this, RegisterActivity.class);
        startActivity(loginIntent);
        finish();
    }
}