package com.honeybadger.gamblingbadger.gamesession;

import com.honeybadger.gamblingbadger.casinosession.CasinoSession;

public class RouletteSession extends GameSession {

    // Constructor

    public RouletteSession(CasinoSession casinoSession) {
        super(casinoSession, EGameType.GT_ROULETTE);
    }

    // Info Methods:

    @Override
    public float getAverageLuck() {
        return 0;
    }
}
