package org.jargus.collect.service;

import lombok.RequiredArgsConstructor;
import org.jargus.collect.model.DatabaseMetricsRequestParams;
import org.jargus.collect.model.MetricInfo;
import org.jargus.database.dao.TsStorageClient;

/**
 * @author Bazhov N.S.
 */
@RequiredArgsConstructor
public class InternalDatabaseServiceImpl implements InternalDatabaseService {
    private final TsStorageClient tsStorageClient;

    @Override
    public MetricInfo getMetrics(DatabaseMetricsRequestParams databaseMetricsRequestParams) {
        return null;
    }
}
