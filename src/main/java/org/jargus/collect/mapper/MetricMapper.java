package org.jargus.collect.mapper;

import org.jargus.collect.model.RawMetric;

import java.util.List;

/**
 * @author Bazhov N.S.
 */
public class MetricMapper {
    public RawMetric map(String metric){

        return new RawMetric(metric, null, 0, null);
    }
}
