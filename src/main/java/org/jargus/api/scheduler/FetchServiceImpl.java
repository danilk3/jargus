package org.jargus.api.scheduler;

import lombok.RequiredArgsConstructor;
import org.jargus.api.scheduler.domain.FetchModel;
import org.jargus.api.scheduler.repository.TaskRepository;
import org.jargus.collect.manager.MetricsCollectionManager;
import org.jargus.common.dto.CollectMetricsInTimeRequest;
import org.jargus.common.dto.CollectMetricsRequest;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;

/**
 * @author Bazhov N.S.
 */
@Service
@RequiredArgsConstructor
public class FetchServiceImpl implements FetchService {
    private final MetricsCollectionManager metricsCollectionManager;
    private final TaskRepository taskRepository;

    @Override
    public void initiateFetch() {
        Collection<FetchModel> tasks = taskRepository.getAllTasks();
        for (FetchModel fetchModel : tasks) {
            CollectMetricsRequest request = new CollectMetricsInTimeRequest(fetchModel.getMetricName(), fetchModel.getFetchName(), fetchModel.getTarget() + fetchModel.getPath());
            Thread thread = new Thread(() -> metricsCollectionManager.exportMetricsFromSidecar(request));
            thread.start();
        }
    }
}
