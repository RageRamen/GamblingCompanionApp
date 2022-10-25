package com.honeybadger.gamblingbadger.gamesession.roulette.roulettebet;

public class RouletteResultHelper {
    // CONSTANTS:

    public static final int ZERO = 0;
    public static final int DOUBLE_ZERO = -1;

    // TESTING METHODS:

    public static boolean isZero(int number) {
        return number == ZERO;
    }

    public static boolean isDoubleZero(int number) {
        return number == DOUBLE_ZERO;
    }

    public static boolean isRed(int number) {
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

    public static boolean isBlack(int number) {
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

    public static boolean isEven(int number) {
        return (number % 2) == 0;
    }

    public static boolean isOdd(int number) {
        return (number % 2) == 1;
    }

    public static boolean isLesserHalf(int number) {
        return (1 <= number) && (number <= 18);
    }

    public static boolean isGreaterHalf(int number) {
        return (19 <= number) && (number <= 36);
    }

    public static boolean isFirstTwelve(int number) {
        return (1 <= number) && (number <= 12);
    }

    public static boolean isSecondTwelve(int number) {
        return (13 <= number) && (number <= 24);
    }

    public static boolean isThirdTwelve(int number) {
        return (25 <= number) && (number <= 36);
    }

    public static boolean isFirstColumn(int number) {
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

    public static boolean isSecondColumn(int number) {
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

    public static boolean isThirdColumn(int number) {
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
