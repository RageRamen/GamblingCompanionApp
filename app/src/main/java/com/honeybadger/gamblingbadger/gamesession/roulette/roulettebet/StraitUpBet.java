package com.honeybadger.gamblingbadger.gamesession.roulette.roulettebet;

import java.math.BigDecimal;

public class StraitUpBet extends RouletteBet {
    // CONSTANTS:

    // VARIABLES:

    private int number;

    // CONSTRUCTOR:

    public StraitUpBet(BigDecimal bet, int number) {
        super(bet);
        this.winPayout = 0;
        this.winChance = 0;

        this.number = number;
    }

    // METHODS:


    @Override
    public boolean testResult(int result) {
        return false;
    }
}
