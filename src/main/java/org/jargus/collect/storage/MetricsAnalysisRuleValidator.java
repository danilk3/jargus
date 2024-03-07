package org.jargus.collect.storage;

import org.jargus.collect.model.MetricInfo;
import org.jargus.common.model.Metric;

/**
 * @author Bazhov N.S.
 */
public interface MetricsAnalysisRuleValidator {
    boolean matches(MetricInfo metricInfo);
}
