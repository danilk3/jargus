package org.jargus.collect.service;

import org.jargus.collect.model.MetricInfo;

/**
 * @author Bazhov N.S.
 */
public interface AnomalyMetricsAnalysisService {
    void analyzeMetrics(MetricInfo metric);
}
