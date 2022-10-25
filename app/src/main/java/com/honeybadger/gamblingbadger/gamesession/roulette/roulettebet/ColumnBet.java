package com.honeybadger.gamblingbadger.gamesession.roulette.roulettebet;

import java.math.BigDecimal;

public class ColumnBet extends RouletteBet {
    // CONSTANTS:

    // VARIABLES:

    private final int columnNum;

    // CONSTRUCTOR:

    public ColumnBet(BigDecimal bet, int columnNum) {
        super(bet);
        this.winPayout = 0;
        this.winChance = 0;

        this.columnNum = columnNum;
    }

    // METHODS:

    public int getColumnNum() {
        return columnNum;
    }

    @Override
    public boolean testResult(int result) {
        if (columnNum == 1)
            return ResultHelper.isFirstColumn(result);
        if (columnNum == 2)
            return ResultHelper.isSecondColumn(result);
        if (columnNum == 3)
            return ResultHelper.isThirdColumn(result);

        return false;
    }
}
