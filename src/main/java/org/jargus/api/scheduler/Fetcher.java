package org.jargus.api.scheduler;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jargus.collect.service.MetricsRequestService;
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
    private final MetricsRequestService metricsRequestService;

    @Scheduled(fixedDelayString = "${global.fetch.interval:PT1S}")
    public void collect(){
        metricsRequestService.exportMetricsFromSidecar(new CollectMetricsInTimeRequest("name", fetch.getFetches().getTaskName(), fetch.getFetches().getTarget() + fetch.getFetches().getPath()));
    }
}
