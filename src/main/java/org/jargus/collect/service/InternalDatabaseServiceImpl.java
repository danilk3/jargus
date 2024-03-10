package org.jargus.collect.service;

import lombok.RequiredArgsConstructor;
import org.jargus.collect.model.DatabaseMetricRequestParams;
import org.jargus.collect.model.RawMetric;
import org.jargus.database.dao.TsStorageClient;

/**
 * @author Bazhov N.S.
 */
@RequiredArgsConstructor
public class InternalDatabaseServiceImpl implements InternalDatabaseService {
    private final TsStorageClient tsStorageClient;

    @Override
    public RawMetric getMetrics(DatabaseMetricRequestParams databaseMetricRequestParams) {
        return null;
    }
}
