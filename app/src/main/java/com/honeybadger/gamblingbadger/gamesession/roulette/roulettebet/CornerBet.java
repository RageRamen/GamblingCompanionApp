package com.honeybadger.gamblingbadger.gamesession.roulette.roulettebet;

import java.math.BigDecimal;

public class CornerBet extends RouletteBet {
    // CONSTANTS:

    // VARIABLES:

    private final int topLeftNum;

    // CONSTRUCTOR:

    public CornerBet(BigDecimal bet, int topLeftNum) {
        super(bet);
        this.winPayout = 0;
        this.winChance = 0;

        this.topLeftNum = topLeftNum;
    }

    // METHODS:

    public int getTopLeftNum() {
        return topLeftNum;
    }

    public int[] getNumbers() {
        return new int[]{
                topLeftNum,
                topLeftNum+1,
                topLeftNum+3,
                topLeftNum+4
        };
    }

    @Override
    public boolean testResult(int result) {
        int[] numbers = getNumbers();

        for (int num : numbers) {
            if (num == result)
                return true;
        }

        return false;
    }
}
