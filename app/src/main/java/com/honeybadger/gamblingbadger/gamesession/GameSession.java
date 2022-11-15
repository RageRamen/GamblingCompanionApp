package com.honeybadger.gamblingbadger.gamesession;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;

import com.honeybadger.gamblingbadger.casinosession.CasinoSession;
import com.honeybadger.gamblingbadger.statistics.LongListOfData;
import com.honeybadger.gamblingbadger.timer.Timer;

public abstract class GameSession {
    // CONSTANTS

    // Variables:

    private final CasinoSession casinoSession;

    protected EGameType gameType;

    private LocalDateTime sessionStartTime;
    private LocalDateTime sessionEndTime;

    // Constructor:

    protected GameSession(CasinoSession casinoSession, EGameType gameType) {
        this.casinoSession = casinoSession;
        this.gameType = gameType;
        this.sessionStartTime = Timer.getCurrentTime();
        this.sessionEndTime = LocalDateTime.MIN;
    }

    // Properties:

    public CasinoSession getCasinoSession() {
        return this.casinoSession;
    }

    public EGameType getGameType() {
        return this.gameType;
    }

    public LocalDateTime getSessionStartTime() {
        return this.sessionStartTime;
    }

    public LocalDateTime getSessionEndTime() {
        return this.sessionEndTime;
    }

    // Action Methods:

    public boolean closeSession() {
        boolean success = false;

        if (isSessionOpen()) {
            sessionEndTime = Timer.getCurrentTime();

            success = true;
        }

        return success;
    }

    // Info Methods:

    public boolean isSessionOpen() {
        return sessionEndTime == LocalDateTime.MIN;
    }

    public Duration getSessionDuration() {
        Duration deltaTime;

        if (this.isSessionOpen()) {
            deltaTime = Duration.between(this.sessionStartTime, Timer.getCurrentTime());
        }
        else {
            deltaTime = Duration.between(this.sessionStartTime, this.sessionEndTime);
        }

        return deltaTime;
    }

    public abstract LongListOfData getStatistics();
}
