package com.honeybadger.gamblingbadger.gamesession.roulette.roulettebet;

import java.math.BigDecimal;

public abstract class RouletteBet {
    // CONSTANTS:

    // VARIABLES:

    protected BigDecimal bet;
    protected int winPayout = 0;
    protected float winChance = 0;

    // CONSTRUCTOR:

    protected RouletteBet(BigDecimal bet) {
        this.bet = bet;
    }

    // PROPERTIES:

    public BigDecimal getBetValue() {
        return this.bet;
    }

    public int getWinPayout() {
        return this.winPayout;
    }

    public float getWinChance() {
        return this.winChance;
    }

    // METHODS:

    public float getExpectedValue() {
        return winChance * ((winPayout - 1) * bet.floatValue());
    }

    public abstract boolean testResult(int result);
}
