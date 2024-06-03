package org.jargus.collect.mapper;

import org.jargus.common.dto.prometheus.PrometheusMetricsResponseDataDto;
import org.jargus.common.dto.prometheus.PrometheusResponseDto;
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

    public List<Metric> map(List<PrometheusResponseDto<PrometheusMetricsResponseDataDto>> responsesList) {
        List<Metric> result = new ArrayList<>();

        for (PrometheusResponseDto<PrometheusMetricsResponseDataDto> metricsResponseDto : responsesList) {
            List<PrometheusMetricsResponseDataDto.PrometheusMetricsResultResponse> metrics = metricsResponseDto.getData().getResult();
            metrics.forEach(metric -> {
                String metricName = metric.getMetric().get("__name__");
                List<Label> labels = metric.getMetric().entrySet().stream()
                        .filter(entry -> !entry.getKey().equals("__name__"))
                        .map(entry -> new Label(entry.getKey(), entry.getValue()))
                        .toList();
                DataPoint dataPoint = new DataPoint(Double.parseDouble(metric.getValue().get(1)), Long.parseLong(metric.getValue().get(0).replace(".", "")));
                result.add(new Metric(metricName, labels, dataPoint));
            });
        }

        return result;
    }

    public List<Metric> mapRaw(List<String> rawMetrics) {
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
