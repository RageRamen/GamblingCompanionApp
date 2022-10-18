package com.honeybadger.gamblingbadger.gamesession;

import java.time.Duration;

import com.honeybadger.gamblingbadger.casinosession.CasinoSession;

public abstract class GameSession {
    // Variables:

    private CasinoSession casinoSession;

    protected EGameType gameType;

    // Constructor:

    protected GameSession(CasinoSession casinoSession, EGameType gameType) {
        this.casinoSession = casinoSession;
        this.gameType = gameType;
    }

    // Properties:

    public EGameType getGameType() {
        return this.gameType;
    }

    // Action Methods:

    // Info Methods:

    public Duration getTimeSpentPlaying() {
        // TODO
        return Duration.ZERO;
    }

    public float getAverageLuck() {
        // TODO
        return 0f;
    }
}
