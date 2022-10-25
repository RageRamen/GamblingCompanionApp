package com.honeybadger.gamblingbadger.gamesession.roulette.roulettebet;

import java.math.BigDecimal;

public class LineBet extends RouletteBet {
    // CONSTANTS:

    // VARIABLES:

    private int firstNumber;

    // CONSTRUCTOR:

    public LineBet(BigDecimal bet, int firstNumber) {
        super(bet);
        this.winPayout = 0;
        this.winChance = 0;

        this.firstNumber = firstNumber;
    }

    // METHODS:


    @Override
    public boolean testResult(int result) {
        return false;
    }
}
