package org.jargus.scheduler;

import lombok.RequiredArgsConstructor;
import org.jargus.scheduler.domain.TaskModel;
import org.jargus.scheduler.repository.TaskRepository;
import org.jargus.collect.manager.MetricsCollectionManager;
import org.jargus.common.dto.CollectMetricsInTimeRequest;
import org.jargus.common.dto.CollectMetricsRequest;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * @author Bazhov N.S.
 */
@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final MetricsCollectionManager metricsCollectionManager;
    private final TaskRepository taskRepository;

    @Override
    public void invokeTasks() {
        Collection<TaskModel> tasks = taskRepository.getAllTasks();
        for (TaskModel taskModel : tasks) {
            CollectMetricsRequest request = new CollectMetricsInTimeRequest(taskModel.getMetricName(), taskModel);
            Thread thread = new Thread(() -> metricsCollectionManager.exportMetricsFromSidecar(request));
            thread.start();
        }
    }
}
