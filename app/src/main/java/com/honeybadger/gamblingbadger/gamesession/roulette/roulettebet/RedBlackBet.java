package com.honeybadger.gamblingbadger.gamesession.roulette.roulettebet;

import java.math.BigDecimal;

public class RedBlackBet extends RouletteBet {
    // CONSTANTS:

    // VARIABLES:

    private final boolean isRed;

    // CONSTRUCTOR:

    public RedBlackBet(BigDecimal bet, boolean isRed) {
        super(bet);
        this.winPayout = 0;
        this.winChance = 0;

        this.isRed = isRed;
    }

    // METHODS:

    public boolean isRed() {
        return isRed;
    }

    public boolean isBlack() {
        return !isRed;
    }

    @Override
    public boolean testResult(int result) {
        return isRed == RouletteResultHelper.isRed(result);
    }
}
