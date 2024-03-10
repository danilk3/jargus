package org.jargus.analyze.service;

import org.jargus.collect.model.RawMetrics;

/**
 * @author Bazhov N.S.
 */
public interface AnomalyMetricsAnalysisService {
    void analyzeMetrics(RawMetrics metric);
}
