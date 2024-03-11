package org.jargus.common.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.jargus.common.model.Label;
import org.jargus.database.models.Granularity;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author Bazhov N.S.
 */
@Data
public class CollectMetricsFromInternalDatabaseRequest {

    private final String metricName;

    private final Optional<Date> fromTime;

    private final Optional<Date> toTime;

    private final Granularity granularity;

    private final List<Label> labels;

    public CollectMetricsFromInternalDatabaseRequest(String metricName,
                                                     @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Optional<Date> fromTime,
                                                     @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Optional<Date> toTime,
                                                     Optional<Granularity> granularity,
                                                     List<Label> labels) {
        this.metricName = metricName;
        this.fromTime = fromTime;
        this.toTime = toTime;
        this.granularity = granularity.orElse(Granularity.SECONDS);
        this.labels = labels;
    }
}
