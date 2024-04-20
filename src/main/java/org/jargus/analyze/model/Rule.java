package org.jargus.analyze.model;

import lombok.Data;

/**
 * @author Bazhov N.S.
 */
@Data
public class Rule {
    String name;
    String condition;
    String label;
    String message;
    Annotation annotation;
}
