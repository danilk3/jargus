package org.jargus.scheduler.repository;

import org.jargus.configuration.model.TsDbConfig;
import org.jargus.scheduler.domain.TaskRequestModel;

import java.util.Collection;

/**
 * @author Bazhov N.S.
 */
public interface TaskRepository {
    TaskRequestModel getTaskModel(String taskName);
    TsDbConfig getTsDbConfig(String taskName);
    Collection<TaskRequestModel> getAllTasks();
}
