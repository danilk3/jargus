package org.jargus.scheduler;

import lombok.RequiredArgsConstructor;
import org.jargus.collect.manager.MetricsCollectionManager;
import org.jargus.common.dto.CollectMetricsInTimeRequest;
import org.jargus.scheduler.domain.TaskRequestModel;
import org.jargus.scheduler.repository.TaskRepository;
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
        Collection<TaskRequestModel> tasks = taskRepository.getAllTasks();
        for (TaskRequestModel taskRequestModel : tasks) {
            CollectMetricsInTimeRequest request = new CollectMetricsInTimeRequest(taskRequestModel);
            Thread thread = new Thread(() -> metricsCollectionManager.exportMetricsFromSidecar(request));
            thread.start();
        }
    }
}
