package org.jargus.analyze.storage;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jargus.analyze.RuleProperties;
import org.jargus.analyze.model.MetricAnalysisRule;
import org.springframework.stereotype.Component;

/**
 * @author Bazhov N.S.
 */
@Component
@Getter
@RequiredArgsConstructor
public class MetricsAnalysisRuleStorageImpl implements MetricsAnalysisRuleStorage {
    private final MetricAnalysisRule rule;
}
