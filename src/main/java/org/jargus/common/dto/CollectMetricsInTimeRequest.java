package org.jargus.common.dto;

import lombok.Getter;
import org.jargus.scheduler.domain.TaskRequestModel;

/**
 * @author Bazhov N.S.
 */
@Getter
public class CollectMetricsInTimeRequest extends CollectMetricsRequest {

    public CollectMetricsInTimeRequest(TaskRequestModel fetchName) {
        super(fetchName);
    }
}
