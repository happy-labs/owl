package com.score.owl.db;

import android.provider.BaseColumns;

/**
 * Define Database schemas here(Keep database table attributes)
 *
 * @author eranga herath(erangaeb@gmail.com)
 */
public class ExpenseDbContract {
    /**
     * Inner class that defines contact table contents
     */
    public static abstract class Contact implements BaseColumns {
        static final String TABLE_NAME = "contacts";
        static final String COLUMN_NAME_USERNAME = "username";
        static final String COLUMN_NAME_PHONE = "phone";
        static final String COLUMN_NAME_DIGSIG = "digsig";
    }
}

