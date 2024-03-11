package org.jargus.collect.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.jargus.common.model.DataPoint;

import java.util.List;

/**
 * @author Bazhov N.S.
 */
@AllArgsConstructor
@NoArgsConstructor
public class RawMetric {
    private String name;
    private List<String> labels;
    private double value;
    private DataPoint datapoint;

}
