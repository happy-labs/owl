package com.score.myexp.ui;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.score.myexp.db.ExpenseDbSource;
import com.score.myexp.pojos.Expense;

import java.util.ArrayList;

public class NewExpenseActivity extends Activity {

    private Expense expense;
    private ExpenseDbSource expenseDbSource;

    private EditText usernameEditText;
    private EditText phoneNoEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_expense_layout);

        expenseDbSource = new ExpenseDbSource(this);
        initUi();
    }

    private void initUi() {
        final ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setBackgroundDrawable(new ColorDrawable(0xff333333));
        actionBar.setTitle("Create User");

        usernameEditText = (EditText) findViewById(R.id.username);
        phoneNoEditText = (EditText) findViewById(R.id.phone_no);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.ok_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_done) {
            // init user
            initExpense();

            // save user
            expenseDbSource.createExpense(expense);

            // get all users
            ArrayList<Expense> expenses = expenseDbSource.getAllExpenses();
            for (Expense e : expenses) {
                System.out.println(e.getName());
            }

//            // get user with phoneno
//            User createdUser = registryDbSource.getUser(user.getPhoneNo());
//            System.out.println("-------");
//            System.out.println(createdUser.getUsername());

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initExpense() {
        String name = usernameEditText.getText().toString().trim();
        double amount = Double.parseDouble(phoneNoEditText.getText().toString().trim());
        expense = new Expense(name, amount);
    }

}
