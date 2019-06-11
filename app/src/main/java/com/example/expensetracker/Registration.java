package com.example.expensetracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registration extends AppCompatActivity {
    private EditText editTextName;
    private EditText editTextContact;
    private EditText editTextEmail;
    private EditText editTextUsername;
    private EditText editTextPassword;
    private EditText editTextConfirmPassword;
    private Button buttonRegister;
    private DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        editTextName = findViewById(R.id.edit_text_full_name);
        editTextContact = findViewById(R.id.edit_text_contact);
        editTextEmail = findViewById(R.id.edit_text_email);
        editTextUsername = findViewById(R.id.edit_text_user_name);
        editTextPassword = findViewById(R.id.edit_text_password1);
        editTextConfirmPassword = findViewById(R.id.edit_text_password2);
        buttonRegister = findViewById(R.id.button_register);
        databaseHelper = new DatabaseHelper(this);


        editTextConfirmPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String password = editTextPassword.getText().toString();
                String cpassword = editTextConfirmPassword.getText().toString();
                if(!hasFocus){
                    if(!password.equals(cpassword)){
                        Toast.makeText(Registration.this,"Incorrect Password",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString();
                String contact = editTextContact.getText().toString();
                String email = editTextEmail.getText().toString();
                String username = editTextUsername.getText().toString();
                String password = editTextPassword.getText().toString();
                String cpassword = editTextConfirmPassword.getText().toString();

                if(password.equals(cpassword)){
                    if (databaseHelper.register(name,contact,email,username,password)){
                        Intent intent = new Intent(Registration.this,MainActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(Registration.this,"Please retry registering",Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(Registration.this,"Please retry registering",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
