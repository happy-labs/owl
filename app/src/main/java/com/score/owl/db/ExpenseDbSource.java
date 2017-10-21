package com.score.owl.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.score.owl.pojo.Expense;

import java.util.ArrayList;

/**
 * We do
 * 1. Object create
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
     * Find all contacts,
     * <p>
     * executing query
     * SELECT * FROM contacts;
     *
     * @return contacts list
     */
    public ArrayList<Expense> getContacts() {
        SQLiteDatabase db = ExpenseDbHelper.getInstance(context).getReadableDatabase();
        Cursor cursor = db.query(ExpenseDbContract.Contact.TABLE_NAME, // table
                null, // columns
                null, // filter
                null, // selection
                null, // order by
                null, // group by
                null); // join

        ArrayList<Expense> contactsList = new ArrayList<>();
        while (cursor.moveToNext()) {
            String username = cursor.getString(cursor.getColumnIndex(ExpenseDbContract.Contact.COLUMN_NAME_USERNAME));
            String phone = cursor.getString(cursor.getColumnIndex(ExpenseDbContract.Contact.COLUMN_NAME_PHONE));
            contactsList.add(new Expense(username, 1.0));
        }

        return contactsList;
    }

    /**
     * Get contact with given username,
     * <p>
     * executing query
     * SELECT * FROM contacts WHERE username = '<given username>';
     *
     * @return contact list
     */
    public Expense getContact(String username) {
        SQLiteDatabase db = ExpenseDbHelper.getInstance(context).getReadableDatabase();
        Cursor cursor = db.query(ExpenseDbContract.Contact.TABLE_NAME, // table
                null, // columns
                ExpenseDbContract.Contact.COLUMN_NAME_USERNAME + " = ?", // constraint
                new String[]{username}, // prams
                null, // order by
                null, // group by
                null); // join

        ArrayList<Expense> contactsList = new ArrayList<>();
        if (cursor.moveToFirst()) {
            String phone = cursor.getString(cursor.getColumnIndex(ExpenseDbContract.Contact.COLUMN_NAME_PHONE));

            return new Expense(username, 1.0);
        }

        return null;
    }

    /**
     * Add Expense to the database
     */
    public long createContact(Expense expense) throws SQLException {
        Log.d(TAG, "Create expense - " + expense.getName() + ": " + expense.getAmount());

        SQLiteDatabase db = ExpenseDbHelper.getInstance(context).getWritableDatabase();

        // content values to inset
        ContentValues values = new ContentValues();
        values.put(ExpenseDbContract.Contact.COLUMN_NAME_USERNAME, expense.getName());
        values.put(ExpenseDbContract.Contact.COLUMN_NAME_PHONE, expense.getAmount());

        // insert the new row, if fails throw an error
        return db.insertOrThrow(ExpenseDbContract.Contact.TABLE_NAME, null, values);
    }
}
