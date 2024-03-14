package org.jargus.collect.controller;

import lombok.RequiredArgsConstructor;
import org.jargus.analyze.service.AnomalyMetricsAnalysisService;
import org.jargus.collect.mapper.ModuleRequestMapper;
import org.jargus.collect.model.ExportMetricRequestParams;
import org.jargus.collect.service.InternalDatabaseService;
import org.jargus.collect.service.MetricsCollectionService;
import org.jargus.common.dto.CollectMetricsRequest;
import org.jargus.common.model.Metric;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Bazhov N.S.
 */
@Component
@RequiredArgsConstructor
public class MetricsRequestControllerImpl implements MetricsRequestController {
    private final MetricsCollectionService metricsCollectionService;
    private final AnomalyMetricsAnalysisService anomalyMetricsAnalysisService;
    private final InternalDatabaseService internalDatabaseService;

    private final ModuleRequestMapper moduleRequestMapper;

    @Override
    public List<Metric> exportMetricsFromSidecar(CollectMetricsRequest request) {

        ExportMetricRequestParams exportMetricRequestParams = moduleRequestMapper.mapExportMetricRequestParams(request);

        List<Metric> metrics = metricsCollectionService.exportMetrics(exportMetricRequestParams);
        anomalyMetricsAnalysisService.analyzeMetrics(metrics);



        return metrics;
    }

    @Override
    public List<Metric> exportMetricsFromInternalDatabase(CollectMetricsRequest request) {

        List<Metric> metrics = internalDatabaseService.getMetrics(null);
        anomalyMetricsAnalysisService.analyzeMetrics(metrics);

        return metrics;
    }
}
