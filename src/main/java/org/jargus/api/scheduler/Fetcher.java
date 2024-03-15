package org.jargus.api.scheduler;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jargus.api.scheduler.model.Fetch;
import org.jargus.collect.controller.MetricsRequestController;
import org.jargus.common.dto.CollectMetricsInTimeRequest;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @author Bazhov N.S.
 */
@Getter
@RequiredArgsConstructor
public class Fetcher {
    private final Fetch fetch;
    private final MetricsRequestController metricsRequestController;

    @Scheduled(fixedDelayString = "${global.fetch.interval:PT1S}")
    public void collect(){
        metricsRequestController.exportMetricsFromSidecar(new CollectMetricsInTimeRequest("name", fetch.getTarget() + fetch.getPath()));
    }
}
