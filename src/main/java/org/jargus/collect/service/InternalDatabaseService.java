package org.jargus.collect.service;

import org.jargus.collect.model.DatabaseMetricRequestParams;
import org.jargus.common.model.Metric;

import java.util.List;

/**
 * @author Bazhov N.S.
 */
public interface InternalDatabaseService {
    List<Metric> getMetrics(DatabaseMetricRequestParams databaseMetricRequestParams);
}
