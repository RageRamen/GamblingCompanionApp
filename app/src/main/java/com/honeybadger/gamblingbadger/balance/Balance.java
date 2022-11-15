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
        // TODO
        this.currentBalance.add(amount);
        return EBalanceMsg.BM_NONE;
    }

    public EBalanceMsg subFromBalance(BigDecimal amount) {
        // TODO
        this.currentBalance.subtract(amount);
        return EBalanceMsg.BM_NONE;
    }

    // Info Methods:

    public BigDecimal getChangeInBalance() {
        return this.currentBalance.subtract(this.startingBalance);
    }
}
