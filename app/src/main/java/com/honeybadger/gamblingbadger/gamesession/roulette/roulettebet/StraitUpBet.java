package com.honeybadger.gamblingbadger.gamesession.roulette.roulettebet;

import java.math.BigDecimal;

public class StraitUpBet extends RouletteBet {
    // CONSTANTS:

    // VARIABLES:

    private final int number;

    // CONSTRUCTOR:

    public StraitUpBet(BigDecimal bet, int number) {
        super(bet);
        this.winPayout = 0;
        this.winChance = 0;

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
