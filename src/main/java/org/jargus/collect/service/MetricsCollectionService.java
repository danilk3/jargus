package org.jargus.collect.service;

import org.jargus.collect.model.ExportMetricsRequestParams;
import org.jargus.collect.model.MetricInfo;

/**
 * @author Bazhov N.S.
 */
public interface MetricsCollectionService {
    MetricInfo exportMetrics(ExportMetricsRequestParams exportMetricsRequestParams);
}
