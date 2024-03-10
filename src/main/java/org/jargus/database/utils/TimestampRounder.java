package org.jargus.database.utils;

/**
 * @author Kotelnikov D.M.
 */
public class TimestampRounder {
    public static long upToSeconds(long timestamp) {
        return 1000 * (timestamp / 1000);
    }

    public static long upToMinutes(long timestamp) {
        return 60000 * (timestamp / 60000);
    }

    public static long upToHours(long timestamp) {
        return 3600000 * (timestamp / 3600000);
    }
}
