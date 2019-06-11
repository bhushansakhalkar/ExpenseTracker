package com.example.expensetracker;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ListViewAdapter extends BaseAdapter {
    private List<Expense> listExpense;
    private Context context;
    private DatabaseHelper databaseHelper;

    public ListViewAdapter(Context context,List<Expense> listExpense){
        this.context = context;
        this.listExpense = listExpense;
        databaseHelper = new DatabaseHelper(context);
    }
    @Override
    public int getCount() {
        return listExpense.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.activity_list_view_adapter,parent,false);
        TextView activity = view.findViewById(R.id.text_view_activityname);
        TextView amount = view.findViewById(R.id.text_view_amount);
        TextView mode = view.findViewById(R.id.text_view_mode);
        TextView date = view.findViewById(R.id.text_view_date);
        Expense expense = listExpense.get(position);
        activity.setText(expense.getActivityName());
        amount.setText(""+expense.getAmount());
        mode.setText(expense.getMode());
        date.setText(expense.getDate());
        Calendar calendar = Calendar.getInstance();

        calendar.get(Calendar.MONTH);
        Date date1 = null;
        Date date2 = null;
        try {
            date2 = new SimpleDateFormat("yyyy/mm/dd").parse("2019/05/24");
            date1 = new SimpleDateFormat("yyyy/mm/dd").parse(expense.getDate());
            calendar.setTime(date1);
            calendar.get(Calendar.MONTH);
        }catch (ParseException e){}
        System.out.println(date1);
        System.out.println(date2);
        return view;
    }
}
