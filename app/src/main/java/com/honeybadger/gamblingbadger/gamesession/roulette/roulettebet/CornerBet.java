package com.honeybadger.gamblingbadger.gamesession.roulette.roulettebet;

import java.math.BigDecimal;

public class CornerBet extends RouletteBet {
    // CONSTANTS:

    private static final float WIN_CHANCE = 4f / 38f;

    // VARIABLES:

    private final int topLeftNum;

    // CONSTRUCTOR:

    public CornerBet(BigDecimal bet, int topLeftNum) {
        super(bet);
        this.winPayout = 8;
        this.winChance = WIN_CHANCE;

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
