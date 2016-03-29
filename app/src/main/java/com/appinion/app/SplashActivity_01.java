package com.appinion.app;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.appinion.utils.Const;
import com.appinion.utils.Pref;


public class SplashActivity_01 extends BaseActivity {
    Intent intent;
    private static final String tag = SplashActivity_01.class.getName();
    int SPLASH_TIME = 2000;// spalsh screen redirection duration
    Handler mSplashHandler;// Manage activity_splash screen flow when click on home
    // button and stop redirect to dashboard

    // Variables
    boolean isFinish = false;
    public Runnable mSplashRunnable = new Runnable() {

        @Override
        public void run() {
            isFinish = true;
            //If the user is logged in thenit will go to the MainActivity.class else the user will be redirected to the login screen.
            if (Pref.getValue(SplashActivity_01.this, Const.Is_LOGIN, false)) {
                intent = new Intent(SplashActivity_01.this, MainActivity.class);
                startActivity(intent);
                finish();
            } else {
                intent = new Intent(SplashActivity_01.this, Login_03.class);
                startActivity(intent);
                finish();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash01);
//        Intent intent = new Intent(this,BuyGiftCard.class);
//        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSplashHandler = new Handler();
        mSplashHandler.postDelayed(mSplashRunnable, SPLASH_TIME);

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (!isFinish) {
            if (mSplashRunnable != null) {
                if (mSplashHandler != null) {
                    mSplashHandler.removeCallbacks(mSplashRunnable);
                }
            }
        }
    }
}