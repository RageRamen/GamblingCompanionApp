package com.honeybadger.gamblingbadger.gamesession.roulette.roulettebet;

import java.math.BigDecimal;

public class OddEvenBet extends RouletteBet {
    // CONSTANTS:

    // VARIABLES:

    private boolean isOdd;

    // CONSTRUCTOR:

    public OddEvenBet(BigDecimal bet, boolean isOdd) {
        super(bet);
        this.winPayout = 0;
        this.winChance = 0;

        this.isOdd = isOdd;
    }

    // METHODS:


    @Override
    public boolean testResult(int result) {
        return false;
    }
}
