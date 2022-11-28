package com.honeybadger.gamblingbadger.casinosession;

import android.util.Log;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;

import java.util.HashMap;
import java.util.List;
import java.util.LinkedList;
import java.util.Map;

import com.honeybadger.gamblingbadger.balance.Balance;

import com.honeybadger.gamblingbadger.statistics.LongListOfData;
import com.honeybadger.gamblingbadger.statistics.Statistics;
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

        Statistics.addCasSesToList(this);
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

    // Action Methods:

    public CasinoSession addGameSession(GameSession gameSession) {
        if (!gamesPlayed.contains(gameSession))
            gamesPlayed.add(gameSession);

        return this;
    }

    public boolean closeSession() {
        this.sessionEndTime = Timer.getCurrentTime();

        try // attempts to cancel any running timers associated with that session
        {
            this.timer.attachedCDT.cancel();
        }
        catch(NullPointerException npe) {Log.w("BEN","Attempted to cancel a nonexistent timer");}


        return false;
    }

    // Info Methods:

    public boolean isSessionOpen() {
        return this.sessionStartTime == this.sessionEndTime;
    }

    public Duration getSessionDuration() {
        Duration duration = Duration.ZERO;

        if (isSessionOpen())
            duration = Duration.between(sessionStartTime, Timer.getCurrentTime());
        else
            duration = Duration.between(sessionStartTime, sessionEndTime);

        return duration;
    }

    public LongListOfData getStatistics() {
        Duration timeInCasino = Duration.ZERO;
        Duration timePlayingGames = Duration.ZERO;
        Map<EGameType, Duration> timePlayingEachGame = new HashMap<>();
        Map<EGameType, BigDecimal> moneyDeltaEachGame = new HashMap<>();
        Map<EGameType, Float> luckIndexEachGame = new HashMap<>();

        Map<EGameType, Integer> luckIndexCount = new HashMap<>();

        // Time spent in casino
        timeInCasino = getSessionDuration();

        // Game Sessions
        LongListOfData gameSessionData;
        for (GameSession gs : gamesPlayed) {
            gameSessionData = gs.getStatistics();

            // Time playing games
            timePlayingGames = timePlayingGames.plus(gameSessionData.totalTimeSpentPlaying);

            // Time playing each game
            timePlayingEachGame.putIfAbsent(gs.getGameType(), Duration.ZERO);
            timePlayingEachGame.put(gs.getGameType(), timePlayingEachGame.get(gs.getGameType()).plus(gameSessionData.totalTimeSpentPlaying));

            // Money delta each game
            moneyDeltaEachGame.putIfAbsent(gs.getGameType(), BigDecimal.ZERO);
            moneyDeltaEachGame.put(gs.getGameType(), moneyDeltaEachGame.get(gs.getGameType()).add(gameSessionData.totalMoneyDelta));

            // luck index each game (1/2)
            luckIndexEachGame.putIfAbsent(gs.getGameType(), 0f);
            luckIndexCount.putIfAbsent(gs.getGameType(), 0);
            luckIndexEachGame.put(gs.getGameType(), luckIndexEachGame.get(gs.getGameType()) + gameSessionData.averageLuckIndex);  // sum
            luckIndexCount.put(gs.getGameType(), luckIndexCount.get(gs.getGameType()) + 1);  // count
        }
        // Luck index each game (2/2)
        for (Map.Entry<EGameType, Float> f : luckIndexEachGame.entrySet()) {
            if (luckIndexCount.get(f.getKey()) != 0)
                luckIndexEachGame.put(f.getKey(), f.getValue() / luckIndexCount.get(f.getKey()));  // Average
        }

        // Aggregate
        LongListOfData casinoData = new LongListOfData(this);
        casinoData.timeSpentInCasino = timeInCasino;
        casinoData.timeSpentPlayingGames = timePlayingGames;
        casinoData.timePlayingEachGame = timePlayingEachGame;
        casinoData.moneyDeltaForEachGame = moneyDeltaEachGame;
        casinoData.luckIndexForEachGame = luckIndexEachGame;

        return casinoData;
    }
}
