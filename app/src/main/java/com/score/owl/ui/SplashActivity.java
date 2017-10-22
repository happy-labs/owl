package com.score.owl.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.score.owl.R;
import com.score.owl.util.PreferenceUtil;

/**
 * Splash activity, send login query from here
 *
 * @author eranga herath(erangaeb@gmail.com)
 */
public class SplashActivity extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 3000;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.splash_layout);
        initNavigation();
    }

    private void initNavigation() {
        // todo determine where to go
        // if have registered user go to login, otherwise goto registration
    }

    private void navigateSplash() {
        // todo wait in splash screen for 3 seconds and navigate to registration
    }

    private void navigateRegistration() {
        Intent intent = new Intent(this, RegisterActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        SplashActivity.this.finish();
    }

    public void navigateLogin() {
        // todo go to login
    }

    public void navigateHome() {
        // todo go to home/contact list
    }

}