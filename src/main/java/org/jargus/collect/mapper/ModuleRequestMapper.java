package org.jargus.collect.mapper;

import org.jargus.collect.model.DatabaseMetricRequestParams;
import org.jargus.collect.model.ExportMetricRequestParams;
import org.jargus.common.dto.CollectMetricsRequest;
import org.mapstruct.Mapper;

/**
 * @author Bazhov N.S.
 */
@Mapper
public interface ModuleRequestMapper {
    DatabaseMetricRequestParams mapDatabaseMetricRequestParams(CollectMetricsRequest request);
    ExportMetricRequestParams mapExportMetricRequestParams(CollectMetricsRequest request);
}
