package com.honeybadger.gamblingbadger.balance;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

import com.honeybadger.gamblingbadger.R;
import com.honeybadger.gamblingbadger.casinosession.CasinoSessionFragment;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Balance {
    // Variables:

    private final BigDecimal startingBalance;
    private BigDecimal currentBalance;

    // Constructor:

    public Balance(BigDecimal startingBalance) {
        this.startingBalance = startingBalance;
        this.currentBalance = this.startingBalance;
    }

    // Properties:

    public BigDecimal getCurrentBalance() {
        return this.currentBalance;
    }

    // Action Methods:

    public EBalanceMsg addToBalance(BigDecimal amount, Activity activity) {
        if (amount.signum() < 0)
            return subFromBalance(amount.negate(), activity);

        EBalanceMsg msg = EBalanceMsg.BM_NONE;

        this.currentBalance = this.currentBalance.add(amount);

        try {
            CasinoSessionFragment.updateActionBarBalance(activity);
        }
        catch (Exception e) {
            showFailAlert(activity);
            msg = EBalanceMsg.BM_FAIL;
        }

        return msg;
    }

    public EBalanceMsg subFromBalance(BigDecimal amount, Activity activity) {
        if (amount.signum() < 0)
            return addToBalance(amount.negate(), activity);

        EBalanceMsg msg = EBalanceMsg.BM_NONE;

        BigDecimal result = this.currentBalance.subtract(amount);

        BigDecimal ONE_HUNDRED = new BigDecimal(100);
        BigDecimal percentage = result.multiply(ONE_HUNDRED).divide(this.startingBalance, RoundingMode.CEILING);
        BigDecimal percent_10 = new BigDecimal(10);
        BigDecimal percent_25 = new BigDecimal(25);
        BigDecimal percent_50 = new BigDecimal(50);
        this.currentBalance = result;

        if(result.signum() < 0){ //if result after adding amount to curr. bal is negative
            showNegativeAlert(activity);
            msg = EBalanceMsg.BM_NEGATIVE_BALANCE;
        }
        //if result after adding amount to curr bal. is less than 10%
        else if(percentage.compareTo(percent_10) < 0){
            show10PAlert(activity);
            msg = EBalanceMsg.BM_10_PERCENT;
        }
        //if result after adding amount to curr bal. is less than 25%
        else if (percentage.compareTo(percent_25) < 0){
            show25PAlert(activity);
            msg = EBalanceMsg.BM_25_PERCENT;
        }

        else if(percentage.compareTo(percent_50) == 0) {
            show50PAlert(activity);
            msg = EBalanceMsg.BM_50_PERCENT;
        }

        //if result after adding amount to curr bal. is less than 50%
        else if (percentage.compareTo(percent_50) < 0){
            showless50PAlert(activity);
            msg = EBalanceMsg.BM_50_PERCENT;
        }

        try {
            CasinoSessionFragment.updateActionBarBalance(activity);
        }
        catch (Exception e) {
            showFailAlert(activity);
            msg = EBalanceMsg.BM_FAIL;
        }

        return msg;
    }

    // Info Methods:

    public BigDecimal getChangeInBalance() {
        return this.currentBalance.subtract(this.startingBalance);
    }

    // ALERT MESSAGES:

    private void showFailAlert(Activity activity) {
        AlertDialog.Builder alert = new AlertDialog.Builder(activity);
        alert.setTitle("Balance Change Failed");
        alert.setMessage("Something wrong happened. Please don't be mad.");
        alert.setPositiveButton("I'm not mad", null);
        alert.setNegativeButton("I'm not mad, just disappointed", null);
        alert.show();
    }

    private void showNegativeAlert(Activity activity) {
        AlertDialog.Builder alert = new AlertDialog.Builder(activity);
        alert.setTitle("Balance has Gone Into the Negatives");
        alert.setMessage("It is advised to trust your past self and stop playing.");
        alert.setNeutralButton("Ok", null);
        alert.show();
    }

    private void show10PAlert(Activity activity) {
        AlertDialog.Builder alert = new AlertDialog.Builder(activity);
        alert.setTitle("Balance Warning");
        alert.setMessage("You balance is now < 10% of your starting balance.");
        alert.setNeutralButton("Ok", null);
        alert.show();
    }

    private void show25PAlert(Activity activity) {
        AlertDialog.Builder alert = new AlertDialog.Builder(activity);
        alert.setTitle("Balance Warning");
        alert.setMessage("Your balance is now < 25% of your starting balance.");
        alert.setNeutralButton("Ok", null);
        alert.show();
    }

    private void show50PAlert(Activity activity) {
        AlertDialog.Builder alert = new AlertDialog.Builder(activity);
        alert.setTitle("Balance Warning");
        alert.setMessage("Your balance is now 50% of your starting balance.");
        alert.setNeutralButton("Ok", null);
        alert.show();
    }

    private void showless50PAlert(Activity activity) {
        AlertDialog.Builder alert = new AlertDialog.Builder(activity);
        alert.setTitle("Balance Warning");
        alert.setMessage("Your balance is now < 50% of your starting balance.");
        alert.setNeutralButton("Ok", null);
        alert.show();
    }
}
