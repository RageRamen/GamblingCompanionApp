package com.honeybadger.gamblingbadger.gamesession.roulette.roulettebet;

import java.math.BigDecimal;

public class SplitBet extends RouletteBet {
    // CONSTANTS:

    // VARIABLES:

    private int[] numbers = new int[2];

    // CONSTRUCTOR:

    public SplitBet(BigDecimal bet, int num1, int num2) {
        super(bet);
        this.winPayout = 0;
        this.winChance = 0;

        this.numbers[0] = num1;
        this.numbers[1] = num2;
    }

    public SplitBet(BigDecimal bet, int[] numbers) {
        this(bet, numbers[0], numbers[1]);
    }

    // METHODS:


    @Override
    public boolean testResult(int result) {
        return false;
    }
}
