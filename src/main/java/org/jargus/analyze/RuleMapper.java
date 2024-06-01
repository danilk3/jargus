package org.jargus.analyze;

import org.jargus.analyze.model.MetricAnalysisRule;
import org.jargus.analyze.model.RuleCondition;
import org.jargus.configuration.model.NotificationRuleConfig;
import org.springframework.stereotype.Component;

/**
 * @author Bazhov N.S.
 */
@Component
public class RuleMapper {
    public MetricAnalysisRule mapRule(NotificationRuleConfig notificationRuleConfig){
        return MetricAnalysisRule.builder()
                .name(notificationRuleConfig.getName())
                .label(notificationRuleConfig.getFlags().get(0))
                .ruleCondition(new RuleCondition(notificationRuleConfig.getCondition()))
                .annotation(notificationRuleConfig.getAnnotation())
                .message(notificationRuleConfig.getAnnotation())
                .build();
    }
}
