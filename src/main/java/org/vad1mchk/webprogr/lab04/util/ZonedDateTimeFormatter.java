package org.vad1mchk.webprogr.lab04.util;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public final class ZonedDateTimeFormatter {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MMM d, yyyy – HH:mm:ss (O)");

    public static String toString(ZonedDateTime dateTime) {
        if (dateTime == null) return null;
        return FORMATTER.format(dateTime);
    }
}
