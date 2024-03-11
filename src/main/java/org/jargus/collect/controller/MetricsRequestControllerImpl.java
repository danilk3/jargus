package org.jargus.collect.controller;

import lombok.RequiredArgsConstructor;
import org.jargus.collect.mapper.ModuleRequestMapper;
import org.jargus.collect.mapper.ModuleResponseMapper;
import org.jargus.collect.model.ExportMetricRequestParams;
import org.jargus.collect.model.RawMetric;
import org.jargus.analyze.service.AnomalyMetricsAnalysisService;
import org.jargus.collect.service.InternalDatabaseService;
import org.jargus.collect.service.MetricsCollectionService;
import org.jargus.collect.service.RequestFilterService;
import org.jargus.common.dto.CollectMetricsRequest;
import org.jargus.common.model.Metric;
import org.springframework.stereotype.Component;

/**
 * @author Bazhov N.S.
 */
@Component
@RequiredArgsConstructor
public class MetricsRequestControllerImpl implements MetricsRequestController {
    private final RequestFilterService requestFilterService;
    private final MetricsCollectionService metricsCollectionService;
    private final AnomalyMetricsAnalysisService anomalyMetricsAnalysisService;
    private final InternalDatabaseService internalDatabaseService;

    private final ModuleRequestMapper moduleRequestMapper;
    private final ModuleResponseMapper moduleResponseMapper;

    @Override
    public Metric exportMetricsFromSidecar(CollectMetricsRequest request) {

        ExportMetricRequestParams exportMetricRequestParams = moduleRequestMapper.mapExportMetricRequestParams(request);

        RawMetric rawMetric = metricsCollectionService.exportMetrics(exportMetricRequestParams);
        anomalyMetricsAnalysisService.analyzeMetrics(rawMetric);

        return moduleResponseMapper.mapMetrics(rawMetric);
    }

    @Override
    public Metric exportMetricsFromInternalDatabase(CollectMetricsRequest request) {

        RawMetric rawMetric = internalDatabaseService.getMetrics(null);
        anomalyMetricsAnalysisService.analyzeMetrics(rawMetric);

        return moduleResponseMapper.mapMetrics(rawMetric);
    }
}
