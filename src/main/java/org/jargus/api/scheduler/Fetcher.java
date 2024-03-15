package org.jargus.api.scheduler;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jargus.api.scheduler.model.Fetch;
import org.jargus.collect.controller.MetricsRequestController;
import org.jargus.common.dto.CollectMetricsInTimeRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author Bazhov N.S.
 */
@Getter
@Component
@RequiredArgsConstructor
public class Fetcher {
//    private final Fetch fetch;
    private final FetchProperties fetch;
    private final MetricsRequestController metricsRequestController;

    @Scheduled(fixedDelayString = "${global.fetch.interval:PT1S}")
    public void collect(){
        metricsRequestController.exportMetricsFromSidecar(new CollectMetricsInTimeRequest("name", fetch.getFetches().getTaskName(), fetch.getFetches().getTarget() + fetch.getFetches().getPath()));
    }
}
