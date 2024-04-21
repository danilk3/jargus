package org.jargus.configuration.mapper;

import org.jargus.configuration.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;
import java.util.Optional;

/**
 * @author Kotelnikov D.M.
 */
@Mapper(
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = "spring"
)
public interface ConfigMapper {

    void updateAppConfig(AppConfig newAppConfig, @MappingTarget AppConfig appConfig);

    default void updateTaskConfigs(List<TaskConfig> newTaskConfigs, @MappingTarget List<TaskConfig> taskConfigs) {
        newTaskConfigs.forEach(newConfig -> {
            Optional<TaskConfig> existedConfig = taskConfigs.stream()
                    .filter(config -> config.getTaskName().equals(newConfig.getTaskName()))
                    .findFirst();
            if (existedConfig.isPresent()) {
                updateTaskConfig(newConfig, existedConfig.get());
            } else {
                taskConfigs.add(newConfig);
            }
        });
    }

    void updateTaskConfig(TaskConfig newTaskConfig, @MappingTarget TaskConfig taskConfig);

    void updateTsDbConfig(TsDbConfig newTsDbConfig, @MappingTarget TsDbConfig tsDbConfig);

    void updateFetchConfig(FetchConfig newFetchConfig, @MappingTarget FetchConfig fetchConfig);

    default void updateRequestsConfig(RequestsConfig newRequestsConfig, @MappingTarget RequestsConfig requestsConfig) {
        requestsConfig.getHeaders().putAll(newRequestsConfig.getHeaders());
        requestsConfig.getParams().putAll(newRequestsConfig.getParams());
    }

    default void updateNotificationRulesConfigs(List<NotificationRuleConfig> newNotificationRuleConfigs, @MappingTarget List<NotificationRuleConfig> notificationRuleConfigs) {
        newNotificationRuleConfigs.forEach(newRule -> {
            Optional<NotificationRuleConfig> existedRule = notificationRuleConfigs.stream()
                    .filter(config -> config.getName().equals(newRule.getName()))
                    .findFirst();
            if (existedRule.isPresent()) {
                updateNotificationRule(newRule, existedRule.get());
            } else {
                notificationRuleConfigs.add(newRule);
            }
        });
    }

    void updateNotificationRule(NotificationRuleConfig newNotificationRuleConfig, @MappingTarget NotificationRuleConfig notificationRuleConfig);

    void updateAlertingConfig(AlertingConfig newAlertingConfig, @MappingTarget AlertingConfig alertingConfig);
}
