package com.score.owl.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * We do
 * 1. Database creation
 * 2. Table creation, deletions
 * 3. Version managements
 * from here
 *
 * @author eranga herath(erangaeb@hgmail.com)
 */
public class ExpenseDbHelper extends SQLiteOpenHelper {

    // we use singleton database
    private static ExpenseDbHelper expenseDbHelper;

    // If you change the database schema, you must increment the database version
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Expense.db";

    // TODO define data types

    // TODO write sql to create expense table
    //private static final String SQL_CREATE_EXPENSE =

    // TODO write sql to delete expense table
    //private static final String SQL_DELETE_EXPENSE =

    private static final String TAG = ExpenseDbHelper.class.getName();

    /**
     * Init context
     * Init database
     *
     * @param context application context
     */
    private ExpenseDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * We are reusing one database instance in all over the app for better memory usage
     *
     * @param context application context
     * @return db helper instance
     */
    synchronized static ExpenseDbHelper getInstance(Context context) {
        if (expenseDbHelper == null) {
            expenseDbHelper = new ExpenseDbHelper(context.getApplicationContext());
        }
        return (expenseDbHelper);
    }

    /**
     * {@inheritDoc}
     */
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "onCreate DbHelper");
        // TODO execute create table queries
    }

    /**
     * {@inheritDoc}
     */
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, "onUpgrade DbHelper");
        // TODO execute drop table queries
        // onCreate(db);
    }

}
