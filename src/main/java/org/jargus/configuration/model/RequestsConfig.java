package org.jargus.configuration.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Kotelnikov D.M.
 */
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class RequestsConfig {
    private Map<String, String> headers = new HashMap<>();
    private Map<String, String> params  = new HashMap<>();
}
