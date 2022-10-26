package com.honeybadger.gamblingbadger.gamesession.blackjack;

import com.honeybadger.gamblingbadger.casinosession.CasinoSession;
import com.honeybadger.gamblingbadger.gamesession.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BlackjackSession extends GameSession {
    // CONSTANTS:

    private static final BigDecimal DEFAULT_BASE_BET = new BigDecimal(5);
    private static final int DEFAULT_NUMBER_OF_DECKS = 6;

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

    // TODO: Review "startSession()" and "endSession()" relevance.

    public boolean startSession() {
        return true;
    }

    public boolean endSession() {
        return true;
    }

    // INFO METHODS:

    public int getNumberOfWins() {
        // TODO
        return 0;
    }

    public int getNumberOfLosses() {
        // TODO
        return 0;
    }

    public List<BlackjackRound> getWinningRounds() {
        // TODO
        return new ArrayList<>();
    }

    public List<BlackjackRound> getLosingRounds() {
        // TODO
        return new ArrayList<>();
    }

    @Override
    public float getAverageLuck() {
        // TODO
        return 0;
    }
}
