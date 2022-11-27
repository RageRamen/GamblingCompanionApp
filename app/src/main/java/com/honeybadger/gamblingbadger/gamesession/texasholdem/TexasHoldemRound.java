package com.honeybadger.gamblingbadger.gamesession.texasholdem;

import com.honeybadger.gamblingbadger.cards.Card;
import com.honeybadger.gamblingbadger.cards.CardRound;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TexasHoldemRound extends CardRound {
    // VARIABLES:

    // CONSTRUCTOR:

    TexasHoldemRound(BigDecimal bet) {
        super(bet);
    }

    // PROPERTIES:

    // ACTION METHODS:

    // INFO METHODS:

    @Override
    public float getHandStrength() {
        // TODO
        return 0f;
    }

    @Override
    public float getExpectedValue() {
        // TODO
        return 0f;
    }

    public float getOdds() {
        // TODO
        return 0f;
    }
}
