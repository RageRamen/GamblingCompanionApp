package com.honeybadger.gamblingbadger.gamesession.blackjack;

import android.content.Context;
import android.content.res.Resources;
import android.util.Pair;

import com.honeybadger.gamblingbadger.R;
import com.honeybadger.gamblingbadger.cards.*;
import com.honeybadger.gamblingbadger.casinosession.CasinoSession;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class BlackjackRound extends CardRound {

    // CONSTANTS:

    public static final String HIT = "H";
    public static final String STAND = "S";
    public static final String BUST = "B";
    public static final String DOUBLE_DOWN_ELSE_HIT = "Dh";
    public static final String DOUBLE_DOWN_ELSE_STAND = "Ds";
    public static final String SPLIT = "P";
    public static final String SPLIT_IF_DD_AFTER_SPLITS_ELSE_HIT = "Ph";
    public static final String SPLIT_IF_DD_AFTER_SPLITS_ELSE_STAND = "Ps";
    public static final String SPLIT_IF_DD_AFTER_SPLITS_ELSE_DH = "Pd";
    public static final String SURRENDER_ELSE_HIT = "Rh";
    public static final String SURRENDER_ELSE_STAND = "Rs";
    public static final String SURRENDER_ELSE_SPLIT = "Rp";

    private static final String HARD_4 = "4";
    private static final String HARD_5 = "5";
    private static final String HARD_6 = "6";
    private static final String HARD_7 = "7";
    private static final String HARD_8 = "8";
    private static final String HARD_9 = "9";
    private static final String HARD_10 = "10";
    private static final String HARD_11 = "11";
    private static final String HARD_12 = "12";
    private static final String HARD_13 = "13";
    private static final String HARD_14 = "14";
    private static final String HARD_15 = "15";
    private static final String HARD_16 = "16";
    private static final String HARD_17 = "17";
    private static final String HARD_18 = "18";
    private static final String HARD_19 = "19";
    private static final String HARD_20 = "20";
    private static final String HARD_21 = "21";

    private static final String SOFT_13 = "A2";
    private static final String SOFT_14 = "A3";
    private static final String SOFT_15 = "A4";
    private static final String SOFT_16 = "A5";
    private static final String SOFT_17 = "A6";
    private static final String SOFT_18 = "A7";
    private static final String SOFT_19 = "A8";
    private static final String SOFT_20 = "A9";
    private static final String SOFT_21 = "AT";

    private static final String PAIR_2 = "22";
    private static final String PAIR_3 = "33";
    private static final String PAIR_4 = "44";
    private static final String PAIR_5 = "55";
    private static final String PAIR_6 = "66";
    private static final String PAIR_7 = "77";
    private static final String PAIR_8 = "88";
    private static final String PAIR_9 = "99";
    private static final String PAIR_10 = "TT";
    private static final String PAIR_A = "AA";

    private static final int NUMBER_OF_TABLE_ENTRIES = 37;

    private static final int SCORE_TABLE_SIZE = 32;

    // VARIABLES:

    private int numberOfDecks;
    private final List<Card> userHand;
    private Card dealerCard;

    // CONSTRUCTOR:

    public BlackjackRound(BigDecimal bet, int numberOfDecks) {
        super(bet);
        this.numberOfDecks = numberOfDecks;
        this.userHand = new LinkedList<>();
    }

    public BlackjackRound(BigDecimal bet, int numberOfDecks, LinkedList userHand) { //made a second constructor for making a new blackjackround including a set userhand since BackjackFragment has both it's own dealerhand and userhand
        super(bet);
        this.numberOfDecks = numberOfDecks;
        this.userHand = userHand;
    }

    // PROPERTIES:

    //GETTERS:

    // ACTION METHODS:

    // INFO METHODS:

    public float getOdds(){
        //TODO

        return 1; //placeholder
    }

    public String getRecommendedAction(Context context) {
        return getRecommendedAction(context.getResources());
    }

    /// LEGACY:

    // PROPERTIES:

    public int getNumberOfDecks() {
        return this.numberOfDecks;
    }

    public BlackjackRound setNumberOfDecks(int numberOfDecks) {
        this.numberOfDecks = numberOfDecks;
        return this;
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

    // ACTION METHODS:

    public BlackjackRound addUserCard(Card card) {
        // Get deck
        List<Card> deck = Card.getDecks(numberOfDecks);
        deck.remove(dealerCard);
        for (Card c : userHand)
            deck.remove(c);

        // Check if card exists in deck
        if (deck.contains(card)) {
            // Card exists in deck
            userHand.add(card);
        }

        return this;
    }

    public boolean removeUserCard(Card card) {
        return userHand.remove(card);
    }

    // INFO METHODS:

    private List<Integer> getPossibleScores(final List<Card> deck, final List<Card> hand) {
        ArrayList<Integer> possibleScores = new ArrayList<>();
        recursiveScoreCheck(possibleScores, deck, hand);
        return possibleScores;
    }

    private List<Integer> getPossibleScores(final List<Card> deck, final Card card) {
        List<Card> hand = new LinkedList<>();
        hand.add(card);
        return getPossibleScores(deck, hand);
    }

    private int[] getScoreTable(final List<Card> deck, final List<Card> hand) {
        int[] scoreTable = new int[SCORE_TABLE_SIZE];  // Guaranteed to be initialized to 0 (by java spec)
        List<Integer> possibleScores = getPossibleScores(deck, hand);
        for (Integer score : possibleScores)
            scoreTable[score]++;
        return scoreTable;
    }

    private int[] getScoreTable(final List<Card> deck, final Card card) {
        List<Card> hand = new LinkedList<>();
        hand.add(card);
        return getScoreTable(deck, hand);
    }

    private void recursiveScoreCheck(ArrayList<Integer> possibleScores, final List<Card> deck, final List<Card> dealerHand) {
        // Assumes dealer stands on soft 17
        int score = getBestScore(getHardSoftScore(dealerHand));
        if (score >= 17)
            possibleScores.add(score);
        else {
            // Hit
            List<Card> hitDeck = new LinkedList<>(deck);
            List<Card> hitHand = new LinkedList<>(dealerHand);
            for (Card card : deck) {
                hitDeck.remove(card);
                hitHand.add(card);
                recursiveScoreCheck(possibleScores, hitDeck, hitHand);
                hitHand.remove(card);
                hitDeck.add(card);
            }
        }
    }

    @Override
    public float getHandStrength() {
        // Get remaining deck
        List<Card> deck = Card.getDecks(numberOfDecks);
        deck.remove(dealerCard);
        for (Card cd : userHand)
            deck.remove(cd);

        // Get user probabilities
        int[] userScoreTable = getScoreTable(deck, userHand);
        int userPossibilitySize = Arrays.stream(userScoreTable).sum();
        double[] userScoreProbabilities = new double[SCORE_TABLE_SIZE];
        assert userPossibilitySize != 0;
        for (int i = 0; i < SCORE_TABLE_SIZE; i++)
            userScoreProbabilities[i] = (double)userScoreTable[i] / (double)userPossibilitySize;

        // Get dealer probabilities
        int[] dealerScoreTable = getScoreTable(deck, dealerCard);
        int dealerPossibilitySize = Arrays.stream(userScoreTable).sum();
        double[] dealerScoreProbabilities = new double[SCORE_TABLE_SIZE];
        assert dealerPossibilitySize != 0;
        for (int i = 0; i < SCORE_TABLE_SIZE; i++)
            dealerScoreProbabilities[i] = (double)dealerScoreTable[i] / (double)dealerPossibilitySize;

        // Calculate expected value
        double expectedValue = 0;
        double matchProbability;
        double value;
        for (int us = 0; us <= SCORE_TABLE_SIZE; us++) {
            if (us > 21) {
                // User bust always loses
                expectedValue += userScoreProbabilities[us] * -1;
            }
            else {
                // User didn't bust
                for (int ds = 0; ds <= SCORE_TABLE_SIZE; ds++) {
                    matchProbability = userScoreProbabilities[us] * dealerScoreProbabilities[ds];
                    if (ds > 21 || ds < us) {
                        // User wins
                        value = 1;
                    }
                    else if (ds == us) {
                        // Push
                        value = 0;
                    }
                    else {
                        // User loses
                        value = -1;
                    }
                    expectedValue += matchProbability * value;
                }
            }
        }

        return (float)expectedValue;
    }

    private static int[] getHardSoftScore(List<Card> hand) {
        int[] scores = new int[2];
        int hardScore = 0, softScore = 0;
        boolean aceCounted = false;

        for (Card card : hand) {
            switch (card.num) {
                case A:
                    hardScore += 1;
                    softScore += 1;
                    if (!aceCounted) {
                        // Only 1 ace can have a value of 11 in a hand
                        softScore += 10;
                        aceCounted = true;
                    }
                    break;
                case N_2:
                    hardScore += 2;
                    softScore += 2;
                    break;
                case N_3:
                    hardScore += 3;
                    softScore += 3;
                    break;
                case N_4:
                    hardScore += 4;
                    softScore += 4;
                    break;
                case N_5:
                    hardScore += 5;
                    softScore += 5;
                    break;
                case N_6:
                    hardScore += 6;
                    softScore += 6;
                    break;
                case N_7:
                    hardScore += 7;
                    softScore += 7;
                    break;
                case N_8:
                    hardScore += 8;
                    softScore += 8;
                break;
                case N_9:
                    hardScore += 9;
                    softScore += 9;
                    break;
                case N_10:
                case J:
                case Q:
                case K:
                    hardScore += 10;
                    softScore += 10;
                    break;
                default:
                    break;
            }
        }

        scores[0] = hardScore;
        scores[1] = softScore;
        return scores;
    }

    private static int getBestScore(int[] hardSoftScore) {
        return hardSoftScore[1] <= 21 ? hardSoftScore[1] : hardSoftScore[0];
    }

    private int getBestScore() {
        return getBestScore(getHardSoftScore(userHand));
    }

    private static boolean hardSoftScoreHasAce(int[] hardSoftScore) {
        return hardSoftScore[0] != hardSoftScore[1];
    }

    @Override
    public float getExpectedValue(){
        return getHandStrength() * bet.floatValue();
    }

    public String getRecommendedAction(Resources resources) {
        return getRecommendedAction(resources, true);
    }

    public String getRecommendedAction(Resources resources, boolean dealerStayOnSoft17) {
        String action = "";

        // Decide table name
        String tableName;
        switch (numberOfDecks) {
            case 1:
                if (dealerStayOnSoft17)
                    tableName = "one_deck_dSos17";
                else
                    tableName = "one_deck_dHos17";
                break;
            case 2:
                if (dealerStayOnSoft17)
                    tableName = "two_deck_dSos17";
                else
                    tableName = "two_deck_dHos17";
                break;
            case 3:
                if (dealerStayOnSoft17)
                    tableName = "three_deck_dSos17";
                else
                    tableName = "three_deck_dHos17";
                break;
            case 4:
                if (dealerStayOnSoft17)
                    tableName = "four_deck_dSos17";
                else
                    tableName = "four_deck_dHos17";
                break;
            case 5:
                if (dealerStayOnSoft17)
                    tableName = "five_deck_dSos17";
                else
                    tableName = "five_deck_dHos17";
                break;
            case 6:
                if (dealerStayOnSoft17)
                    tableName = "six_deck_dSos17";
                else
                    tableName = "six_deck_dHos17";
                break;
            case 7:
                if (dealerStayOnSoft17)
                    tableName = "seven_deck_dSos17";
                else
                    tableName = "seven_deck_dHos17";
                break;
            case 8:
                if (dealerStayOnSoft17)
                    tableName = "eight_deck_dSos17";
                else
                    tableName = "eight_deck_dHos17";
                break;
            default:
                    tableName = "";
                break;
        }

        // Parse xml into readable table
        XmlPullParser parser = resources.getXml(R.xml.blackjack_tables);
        HashMap<String, HashMap<ECardNum, String>> table = parseXml(parser, tableName);

        // Decide action

        if (userHand.size() == 2) {
            // Test for pair
            if (userHand.get(0).num == userHand.get(1).num) {
                // Use split section
                switch (userHand.get(0).num) {
                    case N_2: action = Objects.requireNonNull(table.get(PAIR_2)).get(dealerCard.num); break;
                    case N_3: action = Objects.requireNonNull(table.get(PAIR_3)).get(dealerCard.num); break;
                    case N_4: action = Objects.requireNonNull(table.get(PAIR_4)).get(dealerCard.num); break;
                    case N_5: action = Objects.requireNonNull(table.get(PAIR_5)).get(dealerCard.num); break;
                    case N_6: action = Objects.requireNonNull(table.get(PAIR_6)).get(dealerCard.num); break;
                    case N_7: action = Objects.requireNonNull(table.get(PAIR_7)).get(dealerCard.num); break;
                    case N_8: action = Objects.requireNonNull(table.get(PAIR_8)).get(dealerCard.num); break;
                    case N_9: action = Objects.requireNonNull(table.get(PAIR_9)).get(dealerCard.num); break;
                    case N_10:
                    case J:
                    case Q:
                    case K: action = Objects.requireNonNull(table.get(PAIR_10)).get(dealerCard.num); break;
                    case A: action = Objects.requireNonNull(table.get(PAIR_A)).get(dealerCard.num); break;
                    default: action = BUST; break;
                }
            }
        }

        assert action != null;
        if (action.isEmpty()) {
            // No pair
            int[] hardSoftScore = getHardSoftScore(userHand);
            int score = getBestScore(hardSoftScore);
            if (hardSoftScoreHasAce(hardSoftScore)) {
                // Use soft section
                switch (score) {
                    case 13: action = Objects.requireNonNull(table.get(SOFT_13)).get(dealerCard.num); break;
                    case 14: action = Objects.requireNonNull(table.get(SOFT_14)).get(dealerCard.num); break;
                    case 15: action = Objects.requireNonNull(table.get(SOFT_15)).get(dealerCard.num); break;
                    case 16: action = Objects.requireNonNull(table.get(SOFT_16)).get(dealerCard.num); break;
                    case 17: action = Objects.requireNonNull(table.get(SOFT_17)).get(dealerCard.num); break;
                    case 18: action = Objects.requireNonNull(table.get(SOFT_18)).get(dealerCard.num); break;
                    case 19: action = Objects.requireNonNull(table.get(SOFT_19)).get(dealerCard.num); break;
                    case 20: action = Objects.requireNonNull(table.get(SOFT_20)).get(dealerCard.num); break;
                    case 21: action = Objects.requireNonNull(table.get(SOFT_21)).get(dealerCard.num); break;
                    default: action = BUST; break;
                }
            }
            else {
                // Use hard section
                switch (score) {
                    case 4: action = Objects.requireNonNull(table.get(HARD_4)).get(dealerCard.num); break;
                    case 5: action = Objects.requireNonNull(table.get(HARD_5)).get(dealerCard.num); break;
                    case 6: action = Objects.requireNonNull(table.get(HARD_6)).get(dealerCard.num); break;
                    case 7: action = Objects.requireNonNull(table.get(HARD_7)).get(dealerCard.num); break;
                    case 8: action = Objects.requireNonNull(table.get(HARD_8)).get(dealerCard.num); break;
                    case 9: action = Objects.requireNonNull(table.get(HARD_9)).get(dealerCard.num); break;
                    case 10: action = Objects.requireNonNull(table.get(HARD_10)).get(dealerCard.num); break;
                    case 11: action = Objects.requireNonNull(table.get(HARD_11)).get(dealerCard.num); break;
                    case 12: action = Objects.requireNonNull(table.get(HARD_12)).get(dealerCard.num); break;
                    case 13: action = Objects.requireNonNull(table.get(HARD_13)).get(dealerCard.num); break;
                    case 14: action = Objects.requireNonNull(table.get(HARD_14)).get(dealerCard.num); break;
                    case 15: action = Objects.requireNonNull(table.get(HARD_15)).get(dealerCard.num); break;
                    case 16: action = Objects.requireNonNull(table.get(HARD_16)).get(dealerCard.num); break;
                    case 17: action = Objects.requireNonNull(table.get(HARD_17)).get(dealerCard.num); break;
                    case 18: action = Objects.requireNonNull(table.get(HARD_18)).get(dealerCard.num); break;
                    case 19: action = Objects.requireNonNull(table.get(HARD_19)).get(dealerCard.num); break;
                    case 20: action = Objects.requireNonNull(table.get(HARD_20)).get(dealerCard.num); break;
                    case 21: action = Objects.requireNonNull(table.get(HARD_21)).get(dealerCard.num); break;
                    default: action = BUST; break;
                }
            }
        }

        return action;
    }

    private static HashMap<String, HashMap<ECardNum, String>> parseXml(XmlPullParser parser, String targetTable) {
        HashMap<String, HashMap<ECardNum, String>> keys = new HashMap<>();
        Pair<String, HashMap<ECardNum, String>> hand;

        try {
            while (parser.next() != XmlPullParser.END_DOCUMENT) {
                if (parser.getEventType() != XmlPullParser.START_TAG)
                    continue;

                if (parser.getName().equals("table")) {
                    if (parser.getAttributeValue(null, "name").equals(targetTable)) {
                        for (int i = 0; i < NUMBER_OF_TABLE_ENTRIES; i++) {
                            while (parser.next() != XmlPullParser.START_TAG) ;
                            hand = parseXmlHand(parser);
                            keys.put(hand.first, hand.second);
                        }
                    }
                }
            }
        }
        catch (Exception ignored) {}

        return keys;
    }

    private static Pair<String, HashMap<ECardNum, String>> parseXmlHand(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, null, "hand");

        // Set key
        String key = parser.getAttributeValue(null, "key");

        // Fill map
        HashMap<ECardNum, String> hand = new HashMap<>();
        hand.put(ECardNum.N_2, parser.getAttributeValue(null, "d2"));
        hand.put(ECardNum.N_3, parser.getAttributeValue(null, "d3"));
        hand.put(ECardNum.N_4, parser.getAttributeValue(null, "d4"));
        hand.put(ECardNum.N_5, parser.getAttributeValue(null, "d5"));
        hand.put(ECardNum.N_6, parser.getAttributeValue(null, "d6"));
        hand.put(ECardNum.N_7, parser.getAttributeValue(null, "d7"));
        hand.put(ECardNum.N_8, parser.getAttributeValue(null, "d8"));
        hand.put(ECardNum.N_9, parser.getAttributeValue(null, "d9"));
        hand.put(ECardNum.N_10, parser.getAttributeValue(null, "dt"));
        hand.put(ECardNum.J, parser.getAttributeValue(null, "dt"));
        hand.put(ECardNum.Q, parser.getAttributeValue(null, "dt"));
        hand.put(ECardNum.K, parser.getAttributeValue(null, "dt"));
        hand.put(ECardNum.A, parser.getAttributeValue(null, "da"));

        // Burn to next tag
        while (parser.next() != XmlPullParser.END_TAG);

        return new Pair<>(key, hand);
    }
}
