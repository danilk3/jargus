package org.jargus.database.models;

import org.jargus.common.model.Label;

import java.util.List;

/**
 * @author Kotelnikov D.M.
 */
public record MetricLabelsValueEntry(double value, List<Label> labels) {
}
