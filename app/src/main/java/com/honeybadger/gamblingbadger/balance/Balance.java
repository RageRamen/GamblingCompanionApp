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

        final BigDecimal P10_THRESHOLD = startingBalance.multiply(new BigDecimal(0.10f));
        final BigDecimal P25_THRESHOLD = startingBalance.multiply(new BigDecimal(0.25f));
        final BigDecimal P50_THRESHOLD = startingBalance.multiply(new BigDecimal(0.50f));

        boolean negativeEligible = currentBalance.compareTo(BigDecimal.ZERO) > 0;
        boolean p10Eligible = currentBalance.compareTo(P10_THRESHOLD) > 0;
        boolean p25Eligible = currentBalance.compareTo(P25_THRESHOLD) > 0;
        boolean p50Eligible = currentBalance.compareTo(P50_THRESHOLD) > 0;

        BigDecimal result = currentBalance.subtract(amount);

        boolean negativeGuilty = result.compareTo(BigDecimal.ZERO) <= 0;
        boolean p10Guilty = result.compareTo(P10_THRESHOLD) <= 0;
        boolean p25Guilty = result.compareTo(P25_THRESHOLD) <= 0;
        boolean p50Guilty = result.compareTo(P50_THRESHOLD) <= 0;

        if(negativeEligible && negativeGuilty) {
            // If the balance was positive and is now negative:
            showNegativeAlert(activity);
            msg = EBalanceMsg.BM_NEGATIVE_BALANCE;
        }
        else if(p10Eligible && p10Guilty) {
            // If the balance was above 10% and is now below 10%
            show10PAlert(activity);
            msg = EBalanceMsg.BM_10_PERCENT;
        }
        else if (p25Eligible && p25Guilty) {
            // If the balance was above 25% and is now below 25%
            show25PAlert(activity);
            msg = EBalanceMsg.BM_25_PERCENT;
        }
        else if(p50Eligible && p50Guilty) {
            // If the balance was above 50% and is now below 50%
            show50PAlert(activity);
            msg = EBalanceMsg.BM_50_PERCENT;
        }

        this.currentBalance = this.currentBalance.subtract(amount);

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
        alert.setMessage("Please trust your past self and refrain from playing. Future you will appreciate it.");
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
        alert.setMessage("Your balance is now < 50% of your starting balance.");
        alert.setNeutralButton("Ok", null);
        alert.show();
    }
}
