package com.score.myexp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.score.myexp.pojos.Expense;

import java.util.ArrayList;

/**
 * We do
 * 1. Object create
 * 2. Object update
 * 3. Object delete
 * 4. Get objects
 * from here
 */
public class ExpenseDbSource {

    private static Context context;

    private static final String TAG = ExpenseDbSource.class.getName();

    /**
     * Init db helper
     *
     * @param context application context
     */
    public ExpenseDbSource(Context context) {
        Log.d(TAG, "Init: db source");
        this.context = context;
    }


    /**
     * Find expense with matching name
     *
     * @return Expense
     */
    public Expense findExpense(String name) {
        // get matching Expense if exists
        SQLiteDatabase db = ExpenseDbHelper.getInstance(context).getWritableDatabase();

        // TODO write query

        return null;
    }

    /**
     * Find all expenses
     *
     * @return expenses list
     */
    public ArrayList<Expense> getAllExpenses() {
        ArrayList<Expense> expenseList = new ArrayList<>();

        // TODO write query

        return expenseList;
    }

    /**
     * Add Expense to the database
     */
    public void createExpense(Expense expense) {
        Log.d(TAG, "Create expense - " + expense.getName() + ": " + expense.getAmount());

        // TODO write query
    }
}
