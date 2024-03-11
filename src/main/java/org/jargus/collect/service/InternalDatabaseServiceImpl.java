package org.jargus.collect.service;

import lombok.RequiredArgsConstructor;
import org.jargus.collect.model.DatabaseMetricRequestParams;
import org.jargus.collect.model.RawMetric;
import org.jargus.database.dao.TsStorageClient;
import org.springframework.stereotype.Service;

/**
 * @author Bazhov N.S.
 */
@Service
@RequiredArgsConstructor
public class InternalDatabaseServiceImpl implements InternalDatabaseService {
    private final TsStorageClient tsStorageClient;

    @Override
    public RawMetric getMetrics(DatabaseMetricRequestParams databaseMetricRequestParams) {
        return null;
    }
}
