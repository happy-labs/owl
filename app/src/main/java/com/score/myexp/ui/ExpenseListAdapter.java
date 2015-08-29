package com.score.myexp.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.score.myexp.pojos.Expense;

import java.util.ArrayList;

/**
 * Display expense list
 *
 * @author eranga herath(erangaeb@gmail.com)
 */
public class ExpenseListAdapter extends BaseAdapter {

    private ExpenseListActivity activity;
    private ArrayList<Expense> expenseList;

    /**
     * Initialize context variables
     *
     * @param activity friend list activity
     */
    public ExpenseListAdapter(ExpenseListActivity activity, ArrayList<Expense> expenseList) {
        this.activity = activity;
        this.expenseList = expenseList;
    }

    /**
     * Get size of expense list
     *
     * @return expenseList size
     */
    @Override
    public int getCount() {
        return expenseList.size();
    }

    /**
     * Get specific item from expense list
     *
     * @param i item index
     * @return list item
     */
    @Override
    public Object getItem(int i) {
        return expenseList.get(i);
    }

    /**
     * Get expense list item id
     *
     * @param i item index
     * @return current item id
     */
    @Override
    public long getItemId(int i) {
        return i;
    }

    /**
     * Create list row view
     *
     * @param position index
     * @param view     current list item view
     * @param parent   parent
     * @return view
     */
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        // A ViewHolder keeps references to children views to avoid unnecessary calls
        // to findViewById() on each row.
        final ViewHolder holder;
        final Expense expense = (Expense) getItem(position);

        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.list_row_layout, parent, false);
            holder = new ViewHolder();
            holder.iconText = (TextView) view.findViewById(R.id.icon_text);
            holder.name = (TextView) view.findViewById(R.id.expense_list_row_layout_name);
            holder.amount = (TextView) view.findViewById(R.id.expense_list_row_layout_amount);

            view.setTag(holder);
        } else {
            // get view holder back
            holder = (ViewHolder) view.getTag();
        }

        // bind text with view holder content view for efficient use
        holder.iconText.setText("#");
        holder.name.setText(expense.getName());
        holder.amount.setText(Double.toString(expense.getAmount()));
        view.setBackgroundResource(R.drawable.list_selector);

        return view;
    }


    /**
     * Keep reference to children view to avoid unnecessary calls
     */
    static class ViewHolder {
        TextView iconText;
        TextView name;
        TextView amount;
    }

}
