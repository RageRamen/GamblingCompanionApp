package com.honeybadger.gamblingbadger.gamesession.round;

import com.honeybadger.gamblingbadger.gamesession.EGameType;
import com.honeybadger.gamblingbadger.gamesession.card.Card;
import com.honeybadger.gamblingbadger.gamesession.card.ECardNum;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BlackjackRound extends CardRound {
    // Variables

    private final int numberOfDecks;
    private List<Card> userHand;
    private Card dealerCard;

    // Constructor

    public BlackjackRound(BigDecimal bet, int numberOfDecks) {
        super(bet);
        this.numberOfDecks = numberOfDecks;
        this.userHand = new LinkedList<Card>();
    }

    // Properties

    public int getNumberOfDecks() {
        return this.numberOfDecks;
    }

    public List<Card> getUserHand() {
        return this.userHand;
    }

    public Card getDealerCard() {
        return this.dealerCard;
    }

    public BlackjackRound setDealerCard(Card card) {
        this.dealerCard = card;
        return this;
    }

    // Action Methods

    public BlackjackRound addUserCard(Card card) {
        int count = 0;

        for (Card uc : userHand) {
            if (card == uc)
                count++;
        }

        if (count <= numberOfDecks) {
            userHand.add(card);
        }

        return this;
    }

    public boolean removeUserCard(Card card) {
        return userHand.remove(card);
    }

    // Info Methods

    @Override
    public float getHandStrength();

    private int getHighestScore(List<Card> hand) {
        int lowScore = 0, highScore = 0;
        boolean hadAce = false;

        for (Card card : hand) {
            switch (card.num) {
                case CN_ANY:
                case CN_R:  // Joker (almost never used, but included for completeness)
                    // TODO
                    break;
                case CN_A:
                    lowScore += 1;
                    if (hadAce)
                        highScore += 1;
                    else {
                        // There can only be one ace with the value of 11 (two 11s will always bust)
                        highScore += 11;
                        hadAce = true;
                    }
                    break;
                case CN_2: lowScore = highScore += 2; break;
                case CN_3: lowScore = highScore += 3; break;
                case CN_4: lowScore = highScore += 4; break;
                case CN_5: lowScore = highScore += 5; break;
                case CN_6: lowScore = highScore += 6; break;
                case CN_7: lowScore = highScore += 7; break;
                case CN_8: lowScore = highScore += 8; break;
                case CN_9: lowScore = highScore += 9; break;
                case CN_10:
                case CN_J:
                case CN_Q:
                case CN_K: lowScore = highScore += 10; break;
            }
        }
        if (highScore < 21)
            return highScore;
        else if (lowScore < 21)
            return lowScore;  // High busted
        else return 0;  // Both busted
    }

    @Override
    public BigDecimal getExpectedValue();
}
