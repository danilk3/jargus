package org.jargus.common.dto;

import lombok.RequiredArgsConstructor;

/**
 * @author Bazhov N.S.
 */
@RequiredArgsConstructor
public abstract class CollectMetricsRequest {
    private final String metricName;
}
