package com.honeybadger.gamblingbadger.statistics;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.honeybadger.gamblingbadger.casinosession.CasinoSession;
import com.honeybadger.gamblingbadger.gamesession.EGameType;
import com.honeybadger.gamblingbadger.gamesession.GameSession;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Map;

public class LongListOfData {
    /// VARIABLES:

    public ELLODType type = ELLODType.EMPTY;
    public @Nullable CasinoSession casinoSession = null;
    public @Nullable GameSession gameSession = null;

    // FOR GENERAL:
    public @Nullable Duration aggregateTimeSpentInCasinos = null;
    public @Nullable Duration aggregateTimePlayingGames = null;
    public @Nullable Map<EGameType, Duration> aggregateTimePlayingEachGame = null;
    public @Nullable Map<EGameType, BigDecimal> aggregateMoneyDeltaForEachGame = null;
    public @Nullable Map<EGameType, Float> aggregateLuckIndexForEachGame = null;

    // FOR CASINO SES:
    public @Nullable Duration timeSpentInCasino = null;
    public @Nullable Duration timeSpentPlayingGames = null;
    public @Nullable Map<EGameType, Duration> timePlayingEachGame = null;
    public @Nullable Map<EGameType, BigDecimal> moneyDeltaForEachGame = null;
    public @Nullable Map<EGameType, Float> luckIndexForEachGame = null;

    // FOR GAME (GENERAL AND SPECIFIC):
    public @Nullable EGameType gameType = null;
    public @Nullable Integer numberOfGames = null;
    public @Nullable Duration totalTimeSpentPlaying = null;
    public @Nullable BigDecimal totalMoneyDelta = null;
    public @Nullable Float averageMoneyDelta = null;
    public @Nullable Float averageLuckIndex = null;

    /// CONSTRUCTORS:

    public LongListOfData() {}
    public LongListOfData(@NonNull CasinoSession casinoSession) {
        this.type = ELLODType.CASINO_SESSION;
        this.casinoSession = casinoSession;
    }
    public LongListOfData(@NonNull GameSession gameSession) {
        this.type = ELLODType.SPECIFIC_GAME;
        this.casinoSession = gameSession.getCasinoSession();
        this.gameSession = gameSession;
    }

    /// INFO METHODS:

    public boolean isCasinoSessionData() {
        return casinoSession != null && gameSession == null;
    }

    public boolean isGameSessionData() {
        return casinoSession != null && gameSession != null;
    }
}
