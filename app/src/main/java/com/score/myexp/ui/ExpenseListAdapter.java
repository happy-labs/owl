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
 * Display friend list
 *
 * @author eranga herath(erangaeb@gmail.com)
 */
public class ExpenseListAdapter extends BaseAdapter {

    private ExpenseListActivity activity;
    private ArrayList<Expense> userList;

    /**
     * Initialize context variables
     *
     * @param activity friend list activity
     */
    public ExpenseListAdapter(ExpenseListActivity activity, ArrayList<Expense> userList) {
        this.activity = activity;
        this.userList = userList;
    }

    /**
     * Get size of user list
     *
     * @return userList size
     */
    @Override
    public int getCount() {
        return userList.size();
    }

    /**
     * Get specific item from user list
     *
     * @param i item index
     * @return list item
     */
    @Override
    public Object getItem(int i) {
        return userList.get(i);
    }

    /**
     * Get user list item id
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
