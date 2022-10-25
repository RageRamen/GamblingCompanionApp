package com.honeybadger.gamblingbadger.gamesession.roulette.roulettebet;

import java.math.BigDecimal;

public class CornerBet extends RouletteBet {
    // CONSTANTS:

    // VARIABLES:

    private int topLeftNum;

    // CONSTRUCTOR:

    public CornerBet(BigDecimal bet, int topLeftNum) {
        super(bet);
        this.winPayout = 0;
        this.winChance = 0;

        this.topLeftNum = topLeftNum;
    }

    // METHODS:


    @Override
    public boolean testResult(int result) {
        return false;
    }
}
