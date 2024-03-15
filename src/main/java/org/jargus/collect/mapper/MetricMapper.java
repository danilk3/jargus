package org.jargus.collect.mapper;

import org.jargus.common.model.DataPoint;
import org.jargus.common.model.Label;
import org.jargus.common.model.Metric;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Bazhov N.S.
 */
@Component
public class MetricMapper {
//    TODO: сделать интерфейсик наверное?
    public List<Metric> map(List<String> rawMetrics) {

        List<Metric> metrics = new ArrayList<>();

        for (String rawMetric : rawMetrics) {
            if (!isComment(rawMetric)) {
                if (containsLabels(rawMetric)) {
                    metrics.add(convertToMetricWithLabels(rawMetric));
                } else {
                    metrics.add(convertToMetricWithoutLabels(rawMetric));
                }
            }
        }

        return metrics;
    }

    private boolean isComment(String line) {
        return line.startsWith("#");
    }

    //    <name>{<label_name>="<value>",<label_name>="<value>",...,} <value>
    //    <name> <value>

    private Metric convertToMetricWithoutLabels(String rawMetric) {
        String rawName = rawMetric.substring(0, rawMetric.indexOf(" "));
        String rawDataPoint = rawMetric.substring(rawMetric.indexOf(" ") + 1);

        String name = mapMetricName(rawName);
        DataPoint dataPoint = mapMetricDataPoint(rawDataPoint);

        return Metric.builder()
                .name(name)
                .datapoint(dataPoint)
                .labels(List.of())
                .build();
    }

    private Metric convertToMetricWithLabels(String rawMetric) {
        String rawName = rawMetric.substring(0, rawMetric.indexOf("{"));
        String rawLabels = rawMetric.substring(rawMetric.indexOf("{") + 1, rawMetric.indexOf("}"));
        String rawDataPoint = rawMetric.substring(rawMetric.indexOf(",} ") + 3);

        String name = mapMetricName(rawName);
        List<Label> labels = mapMetricLabels(rawLabels);
        DataPoint dataPoint = mapMetricDataPoint(rawDataPoint);

        return Metric.builder()
                .name(name)
                .labels(labels)
                .datapoint(dataPoint)
                .build();
    }

    private boolean containsLabels(String line) {
        return line.contains(",} ");
    }

    private String mapMetricName(String rawName) {
        return rawName;
    }

    private List<Label> mapMetricLabels(String rawLabels) {
        List<Label> labels = new ArrayList<>();

        String[] rawLabelsArray = rawLabels.split(",");

        for (String rawLabel : rawLabelsArray) {
            String labelName = rawLabel.substring(0, rawLabel.indexOf("="));
            String labelValue = rawLabel.substring(rawLabel.indexOf("=") + 2, rawLabel.length() - 1);
            labels.add(new Label(labelName, labelValue));
        }
        return labels;
    }

    private DataPoint mapMetricDataPoint(String rawValue) {
        return new DataPoint(Double.parseDouble(rawValue), Instant.now().toEpochMilli());
    }
}
