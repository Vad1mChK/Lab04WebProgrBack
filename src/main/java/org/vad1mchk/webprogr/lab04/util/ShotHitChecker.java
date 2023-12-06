package org.vad1mchk.webprogr.lab04.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Utility class for checking if a shot hits a target area.
 */
public final class ShotHitChecker {
    private static final BigDecimal TWO = new BigDecimal(2);

    /**
     * Checks if the shot falls within the target area.
     *
     * @param x the x-coordinate of the shot.
     * @param y the y-coordinate of the shot.
     * @param r the radius defining the target area.
     * @return true if the shot is within the target area, false otherwise.
     * @throws IllegalArgumentException if the radius is non-positive.
     */
    public static boolean check(BigDecimal x, BigDecimal y, BigDecimal r) {
        if (r.signum() <= 0) {
            throw new IllegalArgumentException("Radius must be positive.");
        }

        int xSign = x.signum();
        int ySign = y.signum();

        return (xSign >= 0 && ySign >= 0) && checkFirstQuadrant(x, y, r) ||
                (xSign <= 0 && ySign >= 0) && checkSecondQuadrant(x, y, r) ||
                (xSign <= 0 && ySign <= 0) && checkThirdQuadrant(x, y, r) ||
                (xSign >= 0 && ySign <= 0) && checkFourthQuadrant(x, y, r);
    }

    /**
     * Checks if the shot in the first quadrant (x >= 0, y >= 0) falls within the target area.
     *
     * @param x the x-coordinate of the shot.
     * @param y the y-coordinate of the shot.
     * @param r the radius defining the target area.
     * @return true if the shot is within the target area, false otherwise.
     */
    private static boolean checkFirstQuadrant(BigDecimal x, BigDecimal y, BigDecimal r) {
        BigDecimal halfR = r
                .setScale(r.scale() + 1, RoundingMode.HALF_UP)
                .divide(TWO, RoundingMode.HALF_UP);
        return x.compareTo(halfR) <= 0 && y.compareTo(r) <= 0;
    }

    /**
     * Checks if the shot in the second quadrant (x <= 0, y >= 0) falls within the target area.
     *
     * @param x the x-coordinate of the shot.
     * @param y the y-coordinate of the shot.
     * @param r the radius defining the target area.
     * @return true if the shot is within the target area, false otherwise.
     */
    private static boolean checkSecondQuadrant(BigDecimal x, BigDecimal y, BigDecimal r) {
        return false; // Area is empty
    }

    /**
     * Checks if the shot in the third quadrant (x <= 0, y <= 0) falls within the target area.
     *
     * @param x the x-coordinate of the shot.
     * @param y the y-coordinate of the shot.
     * @param r the radius defining the target area.
     * @return true if the shot is within the target area, false otherwise.
     */
    private static boolean checkThirdQuadrant(BigDecimal x, BigDecimal y, BigDecimal r) {
        BigDecimal halfR = r
                .setScale(r.scale() + 1, RoundingMode.HALF_UP)
                .divide(TWO, RoundingMode.HALF_UP);
        return x.multiply(x).add(y.multiply(y)).compareTo(halfR.multiply(halfR)) <= 0;
    }

    /**
     * Checks if the shot in the fourth quadrant (x >= 0, y <= 0) falls within the target area.
     *
     * @param x the x-coordinate of the shot.
     * @param y the y-coordinate of the shot.
     * @param r the radius defining the target area.
     * @return true if the shot is within the target area, false otherwise.
     */
    private static boolean checkFourthQuadrant(BigDecimal x, BigDecimal y, BigDecimal r) {
        return y.compareTo(r.negate().add(x)) >= 0;
    }
}
