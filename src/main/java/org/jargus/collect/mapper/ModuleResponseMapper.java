package org.jargus.collect.mapper;

import org.jargus.collect.model.RawMetric;
import org.jargus.common.model.Metric;

/**
 * @author Bazhov N.S.
 */
public interface ModuleResponseMapper {
    Metric mapMetrics(RawMetric rawMetric);
}
