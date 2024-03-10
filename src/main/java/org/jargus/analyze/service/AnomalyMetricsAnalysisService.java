package org.jargus.analyze.service;

import org.jargus.collect.model.RawMetric;

/**
 * @author Bazhov N.S.
 */
public interface AnomalyMetricsAnalysisService {
    void analyzeMetrics(RawMetric metric);
}
