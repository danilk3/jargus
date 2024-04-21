package org.jargus.api;

import lombok.RequiredArgsConstructor;
import org.jargus.api.scheduler.FetchManager;
import org.jargus.collect.service.MetricsRequestService;
import org.jargus.configuration.mapper.ConfigMapper;
import org.jargus.configuration.model.AppConfig;
import org.jargus.collect.manager.MetricsCollectionManager;
import org.jargus.common.dto.CollectMetricsFromInternalDatabaseRequest;
import org.jargus.common.dto.CollectMetricsInTimeRequest;
import org.jargus.common.dto.CollectMetricsRequest;
import org.jargus.common.model.Metric;
import org.jargus.scheduler.domain.TaskModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Kotelnikov D.M.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/jargus/api")
public class MetricsController {
    private final MetricsRequestService metricsRequestService;

    private final FetchManager fetchManager;
    private final AppConfig appConfig;
    private final ConfigMapper configMapper;

    @PutMapping("update-config")
    public void updateConfig(@RequestBody AppConfig newConfig) {
        configMapper.updateAppConfig(newConfig, appConfig);
    }

    @DeleteMapping("delete-config")
    public void deleteConfig(@RequestBody AppConfig newConfig) {

    }

    @PostMapping("create-config")
    public void createConfig(@RequestBody AppConfig newConfig) {

    }
    private final MetricsCollectionManager metricsCollectionManager;

    @GetMapping("metrics-db")
    public List<Metric> getMetrics(@RequestBody CollectMetricsFromInternalDatabaseRequest request) {
        return metricsCollectionManager.exportMetricsFromInternalDatabase(request);
    }

    @GetMapping("metrics-in-time")
    public List<Metric> getMetrics(@RequestParam String taskName) {
        CollectMetricsRequest collectMetricsRequest = new CollectMetricsInTimeRequest(new TaskModel());
        return metricsCollectionManager.exportMetricsFromSidecar(collectMetricsRequest);
    }
}
