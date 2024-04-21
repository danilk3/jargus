package org.jargus.configuration.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;

/**
 * @author Kotelnikov D.M.
 */
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class FetchConfig {
    private Duration interval;
    private Long timeout;
}
