package org.jargus.collect.client;

import org.jargus.collect.model.ExportMetricRequestParams;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author Bazhov N.S.
 */
@FeignClient(name = "collector", url = "http://localhost:8081")
public interface CollectMetricsClient {
    @RequestMapping(method = RequestMethod.GET, value = "/actuator/prometheus", consumes = MediaType.TEXT_PLAIN_VALUE)
    List<String> export(ExportMetricRequestParams exportMetricRequestParams);
}
