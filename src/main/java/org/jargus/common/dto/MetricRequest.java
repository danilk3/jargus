package org.jargus.common.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import org.jargus.common.model.Label;
import org.jargus.database.models.Granularity;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author Kotelnikov D.M.
 */
@Getter
public class MetricRequest {

    private final String metricName;

    private final Optional<Long> fromTime;

    private final Optional<Long> toTime;

    private final Granularity granularity;

    private final List<Label> labels;

    public MetricRequest(String metricName,
                         @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Optional<Date> fromTime,
                         @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Optional<Date> toTime,
                         Optional<Granularity> granularity,
                         Optional<List<Label>> labels) {
        this.metricName = metricName;
        this.fromTime = fromTime.map(Date::getTime);
        this.toTime = toTime.map(Date::getTime);
        this.granularity = granularity.orElse(Granularity.SECONDS);
        this.labels = labels.orElse(List.of());
    }

}
