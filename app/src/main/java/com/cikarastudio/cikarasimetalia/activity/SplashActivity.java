package com.cikarastudio.cikarasimetalia.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.cikarastudio.cikarasimetalia.R;

public class SplashActivity extends AppCompatActivity {

    private int loadingTime = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ImageView logo = findViewById(R.id.logo_simetal);
        TextView aplikasi = findViewById(R.id.textView);
        TextView copyright = findViewById(R.id.textView2);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                //setelah loading maka akan langsung berpindah ke intro activity
                Intent home = new Intent(SplashActivity.this, IntroActivity.class);
                startActivity(home);
                finish();

            }
        }, loadingTime);

        Animation myanim = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.splashanimation);
        Animation textanim = AnimationUtils.loadAnimation(SplashActivity.this,R.anim.splashtextanimation);
        aplikasi.startAnimation(textanim);
        copyright.startAnimation(textanim);
        logo.startAnimation(myanim);
    }
}