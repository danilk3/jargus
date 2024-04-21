package org.jargus.api;

import lombok.RequiredArgsConstructor;
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
