package com.example.jacobnelson.assignment2.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.jacobnelson.assignment2.R;
import com.example.jacobnelson.assignment2.db.DatabaseConnector;
import com.example.jacobnelson.assignment2.model.Calculator;
import com.example.jacobnelson.assignment2.ui.CalculatedActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    DatabaseConnector db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        View v = findViewById(R.id.SubmitButton);
        v.setOnClickListener(this);

       db = new DatabaseConnector(MainActivity.this);


    }

    @Override
    public void onClick(View arg0){
        if(arg0.getId() == R.id.SubmitButton){

            int mortgage = Integer.valueOf(((EditText) findViewById(R.id.PurchasePrice)).getText().toString()).intValue();
            int down = Integer.valueOf(((EditText) findViewById(R.id.DownPayment)).getText().toString()).intValue();
            int term = Integer.valueOf(((EditText) findViewById(R.id.MortgageTerm)).getText().toString()).intValue();
            float interest = Float.valueOf(((EditText) findViewById(R.id.InterestRate)).getText().toString());
            int tax = Integer.valueOf(((EditText) findViewById(R.id.PropertyTax)).getText().toString()).intValue();
            int insurance = Integer.valueOf(((EditText) findViewById(R.id.PropertyInsurance)).getText().toString()).intValue();
            String month = ((Spinner) findViewById(R.id.Month)).getSelectedItem().toString();

            int year = Integer.valueOf(((Spinner) findViewById(R.id.Year)).getSelectedItem().toString()).intValue();
            //int year = ((Spinner) findViewById(R.id.Year)).getSelectedItemPosition() + 2000;

            Calculator c = new Calculator(mortgage, down, term, interest, tax, insurance, month, year);
            c.calculate();
            db.insertMortgage(c.monthlyPayment, c.payoffTotal, c.endMonth, Integer.toString(c.endYear));
            Intent intent = new Intent(this, CalculatedActivity.class);
            intent.putExtra("Calculator", c);
            this.startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
