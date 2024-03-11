package org.jargus.api.controller;

import org.jargus.collect.controller.MetricsRequestController;
import org.jargus.common.dto.CollectMetricsFromInternalDatabaseRequest;
import org.jargus.common.dto.CollectMetricsInTimeRequest;
import org.jargus.common.dto.CollectMetricsRequest;
import org.jargus.common.model.Metric;
import org.jargus.database.dao.TsStorageClient;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @author Kotelnikov D.M.
 */
@RestController
@RequestMapping("/jargus/api")
public class MetricsController {

    private final TsStorageClient tsStorageClient;
    private final MetricsRequestController metricsRequestController;

    public MetricsController(TsStorageClient tsStorageClient, MetricsRequestController metricsRequestController) {
        this.tsStorageClient = tsStorageClient;
        this.metricsRequestController = metricsRequestController;
    }

    // todo: продумать выбор режима чтения
    @PostMapping("metrics-db")
    public List<Metric> getMetrics(@RequestBody CollectMetricsFromInternalDatabaseRequest request) {
        Metric metric = metricsRequestController.exportMetricsFromInternalDatabase(request);

        return List.of(metric);
    }

    // todo: temp
    @GetMapping("metrics-in-time")
    public List<Metric> getMetrics() {
        String metricName = "blabla";
        CollectMetricsRequest collectMetricsRequest = new CollectMetricsInTimeRequest(metricName);
        Metric metric = metricsRequestController.exportMetricsFromSidecar(collectMetricsRequest);

        return List.of(metric);
    }
}
