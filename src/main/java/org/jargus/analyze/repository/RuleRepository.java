package org.jargus.analyze.repository;

import org.jargus.analyze.model.MetricAnalysisRule;
import org.jargus.configuration.model.AlertingConfig;

/**
 * @author Bazhov N.S.
 */
public interface RuleRepository {
    MetricAnalysisRule getRuleByName(String taskName, String ruleName);

    AlertingConfig getUri();
}
