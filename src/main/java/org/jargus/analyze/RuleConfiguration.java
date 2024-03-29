package org.jargus.analyze;

import org.jargus.analyze.model.MetricAnalysisRule;
import org.jargus.analyze.model.Rule;
import org.jargus.analyze.model.RuleCondition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Bazhov N.S.
 */
@Configuration
public class RuleConfiguration {

    @Bean
    public MetricAnalysisRule metricAnalysisRule(RuleProperties ruleProperties){
        Rule rule = ruleProperties.getRule();
        return MetricAnalysisRule.builder()
                .name(rule.getName())
                .message(rule.getMessage())
                .label(rule.getLabel())
                .ruleCondition(new RuleCondition(rule.getCondition()))
                .annotation(rule.getAnnotation().getTarget() + rule.getAnnotation().getPath())
                .build();
    }
}
