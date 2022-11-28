package com.honeybadger.gamblingbadger.gamesession.blackjack;

import android.util.Pair;

import com.honeybadger.gamblingbadger.cards.Card;
import com.honeybadger.gamblingbadger.cards.CardRound;
import com.honeybadger.gamblingbadger.casinosession.CasinoSession;
import com.honeybadger.gamblingbadger.gamesession.*;
import com.honeybadger.gamblingbadger.statistics.LongListOfData;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

public class BlackjackSession extends GameSession {
    // CONSTANTS:

    private static final BigDecimal DEFAULT_BASE_BET = new BigDecimal(5);
    public static final int DEFAULT_NUMBER_OF_DECKS = 6;
    public static final int MINIMUM_NUMBER_OF_DECKS = 1;
    public static final int MAXIMUM_NUMBER_OF_DECKS = 8;

    // VARIABLES:

    private final List<BlackjackRound> rounds;
    private BigDecimal baseBet;
    private int numberOfDecks;

    // CONSTRUCTOR:

    public BlackjackSession(CasinoSession casinoSession) {
        super(casinoSession, EGameType.GT_BLACKJACK);

        this.rounds = new LinkedList<>();
        this.baseBet = DEFAULT_BASE_BET;
        this.numberOfDecks = DEFAULT_NUMBER_OF_DECKS;
    }

    // PROPERTIES:

    public List<BlackjackRound> getRounds() {
        return this.rounds;
    }

    public BigDecimal getBaseBet() {
        return this.baseBet;
    }
    public BlackjackSession setBaseBet(BigDecimal baseBet) {
        this.baseBet = baseBet;
        return this;
    }

    public int getNumberOfDecks() {
        return this.numberOfDecks;
    }
    public BlackjackSession setNumberOfDecks(int numberOfDecks) {
        this.numberOfDecks = numberOfDecks;
        return this;
    }

    // ACTION METHODS:

    public void applyRound(BlackjackRound blackjackround){ //adds the blackjack round played into the backjack session
        this.rounds.add(blackjackround);
    }

    public Pair<Float, String> getRoundPrediction(BlackjackRound round){
        //TODO: Delete
        return new Pair<Float, String>(0f, "");
    }

    // INFO METHODS:

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
        for (BlackjackRound round : rounds) {
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

        return data;
    }
}
