package org.jargus.analyze.repository;

import lombok.RequiredArgsConstructor;
import org.jargus.analyze.RuleMapper;
import org.jargus.analyze.model.MetricAnalysisRule;
import org.jargus.configuration.model.AppConfig;
import org.jargus.configuration.model.NotificationRuleConfig;
import org.jargus.configuration.model.TaskConfig;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * @author Bazhov N.S.
 */
@Component
@RequiredArgsConstructor
public class RuleRepositoryImpl implements RuleRepository {
    private final RuleMapper ruleMapper;
    private final AppConfig appConfig;

    public MetricAnalysisRule getRuleByName(String taskName, String ruleName){
        Optional<TaskConfig> taskConfig = appConfig.getTasksConfig().stream().filter(x -> x.getTaskName().equals(taskName)).findFirst();
        if (taskConfig.isPresent()){
            Optional<NotificationRuleConfig> ruleConfig = taskConfig.get().getNotificationRulesConfig().stream().filter(x->x.getName().equals(ruleName)).findFirst();
            if (ruleConfig.isPresent()){
                return ruleMapper.mapRule(ruleConfig.get());
            } else {
                throw new IllegalArgumentException("Unknown rule");
            }
        } else {
            throw new IllegalArgumentException("Unknown task");
        }
    }
}
