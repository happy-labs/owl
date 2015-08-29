package com.score.myexp.ui;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.score.myexp.db.ExpenseDbSource;

public class NewExpenseActivity extends Activity {

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
        final ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setBackgroundDrawable(new ColorDrawable(0xff333333));
        actionBar.setTitle("New expense");

        nameEditText = (EditText) findViewById(R.id.new_expense_layout_name);
        amountEditText = (EditText) findViewById(R.id.new_expense_layout_amount);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.ok_menu, menu);
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_done) {
            // create expense
            createExpense();

            return true;
        }

        return super.onOptionsItemSelected(item);
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
