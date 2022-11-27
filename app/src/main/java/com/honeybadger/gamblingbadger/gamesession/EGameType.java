package com.honeybadger.gamblingbadger.gamesession;

public enum EGameType {
    GT_BLACKJACK(0, "Blackjack"),
    GT_POKER(1, "Poker"),
    GT_TEXAS_HOLDEM(2, "Texas Hole'em"),
    GT_ROULETTE(3, "Roulette");

    public final int INDEX;
    public final String NAME;

    private EGameType(int INDEX, String name) {
        this.INDEX = INDEX;
        this.NAME = name;
    }

    public static EGameType fromIndex(int index) {
        EGameType value = GT_BLACKJACK;

        for (EGameType gt : EGameType.values()) {
            if (gt.INDEX == index){
                value = gt;
                break;
            }
        }

        return value;
    }

    public static int getNumberOfGames() {
        return EGameType.values().length;
    }
}
