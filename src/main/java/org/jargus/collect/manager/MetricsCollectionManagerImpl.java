package org.jargus.collect.manager;

import lombok.RequiredArgsConstructor;
import org.jargus.analyze.service.AnomalyMetricsAnalysisService;
import org.jargus.collect.mapper.ModuleRequestMapper;
import org.jargus.collect.model.ExportMetricRequestParams;
import org.jargus.collect.service.MetricsCollectionService;
import org.jargus.common.dto.CollectMetricsFromInternalDatabaseRequest;
import org.jargus.common.dto.CollectMetricsRequest;
import org.jargus.common.model.Metric;
import org.jargus.database.dao.TsStorageClient;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Bazhov N.S.
 */
@RequiredArgsConstructor
@Component
public class MetricsCollectionManagerImpl implements MetricsCollectionManager {
    private final MetricsCollectionService metricsCollectionService;
    private final AnomalyMetricsAnalysisService anomalyMetricsAnalysisService;
    private final TsStorageClient tsStorageClient;

    private final ModuleRequestMapper moduleRequestMapper;

    @Override
    public List<Metric> exportMetricsFromSidecar(CollectMetricsRequest request) {

        ExportMetricRequestParams exportMetricRequestParams = moduleRequestMapper.mapExportMetricRequestParams(request);

        List<Metric> metrics = metricsCollectionService.exportMetrics(exportMetricRequestParams);
        anomalyMetricsAnalysisService.analyzeMetrics(metrics);

//      TODO: нужно ли добавлять в базу при интайме?
        tsStorageClient.addDataPoints(request.getFetchName(), metrics);

        return metrics;
    }

    @Override
    public List<Metric> exportMetricsFromInternalDatabase(CollectMetricsFromInternalDatabaseRequest request) {
        return tsStorageClient.readMetrics(request.getFetchName(), request.getMetricRequests());
    }
}
