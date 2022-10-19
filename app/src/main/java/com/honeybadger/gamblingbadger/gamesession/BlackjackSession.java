package com.honeybadger.gamblingbadger.gamesession;

import com.honeybadger.gamblingbadger.casinosession.CasinoSession;

public class BlackjackSession extends GameSession {

    // Constructor

    public BlackjackSession(CasinoSession casinoSession) {
        super(casinoSession, EGameType.GT_BLACKJACK);
    }

    // Info Methods:

    @Override
    public float getAverageLuck() {
        return 0;
    }
}
