package com.honeybadger.gamblingbadger.gamesession.poker;

import com.honeybadger.gamblingbadger.cards.Card;
import com.honeybadger.gamblingbadger.cards.CardRound;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PokerRound extends CardRound {
    // VARIABLES:

    // CONSTRUCTOR:

    PokerRound(BigDecimal bet) {
        super(bet);
    }

    // PROPERTIES:

    // ACTION METHODS:

    // INFO METHODS:

    @Override
    public float getHandStrength() {
        // TODO
        return 0f;
    }

    @Override
    public float getExpectedValue() {
        // TODO
        return 0f;
    }

    public float getOdds() {
        // TODO
        return 0f;
    }

    public List<Card> getRecommendedDiscards() {
        // TODO
        return new ArrayList<Card>();
    }
}
