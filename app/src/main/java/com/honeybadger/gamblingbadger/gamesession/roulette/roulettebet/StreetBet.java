package com.honeybadger.gamblingbadger.gamesession.roulette.roulettebet;

import java.math.BigDecimal;

public class StreetBet extends RouletteBet {
    // CONSTANTS:

    // VARIABLES:

    private int firstNum;

    // CONSTRUCTOR:

    public StreetBet(BigDecimal bet, int firstNum) {
        super(bet);
        this.winPayout = 0;
        this.winChance = 0;

        this.firstNum = firstNum;
    }

    // METHODS:


    @Override
    public boolean testResult(int result) {
        return false;
    }
}
