package com.honeybadger.gamblingbadger.gamesession.roulette.roulettebet;

import java.math.BigDecimal;

public class TwelveBet extends RouletteBet {
    // CONSTANTS:

    // VARIABLES:

    private int twelveNum;

    // CONSTRUCTOR:

    public TwelveBet(BigDecimal bet, int twelveNum) {
        super(bet);
        this.winPayout = 0;
        this.winChance = 0;

        this.twelveNum = twelveNum;
    }

    // METHODS:


    @Override
    public boolean testResult(int result) {
        return false;
    }
}
