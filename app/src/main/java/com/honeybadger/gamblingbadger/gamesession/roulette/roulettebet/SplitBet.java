package com.honeybadger.gamblingbadger.gamesession.roulette.roulettebet;

import java.math.BigDecimal;

public class SplitBet extends RouletteBet {
    // CONSTANTS:

    private static final float WIN_CHANCE = 2f / 38f;

    // VARIABLES:

    private final int[] numbers = new int[2];

    // CONSTRUCTOR:

    public SplitBet(BigDecimal bet, int num1, int num2) {
        super(bet);
        this.winPayout = 17;
        this.winChance = WIN_CHANCE;

        this.numbers[0] = num1;
        this.numbers[1] = num2;
    }

    public SplitBet(BigDecimal bet, int[] numbers) {
        this(bet, numbers[0], numbers[1]);
    }

    // METHODS:

    public int[] getNumbers() {
        return numbers.clone();
    }

    @Override
    public boolean testResult(int result) {
        return (numbers[0] == result) || (numbers[1] == result);
    }
}
