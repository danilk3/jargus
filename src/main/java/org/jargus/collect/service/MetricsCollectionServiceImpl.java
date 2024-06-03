package org.jargus.collect.service;

import lombok.RequiredArgsConstructor;
import org.jargus.collect.client.CollectMetricsClient;
import org.jargus.collect.mapper.MetricMapper;
import org.jargus.collect.model.ExportMetricRequestParams;
import org.jargus.common.dto.prometheus.PrometheusMetricsResponseDataDto;
import org.jargus.common.dto.prometheus.PrometheusResponseDto;
import org.jargus.common.model.Metric;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Bazhov N.S.
 */
@Service
@RequiredArgsConstructor
public class MetricsCollectionServiceImpl implements MetricsCollectionService {
    private final CollectMetricsClient collectMetricsClient;
    private final MetricMapper metricMapper;

    @Override
    public List<Metric> exportMetrics(ExportMetricRequestParams exportMetricRequestParams) {
        if (exportMetricRequestParams.getUri().contains("api/v1")){
            List<PrometheusResponseDto<PrometheusMetricsResponseDataDto>> result = collectMetricsClient.export(exportMetricRequestParams.getUri());
            return metricMapper.map(result);
        }

        List<String> result = collectMetricsClient.exportRaw(exportMetricRequestParams.getUri());
        return metricMapper.mapRaw(result);
    }
}
