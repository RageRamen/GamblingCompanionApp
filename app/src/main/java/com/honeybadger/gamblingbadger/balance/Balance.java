package com.honeybadger.gamblingbadger.balance;

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

    public EBalanceMsg addToBalance(BigDecimal amount) {
        BigDecimal ONE_HUNDRED = new BigDecimal(100);
        BigDecimal result = this.currentBalance.add(amount);
        BigDecimal percentage = result.multiply(ONE_HUNDRED).divide(this.currentBalance, RoundingMode.CEILING);
        BigDecimal percent_10 = new BigDecimal(10);
        BigDecimal percent_25 = new BigDecimal(25);
        BigDecimal percent_50 = new BigDecimal(50);
        this.currentBalance = result;


        if(result.signum() < 0){ //if result after adding amount to curr. bal is negative
            return EBalanceMsg.BM_NEGATIVE_BALANCE;
        }
        //if result after adding amount to curr bal. is less than 10%
        else if(percentage.compareTo(percent_10) < 0){
            return EBalanceMsg.BM_10_PERCENT;
        }
        //if result after adding amount to curr bal. is less than 25%
        else if (percentage.compareTo(percent_25) < 0){
            return EBalanceMsg.BM_25_PERCENT;
        }
        //if result after adding amount to curr bal. is less than 50%
        else if(percentage.compareTo(percent_50) < 0){
            return EBalanceMsg.BM_50_PERCENT;
        }
        else
        return EBalanceMsg.BM_NONE;
    }

    public EBalanceMsg subFromBalance(BigDecimal amount) {
        BigDecimal ONE_HUNDRED = new BigDecimal(100);
        BigDecimal result = this.currentBalance.subtract(amount);
        BigDecimal percentage = result.multiply(ONE_HUNDRED).divide(this.currentBalance, RoundingMode.CEILING);
        BigDecimal percent_10 = new BigDecimal(10);
        BigDecimal percent_25 = new BigDecimal(25);
        BigDecimal percent_50 = new BigDecimal(50);
        this.currentBalance = result;

        if(result.signum() < 0){ //if result after adding amount to curr. bal is negative
            return EBalanceMsg.BM_NEGATIVE_BALANCE;
        }
        //if result after adding amount to curr bal. is less than 10%
        else if(percentage.compareTo(percent_10) < 0){
            return EBalanceMsg.BM_10_PERCENT;
        }
        //if result after adding amount to curr bal. is less than 25%
        else if (percentage.compareTo(percent_25) < 0){
            return EBalanceMsg.BM_25_PERCENT;
        }
        //if result after adding amount to curr bal. is less than 50%
        else if(percentage.compareTo(percent_50) < 0){
            return EBalanceMsg.BM_50_PERCENT;
        }
        else
            return EBalanceMsg.BM_NONE;
    }

    // Info Methods:

    public BigDecimal getChangeInBalance() {
        return this.currentBalance.subtract(this.startingBalance);
    }
}
