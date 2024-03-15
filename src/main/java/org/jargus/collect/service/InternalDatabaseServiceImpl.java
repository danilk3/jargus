package org.jargus.collect.service;

import lombok.RequiredArgsConstructor;
import org.jargus.collect.model.DatabaseMetricRequestParams;
import org.jargus.common.model.Metric;
import org.jargus.database.dao.TsStorageClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author Bazhov N.S.
 */
@Service
@RequiredArgsConstructor
public class InternalDatabaseServiceImpl implements InternalDatabaseService {
    private final TsStorageClient tsStorageClient;

    @Override
    public List<Metric> getMetrics(List<DatabaseMetricRequestParams> databaseMetricRequestsParams) {
        List<Metric> result = new ArrayList<>();
        databaseMetricRequestsParams
                .forEach(requestParams ->
                        result.addAll(tsStorageClient.readMetrics(
                                Optional.empty(),
                                requestParams.getGranularity(),
                                requestParams.getFromTime().map(Date::getTime),
                                requestParams.getToTime().map(Date::getTime),
                                requestParams.getMetricName(),
                                requestParams.getLabels()))
                );
        return result;
    }

    @Override
    public void addMetrics(List<Metric> metrics, String fetchName) {
        tsStorageClient.addDataPoints(fetchName, metrics);
    }
}
