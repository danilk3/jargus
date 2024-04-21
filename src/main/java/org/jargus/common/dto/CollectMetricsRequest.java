package org.jargus.common.dto;


import lombok.Getter;
import org.jargus.scheduler.domain.TaskModel;

/**
 * @author Bazhov N.S.
 */
@Getter
public abstract class CollectMetricsRequest {
    private final TaskModel taskModel;

    public CollectMetricsRequest(TaskModel taskModel) {
        this.taskModel = taskModel;
    }
}
