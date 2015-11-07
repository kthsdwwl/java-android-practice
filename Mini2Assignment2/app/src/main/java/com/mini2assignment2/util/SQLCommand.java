package com.mini2assignment2.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by xlin2 on 2015/11/3.
 */
public class SQLCommand {

    private final String TABLE_PAYOFF = "PAYOFF_DATE";
    private final String TABLE_PAYMENT = "PAYMENT";
    // sql command to create a table to store payoff date
    private final String CREATE_TABLE_PAYOFF = "CREATE TABLE IF NOT EXISTS PAYOFF_DATE(" +
                                                    "id int PRIMARY KEY, " +
                                                    "start_month int NOT NULL, " +
                                                    "start_year int NOT NULL, " +
                                                    "total_year int NOT NULL, " +
                                                    "payoff varchar(50) NOT NULL);";
    // sql command to create a table to store payment values
    private final String CREATE_TABLE_PAYMENT = "CREATE TABLE IF NOT EXISTS PAYMENT(" +
                                                     "id int PRIMARY KEY, " +
                                                     "pur_price decimal(10,2) NOT NULL, " +
                                                     "dow_payment decimal(5,2) NOT NULL, " +
                                                     "term int NOT NULL, " +
                                                     "int_rate decimal(5,2) NOT NULL, " +
                                                     "tax int NOT NULL, insurance int NOT NULL, " +
                                                     "monthly_pay varchar(50) NOT NULL, " +
                                                     "total_pay varchar(50) NOT NULL);";

    public SQLCommand() {
    }

    public String createTablePayoff() {
        return CREATE_TABLE_PAYOFF;
    }

    public String createTablePayment() {
        return CREATE_TABLE_PAYMENT;
    }

    public String getPayoffTableName() {
        return TABLE_PAYOFF;
    }

    public String getPaymentTableName() {
        return TABLE_PAYMENT;
    }
}
