package org.jargus.alert.model;

import lombok.Builder;
import lombok.Getter;

/**
 * @author Bazhov N.S.
 */
@Builder
@Getter
public class Event {
    boolean pass;
    String url;

    Message message;
}
