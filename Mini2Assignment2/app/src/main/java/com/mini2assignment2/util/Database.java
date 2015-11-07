package com.mini2assignment2.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by xlin2 on 2015/11/3.
 */
public class Database extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "calc.db";
    private static final int DATABASE_VERSION = 1;
    private SQLCommand cmd;

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        cmd = new SQLCommand();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // execute sql command to create two tables
        db.execSQL(cmd.createTablePayoff());
        db.execSQL(cmd.createTablePayment());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    /**
     * Insert payoff date into database
     */
    public void insertPayoffDate(int startMonth, int startYear, int totalYear, String payoffDate) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("start_month", startMonth);
        values.put("start_year", startYear);
        values.put("total_year", totalYear);
        values.put("payoff", payoffDate);
        db.insert(cmd.getPayoffTableName(), null, values);
    }

    /**
     * Insert total payment and monthly payment into database
     */
    public void insertPayment(double purPrice, double dowPayment, int term, double intRate,
                              int tax, int insurance, String mPayment, String tPayment) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("pur_price", purPrice);
        values.put("dow_payment", dowPayment);
        values.put("term", term);
        values.put("int_rate", intRate);
        values.put("tax", tax);
        values.put("insurance", insurance);
        values.put("monthly_pay", mPayment);
        values.put("total_pay", tPayment);

        db.insert(cmd.getPaymentTableName(), null, values);
    }
}
