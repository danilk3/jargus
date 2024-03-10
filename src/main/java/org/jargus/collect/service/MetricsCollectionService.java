package org.jargus.collect.service;

import org.jargus.collect.model.ExportMetricsRequestParams;
import org.jargus.collect.model.RawMetrics;

/**
 * @author Bazhov N.S.
 */
public interface MetricsCollectionService {
    RawMetrics exportMetrics(ExportMetricsRequestParams exportMetricsRequestParams);
}
