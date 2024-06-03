package org.jargus.database.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.jargus.common.dto.MetricRequest;
import org.jargus.common.model.Metric;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * @author Kotelnikov D.M.
 */
@Component
public interface TsStorageClient {

    void addDataPoint(String fetchName, Metric metric);

    void addDataPoints(String fetchName, List<Metric> metrics);

    List<Metric> readMetrics(Optional<String> fetchName, List<MetricRequest> metricRequests);

    void removeTasks(List<String> taskNamesToDelete);

    String getSerializedSnapshot() throws JsonProcessingException;

    void loadSnapshot(String snapshotString) throws JsonProcessingException;
}
