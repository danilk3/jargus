package org.jargus.common.configuration;

import lombok.AllArgsConstructor;

import java.time.Duration;

/**
 * @author Kotelnikov D.M.
 */
@AllArgsConstructor
public class TsDbConfig {
    private Duration metricSecondsTtl;
    private Duration metricMinutesTtl;
    private Duration metricHoursTtl;
}
