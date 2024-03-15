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

    private final FetchManager fetchManager;

    // todo: продумать выбор режима чтения
    @PostMapping("metrics-db")
    public List<Metric> getMetrics(@RequestBody List<CollectMetricsFromInternalDatabaseRequest> requests) {
        return metricsRequestController.exportMetricsFromInternalDatabase(requests);
    }

    // todo: temp
    @GetMapping("metrics-in-time")
    public List<Metric> getMetrics(@RequestBody String fetchName) {

        CollectMetricsRequest collectMetricsRequest = new CollectMetricsInTimeRequest("", fetchManager.getUri(fetchName));
        return metricsRequestController.exportMetricsFromSidecar(collectMetricsRequest);
    }
}
