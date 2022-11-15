package com.honeybadger.gamblingbadger.statistics;

import com.honeybadger.gamblingbadger.gamesession.EGameType;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Map;

public class LongListOfData {
    /// VARIABLES:

    public ELLODType type = ELLODType.EMPTY;

    // FOR GENERAL:
    public Duration aggregateTimeSpentInCasinos;
    public Map<EGameType, Duration> aggregateTimePlayingEachGame;
    public Map<EGameType, BigDecimal> aggregateMoneyDeltaForEachGame;
    public Map<EGameType, Float> aggregateLuckIndexForEachGame;

    // FOR CASINO SES:
    public Duration timeSpentInCasino;
    public Duration timeSpentPlayingGames;
    public Map<EGameType, Duration> timePlayingEachGame;
    public Map<EGameType, BigDecimal> moneyDeltaForEachGame;
    public Map<EGameType, Float> luckIndexForEachGame;

    // FOR GAME (GENERAL AND SPECIFIC):
    public EGameType gameType;
    public int numberOfGames;
    public Duration totalTimeSpentPlaying;
    public BigDecimal totalMoneyDelta;
    public BigDecimal averageMoneyDelta;
    public float averageLuckIndex;
}
