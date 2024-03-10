package org.jargus.collect.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Bazhov N.S.
 */
@AllArgsConstructor
@NoArgsConstructor
public class RawMetric {
    private String metricName;
    private List<String> labels;
    private double value;


}
