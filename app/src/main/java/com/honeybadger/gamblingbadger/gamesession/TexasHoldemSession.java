package com.honeybadger.gamblingbadger.gamesession;

import com.honeybadger.gamblingbadger.casinosession.CasinoSession;

public class TexasHoldemSession extends GameSession {

    // Constructor

    public TexasHoldemSession(CasinoSession casinoSession) {
        super(casinoSession, EGameType.GT_TEXAS_HOLDEM);
    }
}
