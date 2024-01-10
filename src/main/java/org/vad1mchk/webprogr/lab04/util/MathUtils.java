package org.vad1mchk.webprogr.lab04.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class MathUtils {
    public static BigDecimal roundToSignificantDigits(BigDecimal num, int significantDigits) {
        if (num.compareTo(BigDecimal.ZERO) == 0) {
            return num;
        }
        int newScale = significantDigits - num.precision() + num.scale();

        return num.scale() <= newScale ? num : num.setScale(newScale, RoundingMode.DOWN);
    }
}
