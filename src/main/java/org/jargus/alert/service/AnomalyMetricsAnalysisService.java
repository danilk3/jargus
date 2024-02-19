package org.jargus.alert.service;

import org.jargus.common.model.Metric;

/**
 * @author Bazhov N.S.
 */
public interface AnomalyMetricsAnalysisService {
    void analyzeMetrics(Metric metric);
}
