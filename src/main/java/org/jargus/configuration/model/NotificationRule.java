package org.jargus.configuration.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

/**
 * @author Kotelnikov D.M.
 */
@AllArgsConstructor
@Getter
public class NotificationRule {
    private String name;
    private String condition;
    private List<String> flags;
    private String annotation;
}
