package com.mini2assignment2.ui;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.mini2assignment2.R;
import com.mini2assignment2.exception.MyException;
import com.mini2assignment2.model.MortgageCalc;
import com.mini2assignment2.util.Database;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    // all the views
    private EditText purPriceVal;
    private EditText dowPaymentVal;
    private EditText morTermVal;
    private EditText intRateVal;
    private EditText proTaxVal;
    private EditText proInsurVal;
    private EditText payDateVal;
    private Button changeDateBtn;
    private Button calculateBtn;

    // values in the edittext
    private double purPrice;
    private double dowPayment;
    private int morTerm;
    private double intRate;
    private int proTax;
    private int proInsur;
    private int startYear;
    private int startMonth;

    // year and month user has chosen
    private int year;
    private int month;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
        addListeners();
    }

    /**
     * Get instances of views
     */
    private void findViews() {
        purPriceVal = (EditText) findViewById(R.id.pur_price_val);
        dowPaymentVal = (EditText) findViewById(R.id.dow_payment_val);
        morTermVal = (EditText) findViewById(R.id.mor_term_val);
        intRateVal = (EditText) findViewById(R.id.int_rate_val);
        proTaxVal = (EditText) findViewById(R.id.pro_tax_val);
        proInsurVal = (EditText) findViewById(R.id.pro_insur_val);
        payDateVal = (EditText) findViewById(R.id.pay_date_val);
        changeDateBtn = (Button) findViewById(R.id.change_date_btn);
        calculateBtn = (Button) findViewById(R.id.submit_btn);

        setDate();
    }

    /**
     * Set date value according to user's operation to the date picker
     */
    private void setDate() {
        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);

        payDateVal.setText(new StringBuilder().append(year).append("-").append(month + 1));
    }

    /**
     * Add listeners to the buttons
     */
    private void addListeners() {
        changeDateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(1);
            }
        });

        calculateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateMortgage();
            }
        });

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case 1:
                // set date picker as current date
                return new DatePickerDialog(this, datePickerListener, year, month, 1);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener datePickerListener
            = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, below method will be called.
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            year = selectedYear;
            month = selectedMonth;

            // set selected date into edittext
            payDateVal.setText(new StringBuilder().append(year).append("-").append(month + 1));
        }
    };

    /**
     * Do calculation and store the results into database
     */
    private void calculateMortgage() {
        // prepare for calculation
        MortgageCalc calc = new MortgageCalc();
        Database db = new Database(this);
        if (!getFormValues()) return;

        // do calculation
        String payoffDate = calc.getPayoffDate(startMonth, startYear, morTerm);
        String monthlyPayment = calc.getMonthlyPayment(purPrice, dowPayment, morTerm, intRate,
                                                        proTax, proInsur);
        String totalPayment = calc.getTotalPayment(purPrice, dowPayment, morTerm, intRate,
                                                    proTax, proInsur);
        showResult(payoffDate, monthlyPayment, totalPayment);

        // store results
        db.insertPayoffDate(startMonth, startYear, morTerm, payoffDate);
        db.insertPayment(purPrice, dowPayment, morTerm, intRate,
                         proTax, proInsur, monthlyPayment, totalPayment);
    }

    /**
     * Get values user has input into the edittext
     */
    private boolean getFormValues() {
        try {
            purPrice = Double.parseDouble(purPriceVal.getText().toString());
            dowPayment = Double.parseDouble(dowPaymentVal.getText().toString());
            morTerm = Integer.parseInt(morTermVal.getText().toString());
            intRate = Double.parseDouble(intRateVal.getText().toString());
            proTax = Integer.parseInt(proTaxVal.getText().toString());
            proInsur = Integer.parseInt(proInsurVal.getText().toString());
            startYear = Integer.parseInt(payDateVal.getText().toString().split("-")[0]);
            startMonth = Integer.parseInt(payDateVal.getText().toString().split("-")[1]);
        } catch (Exception e) { // input invalid
            new MyException().handle();
            showError();
            return false;
        }
        return true;
    }

    /**
     * Show error in the dialog;
     */
    private void showError() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        String message = "Input Error! Please input again.";
        builder.setTitle("Error");
        builder.setMessage(message);
        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    /**
     * Show the result to users
     */
    private void showResult(String payoffDate, String monthlyPayment, String totalPayment) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        String message = new StringBuilder().append("Payoff Date is: ").append(payoffDate).append("\n")
                                            .append("Monthly payment is: ").append(monthlyPayment).append(" $\n")
                                            .append("Total payment is: ").append(totalPayment).append(" $\n")
                                            .toString();
        builder.setTitle("Result");
        builder.setMessage(message);
        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

}
