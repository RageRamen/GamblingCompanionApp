package com.honeybadger.gamblingbadger.gamesession.roulette.roulettebet;

import java.math.BigDecimal;

public class StreetBet extends RouletteBet {
    // CONSTANTS:

    // VARIABLES:

    private final int firstNum;

    // CONSTRUCTOR:

    public StreetBet(BigDecimal bet, int firstNum) {
        super(bet);
        this.winPayout = 0;
        this.winChance = 0;

        this.firstNum = firstNum;
    }

    // METHODS:

    public int getFirstNum() {
        return firstNum;
    }

    public int[] getNumbers() {
        return new int[]{firstNum, firstNum+1, firstNum+2};
    }

    @Override
    public boolean testResult(int result) {
        return (firstNum == result) || ((firstNum+1) == result) || ((firstNum+2) == result);
    }
}
