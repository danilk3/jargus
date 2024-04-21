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
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @author Kotelnikov D.M.
 */
public class TsStorageTest {

    private TsStorage tsStorage;

    @BeforeEach
    public void init() {
        tsStorage = new TsStorage(new StorageConfig(Duration.of(1, TimeUnit.DAYS.toChronoUnit()),
                Duration.of(1, TimeUnit.DAYS.toChronoUnit()),
                Duration.of(1, TimeUnit.DAYS.toChronoUnit())));
    }

    @Test
    public void readOneMetricTest() {
        Metric metric = new Metric("metric1",
                List.of(new Label("labelName", "value")),
                new DataPoint(1.0, Instant.now().toEpochMilli()));

        tsStorage.addDataPoint(metric);

        List<Metric> result = tsStorage.readMetrics(Granularity.SECONDS,
                Optional.of(metric.datapoint().timestamp() - 10000),
                Optional.of(metric.datapoint().timestamp() + 1000),
                metric.name(),
                metric.labels());

        Assertions.assertNotNull(result);
        Assertions.assertFalse(result.isEmpty());
        Assertions.assertEquals(1, result.size());
        Metric expectedMetric = new Metric(
                metric.name(),
                metric.labels(),
                new DataPoint(metric.datapoint().value(),
                        TimestampRounder.upToSeconds(metric.datapoint().timestamp()))
        );
        Assertions.assertEquals(expectedMetric, result.get(0));
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
        List<Metric> result = tsStorage.readMetrics(Granularity.SECONDS,
                Optional.of(metric.datapoint().timestamp() - 10000),
                Optional.of(metric.datapoint().timestamp() + 1000),
                metric.name(),
                labelsToSearch);

        Assertions.assertNotNull(result);
        Assertions.assertFalse(result.isEmpty());
        Assertions.assertEquals(1, result.size());
        Metric expectedMetric = new Metric(
                metric.name(),
                metric.labels(),
                new DataPoint(metric.datapoint().value(),
                        TimestampRounder.upToSeconds(metric.datapoint().timestamp()))
        );
        Assertions.assertEquals(expectedMetric, result.get(0));
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
        List<Metric> result = tsStorage.readMetrics(Granularity.SECONDS,
                Optional.of(dataPoint2.timestamp() - 10000),
                Optional.of(dataPoint2.timestamp() + 1000),
                metricName,
                labelsToSearch);

        Assertions.assertNotNull(result);
        Assertions.assertFalse(result.isEmpty());
        Assertions.assertEquals(1, result.size());
        Metric expectedMetric = new Metric(
                metricName,
                labels,
                new DataPoint(dataPoint2.value(),
                        TimestampRounder.upToSeconds(dataPoint2.timestamp()))
        );
        Assertions.assertEquals(expectedMetric, result.get(0));
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

        List<Metric> result = tsStorage.readMetrics(Granularity.SECONDS,
                Optional.of(metric.datapoint().timestamp() - 10000),
                Optional.of(metric.datapoint().timestamp() + 1000),
                metric.name(),
                metric.labels());

        Assertions.assertNotNull(result);
        Assertions.assertFalse(result.isEmpty());
        Assertions.assertEquals(1, result.size());
        Metric expectedMetric = new Metric(
                metric.name(),
                metric.labels(),
                new DataPoint(metric.datapoint().value(),
                        TimestampRounder.upToSeconds(metric.datapoint().timestamp()))
        );
        Assertions.assertEquals(expectedMetric, result.get(0));
    }
}
