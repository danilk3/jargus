package org.jargus.api.scheduler;

import org.jargus.api.controller.MetricsController;
import org.jargus.api.scheduler.model.Fetch;
import org.jargus.collect.controller.MetricsRequestController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;

/**
 * @author Bazhov N.S.
 */
@Configuration
public class FetchConfiguration {
    @Bean
    public FetchManager fetchManager(FetchProperties fetchProperties, MetricsRequestController metricsRequestController){
        HashMap<String, Fetcher> fetchers = new HashMap<>();

        List<Fetch> fetches = fetchProperties.getFetches();

        for (Fetch fetch :
                fetches) {
            fetchers.put(fetch.getTaskName(), new Fetcher(fetch, metricsRequestController));
        }

        return new FetchManagerImpl(fetchers);
    }
}
