package com.honeybadger.gamblingbadger.gamesession.round;

import android.content.res.Resources;
import android.util.Pair;

import com.honeybadger.gamblingbadger.R;
import com.honeybadger.gamblingbadger.gamesession.card.Card;
import com.honeybadger.gamblingbadger.gamesession.card.ECardNum;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class BlackjackRound extends CardRound {
    // Constants

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
        int[] scores = getLowHighScore(userHand);
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

    private static int[] getLowHighScore(List<Card> hand) {
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

    private static boolean scoreHasAce(int[] score) {
        return score[0] != score[1];
    }

    private static int getBestScore(int[] score) {
        return score[1] <= 21 ? score[1] : score[0];
    }

    @Override
    public BigDecimal getExpectedValue(){
        // TODO
        return BigDecimal.ZERO;
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
                    case CN_2: action = Objects.requireNonNull(table.get(PAIR_2)).get(dealerCard.num); break;
                    case CN_3: action = Objects.requireNonNull(table.get(PAIR_3)).get(dealerCard.num); break;
                    case CN_4: action = Objects.requireNonNull(table.get(PAIR_4)).get(dealerCard.num); break;
                    case CN_5: action = Objects.requireNonNull(table.get(PAIR_5)).get(dealerCard.num); break;
                    case CN_6: action = Objects.requireNonNull(table.get(PAIR_6)).get(dealerCard.num); break;
                    case CN_7: action = Objects.requireNonNull(table.get(PAIR_7)).get(dealerCard.num); break;
                    case CN_8: action = Objects.requireNonNull(table.get(PAIR_8)).get(dealerCard.num); break;
                    case CN_9: action = Objects.requireNonNull(table.get(PAIR_9)).get(dealerCard.num); break;
                    case CN_10:
                    case CN_J:
                    case CN_Q:
                    case CN_K: action = Objects.requireNonNull(table.get(PAIR_10)).get(dealerCard.num); break;
                    case CN_A: action = Objects.requireNonNull(table.get(PAIR_A)).get(dealerCard.num); break;
                    default: action = BUST; break;
                }
            }
        }

        assert action != null;
        if (action.isEmpty()) {
            // No pair
            int[] lowHighScore = getLowHighScore(userHand);
            int score = getBestScore(lowHighScore);
            if (scoreHasAce(lowHighScore)) {
                // Use soft section
                switch (score) {
                    case 12: action = HIT;  // In the case of 2 aces with no splits allowed
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

        while (parser.next() != XmlPullParser.END_DOCUMENT) {
            if (parser.getEventType() != XmlPullParser.START_TAG)
                continue;

            if (parser.getName().equals("table")) {
                if (parser.getAttributeValue(null, "name").equals(targetTable)) {
                    try {
                        while (true) {
                            hand = parseXmlHand(parser);
                            keys.put(hand.first, hand.second);
                        }
                    }
                    catch (Exception e) {}
                }
            }
        }

        return keys;
    }

    private static Pair<String, HashMap<ECardNum, String>> parseXmlHand(XmlPullParser parser) throws XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, null, "hand");

        // Set key
        String key = parser.getAttributeValue(null, "key");

        // Fill map
        HashMap<ECardNum, String> hand = new HashMap<>();
        hand.put(ECardNum.CN_2, parser.getAttributeValue(null, "d2"));
        hand.put(ECardNum.CN_3, parser.getAttributeValue(null, "d3"));
        hand.put(ECardNum.CN_4, parser.getAttributeValue(null, "d4"));
        hand.put(ECardNum.CN_5, parser.getAttributeValue(null, "d5"));
        hand.put(ECardNum.CN_6, parser.getAttributeValue(null, "d6"));
        hand.put(ECardNum.CN_7, parser.getAttributeValue(null, "d7"));
        hand.put(ECardNum.CN_8, parser.getAttributeValue(null, "d8"));
        hand.put(ECardNum.CN_9, parser.getAttributeValue(null, "d9"));
        hand.put(ECardNum.CN_10, parser.getAttributeValue(null, "dt"));
        hand.put(ECardNum.CN_J, parser.getAttributeValue(null, "dt"));
        hand.put(ECardNum.CN_Q, parser.getAttributeValue(null, "dt"));
        hand.put(ECardNum.CN_K, parser.getAttributeValue(null, "dt"));
        hand.put(ECardNum.CN_A, parser.getAttributeValue(null, "da"));

        // Burn to next tag
        while (parser.next() != XmlPullParser.START_TAG);

        return new Pair<>(key, hand);
    }
}
