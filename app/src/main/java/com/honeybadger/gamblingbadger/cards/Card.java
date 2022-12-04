package com.honeybadger.gamblingbadger.cards;

import android.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class Card {
    // Variables

    public ECardSuit suit = ECardSuit.ANY;
    public ECardNum num = ECardNum.ANY;

    // Constructors

    public Card() {
        this.suit = ECardSuit.ANY;
        this.num = ECardNum.ANY;
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

        suitMatch = this.suit == ECardSuit.ANY || c.suit == ECardSuit.ANY || this.suit == c.suit;
        cardMatch = this.num == ECardNum.ANY || c.num == ECardNum.ANY || this.num == c.num;

        return suitMatch && cardMatch;
    }
    public boolean equals(Pair<ECardSuit, ECardNum> pair) {
        boolean suitMatch, cardMatch;

        suitMatch = this.suit == ECardSuit.ANY || pair.first == ECardSuit.ANY || this.suit == pair.first;
        cardMatch = this.num == ECardNum.ANY || pair.second == ECardNum.ANY || this.num == pair.second;

        return suitMatch && cardMatch;
    }

    public boolean isAny() {
        return suit == ECardSuit.ANY && num == ECardNum.ANY;
    }

    public static List<Card> getDeck() {
        List<Card> deck = new ArrayList<Card>() {
            {
                add(new Card(ECardSuit.SPADES, ECardNum.A));
                add(new Card(ECardSuit.SPADES, ECardNum.N_2));
                add(new Card(ECardSuit.SPADES, ECardNum.N_3));
                add(new Card(ECardSuit.SPADES, ECardNum.N_4));
                add(new Card(ECardSuit.SPADES, ECardNum.N_5));
                add(new Card(ECardSuit.SPADES, ECardNum.N_6));
                add(new Card(ECardSuit.SPADES, ECardNum.N_7));
                add(new Card(ECardSuit.SPADES, ECardNum.N_8));
                add(new Card(ECardSuit.SPADES, ECardNum.N_9));
                add(new Card(ECardSuit.SPADES, ECardNum.N_10));
                add(new Card(ECardSuit.SPADES, ECardNum.J));
                add(new Card(ECardSuit.SPADES, ECardNum.Q));
                add(new Card(ECardSuit.SPADES, ECardNum.K));
                add(new Card(ECardSuit.HEARTS, ECardNum.A));
                add(new Card(ECardSuit.HEARTS, ECardNum.N_2));
                add(new Card(ECardSuit.HEARTS, ECardNum.N_3));
                add(new Card(ECardSuit.HEARTS, ECardNum.N_4));
                add(new Card(ECardSuit.HEARTS, ECardNum.N_5));
                add(new Card(ECardSuit.HEARTS, ECardNum.N_6));
                add(new Card(ECardSuit.HEARTS, ECardNum.N_7));
                add(new Card(ECardSuit.HEARTS, ECardNum.N_8));
                add(new Card(ECardSuit.HEARTS, ECardNum.N_9));
                add(new Card(ECardSuit.HEARTS, ECardNum.N_10));
                add(new Card(ECardSuit.HEARTS, ECardNum.J));
                add(new Card(ECardSuit.HEARTS, ECardNum.Q));
                add(new Card(ECardSuit.HEARTS, ECardNum.K));
                add(new Card(ECardSuit.DIAMONDS, ECardNum.A));
                add(new Card(ECardSuit.DIAMONDS, ECardNum.N_2));
                add(new Card(ECardSuit.DIAMONDS, ECardNum.N_3));
                add(new Card(ECardSuit.DIAMONDS, ECardNum.N_4));
                add(new Card(ECardSuit.DIAMONDS, ECardNum.N_5));
                add(new Card(ECardSuit.DIAMONDS, ECardNum.N_6));
                add(new Card(ECardSuit.DIAMONDS, ECardNum.N_7));
                add(new Card(ECardSuit.DIAMONDS, ECardNum.N_8));
                add(new Card(ECardSuit.DIAMONDS, ECardNum.N_9));
                add(new Card(ECardSuit.DIAMONDS, ECardNum.N_10));
                add(new Card(ECardSuit.DIAMONDS, ECardNum.J));
                add(new Card(ECardSuit.DIAMONDS, ECardNum.Q));
                add(new Card(ECardSuit.DIAMONDS, ECardNum.K));
                add(new Card(ECardSuit.CLUBS, ECardNum.A));
                add(new Card(ECardSuit.CLUBS, ECardNum.N_2));
                add(new Card(ECardSuit.CLUBS, ECardNum.N_3));
                add(new Card(ECardSuit.CLUBS, ECardNum.N_4));
                add(new Card(ECardSuit.CLUBS, ECardNum.N_5));
                add(new Card(ECardSuit.CLUBS, ECardNum.N_6));
                add(new Card(ECardSuit.CLUBS, ECardNum.N_7));
                add(new Card(ECardSuit.CLUBS, ECardNum.N_8));
                add(new Card(ECardSuit.CLUBS, ECardNum.N_9));
                add(new Card(ECardSuit.CLUBS, ECardNum.N_10));
                add(new Card(ECardSuit.CLUBS, ECardNum.J));
                add(new Card(ECardSuit.CLUBS, ECardNum.Q));
                add(new Card(ECardSuit.CLUBS, ECardNum.K));
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

        deck.add(new Card(ECardSuit.SPADES, ECardNum.R));
        deck.add(new Card(ECardSuit.HEARTS, ECardNum.R));

        return deck;
    }

    public static List<Card> getJokerDecks(int num) {
        List<Card> deck = new ArrayList<Card>();

        for (int i = 0; i < num; i++) {
            deck.addAll(getJokerDeck());
        }

        return deck;
    }

    public ECardNum getECardNum(){
        return this.num;
    }
    public ECardSuit getECardSuit(){
        return this.suit;
    } //added this here for poker -Bryan
}
