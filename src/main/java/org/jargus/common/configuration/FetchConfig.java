package org.jargus.common.configuration;

import lombok.AllArgsConstructor;

import java.time.Duration;

/**
 * @author Kotelnikov D.M.
 */
@AllArgsConstructor
public class FetchConfig {
    private Duration interval;
    private Long timeout;
}
