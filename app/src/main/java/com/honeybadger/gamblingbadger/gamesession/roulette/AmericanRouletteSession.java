package com.honeybadger.gamblingbadger.gamesession.roulette;

import com.honeybadger.gamblingbadger.casinosession.CasinoSession;
import com.honeybadger.gamblingbadger.gamesession.*;

public class AmericanRouletteSession extends RouletteSession {

    // Constructor

    public AmericanRouletteSession(CasinoSession casinoSession) {
        super(casinoSession);
        this.gameType = EGameType.GT_AMERICAN_ROULETTE;
    }
}
