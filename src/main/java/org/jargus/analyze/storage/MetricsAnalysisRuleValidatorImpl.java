package org.jargus.analyze.storage;

import lombok.RequiredArgsConstructor;
import org.jargus.common.model.Metric;
import org.springframework.stereotype.Component;

/**
 * @author Bazhov N.S.
 */
@Component
@RequiredArgsConstructor
public class MetricsAnalysisRuleValidatorImpl implements MetricsAnalysisRuleValidator {
    private final MetricsAnalysisRuleStorage metricsAnalysisRuleStorage;
    
    @Override
    public boolean matches(Metric metric) {
        return false;
    }
}
