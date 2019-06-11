package com.example.expensetracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText editTextUsername;
    private EditText editTextPassword;
    private Button buttonLogin;
    private TextView textViewRegister;
    private DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonLogin = findViewById(R.id.button_login);
        editTextPassword = findViewById(R.id.edit_text_password);
        editTextUsername = findViewById(R.id.edit_text_username);
        textViewRegister = findViewById(R.id.text_view_registration);
        databaseHelper = new DatabaseHelper(this);

        textViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Registration.class);
                startActivity(intent);
            }
        });
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTextUsername.getText().toString();
                Globals.username = username;
                String password = editTextPassword.getText().toString();
                if(databaseHelper.login(username,password)){
                    Intent intent = new Intent(MainActivity.this,Home.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(MainActivity.this,"Invalid User",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
