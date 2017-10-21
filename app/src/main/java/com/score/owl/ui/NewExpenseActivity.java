package com.score.owl.ui;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.score.owl.R;
import com.score.owl.db.ExpenseDbSource;

public class NewExpenseActivity extends AppCompatActivity {

    private EditText nameEditText;
    private EditText amountEditText;
    private Typeface typeface;

    private ExpenseDbSource expenseDbSource;

    private static final String TAG = NewExpenseActivity.class.getName();

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_expense_layout);
        typeface = Typeface.createFromAsset(getAssets(), "fonts/GeosansLight.ttf");

        // TODO create DB source instance

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
        View view = inflater.inflate(R.layout.new_action_bar, null);

        TextView textView = (TextView) view.findViewById(R.id.title_text);
        textView.setText("New expense");
        textView.setTypeface(typeface, Typeface.BOLD);

        ActionBar.LayoutParams params = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        actionBar.setCustomView(view, params);
    }

    /**
     * Initialize UI components
     * Setup action bar
     */
    private void initUi() {
        nameEditText = (EditText) findViewById(R.id.name);
        amountEditText = (EditText) findViewById(R.id.amount);

        nameEditText.setTypeface(typeface);
        amountEditText.setTypeface(typeface);
    }

    /**
     * Initialize expense with UI component values
     * Then create expense in database
     */
    private void createExpense() {
        String name = nameEditText.getText().toString().trim();
        String amount = amountEditText.getText().toString().trim();

        if (name.isEmpty() || amount.isEmpty()) {
            Log.e(TAG, "Invalid input fields");
            Toast.makeText(this, "Invalid input fields", Toast.LENGTH_LONG).show();
        } else {
            // TODO create expense via DB source
        }
    }

    /**
     * Find expense with given name
     *
     * @param name expense name
     */
    private void findExpense(String name) {
        // TODO find expense with given name via DB source
        // TODO display expense amount in a toast
    }

}
