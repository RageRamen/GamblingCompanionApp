package com.honeybadger.gamblingbadger.cards;

import android.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class Card {
    // Variables

    public ECardSuit suit = ECardSuit.CS_NONE;
    public ECardNum num = ECardNum.CN_NONE;

    // Constructors

    public Card() {
        this.suit = ECardSuit.CS_NONE;
        this.num = ECardNum.CN_NONE;
    }
    public Card(ECardSuit suit, ECardNum num) {
        this.suit = suit;
        this.num = num;
    }
    public Card(Pair<ECardSuit, ECardNum> pair) {
        this.suit = pair.first;
        this.num = pair.second;
    }

    // Info Methods

    public boolean equals(Card c) {
        boolean suitMatch, cardMatch;

        suitMatch = this.suit == ECardSuit.CS_NONE || c.suit == ECardSuit.CS_NONE || this.suit == c.suit;
        cardMatch = this.num == ECardNum.CN_NONE || c.num == ECardNum.CN_NONE || this.num == c.num;

        return suitMatch && cardMatch;
    }
    public boolean equals(Pair<ECardSuit, ECardNum> pair) {
        boolean suitMatch, cardMatch;

        suitMatch = this.suit == ECardSuit.CS_NONE || pair.first == ECardSuit.CS_NONE || this.suit == pair.first;
        cardMatch = this.num == ECardNum.CN_NONE || pair.second == ECardNum.CN_NONE || this.num == pair.second;

        return suitMatch && cardMatch;
    }


    public static List<Card> getDeck() {
        List<Card> deck = new ArrayList<Card>() {
            {
                add(new Card(ECardSuit.CS_S, ECardNum.CN_A));
                add(new Card(ECardSuit.CS_S, ECardNum.CN_2));
                add(new Card(ECardSuit.CS_S, ECardNum.CN_3));
                add(new Card(ECardSuit.CS_S, ECardNum.CN_4));
                add(new Card(ECardSuit.CS_S, ECardNum.CN_5));
                add(new Card(ECardSuit.CS_S, ECardNum.CN_6));
                add(new Card(ECardSuit.CS_S, ECardNum.CN_7));
                add(new Card(ECardSuit.CS_S, ECardNum.CN_8));
                add(new Card(ECardSuit.CS_S, ECardNum.CN_9));
                add(new Card(ECardSuit.CS_S, ECardNum.CN_10));
                add(new Card(ECardSuit.CS_S, ECardNum.CN_J));
                add(new Card(ECardSuit.CS_S, ECardNum.CN_Q));
                add(new Card(ECardSuit.CS_S, ECardNum.CN_K));
                add(new Card(ECardSuit.CS_H, ECardNum.CN_A));
                add(new Card(ECardSuit.CS_H, ECardNum.CN_2));
                add(new Card(ECardSuit.CS_H, ECardNum.CN_3));
                add(new Card(ECardSuit.CS_H, ECardNum.CN_4));
                add(new Card(ECardSuit.CS_H, ECardNum.CN_5));
                add(new Card(ECardSuit.CS_H, ECardNum.CN_6));
                add(new Card(ECardSuit.CS_H, ECardNum.CN_7));
                add(new Card(ECardSuit.CS_H, ECardNum.CN_8));
                add(new Card(ECardSuit.CS_H, ECardNum.CN_9));
                add(new Card(ECardSuit.CS_H, ECardNum.CN_10));
                add(new Card(ECardSuit.CS_H, ECardNum.CN_J));
                add(new Card(ECardSuit.CS_H, ECardNum.CN_Q));
                add(new Card(ECardSuit.CS_H, ECardNum.CN_K));
                add(new Card(ECardSuit.CS_D, ECardNum.CN_A));
                add(new Card(ECardSuit.CS_D, ECardNum.CN_2));
                add(new Card(ECardSuit.CS_D, ECardNum.CN_3));
                add(new Card(ECardSuit.CS_D, ECardNum.CN_4));
                add(new Card(ECardSuit.CS_D, ECardNum.CN_5));
                add(new Card(ECardSuit.CS_D, ECardNum.CN_6));
                add(new Card(ECardSuit.CS_D, ECardNum.CN_7));
                add(new Card(ECardSuit.CS_D, ECardNum.CN_8));
                add(new Card(ECardSuit.CS_D, ECardNum.CN_9));
                add(new Card(ECardSuit.CS_D, ECardNum.CN_10));
                add(new Card(ECardSuit.CS_D, ECardNum.CN_J));
                add(new Card(ECardSuit.CS_D, ECardNum.CN_Q));
                add(new Card(ECardSuit.CS_D, ECardNum.CN_K));
                add(new Card(ECardSuit.CS_C, ECardNum.CN_A));
                add(new Card(ECardSuit.CS_C, ECardNum.CN_2));
                add(new Card(ECardSuit.CS_C, ECardNum.CN_3));
                add(new Card(ECardSuit.CS_C, ECardNum.CN_4));
                add(new Card(ECardSuit.CS_C, ECardNum.CN_5));
                add(new Card(ECardSuit.CS_C, ECardNum.CN_6));
                add(new Card(ECardSuit.CS_C, ECardNum.CN_7));
                add(new Card(ECardSuit.CS_C, ECardNum.CN_8));
                add(new Card(ECardSuit.CS_C, ECardNum.CN_9));
                add(new Card(ECardSuit.CS_C, ECardNum.CN_10));
                add(new Card(ECardSuit.CS_C, ECardNum.CN_J));
                add(new Card(ECardSuit.CS_C, ECardNum.CN_Q));
                add(new Card(ECardSuit.CS_C, ECardNum.CN_K));
            }
        };

        return deck;
    }

    public static List<Card> getDecks(int num) {
        List<Card> deck = new ArrayList<Card>();

        for (int i = 0; i < num; i++) {
            deck.addAll(getDeck());
        }

        return deck;
    }

    public static List<Card> getJokerDeck() {
        List<Card> deck = getDeck();

        deck.add(new Card(ECardSuit.CS_R, ECardNum.CN_R));
        deck.add(new Card(ECardSuit.CS_B, ECardNum.CN_R));

        return deck;
    }

    public static List<Card> getJokerDecks(int num) {
        List<Card> deck = new ArrayList<Card>();

        for (int i = 0; i < num; i++) {
            deck.addAll(getJokerDeck());
        }

        return deck;
    }
}
