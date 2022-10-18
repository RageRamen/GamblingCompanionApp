package com.honeybadger.gamblingbadger.gamesession;

import com.honeybadger.gamblingbadger.casinosession.CasinoSession;

public class AmericanRouletteSession extends RouletteSession {

    // Constructor

    public AmericanRouletteSession(CasinoSession casinoSession) {
        super(casinoSession);
        this.gameType = EGameType.GT_AMERICAN_ROULETTE;
    }
}
