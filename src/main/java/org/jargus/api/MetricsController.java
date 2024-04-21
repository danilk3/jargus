package org.jargus.api;

import lombok.RequiredArgsConstructor;
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
        return metricsCollectionManager.exportMetricsFromInternalDatabase(request);
    }

    @GetMapping("metrics-in-time")
    public List<Metric> getMetrics(@RequestParam String taskName) {
        CollectMetricsInTimeRequest collectMetricsRequest = new CollectMetricsInTimeRequest(new TaskRequestModel());
        return metricsCollectionManager.exportMetricsFromSidecar(collectMetricsRequest);
    }
}
