package com.honeybadger.gamblingbadger.gamesession.roulette.roulettebet;

import java.math.BigDecimal;

public class HighLowBet extends RouletteBet {
    // CONSTANTS:

    private static final float WIN_CHANCE = 18f / 38f;

    // VARIABLES:

    private final boolean isHigh;

    // CONSTRUCTOR:

    public HighLowBet(BigDecimal bet, boolean isHigh) {
        super(bet);
        this.winPayout = 1;
        this.winChance = WIN_CHANCE;

        this.isHigh = isHigh;
    }

    // METHODS:

    public boolean isHigh() {
        return isHigh;
    }

    public boolean isLow() {
        return !isHigh;
    }

    @Override
    public boolean testResult(int result) {
        return isHigh == ResultHelper.isGreaterHalf(result);
    }
}
