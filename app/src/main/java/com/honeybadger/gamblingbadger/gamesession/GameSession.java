package com.honeybadger.gamblingbadger.gamesession;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;

import com.honeybadger.gamblingbadger.casinosession.CasinoSession;
import com.honeybadger.gamblingbadger.timer.Timer;

public abstract class GameSession {
    // CONSTANTS

    // Variables:

    private CasinoSession casinoSession;

    protected EGameType gameType;

    private LocalDateTime sessionStartTime;
    private LocalDateTime sessionEndTime;

    private BigDecimal balanceChange;

    private Timer timer;

    // Constructor:

    protected GameSession(CasinoSession casinoSession, EGameType gameType) {
        this.casinoSession = casinoSession;
        this.gameType = gameType;
        this.sessionStartTime = LocalDateTime.MIN;
        this.sessionEndTime = LocalDateTime.MIN;
        this.balanceChange = BigDecimal.ZERO;
        this.timer = new Timer();
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

    public BigDecimal getBalanceChange() {
        return this.balanceChange;
    }

    public Timer getTimer() {
        return this.timer;
    }

    // Action Methods:

    public boolean startSession() {
        boolean success = false;

        if (!hasStarted()){
            sessionStartTime = Timer.getCurrentTime();

            // TODO: Whatever else we need to do.

            success = true;
        }

        return success;
    }

    public boolean stopSession() {
        boolean success = false;

        if (hasStarted() && !hasFinished()) {
            sessionEndTime = Timer.getCurrentTime();

            // TODO: Whatever else we need to do.

            success = true;
        }

        return success;
    }

    // Info Methods:

    public boolean hasStarted() {
        return sessionStartTime != LocalDateTime.MIN;
    }

    public boolean hasFinished() {
        return sessionEndTime != LocalDateTime.MIN;
    }

    public boolean isSessionOpen() {
        return sessionStartTime == sessionEndTime;
    }

    public Duration getTimeSpentPlaying() {
        Duration deltaTime;

        if (this.isSessionOpen()) {
            deltaTime = Duration.between(this.sessionStartTime, Timer.getCurrentTime());
        }
        else {
            deltaTime = Duration.between(this.sessionStartTime, this.sessionEndTime);
        }

        return deltaTime;
    }

    public abstract float getAverageLuck();
}
