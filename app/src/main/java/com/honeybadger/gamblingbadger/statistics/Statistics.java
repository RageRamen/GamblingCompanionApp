package com.honeybadger.gamblingbadger.statistics;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.honeybadger.gamblingbadger.casinosession.CasinoSession;
import com.honeybadger.gamblingbadger.gamesession.EGameType;
import com.honeybadger.gamblingbadger.gamesession.GameSession;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class Statistics {
    // CONSTANTS:

    private static final String DEFAULT_FILE_NAME = "GamblingBadgerUserHistory.info";

    // VARIABLES:

    private static boolean initialized = false;
    private static List<CasinoSession> casinoSessions = new ArrayList<>();
    private static List<LongListOfData> statisticData = new LinkedList<>();

    // INITIALIZER:

    public static boolean init() {
        return init(DEFAULT_FILE_NAME);
    }
    public static boolean init(String srcFileName) {
        boolean success = false;

        if (!isInitialized()) {
            success = loadCasSesFromFile(srcFileName);
            if (success) {
                initialized = true;
            }
        }

        return success;
    }

    public static boolean close() {
        return close(DEFAULT_FILE_NAME);
    }
    public static boolean close(String dstFileName) {
        boolean success = false;

        if (isInitialized()) {
            success = saveCasSesToFile(dstFileName);
            if (success) {
                casinoSessions.clear();
                statisticData.clear();
                initialized = false;
            }
        }

        return success;
    }

    // PROPERTIES:

    public static boolean isInitialized() {
        return initialized;
    }

    // ACTION METHODS:

    public static boolean loadCasSesFromFile(String fileName) {
        // TODO
        return true;
    }

    public static boolean saveCasSesToFile(String fileName) {
        // TODO
        return false;
    }

    public static boolean updateStatistics() {
        boolean success = false;
        if (isInitialized()) {
            for (CasinoSession casinoSession : casinoSessions) {
                statisticData.add(casinoSession.getStatistics());
                for (GameSession gameSession : casinoSession.getGamesPlayed()) {
                    statisticData.add(gameSession.getStatistics());
                }
            }
            success = true;
        }
        return success;
    }

    public static boolean addCasSesToList(CasinoSession casinoSession) {
        boolean success = false;

        if (!casinoSessions.contains(casinoSession)) {
            casinoSessions.add(casinoSession);
            success = true;
        }

        return success;
    }

    // INFO METHODS:

    public static LongListOfData getGeneralStatistics() {
        Duration timeInCasinos = Duration.ZERO;
        Duration timePlayingGames = Duration.ZERO;
        Map<EGameType, Duration> timePlayingEachGame = new HashMap<>();
        Map<EGameType, BigDecimal> moneyDeltaEachGame = new HashMap<>();
        Map<EGameType, Float> luckIndexEachGame = new HashMap<>();

        Map<EGameType, Integer> luckIndexCount = new HashMap<>();

        for (LongListOfData entry : statisticData) {
            if (entry.isCasinoSessionData()) {
                assert entry.timeSpentInCasino != null;
                assert entry.timeSpentPlayingGames != null;
                assert entry.timePlayingEachGame != null;
                assert entry.moneyDeltaForEachGame != null;
                assert entry.luckIndexForEachGame != null;

                // aggregateTimeSpentInCasinos
                timeInCasinos = timeInCasinos.plus(entry.timeSpentInCasino);

                // aggregateTimePlayingGames
                timePlayingGames = timePlayingGames.plus(entry.timeSpentPlayingGames);

                // aggregateTimePlayingEachGame
                for (Map.Entry<EGameType, Duration> e : entry.timePlayingEachGame.entrySet()) {
                    timePlayingEachGame.putIfAbsent(e.getKey(), Duration.ZERO);
                    timePlayingEachGame.put(e.getKey(), timePlayingEachGame.get(e.getKey()).plus(e.getValue()));
                }

                // aggregateMoneyDeltaForEachGame
                for (Map.Entry<EGameType, BigDecimal> e : entry.moneyDeltaForEachGame.entrySet()) {
                    moneyDeltaEachGame.putIfAbsent(e.getKey(), BigDecimal.ZERO);
                    moneyDeltaEachGame.put(e.getKey(), moneyDeltaEachGame.get(e.getKey()).add(e.getValue()));
                }

                // aggregateLuckIndexForEachGame (1/2)
                for (Map.Entry<EGameType, Float> e : entry.luckIndexForEachGame.entrySet()) {
                    luckIndexEachGame.putIfAbsent(e.getKey(), 0f);
                    luckIndexCount.putIfAbsent(e.getKey(), 0);
                    luckIndexEachGame.put(e.getKey(), luckIndexEachGame.get(e.getKey()) + e.getValue());  // sum
                    luckIndexCount.put(e.getKey(), luckIndexCount.get(e.getKey()) + 1);  // count
                }
            }
        }
        // aggregateLuckIndexForEachGame (2/2)
        for (Map.Entry<EGameType, Float> f : luckIndexEachGame.entrySet()) {
            if (luckIndexCount.get(f.getKey()) != 0)
                luckIndexEachGame.put(f.getKey(), f.getValue() / luckIndexCount.get(f.getKey()));  // Average
        }

        LongListOfData data = new LongListOfData();
        data.type = ELLODType.GENERAL;
        data.aggregateTimePlayingGames = timePlayingGames;
        data.aggregateTimeSpentInCasinos = timeInCasinos;
        data.aggregateTimePlayingEachGame = timePlayingEachGame;
        data.aggregateMoneyDeltaForEachGame = moneyDeltaEachGame;
        data.aggregateLuckIndexForEachGame = luckIndexEachGame;

        return data;
    }

    public static LongListOfData getCasSesStatistics(CasinoSession casinoSession) {
        return casinoSession.getStatistics();
    }

    public static LongListOfData getGenGameStatistics(EGameType gameType) {
        int numberOfGames = 0;
        Duration totalTimePlaying = Duration.ZERO;
        BigDecimal totalMoneyDelta = BigDecimal.ZERO;
        float luckIndexSum = 0f;

        for (LongListOfData entry : statisticData) {
            if (entry.isGameSessionData()) {
                assert entry.gameType != null;
                if (entry.gameType == gameType) {
                    assert entry.numberOfGames != null;
                    assert entry.totalTimeSpentPlaying != null;
                    assert entry.totalMoneyDelta != null;
                    assert entry.averageLuckIndex != null;

                    // numberOfGames
                    numberOfGames += entry.numberOfGames;

                    // totalTimeSpentPlaying
                    totalTimePlaying = totalTimePlaying.plus(entry.totalTimeSpentPlaying);

                    // totalMoneyDelta
                    totalMoneyDelta = totalMoneyDelta.add(entry.totalMoneyDelta);

                    // averageLuckIndex
                    luckIndexSum += entry.averageLuckIndex * entry.numberOfGames;
                }
            }
        }

        LongListOfData data = new LongListOfData();
        data.type = ELLODType.GENERAL_GAME;
        data.gameType = gameType;
        data.numberOfGames = numberOfGames;
        data.totalTimeSpentPlaying = totalTimePlaying;
        data.totalMoneyDelta = totalMoneyDelta;
        data.averageMoneyDelta = numberOfGames != 0 ? totalMoneyDelta.floatValue() / numberOfGames : 0f;
        data.averageLuckIndex = numberOfGames != 0 ? luckIndexSum / numberOfGames : 0f;

        return data;
    }

    public static LongListOfData getSpecGameStatistics(CasinoSession casinoSession, EGameType gameType) {
        int numberOfGames = 0;
        Duration totalTimePlaying = Duration.ZERO;
        BigDecimal totalMoneyDelta = BigDecimal.ZERO;
        float luckIndexSum = 0f;

        for (LongListOfData entry : statisticData) {
            if (entry.isGameSessionData()) {
                if (entry.casinoSession == casinoSession) {
                    assert entry.gameType != null;
                    if (entry.gameType == gameType) {
                        assert entry.numberOfGames != null;
                        assert entry.totalTimeSpentPlaying != null;
                        assert entry.totalMoneyDelta != null;
                        assert entry.averageLuckIndex != null;

                        // numberOfGames
                        numberOfGames += entry.numberOfGames;

                        // totalTimeSpentPlaying
                        totalTimePlaying = totalTimePlaying.plus(entry.totalTimeSpentPlaying);

                        // totalMoneyDelta
                        totalMoneyDelta = totalMoneyDelta.add(entry.totalMoneyDelta);

                        // averageLuckIndex
                        luckIndexSum += entry.averageLuckIndex * entry.numberOfGames;
                    }
                }
            }
        }

        LongListOfData data = new LongListOfData();
        data.type = ELLODType.SPECIFIC_GAME;
        data.gameType = gameType;
        data.numberOfGames = numberOfGames;
        data.totalTimeSpentPlaying = totalTimePlaying;
        data.totalMoneyDelta = totalMoneyDelta;
        data.averageMoneyDelta = numberOfGames != 0 ? totalMoneyDelta.floatValue() / numberOfGames : 0f;
        data.averageLuckIndex = numberOfGames != 0 ? luckIndexSum / numberOfGames : 0f;

        return data;
    }
}
