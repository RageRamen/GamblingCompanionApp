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

    public int getFirstNumber() {
        return firstNumber;
    }

    public int[] getNumbers() {
        return new int[]{
                firstNumber,
                firstNumber+1,
                firstNumber+2,
                firstNumber+3,
                firstNumber+4,
                firstNumber+5
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
