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
    public float getHandStrength() {
        // Get deck
        List<Card> deck = Card.getDecks(numberOfDecks);

        // Remove taken cards
        for (Card c : userHand)
            deck.remove(c);
        deck.remove(dealerCard);

        // Get current scores
        int[] scores = getHighLowScore(userHand);
        int activeScore;

        // Get safe numbers
        if (scores[0] == scores[1]) {  // No aces
            activeScore = scores[0];
            if (activeScore > 21) {
                // Busted
                // TODO
            }
            else if (activeScore == 21) {
                // Best hand
                // TODO
            }
            else {
                // Playing
                // TODO
            }
        }

        return 0;
    }

    private static float[] getNumProbabilityDistribution(List<Card> deck) {
        int[] count = new int[12];
        float[] probabilityDist = new float[12];

        for (Card card : deck) {
            switch (card.num) {
                case CN_ANY:
                case CN_R:
                    break;
                case CN_A:
                    count[1]++;
                    count[11]++;
                    break;
                case CN_2:
                    count[2]++;
                    break;
                case CN_3:
                    count[3]++;
                    break;
                case CN_4:
                    count[4]++;
                    break;
                case CN_5:
                    count[5]++;
                    break;
                case CN_6:
                    count[6]++;
                    break;
                case CN_7:
                    count[7]++;
                    break;
                case CN_8:
                    count[8]++;
                    break;
                case CN_9:
                    count[9]++;
                    break;
                case CN_10:
                case CN_J:
                case CN_Q:
                case CN_K:
                    count[10]++;
                    break;
            }
        }

        if (deck.size() > 0) {
            for (int i = 1; i < 12; i++)
                probabilityDist[i] = (float)count[i] / (float)deck.size();
            probabilityDist[0] = 0.0f;  // pulling a 0 is impossible
        }

        return probabilityDist;
    }

    private static float[] getNumCumulativeDistribution(float[] probabilityDist) {
        float[] cumulativeDist = new float[11];

        for (int i = 1; i < 11; i++) {
            cumulativeDist[i] = cumulativeDist[i-1] + probabilityDist[i];
        }

        return cumulativeDist;
    }

    private static int[] getHighLowScore(List<Card> hand) {
        int[] scores = new int[2];
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

        scores[0] = lowScore;
        scores[1] = highScore;
        return scores;
    }

    @Override
    public BigDecimal getExpectedValue(){
        // TODO
        return BigDecimal.ZERO;
    }
}
