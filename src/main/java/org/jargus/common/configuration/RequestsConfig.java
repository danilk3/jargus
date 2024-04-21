package org.jargus.common.configuration;

import lombok.AllArgsConstructor;

import java.util.Map;

/**
 * @author Kotelnikov D.M.
 */
@AllArgsConstructor
public class RequestsConfig {
    private Map<String, String> headers;
    private Map<String, String> params;
}
