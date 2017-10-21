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

    /**
     * {@inheritDoc}
     */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.splash_layout);
        initNavigation();
    }

    /**
     * Determine where to go from here
     */
    private void initNavigation() {
        // determine where to go
        if (PreferenceUtil.getUser(this) == null) navigateToSplash();
        else navigateToHome();
    }

    /**
     * Switch to home activity
     * This method will be call after successful login
     */
    private void navigateToSplash() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                navigateToHome();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }

    private void navigateRegistration() {
        // no user, so move to registration
//        Intent intent = new Intent(this, RegistrationActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        startActivity(intent);
//        finish();
    }

    /**
     * Switch to home activity
     * This method will be call after successful login
     */
    public void navigateToHome() {
        Intent intent = new Intent(this, ExpenseListActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        SplashActivity.this.finish();

    }
}