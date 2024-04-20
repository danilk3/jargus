package org.jargus.api.scheduler.domain;

import lombok.Data;

/**
 * @author Bazhov N.S.
 */
@Data
public class FetchModel {
    String fetchName;
    String target;
    String path;
    String metricName;
}