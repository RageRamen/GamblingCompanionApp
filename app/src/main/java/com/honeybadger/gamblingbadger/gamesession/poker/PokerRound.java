package com.honeybadger.gamblingbadger.gamesession.poker;

import com.honeybadger.gamblingbadger.cards.Card;
import com.honeybadger.gamblingbadger.cards.CardRound;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PokerRound extends CardRound {
    // VARIABLES:

    private Card[] userCards = { new Card(), new Card(), new Card(), new Card(), new Card() };

    // CONSTRUCTOR:

    PokerRound(BigDecimal bet) {
        super(bet);
    }
    PokerRound(BigDecimal bet, Card[] userCards) {
        super(bet);
        this.userCards = userCards;
    }

    // PROPERTIES:

    public Card[] getUserCards() {
        return this.userCards;
    }

    public boolean setUserCards(Card[] userCards) {
        this.userCards = userCards;
        return true;
    }

    // ACTION METHODS:

    public boolean changeUserCard(int index, Card card) {
        if (index < 0 || index >= 5)
            return false;
        userCards[index] = card;
        return true;
    }

    // INFO METHODS:

    public float getOdds() {
        // TODO
        return 0f;
    }

    public List<Card> getRecommendedDiscards() {
        // TODO
        return new ArrayList<Card>();
    }
}
