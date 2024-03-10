package org.jargus.analyze.storage;

import org.jargus.collect.model.RawMetric;

/**
 * @author Bazhov N.S.
 */
public interface MetricsAnalysisRuleValidator {
    boolean matches(RawMetric rawMetric);
}
