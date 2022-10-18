package com.honeybadger.gamblingbadger.gamesession;

import com.honeybadger.gamblingbadger.casinosession.CasinoSession;

public class PokerSession extends GameSession {

    // Constructor

    public PokerSession(CasinoSession casinoSession) {
        super(casinoSession, EGameType.GT_POKER);
    }
}
