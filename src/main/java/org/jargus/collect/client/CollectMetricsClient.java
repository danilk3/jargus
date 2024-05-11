package org.jargus.collect.client;

import org.jargus.common.dto.prometheus.PrometheusMetricsResponseDataDto;
import org.jargus.common.dto.prometheus.PrometheusResponseDto;

import java.util.List;

/**
 * @author Bazhov N.S.
 */
public interface CollectMetricsClient {
    List<PrometheusResponseDto<PrometheusMetricsResponseDataDto>> export(String ip);
}
