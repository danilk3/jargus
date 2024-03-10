package org.jargus.collect.service;

import lombok.RequiredArgsConstructor;
import org.jargus.collect.model.DatabaseMetricsRequestParams;
import org.jargus.collect.model.MetricInfo;
import org.jargus.common.dao.InternalDatabaseDao;

/**
 * @author Bazhov N.S.
 */
@RequiredArgsConstructor
public class InternalDatabaseServiceImpl implements InternalDatabaseService {
    private final InternalDatabaseDao internalDatabaseDao;

    @Override
    public MetricInfo getMetrics(DatabaseMetricsRequestParams databaseMetricsRequestParams) {
        internalDatabaseDao.getMetrics(null);
        return null;
    }
}
