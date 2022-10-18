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

    // Action Methods:

    public EBalanceMsg addToBalance(BigDecimal amount) {
        // TODO
        return EBalanceMsg.BM_NONE;
    }

    public EBalanceMsg subFromBalance(BigDecimal amount) {
        // TODO
        return EBalanceMsg.BM_NONE;
    }

    // Info Methods:

    public BigDecimal getChangeInBalance() {
        return this.currentBalance.subtract(this.startingBalance);
    }
}
