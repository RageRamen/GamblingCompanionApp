package com.honeybadger.gamblingbadger.gamesession.roulette.roulettebet;

import java.math.BigDecimal;

public class OddEvenBet extends RouletteBet {
    // CONSTANTS:

    private static final float WIN_CHANCE = 18f / 38f;

    // VARIABLES:

    private final boolean isOdd;

    // CONSTRUCTOR:

    public OddEvenBet(BigDecimal bet, boolean isOdd) {
        super(bet);
        this.winPayout = 1;
        this.winChance = WIN_CHANCE;

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
        return isOdd == ResultHelper.isOdd(result);
    }
}
