package org.jargus.analyze.storage;

import lombok.RequiredArgsConstructor;
import org.jargus.alert.model.Event;
import org.jargus.alert.model.Message;
import org.jargus.analyze.model.MetricAnalysisRule;
import org.jargus.analyze.repository.RuleRepository;
import org.jargus.common.model.Metric;
import org.jargus.configuration.model.AlertingConfig;
import org.jargus.scheduler.domain.TaskRequestModel;
import org.springframework.stereotype.Component;

/**
 * @author Bazhov N.S.
 */
@Component
@RequiredArgsConstructor
public class MetricsAnalysisRuleValidatorImpl implements MetricsAnalysisRuleValidator {
    private final RuleRepository ruleRepository;
    
    @Override
    public Event matches(Metric metric, TaskRequestModel taskRequestModel) {

//        MetricAnalysisRule metricAnalysisRule = ruleRepository.getRuleByName(taskRequestModel.getTaskName(), "system_cpu_usage");
        MetricAnalysisRule metricAnalysisRule = ruleRepository.getRuleByName(taskRequestModel.getTaskName(), metric.name());
        AlertingConfig alertingConfig = ruleRepository.getUri();
        if (metric.name().equals(metricAnalysisRule.getName())){
            return Event.builder()
                    .url(alertingConfig.getHost() + alertingConfig.getPath())
                    .message(new Message(metricAnalysisRule.getFlags(), metricAnalysisRule.getAnnotation()))
                    .pass(metricAnalysisRule.getRuleCondition().check(metric.datapoint().value()))
                    .build();
        }
        return null;
    }
}
