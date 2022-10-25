package com.honeybadger.gamblingbadger.gamesession.roulette.roulettebet;

import java.math.BigDecimal;

public class OddEvenBet extends RouletteBet {
    // CONSTANTS:

    // VARIABLES:

    private final boolean isOdd;

    // CONSTRUCTOR:

    public OddEvenBet(BigDecimal bet, boolean isOdd) {
        super(bet);
        this.winPayout = 0;
        this.winChance = 0;

        this.isOdd = isOdd;
    }

    // METHODS:

    public boolean isOdd() {
        return isOdd;
    }

    public boolean isEven() {
        return !isOdd;
    }

    @Override
    public boolean testResult(int result) {
        return isOdd == RouletteResultHelper.isOdd(result);
    }
}
