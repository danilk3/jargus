package org.jargus.analyze.repository;

import org.jargus.analyze.model.MetricAnalysisRule;

/**
 * @author Bazhov N.S.
 */
public interface    RuleRepository {
    MetricAnalysisRule getRuleByName(String taskName, String ruleName);
}
