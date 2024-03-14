package org.jargus.analyze.service;

import org.jargus.common.model.Metric;

import java.util.List;

/**
 * @author Bazhov N.S.
 */
public interface AnomalyMetricsAnalysisService {
    void analyzeMetrics(List<Metric> metric);
}
