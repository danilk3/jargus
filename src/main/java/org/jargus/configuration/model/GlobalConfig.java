package org.jargus.configuration.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

/**
 * @author Kotelnikov D.M.
 */
@AllArgsConstructor
@Getter
public class GlobalConfig {
    private TsDbConfig tsDbConfig;
    private FetchConfig fetchConfig;
    private RequestsConfig requestsConfig;
    private List<NotificationRule> notificationRulesConfig;
}
