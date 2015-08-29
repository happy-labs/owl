package com.score.myexp.db;

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

    // data types
    private static final String TEXT_TYPE = " TEXT";
    private static final String REAL_TYPE = " REAL";

    // sql to create expense table
    private static final String SQL_CREATE_EXPENSE =
            "CREATE TABLE " + ExpenseDbContract.Expense.TABLE_NAME + " (" +
                    ExpenseDbContract.Expense._ID + " INTEGER PRIMARY KEY AUTOINCREMENT" + "," +
                    ExpenseDbContract.Expense.COLUMN_NAME_NAME + TEXT_TYPE + "NOT NULL" + "," +
                    ExpenseDbContract.Expense.COLUMN_NAME_AMOUNT + REAL_TYPE + "NOT NULL" +
                    " )";

    // sql to delete expense table
    private static final String SQL_DELETE_EXPENSE =
            "DROP TABLE IF EXISTS " + ExpenseDbContract.Expense.TABLE_NAME;

    private static final String TAG = ExpenseDbSource.class.getName();

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
        db.execSQL(SQL_CREATE_EXPENSE);
    }

    /**
     * {@inheritDoc}
     */
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, "onUpgrade DbHelper");
        db.execSQL(SQL_DELETE_EXPENSE);
        onCreate(db);
    }

    /**
     * {@inheritDoc}
     */
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

}
