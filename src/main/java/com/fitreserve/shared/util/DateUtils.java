package com.fitreserve.shared.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    public static LocalDateTime parse(String dateTime) {
        return LocalDateTime.parse(dateTime, FORMATTER);
    }

    public static String format(LocalDateTime dateTime) {
        return dateTime.format(FORMATTER);
    }
}