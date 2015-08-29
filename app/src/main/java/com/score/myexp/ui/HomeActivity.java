package com.score.myexp.ui;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by eranga on 8/29/15.
 */
public class HomeActivity extends Activity implements View.OnClickListener {

    private RelativeLayout newExpense;
    private RelativeLayout viewExpenses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);

        initUi();
    }

    private void initUi() {
        final ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setBackgroundDrawable(new ColorDrawable(0xff333333));
        actionBar.setTitle("Home");

        newExpense = (RelativeLayout) findViewById(R.id.home_layout_new_expense);
        viewExpenses = (RelativeLayout) findViewById(R.id.home_layout_view_expenses);

        newExpense.setOnClickListener(this);
        viewExpenses.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == newExpense) {
            Intent intent = new Intent(this, NewExpenseActivity.class);
            this.startActivity(intent);
        } else if (v == viewExpenses) {
            Intent intent = new Intent(this, ExpenseListActivity.class);
            this.startActivity(intent);
        }
    }
}
