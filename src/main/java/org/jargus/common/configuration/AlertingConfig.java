package org.jargus.common.configuration;

import lombok.AllArgsConstructor;

/**
 * @author Kotelnikov D.M.
 */
@AllArgsConstructor
public class AlertingConfig {
    private String host;
    private String path;
    private Long timeout;
}
