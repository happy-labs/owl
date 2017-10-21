package com.score.owl.ui;

import android.database.SQLException;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.score.owl.R;
import com.score.owl.db.ContactDbSource;
import com.score.owl.pojo.Contact;

public class NewContactActivity extends AppCompatActivity {

    private EditText nameEditText;
    private EditText phoneEditText;
    private Button createButton;
    private Typeface typeface;

    private static final String TAG = NewContactActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_expense_layout);
        typeface = Typeface.createFromAsset(getAssets(), "fonts/GeosansLight.ttf");

        initActionBar();
        initUi();
    }

    private void initActionBar() {
        // set up action bar
        final android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);

        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.home_action_bar, null);

        TextView textView = (TextView) view.findViewById(R.id.title_text);
        textView.setText("New expense");
        textView.setTypeface(typeface, Typeface.BOLD);

        ActionBar.LayoutParams params = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        actionBar.setCustomView(view, params);
    }

    private void initUi() {
        nameEditText = (EditText) findViewById(R.id.name);
        phoneEditText = (EditText) findViewById(R.id.amount);
        nameEditText.setTypeface(typeface);
        phoneEditText.setTypeface(typeface);

        createButton = (Button) findViewById(R.id.save_button);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createExpense();
            }
        });
    }

    private void createExpense() {
        String name = nameEditText.getText().toString().trim();
        String phone = phoneEditText.getText().toString().trim();

        if (name.isEmpty() || phone.isEmpty()) {
            Toast.makeText(this, "Invalid input fields", Toast.LENGTH_LONG).show();
        } else {
            try {
                // create expense via DB source
                new ContactDbSource(this).createContact(new Contact(name, phone));

                Toast.makeText(this, "Contact successfully saved", Toast.LENGTH_LONG).show();
                this.finish();
            } catch (SQLException e) {
                e.printStackTrace();
                Toast.makeText(this, "Failed to create contact", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void getExpense(String username) {
        // TODO find expense with given name via DB source
        // TODO display expense amount in a toast
    }

}
