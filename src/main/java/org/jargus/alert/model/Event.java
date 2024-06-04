package org.jargus.alert.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
