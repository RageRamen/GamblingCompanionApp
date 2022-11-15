package com.honeybadger.gamblingbadger.statistics;

import com.honeybadger.gamblingbadger.casinosession.CasinoSession;
import com.honeybadger.gamblingbadger.gamesession.EGameType;
import com.honeybadger.gamblingbadger.gamesession.GameSession;

import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;

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
