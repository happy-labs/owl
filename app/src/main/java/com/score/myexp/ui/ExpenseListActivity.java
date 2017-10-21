package com.score.myexp.ui;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.score.myexp.db.ExpenseDbSource;
import com.score.myexp.pojos.Expense;

import java.util.ArrayList;

/**
 * Activity which responsible to display Expense list
 *
 * @author eranga herath(erangaeb@gmail.com)
 */
public class ExpenseListActivity extends AppCompatActivity {

    private ListView expenseListView;
    private ExpenseListAdapter expenseListAdapter;
    private ArrayList<Expense> expenseList;
    private FloatingActionButton newButton;

    private ExpenseDbSource expenseDbSource;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);

        // TODO initialize DB source

        initUi();
        initExpenseList();
        initExpenseListView();
    }

    /**
     * Initialize UI components
     * Setup action bar
     */
    private void initUi() {
        // set up action bar
        final android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setBackgroundDrawable(new ColorDrawable(0x00ffffff));
        actionBar.setTitle("Expenses");

        newButton = (FloatingActionButton) findViewById(R.id.new_expense);
        newButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExpenseListActivity.this, NewExpenseActivity.class);
                ExpenseListActivity.this.startActivity(intent);
            }
        });
    }

    /**
     * Read available expenses from database
     */
    private void initExpenseList() {
        expenseList = new ArrayList<>();

        expenseList.add(new Expense("Elec bill", 300.00));
        expenseList.add(new Expense("Food", 400.00));
        // TODO get all expenses(expenseList) via DB source
    }

    /**
     * Initialize expense list view
     */
    private void initExpenseListView() {
        expenseListView = (ListView) findViewById(R.id.list);
        expenseListAdapter = new ExpenseListAdapter(this, expenseList);
        expenseListView.setAdapter(expenseListAdapter);
        expenseListView.setTextFilterEnabled(false);
    }
}
