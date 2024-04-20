package org.jargus.analyze.storage;

import org.jargus.alert.model.Event;
import org.jargus.common.model.Metric;

/**
 * @author Bazhov N.S.
 */
public interface MetricsAnalysisRuleValidator {
    Event matches(Metric metric);
}
