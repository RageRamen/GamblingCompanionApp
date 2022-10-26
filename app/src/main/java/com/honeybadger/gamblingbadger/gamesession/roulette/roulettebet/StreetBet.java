package com.honeybadger.gamblingbadger.gamesession.roulette.roulettebet;

import java.math.BigDecimal;

public class StreetBet extends RouletteBet {
    // CONSTANTS:

    private static final float WIN_CHANCE = 3f / 38f;

    // VARIABLES:

    private final int firstNum;

    // CONSTRUCTOR:

    public StreetBet(BigDecimal bet, int firstNum) {
        super(bet);
        this.winPayout = 11;
        this.winChance = WIN_CHANCE;

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
