package org.jargus.collect.mapper;

import org.jargus.collect.model.RawMetric;
import org.jargus.common.model.Metric;
import org.mapstruct.Mapper;

/**
 * @author Bazhov N.S.
 */
@Mapper(componentModel = "spring")
public interface ModuleResponseMapper {
    Metric mapMetrics(RawMetric rawMetric);
}
