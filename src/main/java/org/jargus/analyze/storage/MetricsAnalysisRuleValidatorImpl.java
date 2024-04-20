package org.jargus.analyze.storage;

import lombok.RequiredArgsConstructor;
import org.jargus.alert.model.Event;
import org.jargus.alert.model.Message;
import org.jargus.analyze.model.MetricAnalysisRule;
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
    public Event matches(Metric metric) {

        MetricAnalysisRule metricAnalysisRule = metricsAnalysisRuleStorage.getRule();
        if (metric.name().equals(metricAnalysisRule.getName())){
            return Event.builder()
                    .url(metricAnalysisRule.getAnnotation())
                    .message(Message.builder().type(metricAnalysisRule.getLabel()).message(metricAnalysisRule.getMessage()).build())
                    .pass(metricAnalysisRule.getRuleCondition().check(metric.datapoint().value()))
                    .build();
        }
        return null;
    }
}
