package com.honeybadger.gamblingbadger.gamesession.texasholdem;

import com.honeybadger.gamblingbadger.cards.Card;
import com.honeybadger.gamblingbadger.cards.CardRound;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TexasHoldemRound extends CardRound {
    // VARIABLES:

    private Card[] userCards = { new Card(), new Card() };
    private Card[] tableCards = { new Card(), new Card(), new Card(), new Card(), new Card() };

    // CONSTRUCTOR:

    TexasHoldemRound(BigDecimal bet) {
        super(bet);
    }
    TexasHoldemRound(BigDecimal bet, Card[] userCards, Card[] tableCards) {
        super(bet);
        this.userCards = userCards;
        this.tableCards = tableCards;
    }

    // PROPERTIES:

    public Card[] getUserCards() {
        return this.userCards;
    }

    public boolean setUserCards(Card[] userCards) {
        this.userCards = userCards;
        return true;
    }

    public Card[] getTableCards() {
        return this.tableCards;
    }

    public boolean setTableCards(Card[] tableCards) {
        this.tableCards = tableCards;
        return true;
    }

    // ACTION METHODS:

    public boolean changeUserCard(int index, Card card) {
        if (index < 0 || index >= 2)
            return false;
        userCards[index] = card;
        return true;
    }

    public boolean changeTableCard(int index, Card card) {
        if (index < 0 || index >= 5)
            return false;
        tableCards[index] = card;
        return true;
    }

    // INFO METHODS:

    public float getOdds() {
        // TODO
        return 0f;
    }
}
