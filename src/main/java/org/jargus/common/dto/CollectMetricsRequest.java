package org.jargus.common.dto;


import lombok.Getter;
import org.jargus.scheduler.domain.TaskRequestModel;

/**
 * @author Bazhov N.S.
 */
@Getter
public abstract class CollectMetricsRequest {
    private final TaskRequestModel taskRequestModel;

    public CollectMetricsRequest(TaskRequestModel taskRequestModel) {
        this.taskRequestModel = taskRequestModel;
    }

    public String getFetchName() {
        return taskRequestModel.taskName;
    }
}
