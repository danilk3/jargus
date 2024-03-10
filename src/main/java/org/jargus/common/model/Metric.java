package org.jargus.common.model;

import java.util.List;

/**
 * @author Kotelnikov D.M.
 */
public record Metric(String name, List<Label> labels, DataPoint datapoint) {
}
