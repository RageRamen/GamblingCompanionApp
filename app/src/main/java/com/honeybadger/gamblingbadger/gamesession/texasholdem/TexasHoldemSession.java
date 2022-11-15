package com.honeybadger.gamblingbadger.gamesession.texasholdem;

import com.honeybadger.gamblingbadger.casinosession.CasinoSession;
import com.honeybadger.gamblingbadger.gamesession.*;
import com.honeybadger.gamblingbadger.statistics.LongListOfData;

public class TexasHoldemSession extends GameSession {

    // Constructor

    public TexasHoldemSession(CasinoSession casinoSession) {
        super(casinoSession, EGameType.GT_TEXAS_HOLDEM);
    }

    // Info Methods:

    @Override
    public LongListOfData getStatistics() {
        return new LongListOfData();
    }
}
