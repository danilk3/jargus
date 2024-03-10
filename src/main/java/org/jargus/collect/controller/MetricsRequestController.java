package org.jargus.collect.controller;

import org.jargus.common.dto.CollectMetricsRequest;
import org.jargus.common.model.Metric;

/**
 * @author Bazhov N.S.
 */
public interface MetricsRequestController {
    Metric exportMetricsFromSidecar(CollectMetricsRequest request);
    Metric exportMetricsFromInternalDatabase(CollectMetricsRequest request);
}
