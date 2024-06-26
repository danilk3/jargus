package org.jargus.collect.manager;

import org.jargus.common.dto.CollectMetricsFromInternalDatabaseRequest;
import org.jargus.common.dto.CollectMetricsInTimeRequest;
import org.jargus.common.dto.CollectMetricsRequest;
import org.jargus.common.model.Metric;

import java.util.List;

/**
 * @author Bazhov N.S.
 */
public interface MetricsCollectionManager {
    List<Metric> exportMetricsFromSidecar(CollectMetricsInTimeRequest request);
    List<Metric> exportMetricsFromInternalDatabase(CollectMetricsFromInternalDatabaseRequest request);
}
