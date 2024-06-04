package org.jargus.database.service;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.google.common.collect.Sets;
import lombok.NoArgsConstructor;
import org.jargus.common.model.DataPoint;
import org.jargus.common.model.Label;
import org.jargus.common.model.Metric;
import org.jargus.configuration.model.TsDbConfig;
import org.jargus.database.models.Granularity;
import org.jargus.database.models.MetricLabelsValueEntry;
import org.jargus.database.models.MetricTable;

import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @author Kotelnikov D.M.
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@NoArgsConstructor
public class TsStorage {

    private Map<Integer, MetricTable> metrics = new ConcurrentHashMap<>();
    private Map<Integer, Set<Integer>> labelOccurrencesMap = new ConcurrentHashMap<>();
    private Duration metricSecondsTtl;
    private Duration metricMinutesTtl;
    private Duration metricHoursTtl;

    public TsStorage(TsDbConfig tsDbConfig) {
        this.metricSecondsTtl = tsDbConfig.getMetricSecondsTtl();
        this.metricMinutesTtl = tsDbConfig.getMetricMinutesTtl();
        this.metricHoursTtl = tsDbConfig.getMetricHoursTtl();
    }

    public List<Metric> readMetrics(Granularity granularity,
                                    Optional<Long> fromTime,
                                    Optional<Long> toTime,
                                    String metricName,
                                    List<Label> labels) {
        Map<Long, MetricLabelsValueEntry> resultMap = new TreeMap<>();
        Set<Integer> metricTablesToSearch = new TreeSet<>();

        if (labels.isEmpty()) {
            metricTablesToSearch = labelOccurrencesMap.getOrDefault(metricName.hashCode(), Set.of());
        } else {
            for (Label label : labels) {
                int labelOccurrenceKey = (metricName + label.name() + label.value()).hashCode();
                if (metricTablesToSearch.isEmpty()) {
                    metricTablesToSearch = labelOccurrencesMap.getOrDefault(labelOccurrenceKey, Set.of());
                    continue;
                }
                metricTablesToSearch = Sets.intersection(metricTablesToSearch,
                        labelOccurrencesMap.getOrDefault(labelOccurrenceKey, Set.of()));
            }
        }

        for (Integer metricTableKey : metricTablesToSearch) {
            MetricTable metricTable = metrics.computeIfAbsent(metricTableKey, key -> new MetricTable(metricName));
            resultMap.putAll(metricTable.readDataPoints(granularity, fromTime, toTime));
        }

        return resultMap.entrySet().stream()
                .map(entry -> new Metric(
                        metricName,
                        entry.getValue().labels(),
                        new DataPoint(entry.getValue().value(), entry.getKey()))
                ).toList();
    }

    public List<Metric> readAll() {
        List<Metric> result = new ArrayList<>();
        for (MetricTable metricTable : metrics.values()) {
            Map<Long, MetricLabelsValueEntry> metricsList = metricTable.readDataPoints(Granularity.SECONDS, Optional.empty(), Optional.empty());
            result.addAll(
                    metricsList.entrySet().stream()
                            .map(entry ->
                                    new Metric(metricTable.getMetricName(),
                                            entry.getValue().labels(),
                                            new DataPoint(entry.getValue().value(), entry.getKey())))
                            .toList()
            );
        }
        return result;
    }

    public void addDataPoint(Metric metric) {
        StringBuilder metricTableKeyString = new StringBuilder(metric.name());

        for (Label label : metric.labels()) {
            metricTableKeyString.append(label.name()).append(label.value());
        }

        int metricTableKey = metricTableKeyString.toString().hashCode();
        MetricTable metricTable = metrics.computeIfAbsent(metricTableKey, key -> new MetricTable(metric.name()));
        metricTable.addDataPoint(metric.datapoint(), metric.labels());

        for (Label label : metric.labels()) {
            int labelOccurrenceKey = (metric.name() + label.name() + label.value()).hashCode();
            Set<Integer> labelOccurrences = labelOccurrencesMap
                    .computeIfAbsent(labelOccurrenceKey, key -> new TreeSet<>());
            labelOccurrences.add(metricTableKey);
        }

        Set<Integer> labelOccurrences = labelOccurrencesMap
                .computeIfAbsent(metric.name().hashCode(), key -> new TreeSet<>());
        labelOccurrences.add(metricTableKey);
        cleanUpMetrics();
    }

    public void cleanUpMetrics() {
        long now = Instant.now().toEpochMilli();
        long secondsDateTill = now - metricSecondsTtl.toMillis();
        long minutesDateTill = now - metricMinutesTtl.toMillis();
        long hoursDateTill = now - metricHoursTtl.toMillis();
        for (MetricTable metricTable : metrics.values()) {
            metricTable.cleanUp(secondsDateTill, minutesDateTill, hoursDateTill);
        }
    }
}
