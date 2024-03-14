package org.jargus.collect.mapper;

import org.jargus.collect.model.DatabaseMetricRequestParams;
import org.jargus.collect.model.ExportMetricRequestParams;
import org.jargus.common.dto.CollectMetricsRequest;
import org.springframework.stereotype.Component;

/**
 * @author Bazhov N.S.
 */
@Component
public class ModuleRequestMapperImpl implements ModuleRequestMapper {
    @Override
    public DatabaseMetricRequestParams mapDatabaseMetricRequestParams(CollectMetricsRequest request) {
        return null;
    }

    @Override
    public ExportMetricRequestParams mapExportMetricRequestParams(CollectMetricsRequest request) {
        return new ExportMetricRequestParams(request.getMetricName(), "http://localhost:8081/actuator/prometheus");
    }
}
