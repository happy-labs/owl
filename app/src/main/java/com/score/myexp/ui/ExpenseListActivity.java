package com.score.myexp.ui;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
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
public class ExpenseListActivity extends Activity {

    private ListView expenseListView;
    private ExpenseListAdapter expenseListAdapter;
    private ArrayList<Expense> expenseList;

    private ExpenseDbSource expenseDbSource;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);

        expenseDbSource = new ExpenseDbSource(this);

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
        final ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setBackgroundDrawable(new ColorDrawable(0xff333333));
        actionBar.setTitle("Expenses");
    }

    /**
     * Read available expenses from database
     */
    private void initExpenseList() {
        // get all expenses
        expenseList = expenseDbSource.getAllExpenses();
    }

    /**
     * Initialize expense list view
     */
    private void initExpenseListView() {
        expenseListView = (ListView) findViewById(R.id.list_view);
        expenseListAdapter = new ExpenseListAdapter(this, expenseList);

        // add header and footer for list
        View headerView = View.inflate(this, R.layout.list_header, null);
        View footerView = View.inflate(this, R.layout.list_header, null);
        expenseListView.addHeaderView(headerView);
        expenseListView.addFooterView(footerView);
        expenseListView.setAdapter(expenseListAdapter);
        expenseListView.setTextFilterEnabled(false);
    }
}
