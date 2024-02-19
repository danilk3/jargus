package org.jargus.alert.client;

import org.jargus.common.model.Metric;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author Bazhov N.S.
 */
@FeignClient
public interface AlertSystemClient {
    void alert(Metric metric);
}
