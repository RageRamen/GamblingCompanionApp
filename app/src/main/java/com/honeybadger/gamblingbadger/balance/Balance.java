package com.honeybadger.gamblingbadger.balance;

import java.math.BigDecimal;

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

    public BigDecimal getBalance() {
        return this.currentBalance;
    }

    // Action Methods:

    public EBalanceMsg addToBalance(BigDecimal amount) {
        BigDecimal result = this.currentBalance.add(amount);
        if(result.signum() < 0){ //if result after adding amount to curr. bal is negative
            return EBalanceMsg.BM_FAIL;
        }


        return EBalanceMsg.BM_NONE;
    }

    public EBalanceMsg subFromBalance(BigDecimal amount) {
        // TODO
        BigDecimal result = this.currentBalance.subtract(amount);

        return EBalanceMsg.BM_NONE;
    }

    // Info Methods:

    public BigDecimal getChangeInBalance() {
        return this.currentBalance.subtract(this.startingBalance);
    }
}
