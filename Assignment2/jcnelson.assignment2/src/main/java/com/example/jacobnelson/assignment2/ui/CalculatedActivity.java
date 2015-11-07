package com.example.jacobnelson.assignment2.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.jacobnelson.assignment2.R;
import com.example.jacobnelson.assignment2.model.Calculator;

public class CalculatedActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculated);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Calculator c = (Calculator) getIntent().getSerializableExtra("Calculator");
        TextView monthlyPaymentText = (TextView) findViewById(R.id.monthlyPaymentVal);
        monthlyPaymentText.setText("$" + c.monthlyPayment);
        TextView totalPaymetText = (TextView) findViewById(R.id.totalPaymentVal);
        totalPaymetText.setText("$" + c.payoffTotal);
        TextView monthText = (TextView) findViewById(R.id.payoffMonth);
        monthText.setText(c.endMonth);
        TextView yearText = (TextView) findViewById(R.id.payoffYear);
        yearText.setText(Integer.toString(c.endYear));


        View v = findViewById(R.id.backButton);
        v.setOnClickListener(this);

    }

    @Override
    public void onClick(View arg0){
        if(arg0.getId() == R.id.backButton){
            Intent intent = new Intent(this, MainActivity.class);
            this.startActivity(intent);
        }
    }

}
