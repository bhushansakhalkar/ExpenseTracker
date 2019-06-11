package com.example.expensetracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ArrayAdapter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(Context context) {
        super(context,"Mydatabase.db",null,2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create Table Users(name text,contact text,email text,username text,password text)");
        db.execSQL("Create Table Expense(username text,activity text,amount int,mode text,date text,description text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Users");
        db.execSQL("DROP TABLE IF EXISTS Expense");

        onCreate(db);
    }
    public boolean register(String name,String contact,String email,String username,String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("contact",contact);
        contentValues.put("email",email);
        contentValues.put("username",username);
        contentValues.put("password",password);
        db.insert("Users",null,contentValues);
        return true;
    }
    public boolean login(String username,String password){
        SQLiteDatabase db = this.getReadableDatabase();
        String[] values = {username,password};
        Cursor cursor = db.rawQuery("Select username,password from Users where username=? and password=?",values);
        boolean status = false;
        if(cursor.moveToFirst()){
            status = true;
        }
        cursor.close();
        return status;
    }
    public boolean addExpense(String username,String activity,int amount,String mode,String date,String description){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",username);
        contentValues.put("activity",activity);
        Date dateFormat;
        try {
             dateFormat = new SimpleDateFormat("yyyy-MM-dd").parse(date);
            //Globals.date = dateFormat;
        }catch (ParseException e){}

        contentValues.put("amount",amount);
        contentValues.put("date",date);
        contentValues.put("mode",mode);
        contentValues.put("description",description);
        db.insert("Expense",null,contentValues);
        return true;
    }


    public List<Expense> getInformation(String username,String date){
        SQLiteDatabase db = this.getReadableDatabase();
        String[] values = {username,date};
        Cursor cursor = db.rawQuery("Select * from Expense where username=? and date=?",values);
        List<Expense> listExpense = new ArrayList<>();
        boolean status = cursor.moveToFirst();
        if(cursor.moveToFirst()){
            while (!cursor.isAfterLast()) {
                Expense expense = new Expense();
                expense.setActivityName(cursor.getString(1));
                expense.setAmount(cursor.getInt(2));
                expense.setMode(cursor.getString(3));
                Date date1;
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    date1 = dateFormat.parse(cursor.getString(4));
                    Globals.date = date1;
                }catch (ParseException e){}
                expense.setDate(cursor.getString(4));
                listExpense.add(expense);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return listExpense;
    }


    public List<Expense> getInformationMonthly(String username,String date){
        SQLiteDatabase db = this.getReadableDatabase();
        String[] values = {username,date};
        Cursor cursor = db.rawQuery("Select * from Expense where username=? and strftime('%m',date)=?",values);
        List<Expense> listExpense = new ArrayList<>();
        boolean status = cursor.moveToFirst();
        if(cursor.moveToFirst()){
            while (!cursor.isAfterLast()) {
                Expense expense = new Expense();
                expense.setActivityName(cursor.getString(1));
                expense.setAmount(cursor.getInt(2));
                expense.setMode(cursor.getString(3));
                expense.setDate(cursor.getString(4));
                listExpense.add(expense);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return listExpense;
    }

    public List<Expense> getInformationYearly(String username,String date){
        SQLiteDatabase db = this.getReadableDatabase();
        String[] values = {username,date};
        Cursor cursor = db.rawQuery("Select * from Expense where username=? and strftime('%Y',date)=?",values);
        List<Expense> listExpense = new ArrayList<>();
        boolean status = cursor.moveToFirst();
        if(cursor.moveToFirst()){
            while (!cursor.isAfterLast()) {
                Expense expense = new Expense();
                expense.setActivityName(cursor.getString(1));
                expense.setAmount(cursor.getInt(2));
                expense.setMode(cursor.getString(3));
                expense.setDate(cursor.getString(4));
                listExpense.add(expense);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return listExpense;
    }


}



