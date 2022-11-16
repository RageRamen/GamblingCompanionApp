package com.honeybadger.gamblingbadger.casinosession;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Duration;

import java.util.List;
import java.util.LinkedList;

import com.honeybadger.gamblingbadger.balance.Balance;

import com.honeybadger.gamblingbadger.statistics.LongListOfData;
import com.honeybadger.gamblingbadger.timer.Timer;

import com.honeybadger.gamblingbadger.gamesession.EGameType;
import com.honeybadger.gamblingbadger.gamesession.GameSession;
import com.honeybadger.gamblingbadger.gamesession.blackjack.BlackjackSession;
import com.honeybadger.gamblingbadger.gamesession.poker.PokerSession;
import com.honeybadger.gamblingbadger.gamesession.texasholdem.TexasHoldemSession;
import com.honeybadger.gamblingbadger.gamesession.roulette.RouletteSession;

public class CasinoSession {
    // Variables:

    private final LocalDateTime sessionStartTime;
    private LocalDateTime sessionEndTime;

    private final Balance balance;

    private Timer timer;

    private List<GameSession> gamesPlayed;

    // Constructor:

    public CasinoSession(Balance balance) {
        this.sessionStartTime = Timer.getCurrentTime();
        this.sessionEndTime = this.sessionStartTime;
        this.balance = balance;
        this.timer = new Timer();
        this.gamesPlayed = new LinkedList<GameSession>();
    }

    // Properties:

    public LocalDateTime getSessionStartTime() {
        return this.sessionStartTime;
    }

    public LocalDateTime getSessionEndTime() {
        return this.sessionEndTime;
    }

    public Balance getBalance() {
        return this.balance;
    }

    public Timer getTimer() {
        return this.timer;
    }

    public List<GameSession> getGamesPlayed() {
        return this.gamesPlayed;
    }

    public CasinoSession addGameSession(GameSession gameSession) {
        if (!gamesPlayed.contains(gameSession))
            gamesPlayed.add(gameSession);

        return this;
    }

    public CasinoSession removeGameSession(GameSession gameSession) {
        gamesPlayed.remove(gameSession);

        return this;
    }

    // Active Methods:

    public boolean addToBalance(BigDecimal amount) {
        boolean success = true;

        switch (this.balance.addToBalance(amount)) {
            case BM_NONE:
                break;
            case BM_FAIL:
                // TODO: Fail message
                success = false;
                break;
            case BM_10_PERCENT:
                // TODO: 10% warning to user
                break;
            case BM_25_PERCENT:
                // TODO: 25% warning to user
                break;
            case BM_50_PERCENT:
                // TODO: 50% warning to user
                break;
        }

        return success;
    }

    public boolean subFromBalance(BigDecimal amount) {
        return this.addToBalance(amount.negate());
    }

    public GameSession addGameSession(EGameType gameType) {
        GameSession gameSession;

        switch (gameType) {
            default:
            case GT_BLACKJACK:
                // TODO
                gameSession = new BlackjackSession(this);
                this.gamesPlayed.add(gameSession);
                break;
            case GT_POKER:
                // TODO
                gameSession = new PokerSession(this);
                this.gamesPlayed.add(gameSession);
                break;
            case GT_TEXAS_HOLDEM:
                // TODO
                gameSession = new TexasHoldemSession(this);
                this.gamesPlayed.add(gameSession);
                break;
            case GT_ROULETTE:
                // TODO
                gameSession = new RouletteSession(this);
                this.gamesPlayed.add(gameSession);
                break;
        }

        return gameSession;
    }

    public boolean closeSession() {
        // TODO: Other stuff
        this.sessionEndTime = Timer.getCurrentTime();
        return false;
    }

    // Info Methods:

    public boolean isSessionOpen() {
        return this.sessionStartTime == this.sessionEndTime;
    }

    public Duration getTimeSpentInCasino() {
        Duration deltaTime;

        if (this.isSessionOpen()) {
            deltaTime = Duration.between(this.sessionStartTime, Timer.getCurrentTime());
        }
        else {
            deltaTime = Duration.between(this.sessionStartTime, this.sessionEndTime);
        }

        return deltaTime;
    }

    public Duration getTimePlayingGames() {
        Duration sum = Duration.ZERO;

        for (GameSession gameSession : this.gamesPlayed) {
            sum = sum.plus(gameSession.getSessionDuration());
        }

        return sum;
    }

    public Duration getTimePlayingGames(EGameType gameType) {
        Duration sum = Duration.ZERO;

        for (GameSession gameSession : this.gamesPlayed) {
            if (gameSession.getGameType() == gameType)
                sum = sum.plus(gameSession.getSessionDuration());
        }

        return sum;
    }

    public Duration getTimeNotPlayingGames() {
        return this.getTimeSpentInCasino().minus(this.getTimePlayingGames());
    }

    public BigDecimal getChangeInBalance() {
        return this.balance.getChangeInBalance();
    }

    public List<GameSession> getGamesPlayed(EGameType gameType) {
        List<GameSession> newList = new LinkedList<GameSession>();

        for (GameSession gameSession : this.gamesPlayed) {
            if (gameSession.getGameType() == gameType)
                newList.add(gameSession);
        }

        return newList;
    }

    public Duration getSessionDuration() {
        // TODO
        return Duration.ZERO;
    }

    public LongListOfData getStatistics() {
        // TODO
        return new LongListOfData(this);
    }
}
