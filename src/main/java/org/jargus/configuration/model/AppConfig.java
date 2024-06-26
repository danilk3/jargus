package org.jargus.configuration.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jargus.configuration.Default;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

import java.util.ArrayList;
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

    @ConstructorBinding
    public AppConfig(GlobalConfig globalConfig, List<TaskConfig> tasksConfig, AlertingConfig alertingConfig) {
        if (tasksConfig == null) {
            tasksConfig = new ArrayList<>();
        }
        tasksConfig.forEach(taskConfig -> taskConfig.addGlobalConfigs(globalConfig));
        this.tasksConfig = tasksConfig;
        this.alertingConfig = alertingConfig;
    }

    @Default
    public AppConfig(List<TaskConfig> tasksConfig, AlertingConfig alertingConfig) {
        this.tasksConfig = tasksConfig;
        this.alertingConfig = alertingConfig;
    }

    public void setConfig(AppConfig appConfig) {
        this.tasksConfig = appConfig.tasksConfig;
        this.alertingConfig = appConfig.alertingConfig;
    }

    public void deleteTaskConfigs(List<String> taskNamesToDelete) {
        this.tasksConfig = tasksConfig.stream()
                .filter(taskConfig -> !taskNamesToDelete.contains(taskConfig.getTaskName()))
                .toList();
    }
}
