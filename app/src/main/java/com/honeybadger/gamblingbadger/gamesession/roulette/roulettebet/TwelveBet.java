package com.honeybadger.gamblingbadger.gamesession.roulette.roulettebet;

import java.math.BigDecimal;

public class TwelveBet extends RouletteBet {
    // CONSTANTS:

    private static final float WIN_CHANCE = 12f / 38f;

    // VARIABLES:

    private final int twelveNum;

    // CONSTRUCTOR:

    public TwelveBet(BigDecimal bet, int twelveNum) {
        super(bet);
        this.winPayout = 2;
        this.winChance = WIN_CHANCE;

        this.twelveNum = twelveNum;
    }

    // METHODS:

    public int getTwelveNum() {
        return twelveNum;
    }

    @Override
    public boolean testResult(int result) {
        if (twelveNum == 1)
            return ResultHelper.isFirstTwelve(result);
        if (twelveNum == 2)
            return ResultHelper.isSecondTwelve(result);
        if (twelveNum == 3)
            return ResultHelper.isThirdTwelve(result);

        return false;
    }
}
