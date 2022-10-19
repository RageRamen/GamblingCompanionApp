package com.honeybadger.gamblingbadger.gamesession.poker;

import com.honeybadger.gamblingbadger.casinosession.CasinoSession;
import com.honeybadger.gamblingbadger.gamesession.*;

public class PokerSession extends GameSession {

    // Constructor

    public PokerSession(CasinoSession casinoSession) {
        super(casinoSession, EGameType.GT_POKER);
    }

    // Info Methods

    @Override
    public float getAverageLuck() {
        return 0;
    }
}
