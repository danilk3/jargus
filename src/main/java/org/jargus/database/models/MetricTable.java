package org.jargus.database.models;

import org.jargus.common.model.DataPoint;
import org.jargus.database.exception.UnsupportedGranularityException;
import org.jargus.database.utils.TimestampRounder;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author Kotelnikov D.M.
 */
public class MetricTable {

    private final SortedMap<Long, Double> countSeconds = new TreeMap<>();
    private final SortedMap<Long, Double> countMinutes = new TreeMap<>();
    private final SortedMap<Long, Double> countHours = new TreeMap<>();

    public synchronized void addDataPoint(DataPoint dataPoint) {
        long timestamp = dataPoint.timestamp();
        double value = dataPoint.value();
        countSeconds.put(TimestampRounder.upToSeconds(timestamp), value);
        countMinutes.put(TimestampRounder.upToMinutes(timestamp), value);
        countHours.put(TimestampRounder.upToHours(timestamp), value);
    }

    public Map<Long, Double> readDataPoints(Granularity granularity, long fromTime, long toTime) {
        return switch (granularity) {
            case SECONDS ->
                    readDataPointsByGranularity(countSeconds, TimestampRounder.upToSeconds(fromTime), TimestampRounder.upToSeconds(toTime));
            case MINUTES ->
                    readDataPointsByGranularity(countMinutes, TimestampRounder.upToMinutes(fromTime), TimestampRounder.upToMinutes(toTime));
            case HOURS ->
                    readDataPointsByGranularity(countHours, TimestampRounder.upToHours(fromTime), TimestampRounder.upToHours(toTime));
            default -> throw new UnsupportedGranularityException(granularity);
        };
    }

    public Map<Long, Double> readTailDataPoints(Granularity granularity, long fromTime) {
        return switch (granularity) {
            case SECONDS ->
                    readTailDataPointsByGranularity(countSeconds, TimestampRounder.upToSeconds(fromTime));
            case MINUTES ->
                    readTailDataPointsByGranularity(countMinutes, TimestampRounder.upToMinutes(fromTime));
            case HOURS ->
                    readTailDataPointsByGranularity(countHours, TimestampRounder.upToHours(fromTime));
            default -> throw new UnsupportedGranularityException(granularity);
        };
    }

    public Map<Long, Double> readHeadDataPoints(Granularity granularity, long fromTime) {
        return switch (granularity) {
            case SECONDS ->
                    readHeadDataPointsByGranularity(countSeconds, TimestampRounder.upToSeconds(fromTime));
            case MINUTES ->
                    readHeadDataPointsByGranularity(countMinutes, TimestampRounder.upToMinutes(fromTime));
            case HOURS ->
                    readHeadDataPointsByGranularity(countHours, TimestampRounder.upToHours(fromTime));
            default -> throw new UnsupportedGranularityException(granularity);
        };
    }

    private Map<Long, Double> readHeadDataPointsByGranularity(SortedMap<Long, Double> countSeconds, long toKey) {
        return countSeconds.headMap(toKey);
    }

    private Map<Long, Double> readTailDataPointsByGranularity(SortedMap<Long, Double> countSeconds, long fromKey) {
        return countSeconds.tailMap(fromKey);
    }

    private Map<Long, Double> readDataPointsByGranularity(SortedMap<Long, Double> countSeconds, long fromKey, long toKey) {
        return countSeconds.subMap(fromKey, toKey);
    }

    public void cleanUp(long secondsDateTill,
                        long minutesDateTill,
                        long hoursDateTill) {
        long secondsKey = TimestampRounder.upToSeconds(secondsDateTill);
        for (Map.Entry<Long, Double> entry : countSeconds.entrySet()) {
            countSeconds.remove(entry.getKey());
            if (entry.getKey() >= secondsKey) {
                break;
            }
        }

        long minutesKey = TimestampRounder.upToMinutes(minutesDateTill);
        for (Map.Entry<Long, Double> entry : countMinutes.entrySet()) {
            countMinutes.remove(entry.getKey());
            if (entry.getKey() >= minutesKey) {
                break;
            }
        }

        long hoursKey = TimestampRounder.upToHours(hoursDateTill);
        for (Map.Entry<Long, Double> entry : countHours.entrySet()) {
            countHours.remove(entry.getKey());
            if (entry.getKey() >= hoursKey) {
                break;
            }
        }
    }
}
