package org.jargus.api.scheduler;

import lombok.Data;
import org.jargus.api.scheduler.model.Fetch;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Bazhov N.S.
 */
@Data
@ConfigurationProperties
@Component
public class FetchProperties {
    private List<Fetch> fetches;
}
