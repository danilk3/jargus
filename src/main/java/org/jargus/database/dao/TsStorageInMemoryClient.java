package org.jargus.database.dao;

import org.jargus.common.dto.MetricRequest;
import org.jargus.common.model.Metric;
import org.jargus.database.service.TsStorage;
import org.jargus.scheduler.repository.TaskRepository;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author Kotelnikov D.M.
 */
@Component
public class TsStorageInMemoryClient implements TsStorageClient {

    private final Map<String, TsStorage> tsStorageMap = new HashMap<>();
    private final TaskRepository taskRepository;

    public TsStorageInMemoryClient(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }


    @Override
    public synchronized void addDataPoint(String fetchName, Metric metric) {
        tsStorageMap.computeIfAbsent(fetchName, key -> new TsStorage(taskRepository.getTsDbConfig(fetchName)))
                .addDataPoint(metric);
    }

    @Override
    public synchronized void addDataPoints(String fetchName, List<Metric> metrics) {
        metrics.forEach(metric -> addDataPoint(fetchName, metric));
    }

    @Override
    public List<Metric> readMetrics(Optional<String> fetchName,
                                    List<MetricRequest> metricRequests) {
        if (fetchName.isEmpty()) {
            return readFromAllFetches(metricRequests);
        }
        TsStorage tsStorageEntry = tsStorageMap.computeIfAbsent(fetchName.get(), key ->
                new TsStorage(taskRepository.getTsDbConfig(fetchName.get())));

        if (metricRequests.isEmpty()) {
            return tsStorageEntry.readAll();
        }

        List<Metric> result = new ArrayList<>();
        metricRequests.forEach(
                metricRequest -> {
                    result.addAll(
                            tsStorageEntry.readMetrics(
                                    metricRequest.getGranularity(),
                                    metricRequest.getFromTime(),
                                    metricRequest.getToTime(),
                                    metricRequest.getMetricName(),
                                    metricRequest.getLabels()
                            )
                    );
                }
        );

        return result;
    }

    private List<Metric> readFromAllFetches(List<MetricRequest> metricRequests) {
        List<Metric> result = new ArrayList<>();
        if (metricRequests.isEmpty()) {
            tsStorageMap.values().forEach(storage -> result.addAll(storage.readAll()));
            return result;
        }

        for (Map.Entry<String, TsStorage> tsStorageEntry : tsStorageMap.entrySet()) {
            metricRequests.forEach(
                    metricRequest -> result.addAll(
                            tsStorageEntry.getValue().readMetrics(
                                    metricRequest.getGranularity(),
                                    metricRequest.getFromTime(),
                                    metricRequest.getToTime(),
                                    metricRequest.getMetricName(),
                                    metricRequest.getLabels()
                            )
                    )
            );
        }
        return result;
    }
}
