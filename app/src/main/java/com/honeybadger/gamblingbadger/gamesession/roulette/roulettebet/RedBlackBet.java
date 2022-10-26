package com.honeybadger.gamblingbadger.gamesession.roulette.roulettebet;

import java.math.BigDecimal;

public class RedBlackBet extends RouletteBet {
    // CONSTANTS:

    private static final float WIN_CHANCE = 18f / 38f;

    // VARIABLES:

    private final boolean isRed;

    // CONSTRUCTOR:

    public RedBlackBet(BigDecimal bet, boolean isRed) {
        super(bet);
        this.winPayout = 1;
        this.winChance = WIN_CHANCE;

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
        return isRed == ResultHelper.isRed(result);
    }
}
