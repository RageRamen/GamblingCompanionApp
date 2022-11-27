package com.honeybadger.gamblingbadger.gamesession.poker;

import com.honeybadger.gamblingbadger.cards.CardRound;
import com.honeybadger.gamblingbadger.casinosession.CasinoSession;
import com.honeybadger.gamblingbadger.gamesession.*;
import com.honeybadger.gamblingbadger.gamesession.blackjack.BlackjackRound;
import com.honeybadger.gamblingbadger.statistics.LongListOfData;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

public class PokerSession extends GameSession {
    // VARIABLES:

    private List<PokerRound> rounds = new LinkedList<>();

    // Constructor

    public PokerSession(CasinoSession casinoSession) {
        super(casinoSession, EGameType.GT_POKER);
    }

    // Info Methods

    @Override
    public LongListOfData getStatistics() {
        LongListOfData data = new LongListOfData(this);

        // Number of games
        data.numberOfGames = rounds.size();  // Rounds

        // Total time spent playing
        data.totalTimeSpentPlaying = getSessionDuration();

        // Total money delta
        data.totalMoneyDelta = BigDecimal.ZERO;
        for (CardRound round : rounds) {
            if (round.isWin())
                data.totalMoneyDelta = data.totalMoneyDelta.add(round.getBet());
            else
                data.totalMoneyDelta = data.totalMoneyDelta.subtract(round.getBet());
        }

        // Average money delta
        if (data.numberOfGames != 0)
            data.averageMoneyDelta = data.totalMoneyDelta.floatValue() / data.numberOfGames;
        else
            data.averageMoneyDelta = 0f;

        // Average luck index
        float totalLuckIndex = 0f;
        int luckIndexCount = 0;
        float chanceOfWinning;
        float chanceOfLosing;
        for (PokerRound round : rounds) {
            chanceOfWinning = round.getOdds();
            chanceOfLosing = 1.0f - chanceOfWinning;

            if (round.isWin())
                totalLuckIndex += chanceOfLosing;  // Winning a game with high chance of losing = big positive points
            else
                totalLuckIndex -= chanceOfWinning;  // Losing a game with high chance of winning = big negative points

            luckIndexCount += 1;
        }
        if (luckIndexCount != 0)
            data.averageLuckIndex = totalLuckIndex / luckIndexCount;
        else
            data.averageLuckIndex = 0f;

        return new LongListOfData();
    }
}
