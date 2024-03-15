package org.jargus.api.scheduler.model;

import lombok.Data;

/**
 * @author Bazhov N.S.
 */
@Data
public class Fetch {
    String taskName;
    String target;
    String path;
}
