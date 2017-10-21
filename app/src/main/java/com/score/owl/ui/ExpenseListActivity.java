package com.score.owl.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.score.owl.db.ExpenseDbSource;
import com.score.owl.pojos.Expense;

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
    private Typeface typeface;

    private ExpenseDbSource expenseDbSource;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);
        typeface = Typeface.createFromAsset(getAssets(), "fonts/GeosansLight.ttf");

        // TODO initialize DB source

        initActionBar();
        initUi();
        initExpenseList();
        initExpenseListView();
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
        textView.setText("Expenses");
        textView.setTypeface(typeface, Typeface.BOLD);

        ActionBar.LayoutParams params = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        actionBar.setCustomView(view, params);
    }

    /**
     * Initialize UI components
     * Setup action bar
     */
    private void initUi() {
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
