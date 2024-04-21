package org.jargus.configuration.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jargus.configuration.Default;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @author Kotelnikov D.M.
 */
@ConfigurationProperties
@Getter
@Setter
@NoArgsConstructor
public class AppConfig {

    private List<TaskConfig> tasksConfig;
    private AlertingConfig alertingConfig;

    public AppConfig(GlobalConfig globalConfig, List<TaskConfig> tasksConfig, AlertingConfig alertingConfig) {
        tasksConfig.forEach(taskConfig -> taskConfig.addGlobalConfigs(globalConfig));
        this.tasksConfig = tasksConfig;
        this.alertingConfig = alertingConfig;
    }

    @Default
    public AppConfig(List<TaskConfig> tasksConfig, AlertingConfig alertingConfig) {
        this.tasksConfig = tasksConfig;
        this.alertingConfig = alertingConfig;
    }
}
