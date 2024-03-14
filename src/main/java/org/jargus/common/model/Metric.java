package org.jargus.common.model;

import lombok.Builder;

import java.util.List;

/**
 * @author Kotelnikov D.M.
 */
@Builder
public record Metric(String name, List<Label> labels, DataPoint datapoint) {
}
