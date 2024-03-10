package org.jargus.analyze.storage;

import org.jargus.collect.model.RawMetrics;

/**
 * @author Bazhov N.S.
 */
public interface MetricsAnalysisRuleValidator {
    boolean matches(RawMetrics rawMetrics);
}
