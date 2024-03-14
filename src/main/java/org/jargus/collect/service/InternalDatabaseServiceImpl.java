package org.jargus.collect.service;

import lombok.RequiredArgsConstructor;
import org.jargus.collect.model.DatabaseMetricRequestParams;
import org.jargus.common.model.Metric;
import org.jargus.database.dao.TsStorageClient;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author Bazhov N.S.
 */
@Service
@RequiredArgsConstructor
public class InternalDatabaseServiceImpl implements InternalDatabaseService {
    private final TsStorageClient tsStorageClient;

    @Override
    public List<Metric> getMetrics(DatabaseMetricRequestParams databaseMetricRequestParams) {
        return tsStorageClient.readMetrics(
                databaseMetricRequestParams.getGranularity(),
                databaseMetricRequestParams.getFromTime().map(Date::getTime),
                databaseMetricRequestParams.getToTime().map(Date::getTime),
                databaseMetricRequestParams.getMetricName(),
                databaseMetricRequestParams.getLabels());
    }
}
