package org.jargus.api;

import lombok.RequiredArgsConstructor;
import org.jargus.collect.manager.MetricsCollectionManager;
import org.jargus.common.dto.CollectMetricsFromInternalDatabaseRequest;
import org.jargus.common.dto.CollectMetricsInTimeRequest;
import org.jargus.common.model.Metric;
import org.jargus.configuration.model.AppConfig;
import org.jargus.configuration.model.TaskConfig;
import org.jargus.scheduler.domain.TaskRequestModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Kotelnikov D.M.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/jargus/api")
public class MetricsController {

    private final AppConfig appConfig;

    private final MetricsCollectionManager metricsCollectionManager;

    @PostMapping("metrics-db")
    public List<Metric> getMetrics(@RequestBody CollectMetricsFromInternalDatabaseRequest request) {
        return metricsCollectionManager.exportMetricsFromInternalDatabase(request);
    }

    @GetMapping("metrics-in-time")
    public List<Metric> getMetrics(@RequestParam String taskName) {
        TaskRequestModel taskRequestModel = new TaskRequestModel();
        taskRequestModel.setTaskName(taskName);
        TaskConfig taskConfig = appConfig.getTasksConfig().stream().filter(x -> x.getTaskName().equals(taskName)).findFirst().get();
        taskRequestModel.setParams(taskConfig.getRequestsConfig().getParams());
        taskRequestModel.setUri(taskConfig.getTarget() + taskConfig.getPath());
        taskRequestModel.setHeaders(taskConfig.getRequestsConfig().getHeaders());
        CollectMetricsInTimeRequest collectMetricsRequest = new CollectMetricsInTimeRequest(taskRequestModel);
        return metricsCollectionManager.exportMetricsFromSidecar(collectMetricsRequest);
    }
}
