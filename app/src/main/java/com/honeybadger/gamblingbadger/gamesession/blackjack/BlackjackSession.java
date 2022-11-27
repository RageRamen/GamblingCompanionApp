package com.honeybadger.gamblingbadger.gamesession.blackjack;

import android.util.Pair;

import com.honeybadger.gamblingbadger.cards.Card;
import com.honeybadger.gamblingbadger.casinosession.CasinoSession;
import com.honeybadger.gamblingbadger.gamesession.*;
import com.honeybadger.gamblingbadger.statistics.LongListOfData;

import java.math.BigDecimal;
import java.util.ArrayList;
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

    public Pair<Float, String> getRoundPrediction(Card[] userHand, Card dealerCard){
        //TODO
        //Take args from getRoundPrediction, put them in a round - what round??
        //call getOdds() & getRecommendedAction() from the round - return the results in a pair

        return new Pair<Float, String>(0f, "");
    }



    public boolean closeSession() {
        // TODO
        return false;
    }

    // INFO METHODS:

    @Override
    public LongListOfData getStatistics() {
        // TODO
        return new LongListOfData();
    }
}
