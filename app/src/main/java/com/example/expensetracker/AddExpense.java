package com.example.expensetracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddExpense extends AppCompatActivity {
    private EditText editTextActivity;
    private EditText editTextAmount;
    private EditText editTextPaymentMode;
    private EditText editTextDate;
    private EditText editTextDescription;
    private Button buttonAdd;
    private DatabaseHelper databaseHelper;
    private Button buttonHome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);
        editTextActivity = findViewById(R.id.edit_text_activity);
        editTextAmount = findViewById(R.id.edit_text_amount);
        editTextPaymentMode = findViewById(R.id.edit_text_Mode_of_payment);
        editTextDate = findViewById(R.id.edit_text_date);
        editTextDescription = findViewById(R.id.edit_text_description);
        buttonAdd = findViewById(R.id.button_add);
        databaseHelper = new DatabaseHelper(this);
        buttonHome = findViewById(R.id.button_home);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String activity = editTextActivity.getText().toString();
                int amount = Integer.parseInt(editTextAmount.getText().toString());
                String mode = editTextPaymentMode.getText().toString();
                String date = editTextDate.getText().toString();
                String description = editTextDescription.getText().toString();

                if(databaseHelper.addExpense(Globals.username,activity,amount,mode,date,description)){
                    Toast.makeText(AddExpense.this,"ADDED",Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(AddExpense.this,"Could not add",Toast.LENGTH_LONG).show();
                }
            }
        });

        buttonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddExpense.this,Home.class);
                startActivity(intent);
            }
        });
    }
}
