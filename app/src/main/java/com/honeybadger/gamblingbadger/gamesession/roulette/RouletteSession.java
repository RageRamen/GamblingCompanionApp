package com.honeybadger.gamblingbadger.gamesession.roulette;

import com.honeybadger.gamblingbadger.casinosession.CasinoSession;
import com.honeybadger.gamblingbadger.gamesession.*;
import com.honeybadger.gamblingbadger.statistics.LongListOfData;

public class RouletteSession extends GameSession {

    // Constructor

    public RouletteSession(CasinoSession casinoSession) {
        super(casinoSession, EGameType.GT_ROULETTE);
    }

    // Info Methods:

    @Override
    public LongListOfData getStatistics() {
        return new LongListOfData();
    }
}
