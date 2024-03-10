package org.jargus.collect.mapper;

import org.jargus.collect.model.RawMetrics;
import org.jargus.common.model.Metric;

/**
 * @author Bazhov N.S.
 */
public interface ModuleResponseMapper {
    Metric mapMetrics(RawMetrics rawMetrics);
}
