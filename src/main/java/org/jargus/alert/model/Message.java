package org.jargus.alert.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * @author Bazhov N.S.
 */
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    public List<String> type;
    public String message;



}
