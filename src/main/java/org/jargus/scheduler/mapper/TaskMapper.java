package org.jargus.scheduler.mapper;

import org.jargus.configuration.model.TaskConfig;
import org.jargus.scheduler.domain.TaskRequestModel;
import org.springframework.stereotype.Component;

/**
 * @author Bazhov N.S.
 */
@Component
public class TaskMapper {
    public TaskRequestModel mapTaskConfigToTaskModel(TaskConfig taskConfig){
        String uri = taskConfig.getTarget() + taskConfig.getPath();
        TaskRequestModel task = new TaskRequestModel();
        task.setUri(uri);
        task.setTaskName(taskConfig.getTaskName());
//        task.setHeaders(taskConfig.getRequestsConfig().getHeaders());
//        task.setParams(taskConfig.getRequestsConfig().getParams());

        return task;
    }
}
