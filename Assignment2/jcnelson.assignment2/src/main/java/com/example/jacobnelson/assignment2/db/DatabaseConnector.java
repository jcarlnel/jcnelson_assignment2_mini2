package com.example.jacobnelson.assignment2.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class DatabaseConnector {
    // database name
    private static final String DATABASE_NAME = "Mortgages";
    private SQLiteDatabase database; // database object
    private DatabaseOpenHelper databaseOpenHelper; // database helper

    // public constructor for DatabaseConnector
    public DatabaseConnector(Context context)
    {
        // create a new DatabaseOpenHelper
        databaseOpenHelper =
                new DatabaseOpenHelper(context, DATABASE_NAME, null, 1);
    } // end DatabaseConnector constructor

    // open the database connection
    public void open() throws SQLException
    {
        // create or open a database for reading/writing
        database = databaseOpenHelper.getWritableDatabase();
    } // end method open

    // close the database connection
    public void close()
    {
        if (database != null)
            database.close(); // close the database connection
    } // end method close

    // inserts a new mortgage in the database
    public void insertMortgage(double payment, double total, String month, String year)
    {
        ContentValues newMortgage = new ContentValues();
        newMortgage.put("monthlyPayment", payment);
        newMortgage.put("payoffTotal", total);
        newMortgage.put("payoffMonth", month);
        newMortgage.put("payoffYear", year);

        open(); // open the database
        database.insert("mortgages", null, newMortgage);
        System.out.println("Mortgage added: " + month + " " + year);
        close(); // close the database

    } // end method insertContact


    private class DatabaseOpenHelper extends SQLiteOpenHelper
    {
        // public constructor
        public DatabaseOpenHelper(Context context, String name,
                                  SQLiteDatabase.CursorFactory factory, int version)
        {
            super(context, name, factory, version);
        } // end DatabaseOpenHelper constructor

        // creates the contacts table when the database is created
        @Override
        public void onCreate(SQLiteDatabase db)
        {
            // query to create a new table named contacts
            String createQuery = "CREATE TABLE mortgages" +
                    "(_id integer primary key autoincrement," +
                    "monthlyPayment FLOAT, payoffTotal FLOAT, payoffMonth TEXT," +
                    "payoffYear TEXT);";

            db.execSQL(createQuery); // execute the query
        } // end method onCreate

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion,
                              int newVersion)
        {
        } // end method onUpgrade
    } // end class DatabaseOpenHelper
} // end class DatabaseConnector
