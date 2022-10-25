package com.honeybadger.gamblingbadger.gamesession.roulette;

public class RouletteResult {
    // CONSTANTS:

    public static final int ZERO = 0;
    public static final int DOUBLE_ZERO = -1;

    // VARIABLES:

    public int number;

    // CONSTRUCTORS:

    public RouletteResult(int number) {
        this.number = number;
    }

    // TESTING METHODS:

    public boolean isZero() {
        return number == ZERO;
    }

    public boolean isDoubleZero() {
        return number == DOUBLE_ZERO;
    }

    public boolean isRed() {
        switch (number) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 9:
            case 12:
            case 14:
            case 16:
            case 18:
            case 19:
            case 21:
            case 23:
            case 25:
            case 27:
            case 30:
            case 32:
            case 34:
            case 36:
                return true;
            default:
                return false;
        }
    }

    public boolean isBlack() {
        switch (number) {
            case 2:
            case 4:
            case 6:
            case 8:
            case 10:
            case 11:
            case 13:
            case 15:
            case 17:
            case 20:
            case 22:
            case 24:
            case 26:
            case 28:
            case 29:
            case 31:
            case 33:
            case 35:
                return true;
            default:
                return false;
        }
    }

    public boolean isEven() {
        return (number % 2) == 0;
    }

    public boolean isOdd() {
        return (number % 2) == 1;
    }

    public boolean isLesserHalf() {
        return (1 <= number) && (number <= 18);
    }

    public boolean isGreaterHalf() {
        return (19 <= number) && (number <= 36);
    }

    public boolean isFirstTwelve() {
        return (1 <= number) && (number <= 12);
    }

    public boolean isSecondTwelve() {
        return (13 <= number) && (number <= 24);
    }

    public boolean isThirdTwelve() {
        return (25 <= number) && (number <= 36);
    }

    public boolean isFirstColumn() {
        switch (number) {
            case 1:
            case 4:
            case 7:
            case 10:
            case 13:
            case 16:
            case 19:
            case 22:
            case 25:
            case 28:
            case 31:
            case 34:
                return true;
            default:
                return false;
        }
    }

    public boolean isSecondColumn() {
        switch (number) {
            case 2:
            case 5:
            case 8:
            case 11:
            case 14:
            case 17:
            case 20:
            case 23:
            case 26:
            case 29:
            case 32:
            case 35:
                return true;
            default:
                return false;
        }
    }

    public boolean isThirdColumn() {
        switch (number) {
            case 3:
            case 6:
            case 9:
            case 12:
            case 15:
            case 18:
            case 21:
            case 24:
            case 27:
            case 30:
            case 33:
            case 36:
                return true;
            default:
                return false;
        }
    }
}
