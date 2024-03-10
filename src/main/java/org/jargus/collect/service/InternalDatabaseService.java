package org.jargus.collect.service;

import org.jargus.collect.model.DatabaseMetricsRequestParams;
import org.jargus.collect.model.RawMetrics;

/**
 * @author Bazhov N.S.
 */
public interface InternalDatabaseService {
    RawMetrics getMetrics(DatabaseMetricsRequestParams databaseMetricsRequestParams);
}
