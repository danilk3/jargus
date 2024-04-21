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
//        TODO: настроить имена метрик
        return new ExportMetricRequestParams("name", request.getTaskRequestModel().getUri());
    }
}
