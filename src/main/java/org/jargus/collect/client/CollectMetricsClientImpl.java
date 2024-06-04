package org.jargus.collect.client;

import org.jargus.common.dto.prometheus.PrometheusMetricsResponseDataDto;
import org.jargus.common.dto.prometheus.PrometheusResponseDto;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author Bazhov N.S.
 */
@Component
public class CollectMetricsClientImpl implements CollectMetricsClient {
    RestClient defaultClient;

    public CollectMetricsClientImpl() {
        this.defaultClient = RestClient.create();
    }

    @Override
    public List<PrometheusResponseDto<PrometheusMetricsResponseDataDto>> export(String uri) {

        PrometheusResponseDto<List<String>> metricList = defaultClient.get().uri(uri + "/api/v1/label/__name__/values").retrieve()
                .body(new ParameterizedTypeReference<PrometheusResponseDto<List<String>>>(){});

        List<PrometheusResponseDto<PrometheusMetricsResponseDataDto>> result = new ArrayList<>();

        for (String metricName : metricList.getData()) {
            result.add(defaultClient
                    .get()
                    .uri(uri + "/api/v1/query?query=" + metricName).retrieve()
                    .body(new ParameterizedTypeReference<PrometheusResponseDto<PrometheusMetricsResponseDataDto>>(){}));
        }

        return result;
    }

    @Override
    public List<String> exportRaw(String uri) {

        // TODO: add exception handling

        String body = Objects.requireNonNull(defaultClient.get().uri(uri).retrieve().body(String.class));

        return Arrays.stream(body.split("\n")).toList();
    }
}
