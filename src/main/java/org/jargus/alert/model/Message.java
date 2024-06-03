package org.jargus.alert.model;

import lombok.Builder;
import lombok.ToString;

import java.util.List;

/**
 * @author Bazhov N.S.
 */
@Builder
@ToString
public class Message {
    List<String> type;
    String message;
}
