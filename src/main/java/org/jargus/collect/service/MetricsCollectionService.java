package org.jargus.collect.service;

import org.jargus.collect.model.ExportMetricRequestParams;
import org.jargus.common.model.Metric;

import java.util.List;

/**
 * @author Bazhov N.S.
 */
public interface MetricsCollectionService {
    List<Metric> exportMetrics(ExportMetricRequestParams exportMetricRequestParams);
}
