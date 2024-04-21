package org.jargus.analyze.service;

import org.jargus.common.model.Metric;
import org.jargus.scheduler.domain.TaskRequestModel;

import java.util.List;

/**
 * @author Bazhov N.S.
 */
public interface AnomalyMetricsAnalysisService {
    void analyzeMetrics(List<Metric> metrics, TaskRequestModel taskRequestModel);
}
