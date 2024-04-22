package org.jargus.scheduler.repository;

import lombok.RequiredArgsConstructor;
import org.jargus.configuration.model.AppConfig;
import org.jargus.configuration.model.TaskConfig;
import org.jargus.configuration.model.TsDbConfig;
import org.jargus.scheduler.domain.TaskRequestModel;
import org.jargus.scheduler.mapper.TaskMapper;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Bazhov N.S.
 */
@RequiredArgsConstructor
@Component
public class TaskRepositoryImpl implements TaskRepository {
    private final AppConfig appConfig;
    private final TaskMapper taskMapper;

    @Override
    public TaskRequestModel getTaskModel(String taskName) {
        Optional<TaskConfig> taskConfig = appConfig.getTasksConfig().stream().filter(x -> x.getTaskName().equals(taskName)).findFirst();
        if (taskConfig.isPresent()){
            return taskMapper.mapTaskConfigToTaskModel(taskConfig.get());
        } else {
            throw new IllegalArgumentException("Unknown task name");
        }
    }

    @Override
    public TsDbConfig getTsDbConfig(String taskName) {
        Optional<TaskConfig> taskConfig = appConfig.getTasksConfig().stream().filter(x -> x.getTaskName().equals(taskName)).findFirst();
        if (taskConfig.isPresent()){
            return taskConfig.get().getTsDbConfig();
        } else {
            throw new IllegalArgumentException("Unknown task name");
        }
    }

    @Override
    public Collection<TaskRequestModel> getAllTasks() {
        return appConfig.getTasksConfig().stream().map(taskMapper::mapTaskConfigToTaskModel).collect(Collectors.toList());
    }
}
