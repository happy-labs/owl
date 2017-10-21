package com.score.owl.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.score.owl.R;
import com.score.owl.pojo.User;
import com.score.owl.util.CryptoUtil;
import com.score.owl.util.PreferenceUtil;

import java.security.NoSuchAlgorithmException;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = LoginActivity.class.getName();

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private Typeface typeface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        typeface = Typeface.createFromAsset(getAssets(), "fonts/GeosansLight.ttf");

        //initActionBar();
        initUi();
    }

    private void initActionBar() {
        // set up action bar
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);

        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.action_bar_layout, null);

        TextView textView = (TextView) view.findViewById(R.id.title_text);
        textView.setText("Login");
        textView.setTypeface(typeface, Typeface.BOLD);

        ActionBar.LayoutParams params = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        actionBar.setCustomView(view, params);
    }

    private void initUi() {
        usernameEditText = (EditText) findViewById(R.id.username);
        passwordEditText = (EditText) findViewById(R.id.password);

        usernameEditText.setTypeface(typeface);
        passwordEditText.setTypeface(typeface);

        loginButton = (Button) findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doLogin();
            }
        });
    }

    private void doLogin() {
        String username = usernameEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Invalid input fields", Toast.LENGTH_LONG).show();
        } else {
            try {
                // get matching user
                User user = PreferenceUtil.getUser(this);
                if (user != null) {
                    // SHA256 hash of password
                    String hash = CryptoUtil.hashSHA256(password);
                    Log.d(TAG, "user password: " + user.getPassword());
                    Log.d(TAG, "hashed password: " + hash);

                    // match username/password
                    if (user.getUsername().equals(username) && user.getPassword().equals(hash))
                        navigateHome();
                    else
                        Toast.makeText(this, "Wrong username/password", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "No registered user", Toast.LENGTH_LONG).show();
                }
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void navigateHome() {
        Intent intent = new Intent(this, ContactListActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        this.finish();
    }
}

