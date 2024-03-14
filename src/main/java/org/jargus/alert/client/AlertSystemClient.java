package org.jargus.alert.client;

import org.jargus.common.model.Metric;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Bazhov N.S.
 */
@FeignClient("alertClient")
public interface AlertSystemClient {
    @RequestMapping(method = RequestMethod.POST)
    void alert(Metric metric);
}
