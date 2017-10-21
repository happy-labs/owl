package com.score.myexp.ui;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.score.myexp.db.ExpenseDbSource;

public class NewExpenseActivity extends AppCompatActivity {

    private EditText nameEditText;
    private EditText amountEditText;

    private ExpenseDbSource expenseDbSource;

    private static final String TAG = NewExpenseActivity.class.getName();

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_expense_layout);

        // TODO create DB source instance

        initUi();
    }

    /**
     * Initialize UI components
     * Setup action bar
     */
    private void initUi() {
        // set up action bar
        final android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setBackgroundDrawable(new ColorDrawable(0xff333333));
        actionBar.setTitle("New expense");

        nameEditText = (EditText) findViewById(R.id.name);
        amountEditText = (EditText) findViewById(R.id.amount);
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
