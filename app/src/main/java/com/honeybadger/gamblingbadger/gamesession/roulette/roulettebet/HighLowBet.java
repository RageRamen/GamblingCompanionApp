package com.honeybadger.gamblingbadger.gamesession.roulette.roulettebet;

import java.math.BigDecimal;

public class HighLowBet extends RouletteBet {
    // CONSTANTS:

    // VARIABLES:

    private boolean isHigh;

    // CONSTRUCTOR:

    public HighLowBet(BigDecimal bet, boolean isHigh) {
        super(bet);
        this.winPayout = 0;
        this.winChance = 0;

        this.isHigh = isHigh;
    }

    // METHODS:


    @Override
    public boolean testResult(int result) {
        return false;
    }
}
