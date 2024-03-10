package org.jargus.collect.mapper;

import org.jargus.collect.model.DatabaseMetricsRequestParams;
import org.jargus.collect.model.ExportMetricsRequestParams;
import org.jargus.common.dto.ModuleRequest;

/**
 * @author Bazhov N.S.
 */
public interface ModuleRequestMapper {
    DatabaseMetricsRequestParams mapDatabaseMetricsRequestParams(ModuleRequest request);
    ExportMetricsRequestParams mapExportMetricsRequestParams(ModuleRequest request);
}
