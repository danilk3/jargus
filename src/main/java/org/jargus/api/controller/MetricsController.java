package org.jargus.api.controller;

import lombok.RequiredArgsConstructor;
import org.jargus.collect.controller.MetricsRequestController;
import org.jargus.common.dto.CollectMetricsFromInternalDatabaseRequest;
import org.jargus.common.dto.CollectMetricsInTimeRequest;
import org.jargus.common.dto.CollectMetricsRequest;
import org.jargus.common.model.Metric;
import org.jargus.database.dao.TsStorageClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Kotelnikov D.M.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/jargus/api")
public class MetricsController {
    private final MetricsRequestController metricsRequestController;

    // todo: продумать выбор режима чтения
    @PostMapping("metrics-db")
    public List<Metric> getMetrics(@RequestBody CollectMetricsFromInternalDatabaseRequest request) {
        return metricsRequestController.exportMetricsFromInternalDatabase(request);
    }

    // todo: temp
    @GetMapping("metrics-in-time")
    public List<Metric> getMetrics() {

        CollectMetricsRequest collectMetricsRequest = new CollectMetricsInTimeRequest("");
        return metricsRequestController.exportMetricsFromSidecar(collectMetricsRequest);
    }
}
