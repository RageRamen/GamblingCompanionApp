package com.honeybadger.gamblingbadger.statistics;

import androidx.annotation.NonNull;

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
        return false;
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

    // INFO METHODS:

    public static LongListOfData getGeneralStatistics() {
        Duration timeInCasinos = Duration.ZERO;
        Map<EGameType, Duration> timePlayingEachGame = new HashMap<>();
        Map<EGameType, BigDecimal> moneyDeltaEachGame = new HashMap<>();
        Map<EGameType, Float> luckIndexEachGame = new HashMap<>();

        Map<EGameType, Integer> luckIndexCount = new HashMap<>();

        for (LongListOfData entry : statisticData) {
            if (entry.type == ELLODType.CASINO_SESSION) {
                assert entry.timeSpentInCasino != null;
                assert entry.timeSpentPlayingGames != null;
                assert entry.timePlayingEachGame != null;
                assert entry.moneyDeltaForEachGame != null;
                assert entry.luckIndexForEachGame != null;

                // aggregateTimeSpentInCasinos
                timeInCasinos = entry.timeSpentInCasino.plus(timeInCasinos);

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
            // aggregateLuckIndexForEachGame (2/2)
            for (Map.Entry<EGameType, Float> f : luckIndexEachGame.entrySet()) {
                if (luckIndexCount.get(f.getKey()) != 0)
                    luckIndexEachGame.put(f.getKey(), f.getValue() / luckIndexCount.get(f.getKey()));  // Average
            }

            LongListOfData data = new LongListOfData();
            data.type = ELLODType.GENERAL;
            data.aggregateTimeSpentInCasinos = timeInCasinos;
            data.aggregateTimePlayingEachGame = timePlayingEachGame;
            data.aggregateMoneyDeltaForEachGame = moneyDeltaEachGame;
            data.aggregateLuckIndexForEachGame = luckIndexEachGame;

            return data;
        }

        // TODO
        return new LongListOfData();
    }

    public static LongListOfData getCasSesStatistics(CasinoSession casinoSession) {
        // TODO
        return new LongListOfData();
    }

    public static LongListOfData getGenGameStatistics(EGameType gameType) {
        // TODO
        return new LongListOfData();
    }

    public static LongListOfData getSpecGameStatistics(CasinoSession casinoSession, EGameType gameType) {
        // TODO
        return new LongListOfData();
    }
}
