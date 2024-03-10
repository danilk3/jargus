package org.jargus.database.service;

import org.jargus.common.model.DataPoint;
import org.jargus.common.model.Label;
import org.jargus.common.model.Metric;
import org.jargus.database.models.Granularity;
import org.jargus.database.utils.TimestampRounder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Kotelnikov D.M.
 */
public class TsStorageTest {

    private TsStorage tsStorage;

    @BeforeEach
    public void init() {
        tsStorage = new TsStorage(Duration.of(1, TimeUnit.DAYS.toChronoUnit()),
                Duration.of(1, TimeUnit.DAYS.toChronoUnit()),
                Duration.of(1, TimeUnit.DAYS.toChronoUnit()));
    }

    @Test
    public void readOneMetricTest() {
        Metric metric = new Metric("metric1",
                List.of(new Label("labelName", "value")),
                new DataPoint(1.0, Instant.now().toEpochMilli()));

        tsStorage.addDataPoint(metric);

        Map<Long, Double> result = tsStorage.readMetrics(Granularity.SECONDS,
                metric.datapoint().timestamp() - 10000,
                metric.datapoint().timestamp() + 1000,
                metric.name(),
                metric.labels());

        Assertions.assertNotNull(result.get(TimestampRounder.upToSeconds(metric.datapoint().timestamp())));
        Assertions.assertEquals(metric.datapoint().value(), result.get(TimestampRounder.upToSeconds(metric.datapoint().timestamp())));
    }

    @Test
    public void readMetricByLabelFromMultipleLabelsTest() {
        Metric metric = new Metric("metric1",
                List.of(
                        new Label("labelName", "value"),
                        new Label("labelName1", "value1"),
                        new Label("labelName2", "value2"),
                        new Label("labelName3", "value3")),
                new DataPoint(1.0, Instant.now().toEpochMilli()));
        tsStorage.addDataPoint(metric);

        List<Label> labelsToSearch = List.of(new Label("labelName2", "value2"));
        Map<Long, Double> result = tsStorage.readMetrics(Granularity.SECONDS,
                metric.datapoint().timestamp() - 10000,
                metric.datapoint().timestamp() + 1000,
                metric.name(),
                labelsToSearch);

        Assertions.assertNotNull(result.get(TimestampRounder.upToSeconds(metric.datapoint().timestamp())));
        Assertions.assertEquals(metric.datapoint().value(), result.get(TimestampRounder.upToSeconds(metric.datapoint().timestamp())));
    }

    @Test
    public void readMetricByLabelFromMultipleDataPointsTest() {
        String metricName = "metric1";
        List<Label> labels = List.of(
                new Label("labelName", "value"),
                new Label("labelName1", "value1"),
                new Label("labelName2", "value2"),
                new Label("labelName3", "value3"));
        long timestamp = Instant.now().toEpochMilli();
        DataPoint dataPoint1 = new DataPoint(1.0, timestamp + 2000);
        DataPoint dataPoint2 = new DataPoint(2.0, timestamp);

        tsStorage.addDataPoint(new Metric(metricName,
                labels,
                dataPoint1));

        tsStorage.addDataPoint(new Metric(metricName,
                labels,
                dataPoint2));

        List<Label> labelsToSearch = List.of(new Label("labelName2", "value2"));
        Map<Long, Double> result = tsStorage.readMetrics(Granularity.SECONDS,
                dataPoint2.timestamp() - 100000,
                dataPoint2.timestamp() + 100000,
                metricName,
                labelsToSearch);

        Assertions.assertNotNull(result.get(TimestampRounder.upToSeconds(dataPoint2.timestamp())));
        Assertions.assertEquals(dataPoint2.value(), result.get(TimestampRounder.upToSeconds(dataPoint2.timestamp())));
    }

    @Test
    public void readMetricByLabelFromMultipleMetricsTest() {
        Metric metric = new Metric("metric1",
                List.of(new Label("labelName", "value")),
                new DataPoint(1.0, Instant.now().toEpochMilli()));

        Metric metric1 = new Metric("metric2",
                List.of(new Label("labelName2", "value2")),
                new DataPoint(2.0, Instant.now().toEpochMilli()));

        tsStorage.addDataPoint(metric);
        tsStorage.addDataPoint(metric1);

        Map<Long, Double> result = tsStorage.readMetrics(Granularity.SECONDS,
                metric.datapoint().timestamp() - 10000,
                metric.datapoint().timestamp() + 1000,
                metric.name(),
                metric.labels());

        Assertions.assertNotNull(result.get(TimestampRounder.upToSeconds(metric.datapoint().timestamp())));
        Assertions.assertEquals(metric.datapoint().value(), result.get(TimestampRounder.upToSeconds(metric.datapoint().timestamp())));
    }
}
