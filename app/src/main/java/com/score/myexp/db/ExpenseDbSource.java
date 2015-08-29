package com.score.myexp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.score.myexp.pojos.Expense;

import java.util.ArrayList;

/**
 * Created by eranga on 8/28/15.
 */
public class ExpenseDbSource {

    private static final String TAG = ExpenseDbSource.class.getName();
    private static Context context;

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
     * Get Expense with matching Expensename
     *
     * @return Expense
     */
    public Expense getExpense(String name) {
        // get matching Expense if exists
        SQLiteDatabase db = ExpenseDbHelper.getInstance(context).getWritableDatabase();


        Cursor cursor = db.query(ExpenseDbContract.Expense.TABLE_NAME, // table
                null, ExpenseDbContract.Expense.COLUMN_NAME_NAME + "=?", // constraint
                new String[]{name}, // prams
                null, // order by
                null, // group by
                null); // join


        if (cursor.moveToFirst()) {
            // have matching Expense
            // so get Expense data
            // we return id as password since we no storing Expenses password in database
            String _id = cursor.getString(cursor.getColumnIndex(ExpenseDbContract.Expense._ID));
            String _name = cursor.getString(cursor.getColumnIndex(ExpenseDbContract.Expense.COLUMN_NAME_NAME));
            double _amount = cursor.getDouble(cursor.getColumnIndex(ExpenseDbContract.Expense.COLUMN_NAME_AMOUNT));

            Expense Expense = new Expense(_name, _amount);
            return Expense;
        }

        // clear
        cursor.close();
        db.close();

        return null;
    }

    public ArrayList<Expense> getAllExpenses() {
        ArrayList<Expense> ExpenseList = new ArrayList<>();

        // get matching Expense if exists
        SQLiteDatabase db = ExpenseDbHelper.getInstance(context).getWritableDatabase();
        Cursor cursor = db.query(ExpenseDbContract.Expense.TABLE_NAME, // table
                null, null, // constraint
                null, // prams
                null, // order by
                null, // group by
                null); // join

        while (cursor.moveToNext()) {
            String _id = cursor.getString(cursor.getColumnIndex(ExpenseDbContract.Expense._ID));
            String _name = cursor.getString(cursor.getColumnIndex(ExpenseDbContract.Expense.COLUMN_NAME_NAME));
            double _amount = cursor.getDouble(cursor.getColumnIndex(ExpenseDbContract.Expense.COLUMN_NAME_AMOUNT));


            Log.d(TAG, "have Expense, so return it: " + _id);
            Expense Expense = new Expense(_name, _amount);
            ExpenseList.add(Expense);
        }

        // clear
        cursor.close();
        db.close();

        return ExpenseList;
    }

    /**
     * Add Expense to the database
     */
    public void createExpense(Expense expense) {
        Log.d(TAG, "Add Expense to DB: " + expense.getName());
        SQLiteDatabase db = ExpenseDbHelper.getInstance(context).getWritableDatabase();

        // content values to inset
        ContentValues values = new ContentValues();
        values.put(ExpenseDbContract.Expense.COLUMN_NAME_NAME, expense.getName());
        values.put(ExpenseDbContract.Expense.COLUMN_NAME_AMOUNT, expense.getAmount());

        // Insert the new row, if fails throw an error
        db.insertOrThrow(ExpenseDbContract.Expense.TABLE_NAME, ExpenseDbContract.Expense.COLUMN_NAME_NAME, values);
        db.close();
    }
}
