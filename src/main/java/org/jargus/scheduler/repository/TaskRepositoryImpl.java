package org.jargus.scheduler.repository;

import org.jargus.scheduler.domain.TaskModel;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;

/**
 * @author Bazhov N.S.
 */
//@RequiredArgsConstructor
@Component
public class TaskRepositoryImpl implements TaskRepository {

    TaskModel t1;
    TaskModel t2;

    private final Map<String, TaskModel> tasks;

    public TaskRepositoryImpl() {
        t1 = new TaskModel();
        t1.setUri("http://localhost:8081/actuator/prometheus");

        t2 = new TaskModel();
        t2.setUri("http://localhost:8082/actuator/prometheus");

        tasks = Map.of("t1", t1, "t2", t2);
    }

    @Override
    public TaskModel getFetchModel(String taskName) {
        return tasks.get(taskName);
    }

    @Override
    public Collection<TaskModel> getAllTasks() {
        return tasks.values();
    }
}
