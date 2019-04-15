package com.example.inclass08;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ExpenseAdapter extends ArrayAdapter<Expense>{

    public ExpenseAdapter(@NonNull Context context, int resource, @NonNull List<Expense> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        Expense expense = getItem(position);

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.expense_list, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.textViewExpenseName = convertView.findViewById(R.id.textViewExpenseName);
            viewHolder.textViewExpenseAmount = convertView.findViewById(R.id.textViewExpenseAmount);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.textViewExpenseName.setText(expense.getExpenseName());
        viewHolder.textViewExpenseAmount.setText("$"+expense.getAmount());

        return convertView;
    }

    private static class ViewHolder{
        TextView textViewExpenseAmount;
        TextView textViewExpenseName;
    }
}
