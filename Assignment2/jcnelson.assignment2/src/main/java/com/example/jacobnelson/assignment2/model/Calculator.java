package com.example.jacobnelson.assignment2.model;

import android.widget.EditText;
import android.widget.TextView;

import com.example.jacobnelson.assignment2.R;

import java.io.Serializable;

/**
 * Created by jacobnelson on 11/6/15.
 */
public class Calculator implements Serializable{
    private final int downPayment;
    private final int mortgage;
    private final int tax;
    private final int term;
    private final float interest;
    private final int insurance;
    private final String startMonth;
    public int startYear;
    public double monthlyPayment;
    public String endMonth;
    public int endYear;
    public double payoffTotal;

    public Calculator(int mortgage, int payment, int term, float interest, int tax, int insurance, String month, int year){
        downPayment = payment;
        this.mortgage = mortgage;
        this.term = term;
        this.interest = interest;
        this.tax = tax;
        this.insurance = insurance;
        this.startMonth = month;
        this.startYear = year;
    }
    
    public void calculate(){
        monthlyPayment = Math.round(calculateMonthlyPayment()*100.0)/100.0;
        payoffTotal = Math.round(monthlyPayment * term * 12 *100.0)/100.0;
        endYear = startYear + term;
        if(startMonth.equals("January")){
            endYear = endYear - 1;
        }
        endMonth = calculateMonth();
    }

    private String calculateMonth() {
        String payoffMonth = "";
        switch(startMonth){
            case "January": payoffMonth = "December";
                break;

            case "February": payoffMonth = "January";
                break;

            case "March": payoffMonth = "February";
                break;

            case "April": payoffMonth = "March";
                break;

            case "May": payoffMonth = "April";
                break;

            case "June": payoffMonth = "May";
                break;

            case "July": payoffMonth = "June";
                break;

            case "August": payoffMonth = "July";
                break;

            case "September": payoffMonth = "August";
                break;

            case "October": payoffMonth = "September";
                break;

            case "November": payoffMonth = "October";
                break;

            case "December": payoffMonth = "November";
                break;

        }
        return payoffMonth;
    }

    private double calculateMonthlyPayment() {

        // Convert interest rate into a decimal
        // eg. 6.5% = 0.065

        double interestRate = interest / 100.0;

        // Monthly interest rate
        // is the yearly rate divided by 12

        double monthlyRate = interestRate / 12.0;

        // The length of the term in months
        // is the number of years times 12

        int termInMonths = term * 12;

        // Calculate the monthly payment
        // Typically this formula is provided so
        // we won't go into the details

        // The Math.pow() method is used
        // to calculate values raised to a power

        double monthlyPayment =
                (mortgage*monthlyRate) /
                        (1-Math.pow(1+monthlyRate, -termInMonths));

        return monthlyPayment;
    }
}
