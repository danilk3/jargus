package org.jargus.collect.client;

import org.jargus.collect.model.ExportMetricRequestParams;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Bazhov N.S.
 */
public interface CollectMetricsClient {
    List<String> export(String ip);
}
