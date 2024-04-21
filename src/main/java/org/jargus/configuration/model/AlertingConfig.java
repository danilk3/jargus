package org.jargus.configuration.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Kotelnikov D.M.
 */
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class AlertingConfig {
    private String host;
    private String path;
    private Long timeout;
}
