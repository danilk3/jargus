package org.jargus.collect.controller;

import lombok.RequiredArgsConstructor;
import org.jargus.collect.mapper.ModuleRequestMapper;
import org.jargus.collect.mapper.ModuleResponseMapper;
import org.jargus.collect.model.DatabaseMetricsRequestParams;
import org.jargus.collect.model.ExportMetricsRequestParams;
import org.jargus.collect.model.MetricInfo;
import org.jargus.collect.service.AnomalyMetricsAnalysisService;
import org.jargus.collect.service.InternalDatabaseService;
import org.jargus.collect.service.MetricsCollectionService;
import org.jargus.collect.service.RequestFilterService;
import org.jargus.common.dto.ModuleRequest;
import org.jargus.common.dto.ModuleResponse;

/**
 * @author Bazhov N.S.
 */
@RequiredArgsConstructor
public class MetricsRequestControllerImpl implements MetricsRequestController {
    private final RequestFilterService requestFilterService;
    private final MetricsCollectionService metricsCollectionService;
    private final AnomalyMetricsAnalysisService anomalyMetricsAnalysisService;
    private final InternalDatabaseService internalDatabaseService;

    private final ModuleRequestMapper moduleRequestMapper;
    private final ModuleResponseMapper moduleResponseMapper;

    @Override
    public ModuleResponse exportMetricsFromSidecar(ModuleRequest request) {
        ExportMetricsRequestParams exportMetricsRequestParams = moduleRequestMapper.mapExportMetricsRequestParams(request);
        MetricInfo metricInfo = metricsCollectionService.exportMetrics(exportMetricsRequestParams);
        anomalyMetricsAnalysisService.analyzeMetrics(metricInfo);

        return moduleResponseMapper.mapMetrics(metricInfo);
    }

    @Override
    public ModuleResponse exportMetricsFromInternalDatabase(ModuleRequest request) {
        DatabaseMetricsRequestParams databaseMetricsRequestParams = moduleRequestMapper.mapDatabaseMetricsRequestParams(request);
        MetricInfo metricInfo = internalDatabaseService.getMetrics(databaseMetricsRequestParams);
        anomalyMetricsAnalysisService.analyzeMetrics(metricInfo);

        return moduleResponseMapper.mapMetrics(metricInfo);
    }
}
