package com.score.myexp.ui;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.score.myexp.pojos.Expense;

import java.util.ArrayList;

/**
 * Created by eranga on 8/29/15.
 */
public class ExpenseListActivity extends Activity {

    private ListView expenseListView;
    private ExpenseListAdapter expenseListAdapter;
    private ArrayList<Expense> expenseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);

        initUi();
        initUserList();
        initUserListView();
    }

    private void initUi() {
        final ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setBackgroundDrawable(new ColorDrawable(0xff333333));
        actionBar.setTitle("User list");
    }

    private void initUserList() {
        expenseList = new ArrayList<>();
        expenseList.add(new Expense("Dinner", 400.00));
        expenseList.add(new Expense("Lunch", 1334.45));
    }

    /**
     * Initialize friend list
     */
    private void initUserListView() {
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
