package org.jargus.collect.service;

import org.jargus.collect.model.DatabaseMetricRequestParams;
import org.jargus.collect.model.RawMetric;

/**
 * @author Bazhov N.S.
 */
public interface InternalDatabaseService {
    RawMetric getMetrics(DatabaseMetricRequestParams databaseMetricRequestParams);
}
