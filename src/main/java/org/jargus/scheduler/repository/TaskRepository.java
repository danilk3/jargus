package org.jargus.scheduler.repository;

import org.jargus.scheduler.domain.TaskModel;

import java.util.Collection;

/**
 * @author Bazhov N.S.
 */
public interface TaskRepository {
    TaskModel getFetchModel(String taskName);
    Collection<TaskModel> getAllTasks();
}
