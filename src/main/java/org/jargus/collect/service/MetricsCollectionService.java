package org.jargus.collect.service;

import org.jargus.collect.model.ExportMetricRequestParams;
import org.jargus.collect.model.RawMetric;

/**
 * @author Bazhov N.S.
 */
public interface MetricsCollectionService {
    RawMetric exportMetrics(ExportMetricRequestParams exportMetricRequestParams);
}
