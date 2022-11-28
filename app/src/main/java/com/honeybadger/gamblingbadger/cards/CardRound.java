package com.honeybadger.gamblingbadger.cards;

import java.math.BigDecimal;

public abstract class CardRound {
    // Variables

    protected final BigDecimal bet;
    protected boolean win;

    // Constructor

    protected CardRound(BigDecimal bet) {
        this.bet = bet;
    }

    // Properties

    public BigDecimal getBet() {
        return this.bet;
    }

    public boolean isWin() {
        return this.win;
    }

    public CardRound setWin(boolean win) {
        this.win = win;
        return this;
    }
}
