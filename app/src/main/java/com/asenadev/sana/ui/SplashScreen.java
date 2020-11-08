package com.asenadev.sana.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.asenadev.sana.R;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // timer
        new Handler().postDelayed(() -> {
            Intent intent=new Intent(SplashScreen.this, LoginActivity.class);
            startActivity(intent);
            finish();
        },3000);

    }
}