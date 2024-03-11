package org.jargus.api.controller;

import org.jargus.common.dto.CollectMetricsFromInternalDatabaseRequest;
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

    public MetricsController(TsStorageClient tsStorageClient) {
        this.tsStorageClient = tsStorageClient;
    }

    // todo: продумать выбор режима чтения
    @PostMapping("metrics-db")
    public List<Metric> getMetrics(@RequestBody CollectMetricsFromInternalDatabaseRequest request) {
        return tsStorageClient.readMetrics(
                request.getGranularity(),
                request.getFromTime().map(Date::getTime),
                request.getToTime().map(Date::getTime),
                request.getMetricName(),
                request.getLabels());
    }

    // todo: temp
    @GetMapping("metrics-in-time")
    public List<Metric> getMetrics() {
        return List.of();
    }
}
