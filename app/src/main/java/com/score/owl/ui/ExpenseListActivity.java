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

import com.score.owl.R;
import com.score.owl.db.ExpenseDbSource;
import com.score.owl.pojo.Expense;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);
        typeface = Typeface.createFromAsset(getAssets(), "fonts/GeosansLight.ttf");

        initActionBar();
        initUi();
        initList();
    }

    @Override
    public void onResume() {
        super.onResume();
        refreshList();
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

    private void initList() {
        expenseListView = (ListView) findViewById(R.id.list);
        expenseListView.setTextFilterEnabled(false);

        expenseList = new ExpenseDbSource(this).getContacts();
        expenseListAdapter = new ExpenseListAdapter(this, expenseList);
        expenseListView.setAdapter(expenseListAdapter);
    }

    private void refreshList() {
        expenseList.clear();
        expenseList.addAll(new ExpenseDbSource(this).getContacts());
        expenseListAdapter.notifyDataSetChanged();
    }

}
