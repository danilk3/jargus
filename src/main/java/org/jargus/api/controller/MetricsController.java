package org.jargus.api.controller;

import lombok.RequiredArgsConstructor;
import org.jargus.api.scheduler.FetchManager;
import org.jargus.collect.service.MetricsRequestService;
import org.jargus.configuration.mapper.ConfigMapper;
import org.jargus.configuration.model.AppConfig;
import org.jargus.common.dto.CollectMetricsFromInternalDatabaseRequest;
import org.jargus.common.dto.CollectMetricsInTimeRequest;
import org.jargus.common.dto.CollectMetricsRequest;
import org.jargus.common.model.Metric;
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

    @GetMapping("metrics-db")
    public List<Metric> getMetrics(@RequestBody CollectMetricsFromInternalDatabaseRequest request) {
        return metricsRequestService.exportMetricsFromInternalDatabase(request);
    }

    @GetMapping("metrics-in-time")
    public List<Metric> getMetrics(@RequestParam String fetchName) {

        CollectMetricsRequest collectMetricsRequest = new CollectMetricsInTimeRequest("", fetchName, fetchManager.getUri(fetchName));
        return metricsRequestService.exportMetricsFromSidecar(collectMetricsRequest);
    }
}
