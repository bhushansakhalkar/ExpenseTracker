package com.example.expensetracker;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;

public class ViewExpense extends AppCompatActivity {
    private Spinner spinnerActivity;
    private LinearLayout linearLayoutFragment;
    private ArrayAdapter<String> adapter;
    private DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_expense);
        spinnerActivity = findViewById(R.id.spinner_activity);
        linearLayoutFragment = findViewById(R.id.fragment);
        databaseHelper = new DatabaseHelper(this);
        adapter = new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item);
        spinnerActivity.setAdapter(adapter);
        adapter.add("Show Daily Expenses");
        adapter.add("Show Monthly Expenses");
        adapter.add("Show Yearly Expenses");

        spinnerActivity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Expense expense = new Expense();
                if(item.equals("Show Daily Expenses")){
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    transaction.replace(R.id.fragment,new DailyFragment());
                    transaction.commit();
                }
                if (item.equals("Show Monthly Expenses")){
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    transaction.replace(R.id.fragment,new MonthlyFragment());
                    transaction.commit();
                }
               if (item.equals("Show Yearly Expenses")){
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    transaction.replace(R.id.fragment,new YearlyFragment());
                    transaction.commit();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}
