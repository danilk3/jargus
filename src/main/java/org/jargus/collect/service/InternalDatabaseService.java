package org.jargus.collect.service;

import org.jargus.collect.model.DatabaseMetricsRequestParams;
import org.jargus.collect.model.MetricInfo;

/**
 * @author Bazhov N.S.
 */
public interface InternalDatabaseService {
    MetricInfo getMetrics(DatabaseMetricsRequestParams databaseMetricsRequestParams);
}
