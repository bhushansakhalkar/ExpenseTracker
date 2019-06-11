package com.example.expensetracker;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DailyFragment extends Fragment {
    private ListViewAdapter listViewAdapter;
    private DatabaseHelper databaseHelper;
    private ListView listView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment1_daily,container,false);
        listView = view.findViewById(R.id.list_view_activity);
        databaseHelper = new DatabaseHelper(view.getContext());
        String username = Globals.username;
        int date = new Date().getDay();
        Date d = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dat = dateFormat.format(d);
                    List<Expense> list = databaseHelper.getInformation(username,dat);
                    listViewAdapter = new ListViewAdapter(view.getContext(), list);
                    listView.setAdapter(listViewAdapter);
                    return view;
    }
}
