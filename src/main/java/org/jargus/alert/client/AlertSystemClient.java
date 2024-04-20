package org.jargus.alert.client;

import org.jargus.alert.model.Event;
import org.jargus.common.model.Metric;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestClient;

/**
 * @author Bazhov N.S.
 */
public interface AlertSystemClient {
    void alert(Event event);
}
