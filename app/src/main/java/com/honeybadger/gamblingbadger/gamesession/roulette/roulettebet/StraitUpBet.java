package com.honeybadger.gamblingbadger.gamesession.roulette.roulettebet;

import java.math.BigDecimal;

public class StraitUpBet extends RouletteBet {
    // CONSTANTS:

    private static final float WIN_CHANCE = 1f / 38f;

    // VARIABLES:

    private final int number;

    // CONSTRUCTOR:

    public StraitUpBet(BigDecimal bet, int number) {
        super(bet);
        this.winPayout = 35;
        this.winChance = WIN_CHANCE;

        this.number = number;
    }

    // METHODS:

    public int getNumber() {
        return number;
    }

    @Override
    public boolean testResult(int result) {
        return number == result;
    }
}
