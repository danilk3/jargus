package org.jargus.collect.mapper;

import org.jargus.collect.model.MetricInfo;
import org.jargus.common.dto.ModuleResponse;
import org.jargus.common.model.Metric;

/**
 * @author Bazhov N.S.
 */
public interface ModuleResponseMapper {
    ModuleResponse mapMetrics(MetricInfo metricInfo);
}
