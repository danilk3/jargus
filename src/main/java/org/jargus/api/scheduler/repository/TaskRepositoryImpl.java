package org.jargus.api.scheduler.repository;

import lombok.RequiredArgsConstructor;
import org.jargus.api.scheduler.domain.FetchModel;

import java.util.Collection;
import java.util.HashMap;

/**
 * @author Bazhov N.S.
 */
@RequiredArgsConstructor
public class TaskRepositoryImpl implements TaskRepository {

    private final HashMap<String, FetchModel> tasks;
    @Override
    public FetchModel getFetchModel(String taskName) {
        return tasks.get(taskName);
    }

    @Override
    public Collection<FetchModel> getAllTasks() {
        return tasks.values();
    }
}
