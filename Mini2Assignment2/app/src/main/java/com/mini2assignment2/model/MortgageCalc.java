package com.mini2assignment2.model;

/**
 * Created by xlin2 on 2015/11/2.
 */
public class MortgageCalc {
    /**
     * Calculate payoff date according to start and end dates
     */
    public String getPayoffDate(int startMonth, int startYear, int totalYear) {
        StringBuilder builder = new StringBuilder();

        int endYear = startYear + totalYear;
        int endMonth = startMonth;

        // subtract one month
        if (startMonth == 1) {
            endMonth = 12;
            --endYear;
        }
        else {
            --endMonth;
        }

        return builder.append(endYear).append("-").append(endMonth).toString();
    }

    /**
     * Calculate and return monthly payment
     */
    public String getMonthlyPayment(double purPrice, double dowPayment, int term, double rate,
                                    int tax, int insurance) {
        double ret;
        double monthlyRate = rate / 12 / 100;
        double restPrice = purPrice * (100 - dowPayment) / 100;

        ret = (restPrice * monthlyRate) / (1 - Math.pow(1 + monthlyRate, term * -12));
        ret += (double)tax / 12;
        ret += (double)insurance / 12;

        return String.format("%.2f", ret);
    }

    /**
     * Calculate and return total payment
     */
    public String getTotalPayment(double purPrice, double dowPayment, int term, double rate,
                                  int tax, int insurance) {
        double ret;
        double monthlyRate = rate / 12 / 100;
        double restPrice = purPrice * (100 - dowPayment) / 100;

        ret = restPrice / ((1 - (1 / Math.pow(1 + monthlyRate, term * 12))) / monthlyRate) * term * 12;
        ret += tax * term;
        ret += insurance * term;

        return String.format("%.2f", ret);
    }
}
