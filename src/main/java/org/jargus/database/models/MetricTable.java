package org.jargus.database.models;

import org.jargus.common.model.DataPoint;
import org.jargus.common.model.Label;
import org.jargus.database.exception.UnsupportedGranularityException;
import org.jargus.database.utils.TimestampRounder;

import java.util.*;

/**
 * @author Kotelnikov D.M.
 */
public class MetricTable {

    private final String metricName;

    public MetricTable(String metricName) {
        this.metricName = metricName;
    }

    private final SortedMap<Long, MetricLabelsValueEntry> countSeconds = new TreeMap<>();
    private final SortedMap<Long, MetricLabelsValueEntry> countMinutes = new TreeMap<>();
    private final SortedMap<Long, MetricLabelsValueEntry> countHours = new TreeMap<>();

    public synchronized void addDataPoint(DataPoint dataPoint, List<Label> labels) {
        long timestamp = dataPoint.timestamp();
        double value = dataPoint.value();
        countSeconds.put(TimestampRounder.upToSeconds(timestamp), new MetricLabelsValueEntry(value, labels));
        countMinutes.put(TimestampRounder.upToMinutes(timestamp), new MetricLabelsValueEntry(value, labels));
        countHours.put(TimestampRounder.upToHours(timestamp), new MetricLabelsValueEntry(value, labels));
    }

    public Map<Long, MetricLabelsValueEntry> readDataPoints(Granularity granularity, Optional<Long> fromTime, Optional<Long> toTime) {
        if (fromTime.isPresent() && toTime.isPresent()) {
            return readDataPointsInterval(granularity, fromTime.get(), toTime.get());
        }
        if (fromTime.isPresent()) {
            return readTailDataPoints(granularity, fromTime.get());
        }
        if (toTime.isPresent()) {
            return readHeadDataPoints(granularity, toTime.get());
        }
        return readDataPoints(granularity);
    }

    private Map<Long, MetricLabelsValueEntry> readDataPointsInterval(Granularity granularity, long fromTime, long toTime) {
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

    private Map<Long, MetricLabelsValueEntry> readTailDataPoints(Granularity granularity, long fromTime) {
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

    private Map<Long, MetricLabelsValueEntry> readHeadDataPoints(Granularity granularity, long fromTime) {
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

    private Map<Long, MetricLabelsValueEntry> readDataPoints(Granularity granularity) {
        return switch (granularity) {
            case SECONDS -> countSeconds;
            case MINUTES -> countMinutes;
            case HOURS -> countHours;
            default -> throw new UnsupportedGranularityException(granularity);
        };
    }

    private Map<Long, MetricLabelsValueEntry> readHeadDataPointsByGranularity(SortedMap<Long, MetricLabelsValueEntry> countSeconds, long toKey) {
        return countSeconds.headMap(toKey);
    }

    private Map<Long, MetricLabelsValueEntry> readTailDataPointsByGranularity(SortedMap<Long, MetricLabelsValueEntry> countSeconds, long fromKey) {
        return countSeconds.tailMap(fromKey);
    }

    private Map<Long, MetricLabelsValueEntry> readDataPointsByGranularity(SortedMap<Long, MetricLabelsValueEntry> countSeconds, long fromKey, long toKey) {
        return countSeconds.subMap(fromKey, toKey);
    }

    public void cleanUp(long secondsDateTill,
                        long minutesDateTill,
                        long hoursDateTill) {
        long secondsKey = TimestampRounder.upToSeconds(secondsDateTill);
        for (Long key : countSeconds.keySet()) {
            if (key >= secondsKey) {
                break;
            }
            countSeconds.remove(key);
        }

        long minutesKey = TimestampRounder.upToMinutes(minutesDateTill);
        for (Long key : countMinutes.keySet()) {
            if (key >= minutesKey) {
                break;
            }
            countMinutes.remove(key);
        }

        long hoursKey = TimestampRounder.upToHours(hoursDateTill);
        for (Long key : countHours.keySet()) {
            if (key >= hoursKey) {
                break;
            }
            countHours.remove(key);
        }
    }

    public String getMetricName() {
        return metricName;
    }
}
