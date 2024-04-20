package org.jargus.api.scheduler.repository;

import org.jargus.api.scheduler.domain.FetchModel;

import java.util.Collection;

/**
 * @author Bazhov N.S.
 */
public interface TaskRepository {
    FetchModel getFetchModel(String taskName);
    Collection<FetchModel> getAllTasks();
}
