package org.jargus.configuration.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author Kotelnikov D.M.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TaskConfig {

    private String taskName;
    private String target;
    private String path;
    private TsDbConfig tsDbConfig;
    private FetchConfig fetchConfig;
    private RequestsConfig requestsConfig;
    private List<NotificationRuleConfig> notificationRulesConfig;

    public void addGlobalConfigs(GlobalConfig globalConfig) {
        if (tsDbConfig == null) {
            tsDbConfig = globalConfig.getTsDbConfig();
        }
        if (fetchConfig == null) {
            fetchConfig = globalConfig.getFetchConfig();
        }
        if (requestsConfig == null) {
            requestsConfig = globalConfig.getRequestsConfig();
        }
        if (notificationRulesConfig == null || notificationRulesConfig.isEmpty()) {
            notificationRulesConfig = globalConfig.getNotificationRulesConfig();
        }
    }
}
