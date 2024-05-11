package org.jargus.collect.mapper;

import org.jargus.common.dto.prometheus.PrometheusMetricsResponseDataDto;
import org.jargus.common.dto.prometheus.PrometheusResponseDto;
import org.jargus.common.model.DataPoint;
import org.jargus.common.model.Label;
import org.jargus.common.model.Metric;
import org.springframework.stereotype.Component;

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
}
