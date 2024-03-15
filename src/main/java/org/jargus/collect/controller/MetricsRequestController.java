package org.jargus.collect.controller;

import org.jargus.common.dto.CollectMetricsFromInternalDatabaseRequest;
import org.jargus.common.dto.CollectMetricsRequest;
import org.jargus.common.model.Metric;

import java.util.List;

/**
 * @author Bazhov N.S.
 */
public interface MetricsRequestController {
    List<Metric> exportMetricsFromSidecar(CollectMetricsRequest request);
    List<Metric> exportMetricsFromInternalDatabase(List<CollectMetricsFromInternalDatabaseRequest> requests);
}
