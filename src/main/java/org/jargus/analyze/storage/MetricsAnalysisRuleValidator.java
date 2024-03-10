package org.jargus.analyze.storage;

import org.jargus.collect.model.MetricInfo;

/**
 * @author Bazhov N.S.
 */
public interface MetricsAnalysisRuleValidator {
    boolean matches(MetricInfo metricInfo);
}
