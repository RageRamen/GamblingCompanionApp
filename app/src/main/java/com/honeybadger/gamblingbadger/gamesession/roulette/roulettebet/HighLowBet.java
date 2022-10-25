package com.honeybadger.gamblingbadger.gamesession.roulette.roulettebet;

import java.math.BigDecimal;

public class HighLowBet extends RouletteBet {
    // CONSTANTS:

    // VARIABLES:

    private final boolean isHigh;

    // CONSTRUCTOR:

    public HighLowBet(BigDecimal bet, boolean isHigh) {
        super(bet);
        this.winPayout = 0;
        this.winChance = 0;

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
