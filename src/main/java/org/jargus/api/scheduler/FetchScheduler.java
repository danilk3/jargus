package org.jargus.api.scheduler;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jargus.api.scheduler.properties.FetchProperties;
import org.jargus.collect.manager.MetricsCollectionManagerImpl;
import org.jargus.common.dto.CollectMetricsInTimeRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author Bazhov N.S.
 */
@Getter
@Component
@RequiredArgsConstructor
public class FetchScheduler {
    private final FetchService fetchService;

    @Scheduled(fixedDelayString = "${global.fetch.interval:PT1S}")
    public void collect(){
        fetchService.initiateFetch();
    }
}
