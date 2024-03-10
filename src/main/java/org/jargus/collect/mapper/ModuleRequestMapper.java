package org.jargus.collect.mapper;

import org.jargus.collect.model.DatabaseMetricsRequestParams;
import org.jargus.collect.model.ExportMetricsRequestParams;
import org.jargus.common.dto.CollectMetricsRequest;

/**
 * @author Bazhov N.S.
 */
public interface ModuleRequestMapper {
    DatabaseMetricsRequestParams mapDatabaseMetricsRequestParams(CollectMetricsRequest request);
    ExportMetricsRequestParams mapExportMetricsRequestParams(CollectMetricsRequest request);
}
