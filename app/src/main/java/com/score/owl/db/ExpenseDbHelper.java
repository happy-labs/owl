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

    private static final String TAG = ExpenseDbHelper.class.getName();

    // we use singleton database
    private static ExpenseDbHelper expenseDbHelper;

    // if you change the database schema, you must increment the database version
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "owl.db";

    //
    // sql to create contacts table
    //    CREATE TABLE contacts (
    //        _id INTEGER PRIMARY KEY AUTOINCREMENT,
    //        username TEXT,
    //        phone TEXT,
    //        digsig TEXT
    //    );
    private static final String SQL_CREATE_CONTACT =
            "CREATE TABLE " + ExpenseDbContract.Contact.TABLE_NAME + " (" +
                    ExpenseDbContract.Contact._ID + " INTEGER PRIMARY KEY AUTOINCREMENT" + ", " +
                    ExpenseDbContract.Contact.COLUMN_NAME_USERNAME + " TEXT, " +
                    ExpenseDbContract.Contact.COLUMN_NAME_PHONE + " TEXT, " +
                    ExpenseDbContract.Contact.COLUMN_NAME_DIGSIG + " TEXT" +
                    " )";

    // sql to delete contacts table
    //    DROP TABLE IF EXISTS contacts;
    private static final String SQL_DELETE_CONTACT = "DROP TABLE IF EXISTS " + ExpenseDbContract.Contact.TABLE_NAME;


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
        Log.i(TAG, SQL_CREATE_CONTACT);

        db.execSQL(SQL_CREATE_CONTACT);
    }

    /**
     * {@inheritDoc}
     */
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, "onUpgrade DbHelper");
        Log.i(TAG, SQL_DELETE_CONTACT);

        db.execSQL(SQL_DELETE_CONTACT);
        onCreate(db);
    }

}
