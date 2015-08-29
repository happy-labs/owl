package com.score.myexp.db;

import android.provider.BaseColumns;

/**
 * Define Database schemas here(Keep database table attributes)
 *
 * @author eranga herath(erangaeb@gmail.com)
 */
public class ExpenseDbContract {
    /* Inner class that defines Expense table contents */
    public static abstract class Expense implements BaseColumns {
        public static final String TABLE_NAME = "expense";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_AMOUNT = "amount";
    }
}

