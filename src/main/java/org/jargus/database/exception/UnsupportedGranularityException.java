package org.jargus.database.exception;

import org.jargus.database.models.Granularity;

/**
 * @author Kotelnikov D.M.
 */
public class UnsupportedGranularityException extends RuntimeException {
    public UnsupportedGranularityException(Granularity granularity) {
        super(String.format("Unsupported granularity with value - %s received.", granularity.getValue()));
    }
}
