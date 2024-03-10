package org.jargus.database.models;

/**
 * @author Kotelnikov D.M.
 */
public enum Granularity {
    SECONDS("seconds"),
    MINUTES("minutes"),
    HOURS("hours");

    private final String value;

    Granularity(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
