package org.jargus.common.dto.prometheus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * @author Kotelnikov D.M.
 */
@Getter
@RequiredArgsConstructor
public class PrometheusMetricsResponseDataDto {

    private String resultType;
    private List<PrometheusMetricsResultResponse> result;

    @Getter
    public static class PrometheusMetricsResultResponse {
        private Map<String, String> metric;
        private List<String> value;
    }
}
