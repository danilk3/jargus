package org.jargus.common.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @author Kotelnikov D.M.
 */
@ConfigurationProperties
public class AppConfig {

    private List<TaskConfig> tasksConfig;
    private AlertingConfig alertingConfig;

    public AppConfig(GlobalConfig globalConfig, List<TaskConfig> tasksConfig, AlertingConfig alertingConfig) {
        tasksConfig.forEach(taskConfig -> taskConfig.addGlobalConfigs(globalConfig));
        this.tasksConfig = tasksConfig;
        this.alertingConfig = alertingConfig;
    }
}
