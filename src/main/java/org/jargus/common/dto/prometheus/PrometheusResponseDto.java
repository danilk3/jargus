package org.jargus.common.dto.prometheus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

/**
 * @author Kotelnikov D.M.
 */
@Getter
@RequiredArgsConstructor
public class PrometheusResponseDto<T> {

    private String status;
    private T data;
}
