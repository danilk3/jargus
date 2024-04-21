package org.jargus.scheduler.domain;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Bazhov N.S.
 */
@Data
public class TaskRequestModel {
    String taskName;
    String uri;
    private Map<String, String> headers = new HashMap<>();
    private Map<String, String> params  = new HashMap<>();
}