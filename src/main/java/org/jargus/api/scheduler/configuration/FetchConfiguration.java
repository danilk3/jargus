package org.jargus.api.scheduler.configuration;

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