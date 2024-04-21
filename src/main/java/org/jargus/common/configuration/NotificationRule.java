package org.jargus.common.configuration;

import lombok.AllArgsConstructor;

import java.util.List;

/**
 * @author Kotelnikov D.M.
 */
@AllArgsConstructor
public class NotificationRule {
    private String name;
    private String condition;
    private List<String> flags;
    private String annotation;
}
