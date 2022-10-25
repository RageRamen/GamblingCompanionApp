package com.honeybadger.gamblingbadger.gamesession.roulette.roulettebet;

import java.math.BigDecimal;

public class ColumnBet extends RouletteBet {
    // CONSTANTS:

    // VARIABLES:

    private int columnNum;

    // CONSTRUCTOR:

    public ColumnBet(BigDecimal bet, int columnNum) {
        super(bet);
        this.winPayout = 0;
        this.winChance = 0;

        this.columnNum = columnNum;
    }

    // METHODS:


    @Override
    public boolean testResult(int result) {
        return false;
    }
}
