package org.jargus.api.controller;

import lombok.RequiredArgsConstructor;
import org.jargus.configuration.mapper.ConfigMapper;
import org.jargus.configuration.model.AppConfig;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Kotelnikov D.M.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/jargus/api/config")
public class ConfigController {

    private final AppConfig appConfig;
    private final ConfigMapper configMapper;

    @PatchMapping
    public AppConfig updateConfig(@RequestBody AppConfig newConfig) {
        configMapper.updateAppConfig(newConfig, appConfig);
        return appConfig;
    }

    @DeleteMapping
    public AppConfig deleteConfig(@RequestParam List<String> taskNamesToDelete) {
        appConfig.deleteTaskConfigs(taskNamesToDelete);
        return appConfig;
    }

    @PostMapping
    public AppConfig createConfig(@RequestBody AppConfig newConfig) {
        appConfig.setConfig(newConfig);
        return appConfig;
    }

    @GetMapping
    public AppConfig getConfig() {
        return appConfig;
    }
}
