package org.jargus.collect.client;

import org.jargus.collect.model.ExportMetricsRequestParams;
import org.jargus.collect.model.RawMetrics;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Bazhov N.S.
 */
@FeignClient()
public interface CollectMetricsClient {
    @RequestMapping(method = RequestMethod.POST)
    RawMetrics export(ExportMetricsRequestParams exportMetricsRequestParams);
}
