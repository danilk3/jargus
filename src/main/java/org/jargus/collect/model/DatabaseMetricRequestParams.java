package org.jargus.collect.model;

import lombok.Builder;
import lombok.Getter;
import org.jargus.common.model.Label;
import org.jargus.database.models.Granularity;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author Bazhov N.S.
 */
@Builder
@Getter
public class DatabaseMetricRequestParams {
    private String metricName;

    private Optional<Date> fromTime;

    private Optional<Date> toTime;

    private Granularity granularity;

    private List<Label> labels;
}
