package org.jargus.api.controller;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.jargus.configuration.model.AppConfig;
import org.jargus.configuration.service.ConfigService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Kotelnikov D.M.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/jargus/api/config")
public class ConfigController {

    private final ConfigService configService;

    @PatchMapping
    public AppConfig updateConfig(@RequestBody AppConfig newConfig) {
        configService.updateConfig(newConfig);
        return configService.getConfig();
    }

    @DeleteMapping
    public AppConfig deleteConfig(@RequestParam List<String> taskNamesToDelete) {
        configService.deleteTaskConfigs(taskNamesToDelete);
        return configService.getConfig();
    }

    @PostMapping
    public AppConfig createConfig(@RequestBody AppConfig newConfig) {
        configService.setConfig(newConfig);
        return configService.getConfig();
    }

    @GetMapping
    public AppConfig getConfig() {
        return configService.getConfig();
    }
}
