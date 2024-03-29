package org.jargus.alert.model;

import lombok.Builder;
import lombok.ToString;

/**
 * @author Bazhov N.S.
 */
@Builder
@ToString
public class Message {
    String type;
    String message;
}
