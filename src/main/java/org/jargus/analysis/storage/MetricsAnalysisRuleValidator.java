package org.jargus.analysis.storage;

import org.jargus.common.model.Metric;

/**
 * @author Bazhov N.S.
 */
public interface MetricsAnalysisRuleValidator {
    boolean matches(Metric metric);
}
