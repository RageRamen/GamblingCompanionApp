package com.honeybadger.gamblingbadger.gamesession;

public enum EGameType {
    GT_BLACKJACK(0),
    GT_POKER(1),
    GT_TEXAS_HOLDEM(2),
    GT_ROULETTE(3),
    GT_AMERICAN_ROULETTE(4);

    public final int INDEX;

    private EGameType(int INDEX) {
        this.INDEX = INDEX;
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
