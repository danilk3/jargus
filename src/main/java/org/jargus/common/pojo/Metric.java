package org.jargus.common.pojo;

import java.util.List;

/**
 * @author Kotelnikov D.M.
 */
public record Metric(String name, List<Label> labels, DataPoint datapoint) {
}
