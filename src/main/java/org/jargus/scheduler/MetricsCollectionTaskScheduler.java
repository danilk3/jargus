package org.jargus.scheduler;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author Bazhov N.S.
 */
@Getter
@Component
@RequiredArgsConstructor
public class MetricsCollectionTaskScheduler {
    private final TaskService taskService;

    @Scheduled(fixedDelayString = "${global.fetch.interval:PT30S}")
    public void collect(){
        taskService.invokeTasks();
    }
}
