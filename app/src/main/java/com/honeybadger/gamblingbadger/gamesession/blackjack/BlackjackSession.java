package com.honeybadger.gamblingbadger.gamesession.blackjack;

import com.honeybadger.gamblingbadger.casinosession.CasinoSession;
import com.honeybadger.gamblingbadger.gamesession.*;

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
