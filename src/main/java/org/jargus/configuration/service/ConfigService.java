package org.jargus.configuration.service;

import java.util.List;

import lombok.AllArgsConstructor;
import org.jargus.configuration.mapper.ConfigMapper;
import org.jargus.configuration.model.AppConfig;
import org.jargus.database.dao.TsStorageClient;
import org.springframework.stereotype.Service;

/**
 * @author kotelnikovdan
 */
@AllArgsConstructor
@Service
public class ConfigService {

    private AppConfig appConfig;
    private TsStorageClient tsStorageClient;
    private ConfigMapper configMapper;

    public void setConfig(AppConfig newConfig) {
        configMapper.updateAppConfig(newConfig, appConfig);
    }

    public void deleteTaskConfigs(List<String> taskNamesToDelete) {
        appConfig.deleteTaskConfigs(taskNamesToDelete);
        tsStorageClient.removeTasks(taskNamesToDelete);
    }

    public AppConfig getConfig() {
        return appConfig;
    }
}
