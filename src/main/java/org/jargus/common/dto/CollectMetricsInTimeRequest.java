package org.jargus.common.dto;

import lombok.Getter;
import org.jargus.scheduler.domain.TaskModel;

/**
 * @author Bazhov N.S.
 */
@Getter
public class CollectMetricsInTimeRequest extends CollectMetricsRequest {

    public CollectMetricsInTimeRequest(TaskModel fetchName) {
        super(fetchName);
    }
}
