package org.jargus.api.scheduler;

import org.jargus.api.controller.MetricsController;
import org.jargus.api.scheduler.model.Fetch;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.Scope;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;

/**
 * @author Bazhov N.S.
 */
//@Configuration
public class FetchConfiguration {
//    @Bean
//    public FetchManager fetchManager(FetchProperties fetchProperties, MetricsRequestController metricsRequestController, ConfigurableApplicationContext applicationContext){
//        HashMap<String, Fetcher> fetchers = new HashMap<>();
//
//        List<Fetch> fetches = fetchProperties.getFetches();
//
//        for (Fetch fetch : fetches) {
//
//            Fetcher bean = new Fetcher(fetch, metricsRequestController);
//
//            ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
//            beanFactory.registerSingleton(bean.getFetch().getTaskName(), bean);
//
//            fetchers.put(fetch.getTaskName(), bean);
//        }
//
//        return new FetchManagerImpl(fetchers);
//    }
}
